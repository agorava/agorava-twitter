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

package org.agorava;

import java.lang.annotation.Annotation;

import org.agorava.core.api.oauth.OAuthService;
import org.agorava.core.cdi.AbstractSocialNetworkService;

/**
 * A specialization of {@link OAuthService} to add TwitterRelated specific methods
 *
 * @author Antoine Sabot-Durand
 */

public abstract class TwitterBaseService extends AbstractSocialNetworkService {

    static final String API_ROOT = "https://api.twitter.com/1/";

    @Override
    public Annotation getQualifier() {
        return TwitterLiteral.INSTANCE;
    }

    @Override
    public String getApiRootUrl() {
        return API_ROOT;
    }

}
