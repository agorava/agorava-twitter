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

import org.agorava.api.atinject.Current;
import org.agorava.api.oauth.OAuthService;
import org.agorava.api.oauth.OAuthSession;
import org.agorava.api.oauth.Token;
import org.agorava.api.service.OAuthLifeCycleService;
import org.agorava.twitter.Twitter;
import org.agorava.twitter.TwitterTimelineService;
import org.agorava.twitter.TwitterUserService;
import org.agorava.twitter.model.SuggestionCategory;
import org.agorava.twitter.model.Tweet;
import org.agorava.twitter.model.TwitterProfile;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@RunWith(Arquillian.class)
public class TwitterTest extends TwitterTestDeploy {

    @Inject
    @Twitter
    TwitterTimelineService tl;

    @Inject
    @Twitter
    TwitterUserService userService;

    @Inject
    @Twitter
    OAuthService service;

    @Inject
    OAuthLifeCycleService OAuthLifeCycleService;

    @Inject
    @Twitter
    @Current
    OAuthSession sessionTest;

    @Before
    public void init() {
        Token token = new Token("334872715-u75bjYqWyQSYjFMnKeTDZUn8i0QAExjUQ4ENZXH3",
                "08QG7HVqDjkr1oH1YfBRWmd0n8EG73CuzJgTjFI0sk");
        //service.getSession().setAccessToken(token);
        sessionTest.setAccessToken(token);
        OAuthLifeCycleService.endDance();
    }

    @Test
    public void authorizationUrlTest() {
        Assert.assertTrue(service.getAuthorizationUrl().startsWith("http"));
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
    public void suggestionCaegoriesNotEmpty() {
        List<SuggestionCategory> res = userService.getSuggestionCategories();
        Assert.assertFalse(res.isEmpty());

    }

    @Test
    public void testSessionUnicity() {
        Assert.assertEquals(sessionTest.getUserProfile(), service.getSession().getUserProfile());
    }

    @Test
    public void testProfileIsNotNull() {
        Assert.assertNotNull(service.getSession().getUserProfile());
        System.out.println(service.getSession().getUserProfile().getFullName());
    }
}
