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

package org.agorava.twitter;

import java.util.List;

import org.agorava.twitter.model.TwitterProfile;

/**
 * Interface defining the operations for blocking and unblocking users
 *
 * @author Craig Walls
 * @author Antoine Sabot-Durand
 */
public interface TwitterBlockService {

    /**
     * Blocks a user. If a friendship exists with the user, it will be destroyed.
     *
     * @param userId the ID of the user to block.
     * @return The {@link TwitterProfile} of the blocked user.
     * @throws ApiException                  if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    TwitterProfile block(long userId);

    /**
     * Blocks a user. If a friendship exists with the user, it will be destroyed.
     *
     * @param screenName the screen name of the user to block.
     * @return The {@link TwitterProfile} of the blocked user.
     * @throws ApiException                  if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    TwitterProfile block(String screenName);

    /**
     * Unblocks a user.
     *
     * @param userId the ID of the user to unblock.
     * @return The {@link TwitterProfile} of the unblocked user.
     * @throws ApiException                  if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    TwitterProfile unblock(long userId);

    /**
     * Unblocks a user.
     *
     * @param screenName the screen name of the user to unblock.
     * @return The {@link TwitterProfile} of the unblocked user.
     * @throws ApiException                  if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    TwitterProfile unblock(String screenName);

    /**
     * Retrieves a list of users that the authenticating user has blocked.
     *
     * @return a list of {@link TwitterProfile}s for the users that are blocked.
     * @throws ApiException                  if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    List<TwitterProfile> getBlockedUsers();

    /**
     * Retrieves a list of users that the authenticating user has blocked.
     *
     * @param page     the page of blocked users to return
     * @param pageSize the number of users per page
     * @return a list of {@link TwitterProfile}s for the users that are blocked.
     * @throws ApiException                  if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    List<TwitterProfile> getBlockedUsers(int page, int pageSize);

    /**
     * Retrieves a list of user IDs for the users that the authenticating user has blocked.
     *
     * @return a list of user IDs for the users that are blocked.
     * @throws ApiException                  if there is an error while communicating with Twitter.
     * @throws MissingAuthorizationException if TwitterTemplate was not created with OAuth credentials.
     */
    List<Long> getBlockedUserIds();

    /**
     * Determines if the user has blocked a specific user.
     *
     * @param userId the ID of the user to check for a block.
     * @return true if the user is blocked; false otherwise
     * @throws ApiException if there is an error while communicating with Twitter.
     */
    boolean isBlocking(long userId);

    /**
     * Determines if the user has blocked a specific user.
     *
     * @param screenName the screen name of the user to check for a block.
     * @return true if the user is blocked; false otherwise
     * @throws ApiException if there is an error while communicating with Twitter.
     */
    boolean isBlocking(String screenName);

}
