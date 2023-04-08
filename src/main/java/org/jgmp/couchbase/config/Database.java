/**
 * Copyright (C) 2015 Couchbase, Inc.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALING
 * IN THE SOFTWARE.
 */
package org.jgmp.couchbase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;

@Configuration
public class Database extends AbstractCouchbaseConfiguration {


    @Value("${storage.host}")
    private String host;

    @Value("${storage.bucket}")
    private String bucketName;

    @Value("${storage.username}")
    private String username;

    @Value("${storage.password}")
    private String password;

    @Override
    public String getConnectionString() {
        return host;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }

    public Bucket bucket(){
       return cluster().bucket(getBucketName());
    }


    @Bean
    public Cluster cluster() {
        return Cluster.connect(
                host,
                ClusterOptions.clusterOptions(username, password).environment(env -> {
                    // Sets a pre-configured profile called "wan-development" to help avoid
                    // latency issues when accessing Capella from a different Wide Area Network
                    // or Availability Zone (e.g. your laptop).
//                    env.applyProfile("wan-development");
                })
                              );


    }


}
