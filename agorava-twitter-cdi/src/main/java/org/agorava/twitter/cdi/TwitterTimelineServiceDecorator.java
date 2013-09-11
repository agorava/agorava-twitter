/*
 * Copyright 2013 Agorava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.agorava.twitter.cdi;

import org.agorava.core.api.event.SocialEvent.Status;
import org.agorava.core.api.event.StatusUpdated;
import org.agorava.twitter.Twitter;
import org.agorava.twitter.TwitterTimelineService;
import org.agorava.twitter.model.Tweet;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
/**
 *
 * @author Antoine Sabot-Durand
 *
 */
public abstract class TwitterTimelineServiceDecorator implements TwitterTimelineService {

    /**
     *
     */
    @Inject
    @Delegate
    @Any
    private TwitterTimelineService delegate;

    @Inject
    @Twitter
    private Event<StatusUpdated> statusUpdateEventProducer;

    @Override
    public Tweet updateStatus(String status) {
        Tweet res = delegate.updateStatus(status);
        statusUpdateEventProducer.fire(new StatusUpdated(Status.SUCCESS, status, res));
        return res;
    }

}
