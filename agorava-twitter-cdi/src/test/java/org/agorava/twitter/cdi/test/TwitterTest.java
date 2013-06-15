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

package org.agorava.twitter.cdi.test;

import org.agorava.Twitter;
import org.agorava.core.api.SocialMediaApiHub;
import org.agorava.core.api.oauth.OAuthToken;
import org.agorava.core.oauth.scribe.OAuthTokenScribe;
import org.agorava.twitter.TwitterTimelineService;
import org.agorava.twitter.TwitterUserService;
import org.agorava.twitter.model.SuggestionCategory;
import org.agorava.twitter.model.Tweet;
import org.agorava.twitter.model.TwitterProfile;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

@RunWith(Arquillian.class)
public class TwitterTest {

    @Inject
    @Twitter
    SocialMediaApiHub serviceHub;
    @Inject
    TwitterTimelineService tl;
    @Inject
    TwitterUserService userService;

    @Deployment
    public static Archive<?> createTestArchive() throws FileNotFoundException {

        WebArchive ret = ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addPackages(true, "org.agorava")
                .addClass(TwitterServiceProducer.class);

        return ret;
    }

    @Before
    public void init() {
        OAuthToken token = new OAuthTokenScribe("334872715-u75bjYqWyQSYjFMnKeTDZUn8i0QAExjUQ4ENZXH3",
                "08QG7HVqDjkr1oH1YfBRWmd0n8EG73CuzJgTjFI0sk");
        serviceHub.getSession().setAccessToken(token);
        serviceHub.getService().initAccessToken();
    }

    @Test
    public void authorizationUrlTest() {
        Assert.assertTrue(serviceHub.getService().getAuthorizationUrl().startsWith("http"));
    }

    @Test
    public void sendATweet() {
        Tweet tweet = tl.updateStatus("Tweet sent from JUnit in API 1.1 at " + new Date().toString());
        Assert.assertFalse(tweet.getId() == 0);

    }

    @Test
    public void searchUser() {
        List<TwitterProfile> res = userService.searchForUsers("antoine");
        Assert.assertFalse(res.isEmpty());

    }

    @Test
    public void SuggestionCaegoriesNotEmpty() {
        List<SuggestionCategory> res = userService.getSuggestionCategories();
        Assert.assertFalse(res.isEmpty());

    }
}
