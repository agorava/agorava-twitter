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

import org.agorava.TwitterBaseService;
import org.agorava.core.utils.URLUtils;
import org.agorava.twitter.TwitterTimelineService;
import org.agorava.twitter.impl.TwitterUserServiceImpl.TwitterProfileList;
import org.agorava.twitter.model.StatusDetails;
import org.agorava.twitter.model.Tweet;
import org.agorava.twitter.model.TwitterProfile;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author Antoine Sabot-Durand
 * @author Craig Walls
 */
@Named
public class TwitterTimelineServiceImpl extends TwitterBaseService implements TwitterTimelineService {

    private static final String USER_TIMELINE_URL = "statuses/user_timeline.json";
    private static final String HOME_TIMELINE_URL = "statuses/home_timeline.json";
    private static final String PUBLIC_TIMELINE_URL = "statuses/public_timeline.json";

    @Override
    public List<Tweet> getPublicTimeline() {
        return getService().get(buildUri(PUBLIC_TIMELINE_URL), TweetList.class);
    }

    @Override
    public List<Tweet> getHomeTimeline() {
        return getHomeTimeline(1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getHomeTimeline(int page, int pageSize) {
        return getHomeTimeline(page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getHomeTimeline(int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        return getService().get(buildUri(HOME_TIMELINE_URL, parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getUserTimeline() {
        return getUserTimeline(1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getUserTimeline(int page, int pageSize) {
        return getUserTimeline(page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getUserTimeline(int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        return getService().get(buildUri(USER_TIMELINE_URL, parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getUserTimeline(String screenName) {
        return getUserTimeline(screenName, 1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getUserTimeline(String screenName, int page, int pageSize) {
        return getUserTimeline(screenName, page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getUserTimeline(String screenName, int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        parameters.put("screen_name", screenName);
        return getService().get(buildUri(USER_TIMELINE_URL, parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getUserTimeline(long userId) {
        return getUserTimeline(userId, 1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getUserTimeline(long userId, int page, int pageSize) {
        return getUserTimeline(userId, page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getUserTimeline(long userId, int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        parameters.put("user_id", String.valueOf(userId));
        return getService().get(buildUri(USER_TIMELINE_URL, parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getMentions() {
        return getMentions(1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getMentions(int page, int pageSize) {
        return getMentions(page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getMentions(int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        return getService().get(buildUri("statuses/mentions.json", parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getRetweetedByMe() {
        return getRetweetedByMe(1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedByMe(int page, int pageSize) {
        return getRetweetedByMe(page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedByMe(int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        return getService().get(buildUri("statuses/retweeted_by_me.json", parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getRetweetedByUser(long userId) {
        return getRetweetedByUser(userId, 1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedByUser(long userId, int page, int pageSize) {
        return getRetweetedByUser(userId, page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedByUser(long userId, int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        parameters.put("user_id", String.valueOf(userId));
        return getService().get(buildUri("statuses/retweeted_by_user.json", parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getRetweetedByUser(String screenName) {
        return getRetweetedByUser(screenName, 1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedByUser(String screenName, int page, int pageSize) {
        return getRetweetedByUser(screenName, page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedByUser(String screenName, int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        parameters.put("screen_name", screenName);
        return getService().get(buildUri("statuses/retweeted_by_user.json", parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getRetweetedToMe() {
        return getRetweetedToMe(1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedToMe(int page, int pageSize) {
        return getRetweetedToMe(page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedToMe(int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        return getService().get(buildUri("statuses/retweeted_to_me.json", parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getRetweetedToUser(long userId) {
        return getRetweetedToUser(userId, 1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedToUser(long userId, int page, int pageSize) {
        return getRetweetedToUser(userId, page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedToUser(long userId, int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        parameters.put("user_id", String.valueOf(userId));
        return getService().get(buildUri("statuses/retweeted_to_user.json", parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getRetweetedToUser(String screenName) {
        return getRetweetedToUser(screenName, 1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedToUser(String screenName, int page, int pageSize) {
        return getRetweetedToUser(screenName, page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetedToUser(String screenName, int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        parameters.put("screen_name", screenName);
        return getService().get(buildUri("statuses/retweeted_to_user.json", parameters), TweetList.class);
    }

    @Override
    public List<Tweet> getRetweetsOfMe() {
        return getRetweetsOfMe(1, 20, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetsOfMe(int page, int pageSize) {
        return getRetweetsOfMe(page, pageSize, 0, 0);
    }

    @Override
    public List<Tweet> getRetweetsOfMe(int page, int pageSize, long sinceId, long maxId) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        return getService().get(buildUri("statuses/retweets_of_me.json", parameters), TweetList.class);
    }

    @Override
    public Tweet getStatus(long tweetId) {
        return getService().get(buildUri("statuses/show/" + tweetId + ".json"), Tweet.class);
    }

    @Override
    public Tweet updateStatus(String message) {
        return updateStatus(message, new StatusDetails());
    }

    // public Tweet updateStatus(String message, Resource media) {
    // return updateStatus(message, media, new StatusDetails());
    // }

    public Tweet updateStatus(String message, StatusDetails details) {
        Map<String, Object> tweetParams = newHashMap();
        tweetParams.put("status", message);
        tweetParams.putAll(details.toParameterMap());
        return getService().post(buildUri("statuses/update.json"), tweetParams, Tweet.class);
    }

    // public Tweet updateStatus(String message, Resource media, StatusDetails details) {
    //
    // Multimap<String, Object> tweetParams = LinkedHashMultimap.create();
    // tweetParams.add("status", message);
    // tweetParams.add("media", media);
    // tweetParams.putAll(details.toParameterMap());
    // return restTemplate.getService().post("https://upload.twitter.com/1/statuses/update_with_media.json",
    // tweetParams,
    // Tweet.class);
    // }

    @Override
    public void deleteStatus(long tweetId) {
        getService().delete(buildUri("statuses/destroy/" + tweetId + ".json"));
    }

    @Override
    public void retweet(long tweetId) {
        Map<String, Object> data = newHashMap();
        getService().post(buildUri("statuses/retweet/" + tweetId + ".json"), data, String.class);
    }

    @Override
    public List<Tweet> getRetweets(long tweetId) {
        return getRetweets(tweetId, 100);
    }

    @Override
    public List<Tweet> getRetweets(long tweetId, int count) {
        Map<String, String> parameters = newHashMap();
        parameters.put("count", String.valueOf(count));
        return getService().get(buildUri("statuses/retweets/" + tweetId + ".json", parameters), TweetList.class);
    }

    @Override
    public List<TwitterProfile> getRetweetedBy(long tweetId) {
        return getRetweetedBy(tweetId, 1, 100);
    }

    @Override
    public List<TwitterProfile> getRetweetedBy(long tweetId, int page, int pageSize) {
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, 0, 0);
        return getService().get(buildUri("statuses/" + tweetId + "/retweeted_by.json", parameters),
                TwitterProfileList.class);
    }

    @Override
    public List<Long> getRetweetedByIds(long tweetId) {
        return getRetweetedByIds(tweetId, 1, 100);
    }

    @Override
    public List<Long> getRetweetedByIds(long tweetId, int page, int pageSize) {
        // requires authentication, even though getRetweetedBy() does not.
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, 0, 0);
        return getService()
                .get(buildUri("statuses/" + tweetId + "/retweeted_by/ids.json", parameters), LongList.class);
    }

    @Override
    public List<Tweet> getFavorites() {
        return getFavorites(1, 20);
    }

    @Override
    public List<Tweet> getFavorites(int page, int pageSize) {
        // Note: The documentation for favorites.json doesn't list the count parameter, but it works
        // anyway.
        Map<String, String> parameters = URLUtils.buildPagingParametersWithCount(page, pageSize, 0, 0);
        return getService().get(buildUri("favorites.json", parameters), TweetList.class);
    }

    @Override
    public void addToFavorites(long tweetId) {
        Map<String, Object> data = newHashMap();
        getService().post(buildUri("favorites/create/" + tweetId + ".json"), data, String.class);
    }

    @Override
    public void removeFromFavorites(long tweetId) {
        Map<String, Object> data = newHashMap();
        getService().post(buildUri("favorites/destroy/" + tweetId + ".json"), data, String.class);
    }

    @SuppressWarnings("serial")
    private static class LongList extends ArrayList<Long> {
    }

    @SuppressWarnings("serial")
    private static class TweetList extends ArrayList<Tweet> {
    }

}
