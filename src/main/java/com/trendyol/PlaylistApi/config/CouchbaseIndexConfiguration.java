package com.trendyol.PlaylistApi.config;

import com.couchbase.client.java.Cluster;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseIndexConfiguration {

    private final Cluster couchbaseCluster;

    public CouchbaseIndexConfiguration(Cluster couchbaseCluster) {
        this.couchbaseCluster = couchbaseCluster;
    }

    @Bean
    public void createIndexes() {
        //couchbaseCluster.query("CREATE PRIMARY INDEX `indexPlaylists` ON `playlist`");
        //couchbaseCluster.query("CREATE INDEX indexTrackId ON `playlist` ( DISTINCT ARRAY v.id FOR v IN `tracks` END)");



    }


}
