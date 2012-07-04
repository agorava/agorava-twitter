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
package org.agorava.twitter.impl;

import org.agorava.TwitterBaseService;
import org.agorava.core.utils.URLUtils;
import org.agorava.twitter.TwitterBlockService;
import org.agorava.twitter.impl.TwitterUserServiceImpl.TwitterProfileList;
import org.agorava.twitter.model.TwitterProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author Antoine Sabot-Durand
 * @author Craig Walls
 */
public class TwitterBlockServiceImpl extends TwitterBaseService implements TwitterBlockService {

    @SuppressWarnings("serial")
    private static class LongList extends ArrayList<Long> {
    }

    @Override
    public TwitterProfile block(long userId) {
        Map<String, String> request = newHashMap();
        request.put("user_id", String.valueOf(userId));
        return getService().post(buildUri("blocks/create.json"), request, TwitterProfile.class);
    }

    @Override
    public TwitterProfile block(String screenName) {
        Map<String, String> request = newHashMap();
        request.put("screen_name", screenName);
        return getService().post(buildUri("blocks/create.json"), request, TwitterProfile.class);
    }

    @Override
    public TwitterProfile unblock(long userId) {
        Map<String, String> request = newHashMap();
        request.put("user_id", String.valueOf(userId));
        return getService().post(buildUri("blocks/destroy.json"), request, TwitterProfile.class);
    }

    @Override
    public TwitterProfile unblock(String screenName) {
        Map<String, String> request = newHashMap();
        request.put("screen_name", screenName);
        return getService().post(buildUri("blocks/destroy.json"), request, TwitterProfile.class);
    }

    @Override
    public List<TwitterProfile> getBlockedUsers() {
        return getBlockedUsers(1, 20);
    }

    @Override
    public List<TwitterProfile> getBlockedUsers(int page, int pageSize) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithPerPage(page, pageSize, 0, 0);
        return getService().get(buildUri("blocks/blocking.json", parameters), TwitterProfileList.class);
    }

    @Override
    public List<Long> getBlockedUserIds() {
        return getService().get(buildUri("blocks/blocking/ids.json"), LongList.class);
    }

    @Override
    public boolean isBlocking(long userId) {
        return isBlocking(buildUri("blocks/exists.json", "user_id", String.valueOf(userId)));
    }

    @Override
    public boolean isBlocking(String screenName) {
        return isBlocking(buildUri("blocks/exists.json", "screen_name", screenName));
    }

}
