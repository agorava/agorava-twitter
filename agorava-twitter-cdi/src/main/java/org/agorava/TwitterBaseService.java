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

package org.agorava;

import com.google.common.base.Joiner;
import org.agorava.core.api.oauth.OAuthService;
import org.agorava.core.spi.ProvideApiService;
import org.agorava.twitter.Twitter;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * A specialization of {@link OAuthService} to add TwitterRelated specific methods
 *
 * @author Antoine Sabot-Durand
 */

public abstract class TwitterBaseService extends ProvideApiService {

    protected static final char MULTI_VALUE_SEPARATOR = ',';

    protected static Joiner commaJoiner = Joiner.on(MULTI_VALUE_SEPARATOR).skipNulls();

    public static final String API_ROOT = "https://api.twitter.com/1.1/";

    public Map<String, String> buildPagingParametersWithCount(int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("page", String.valueOf(page));
        parameters.put("count", String.valueOf(pageSize));
        if (sinceId > 0) {
            parameters.put("since_id", String.valueOf(sinceId));
        }
        if (maxId > 0) {
            parameters.put("max_id", String.valueOf(maxId));
        }
        return parameters;
    }

    public Map<String, String> buildPagingParametersWithPerPage(int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("page", String.valueOf(page));
        parameters.put("per_page", String.valueOf(pageSize));
        if (sinceId > 0) {
            parameters.put("since_id", String.valueOf(sinceId));
        }
        if (maxId > 0) {
            parameters.put("max_id", String.valueOf(maxId));
        }
        return parameters;
    }

    @Inject
    @Twitter
    private OAuthService service;

    @Override
    public String buildAbsoluteUri(String uri) {
        return API_ROOT + uri;
    }

    @Override
    public OAuthService getService() {
        return service;
    }
}
