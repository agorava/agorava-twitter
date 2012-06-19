/*******************************************************************************
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
 ******************************************************************************/
/**
 * 
 */
package org.agorava.twitter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.agorava.TwitterBaseService;
import org.agorava.core.utils.URLUtils;
import org.agorava.twitter.TwitterUserService;
import org.agorava.twitter.model.ImageSize;
import org.agorava.twitter.model.RateLimitStatus;
import org.agorava.twitter.model.SuggestionCategory;
import org.agorava.twitter.model.TwitterProfile;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Antoine Sabot-Durand
 * 
 */
public class TwitterUserServiceImpl extends TwitterBaseService implements TwitterUserService {

    /**
     * Typed list of TwitterProfile. This helps Jackson know which type to deserialize list contents into.
     * 
     * @author Craig Walls
     * @author Antoine Sabot-Durand
     */
    @SuppressWarnings("serial")
    static class TwitterProfileList extends ArrayList<TwitterProfile> {
    }

    @SuppressWarnings("serial")
    static class SuggestionCategoryList extends ArrayList<SuggestionCategory> {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class TwitterProfileUsersList {

        private final List<TwitterProfile> list;

        @JsonCreator
        public TwitterProfileUsersList(@JsonProperty("users") List<TwitterProfile> list) {
            this.list = list;
        }

        public List<TwitterProfile> getList() {
            return list;
        }
    }

    static final String VERIFY_CREDENTIALS_URL = "account/verify_credentials.json";

    static final String GET_USER_PROFILE_URL = "users/show.json";
    static final String SEARCH_USER_URL = "users/search.json";
    static final String SUGGESTION_CATEGORIES = "users/suggestions.json";
    static final String LOOKUP = "users/lookup.json";
    static final String RATE_LIMIT_STATUS = "account/rate_limit_status.json";

    @Override
    public String getProfileId() {
        return getSession().getUserProfile().getId();
    }

    @Override
    public String getScreenName() {
        return getSession().getUserProfile().getFullName();
    }

    @Override
    public TwitterProfile getUserProfile(String screenName) {
        return getService().getForObject(buildUri(GET_USER_PROFILE_URL, "screen_name", screenName), TwitterProfile.class);
    }

    @Override
    public TwitterProfile getUserProfile(long userId) {
        return getService().getForObject(buildUri(GET_USER_PROFILE_URL, "user_id", String.valueOf(userId)),
                TwitterProfile.class);
    }

    @Override
    public byte[] getUserProfileImage(String screenName) {
        return getUserProfileImage(screenName, ImageSize.NORMAL);
    }

    @Override
    public byte[] getUserProfileImage(String screenName, ImageSize size) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<TwitterProfile> getUsers(String... userIds) {
        String joinedIds = URLUtils.commaJoiner.join(userIds);
        return getService().getForObject(buildUri(LOOKUP, "user_id", joinedIds), TwitterProfileList.class);
    }

    @Override
    public List<TwitterProfile> getUsersByName(String... screenNames) {
        String joinedScreenNames = URLUtils.commaJoiner.join(screenNames);
        return getService().getForObject(buildUri(LOOKUP, "screen_name", joinedScreenNames), TwitterProfileList.class);
    }

    @Override
    public List<TwitterProfile> searchForUsers(String query) {
        return searchForUsers(query, 1, 20);
    }

    @Override
    public List<TwitterProfile> searchForUsers(String query, int page, int pageSize) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithPerPage(page, pageSize, 0, 0);
        parameters.put("q", query);
        return getService().getForObject(buildUri(SEARCH_USER_URL, parameters), TwitterProfileList.class);
    }

    @Override
    public List<SuggestionCategory> getSuggestionCategories() {
        return getService().getForObject(buildUri(SUGGESTION_CATEGORIES), SuggestionCategoryList.class);
    }

    @Override
    public List<TwitterProfile> getSuggestions(String slug) {
        return getService().getForObject(buildUri("users/suggestions/" + slug + ".json"), TwitterProfileUsersList.class)
                .getList();
    }

    @Override
    public RateLimitStatus getRateLimitStatus() {
        return getService().getForObject(buildUri(RATE_LIMIT_STATUS), RateLimitStatus.class);
    }

    @Override
    public TwitterProfile getUserProfile() {
        return getService().getForObject(buildUri(VERIFY_CREDENTIALS_URL), TwitterProfile.class);
    }

}
