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

package org.agorava.twitter.cdi.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import java.io.FileNotFoundException;

public class TwitterTestDeploy {
    @Deployment
    public static Archive<?> createTestArchive() throws FileNotFoundException {
        JavaArchive testJar = ShrinkWrap.create(JavaArchive.class, "all-agorava.jar")
                .addPackages(true, new Filter<ArchivePath>() {
                    @Override
                    public boolean include(ArchivePath path) {
                        return !((path.get().contains("test") || path.get().contains("web")));
                    }
                }, "org.agorava")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        GenericArchive[] libs = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .resolve("org.apache.deltaspike.core:deltaspike-core-impl")
                .withTransitivity().as(GenericArchive.class);


        WebArchive ret = ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addClasses(TwitterServiceProducer.class)
                .addAsLibraries(libs)
                .addAsLibraries(testJar)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        System.out.println(System.getProperty("arquillian"));
        return ret;
    }
}
