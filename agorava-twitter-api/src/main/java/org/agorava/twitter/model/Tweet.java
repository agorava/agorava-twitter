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

package org.agorava.twitter.model;

import java.util.Date;

import org.agorava.api.function.LongIdentifiable;

/**
 * Represents a Twitter status update (e.g., a "tweet").
 *
 * @author Craig Walls
 * @author Antoine Sabot-Durand
 * @author Werner Keil
 */
public class Tweet implements LongIdentifiable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5804753278496131500L;

	private final long id;

    private final String text;

    private final Date createdAt;

    private final String fromUser;

    private String profileImageUrl;

    private long toUserId;

    private long inReplyToStatusId;

    private long fromUserId;

    private boolean favorited;

    private String languageCode;

    private String source;

    private int retweetCount;

    public Tweet(long id, String text, Date createdAt, String fromUser, String profileImageUrl, Long toUserId, long fromUserId,
                 String languageCode, String source) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.fromUser = fromUser;
        this.profileImageUrl = profileImageUrl;
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.languageCode = languageCode;
        this.source = source;
    }

    public String getText() {
        return text;
    }
    
 // TODO setter is currently unused, clarify, if Twitter API requires it or we can leave this immutable
//    public void setText(String text) {
//        this.text = text;
//    }

    public Date getCreatedAt() {
        return createdAt;
    }

 // TODO setter is currently unused, clarify, if Twitter API requires it or we can leave this immutable
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }

    public String getFromUser() {
        return fromUser;
    }

 // TODO setter is currently unused, clarify, if Twitter API requires it or we can leave this immutable
//    public void setFromUser(String fromUser) {
//        this.fromUser = fromUser;
//    }

    public long getId() {
        return id;
    }

    // TODO setter is currently unused, clarify, if Twitter API requires it or we can leave this immutable
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setInReplyToStatusId(long inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    public Long getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    /**
     * The number of times this tweet has been retweeted. Only available in timeline results. getRetweetCount() will return null
     * for Tweet objects returned in search results.
     */
    public int getRetweetCount() {
        return retweetCount;
    }

    /**
     * @return the favorited
     */
    public boolean isFavorited() {
        return favorited;
    }

    /**
     * @param favorited the favorited to set
     */
    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
