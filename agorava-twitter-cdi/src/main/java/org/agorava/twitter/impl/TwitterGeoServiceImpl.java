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
import org.agorava.twitter.TwitterGeoService;
import org.agorava.twitter.jackson.PlacesList;
import org.agorava.twitter.model.Place;
import org.agorava.twitter.model.PlacePrototype;
import org.agorava.twitter.model.PlaceType;
import org.agorava.twitter.model.SimilarPlaces;
import org.agorava.twitter.model.SimilarPlacesResponse;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author Antoine Sabot-Durand
 * @author Craig Walls
 */
public class TwitterGeoServiceImpl extends TwitterBaseService implements TwitterGeoService {

    @Override
    public Place getPlace(String placeId) {
        return getService().get(buildAbsoluteUri("geo/id/" + placeId + ".json"), Place.class);
    }

    @Override
    public List<Place> reverseGeoCode(double latitude, double longitude) {
        return reverseGeoCode(latitude, longitude, null, null);
    }

    @Override
    public List<Place> reverseGeoCode(double latitude, double longitude, PlaceType granularity, String accuracy) {
        Map<String, String> parameters = buildGeoParameters(latitude, longitude, granularity, accuracy, null);
        return getService().get(buildUri("geo/reverse_geocode.json", parameters), PlacesList.class).getList();
    }

    @Override
    public List<Place> search(double latitude, double longitude) {
        return search(latitude, longitude, null, null, null);
    }

    @Override
    public List<Place> search(double latitude, double longitude, PlaceType granularity, String accuracy, String query) {
        Map<String, String> parameters = buildGeoParameters(latitude, longitude, granularity, accuracy, query);
        return getService().get(buildUri("geo/search.json", parameters), PlacesList.class).getList();
    }

    @Override
    public SimilarPlaces findSimilarPlaces(double latitude, double longitude, String name) {
        return findSimilarPlaces(latitude, longitude, name, null, null);
    }

    @Override
    public SimilarPlaces findSimilarPlaces(double latitude, double longitude, String name, String streetAddress,
                                           String containedWithin) {
        Map<String, String> parameters = buildPlaceParameters(latitude, longitude, name, streetAddress, containedWithin);
        SimilarPlacesResponse response = getService().get(buildUri("geo/similar_places.json", parameters),
                SimilarPlacesResponse.class);
        PlacePrototype placePrototype = new PlacePrototype(response.getToken(), latitude, longitude, name, streetAddress,
                containedWithin);

        return new SimilarPlaces(response.getPlaces(), placePrototype);
    }

    @Override
    public Place createPlace(PlacePrototype placePrototype) {
        Map<String, String> request = buildPlaceParameters(placePrototype.getLatitude(), placePrototype.getLongitude(),
                placePrototype.getName(), placePrototype.getStreetAddress(), placePrototype.getContainedWithin());
        request.put("token", placePrototype.getCreateToken());
        return getService().post("https://api.twitter.com/1/geo/place.json", request, Place.class);
    }

    // private helpers

    private Map<String, String> buildGeoParameters(double latitude, double longitude, PlaceType granularity, String accuracy,
                                                   String query) {
        Map<String, String> parameters = newHashMap();
        parameters.put("lat", String.valueOf(latitude));
        parameters.put("long", String.valueOf(longitude));
        if (granularity != null) {
            parameters.put("granularity", granularity.equals(PlaceType.POINT_OF_INTEREST) ? "poi" : granularity.toString()
                    .toLowerCase());
        }
        if (accuracy != null) {
            parameters.put("accuracy", accuracy);
        }
        if (query != null) {
            parameters.put("query", query);
        }
        return parameters;
    }

    private Map<String, String> buildPlaceParameters(double latitude, double longitude, String name, String streetAddress,
                                                     String containedWithin) {
        Map<String, String> parameters = newHashMap();
        parameters.put("lat", String.valueOf(latitude));
        parameters.put("long", String.valueOf(longitude));
        parameters.put("name", name);
        if (streetAddress != null) {
            parameters.put("attribute:street_address", streetAddress);
        }
        if (containedWithin != null) {
            parameters.put("contained_within", containedWithin);
        }
        return parameters;
    }

}
