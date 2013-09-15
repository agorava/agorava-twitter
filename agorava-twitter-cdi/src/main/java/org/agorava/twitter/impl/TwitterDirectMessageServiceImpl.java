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
package org.agorava.twitter.impl;

import org.agorava.TwitterBaseService;
import org.agorava.twitter.TwitterDirectMessageService;
import org.agorava.twitter.model.DirectMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author Antoine Sabot-Durand
 */
public class TwitterDirectMessageServiceImpl extends TwitterBaseService implements TwitterDirectMessageService {

    @SuppressWarnings("serial")
    class DirectMessageList extends ArrayList<DirectMessage> {
    }

    @Override
    public List<DirectMessage> getDirectMessagesReceived() {
        return getDirectMessagesReceived(1, 20, 0, 0);
    }

    @Override
    public List<DirectMessage> getDirectMessagesReceived(int page, int pageSize) {
        return getDirectMessagesReceived(page, pageSize, 0, 0);
    }

    @Override
    public List<DirectMessage> getDirectMessagesReceived(int page, int pageSize, long sinceId, long maxId) {

        Map<String, String> parameters = buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        return getService().get(buildUri("direct_messages.json", parameters), DirectMessageList.class);
    }

    @Override
    public List<DirectMessage> getDirectMessagesSent() {
        return getDirectMessagesSent(1, 20, 0, 0);
    }

    @Override
    public List<DirectMessage> getDirectMessagesSent(int page, int pageSize) {
        return getDirectMessagesSent(page, pageSize, 0, 0);
    }

    @Override
    public List<DirectMessage> getDirectMessagesSent(int page, int pageSize, long sinceId, long maxId) {

        Map<String, String> parameters = buildPagingParametersWithCount(page, pageSize, sinceId, maxId);
        return getService().get(buildUri("direct_messages/sent.json", parameters), DirectMessageList.class);
    }

    @Override
    public DirectMessage getDirectMessage(long id) {

        return getService().get(buildAbsoluteUri("direct_messages/show/" + id + ".json"), DirectMessage.class);
    }

    @Override
    public DirectMessage sendDirectMessage(String toScreenName, String text) {

        Map<String, Object> data = newHashMap();
        data.put("screen_name", String.valueOf(toScreenName));
        data.put("text", text);
        return getService().post(buildAbsoluteUri("direct_messages/new.json"), data, DirectMessage.class);
    }

    @Override
    public DirectMessage sendDirectMessage(long toUserId, String text) {

        Map<String, Object> data = newHashMap();
        data.put("user_id", String.valueOf(toUserId));
        data.put("text", text);
        return getService().post(buildAbsoluteUri("direct_messages/new.json"), data, DirectMessage.class);
    }

    @Override
    public void deleteDirectMessage(long messageId) {

        getService().delete(buildAbsoluteUri("direct_messages/destroy/" + messageId + ".json"));
    }

}
