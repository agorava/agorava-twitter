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

package org.agorava.twitter.jackson;

import org.agorava.Twitter;
import org.agorava.twitter.model.DirectMessage;
import org.agorava.twitter.model.Place;
import org.agorava.twitter.model.RateLimitStatus;
import org.agorava.twitter.model.SavedSearch;
import org.agorava.twitter.model.SearchResults;
import org.agorava.twitter.model.SimilarPlacesResponse;
import org.agorava.twitter.model.SuggestionCategory;
import org.agorava.twitter.model.Trend;
import org.agorava.twitter.model.Trends;
import org.agorava.twitter.model.Tweet;
import org.agorava.twitter.model.TwitterProfile;
import org.agorava.twitter.model.UserList;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;

/**
 * Jackson module for registering mixin annotations against Twitter model classes.
 * 
 * @author Craig Walls
 * @author Antoine Sabot-Durand
 */

@Twitter
class TwitterModule extends SimpleModule {
    public TwitterModule() {
        super("TwitterModule", new Version(1, 0, 0, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(TwitterProfile.class, TwitterProfileMixin.class);
        context.setMixInAnnotations(SavedSearch.class, SavedSearchMixin.class);
        context.setMixInAnnotations(Trend.class, TrendMixin.class);
        context.setMixInAnnotations(Trends.class, TrendsMixin.class);
        context.setMixInAnnotations(SuggestionCategory.class, SuggestionCategoryMixin.class);
        context.setMixInAnnotations(DirectMessage.class, DirectMessageMixin.class);
        context.setMixInAnnotations(UserList.class, UserListMixin.class);
        context.setMixInAnnotations(Tweet.class, TweetMixin.class);
        context.setMixInAnnotations(SearchResults.class, SearchResultsMixin.class);
        context.setMixInAnnotations(Place.class, PlaceMixin.class);
        context.setMixInAnnotations(SimilarPlacesResponse.class, SimilarPlacesMixin.class);
        context.setMixInAnnotations(RateLimitStatus.class, RateLimitStatusMixin.class);
    }
}
