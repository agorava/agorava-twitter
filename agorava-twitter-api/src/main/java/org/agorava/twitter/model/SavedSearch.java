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
import org.agorava.api.function.Nameable;

/**
 * Represents a saved search.
 *
 * @author Craig Walls
 * @author Antoine Sabot-Durand
 * @author Werner Keil
 */
public class SavedSearch implements LongIdentifiable, Nameable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 333258941953563016L;

	private final long id;

    private final String name;

    private final String query;

    private final Date createdAt;

    private final int position;

    public SavedSearch(long id, String name, String query, int position, Date createdAt) {
        this.id = id;
        this.name = name;
        this.query = query;
        this.position = position;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getPosition() {
        return position;
    }

}
