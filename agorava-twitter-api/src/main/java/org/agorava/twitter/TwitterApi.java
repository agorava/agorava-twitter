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
package org.agorava.twitter;

import org.agorava.api.oauth.Token;
import org.agorava.spi.ProviderConfigOauth10a;


/**
 * @author Antoine Sabot-Durand
 */

@Twitter
public class TwitterApi extends ProviderConfigOauth10a {

    private static final String MEDIA_NAME = "Twitter";

    //private static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize?oauth_token=%s";

    private static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authenticate?oauth_token=%s";

    private static final String REQUEST_TOKEN_RESOURCE = "https://api.twitter.com/oauth/request_token";

    private static final String ACCESS_TOKEN_RESOURCE = "https://api.twitter.com/oauth/access_token";

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_RESOURCE;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return REQUEST_TOKEN_RESOURCE;
    }

    @Override
    public String getAuthorizationUrl(Token requestToken) {
        return String.format(AUTHORIZE_URL, requestToken.getToken());
    }


    @Override
    public String getProviderName() {
        return MEDIA_NAME;
    }
}
