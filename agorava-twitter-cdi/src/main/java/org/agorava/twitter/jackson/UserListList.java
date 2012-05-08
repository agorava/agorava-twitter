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

import java.util.List;

import org.agorava.twitter.model.CursoredList;
import org.agorava.twitter.model.UserList;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Holder for list of UserList, pulled from JSON object's "lists" property.
 * 
 * @author Craig Walls
 * @author Antoine Sabot-Durand
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class UserListList {
    private final CursoredList<UserList> list;

    @JsonCreator
    public UserListList(@JsonProperty("lists") List<UserList> list, @JsonProperty("previous_cursor") long previousCursor,
            @JsonProperty("next_cursor") long nextCursor) {
        this.list = new CursoredList<UserList>(list, previousCursor, nextCursor);
    }

    @JsonIgnore
    public CursoredList<UserList> getList() {
        return list;
    }
}
