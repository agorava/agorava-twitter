/*
 * Copyright 2012 Agorava
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
/**
 *
 */
package org.agorava.twitter.cdi.test;

import org.agorava.Twitter;
import org.agorava.TwitterServicesHub;
import org.agorava.core.api.SocialMediaApiHub;
import org.agorava.core.api.oauth.Param;
import org.agorava.core.cdi.OAuthApplication;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import static org.agorava.core.api.oauth.OAuthAppSettingsBuilder.API_KEY;
import static org.agorava.core.api.oauth.OAuthAppSettingsBuilder.API_SECRET;

/**
 * @author antoine
 */
public class TwitterServiceProducer {

    @Twitter
    @ApplicationScoped
    @OAuthApplication(params = {@Param(name = API_KEY, value = "FQzlQC49UhvbMZoxUIvHTQ"),
            @Param(name = API_SECRET, value = "VQ5CZHG4qUoAkUUmckPn4iN4yyjBKcORTW0wnok4r1k")})
    @Produces
    public SocialMediaApiHub OAuthSettinsProducer(TwitterServicesHub service) {
        return service;
    }

}
