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

/**
 *
 */
package org.agorava.twitter.cdi.test;

import org.agorava.TwitterLiteral;
import org.agorava.api.atinject.Current;
import org.agorava.api.oauth.OAuthSession;
import org.agorava.api.oauth.OAuthSessionBuilder;
import org.agorava.api.oauth.application.OAuthAppSettings;
import org.agorava.api.oauth.application.OAuthApplication;
import org.agorava.oauth.UserSessionRepositoryImpl;
import org.agorava.twitter.Twitter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

/**
 * @author antoine
 */
public class TwitterServiceProducer {


    @ApplicationScoped
    @Produces
    @Twitter
    @OAuthApplication
    public OAuthAppSettings produceSettings;

    @SessionScoped
    @Produces
    @Twitter
    @Current
    public OAuthSession produceOauthSession() {
        OAuthSession res = new OAuthSessionBuilder().qualifier(TwitterLiteral.INSTANCE).build();
        res.setRepo(new UserSessionRepositoryImpl());
        return res;

    }


}
