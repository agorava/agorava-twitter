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

package org.agorava.twitter.jackson;

import org.agorava.twitter.model.Trend;
import org.agorava.twitter.model.Trends;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;


/**
 * Abstract model class representing a list of trends.
 *
 * @author Craig Walls
 * @author Antoine Sabot-Durand
 */
class AbstractTrendsList {
    private final List<Trends> list;

    public AbstractTrendsList(Map<String, List<Trend>> trends, DateFormat dateFormat) {
        list = new ArrayList<Trends>(trends.size());
        for (Iterator<Entry<String, List<Trend>>> trendsIt = trends.entrySet().iterator(); trendsIt.hasNext(); ) {
            Entry<String, List<Trend>> entry = trendsIt.next();

            list.add(new Trends(toDate(entry.getKey(), dateFormat), entry.getValue()));
        }
        Collections.sort(list, new Comparator<Trends>() {
            public int compare(Trends t1, Trends t2) {
                return t1.getTime().getTime() > t2.getTime().getTime() ? -1 : 1;
            }
        });
    }

    public List<Trends> getList() {
        return list;
    }

    protected Date toDate(String dateString, DateFormat dateFormat) {
        if (dateString == null) {
            return null;
        }

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

}
