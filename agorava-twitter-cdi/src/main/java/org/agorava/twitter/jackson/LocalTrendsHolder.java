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

package org.agorava.twitter.jackson;

import org.agorava.twitter.model.Trends;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * Holds a Trends object deserialized from Twitter's local trends JSON structure. Provides a convenient place to hang the @JsonDeserialize
 * annotation.
 *
 * @author Craig Walls
 * @author Antoine Sabot-Durand
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = LocalTrendsDeserializer.class)
class LocalTrendsHolder {

    private final Trends trends;

    public LocalTrendsHolder(Trends trends) {
        this.trends = trends;
    }

    public Trends getTrends() {
        return trends;
    }

}