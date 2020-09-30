package com.trendyol.PlaylistApi.repository;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.trendyol.PlaylistApi.domain.Playlist;
import com.trendyol.PlaylistApi.domain.Track;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepository {

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;


    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    public Playlist insert(Playlist playlist) {
        playlistCollection.insert(playlist.getId(), playlist);
        return playlist;
    }

    public Playlist update(Playlist playlist) {
        playlistCollection.replace(playlist.getId(), playlist);
        return playlist;
    }

    public void delete(String id) {
        playlistCollection.remove(id); }

    public Playlist addTrackToPlayList(Track track,String playListId){
        Playlist playlist = this.findById(playListId);
        List<Track> tracks = playlist.getTracks();
        tracks.add(track);
        playlist.setTracks(tracks);
        playlist.setTrackCount(playlist.getTrackCount()+1);
        return this.update(playlist);
    }

    public Playlist removeTrackToPlayList(Track track,String playListId){
        Playlist playlist = this.findById(playListId);
        List<Track> tracks = playlist.getTracks();

        tracks.removeIf(x->x.getId().equals(track.getId()));
        playlist.setTracks(tracks);
        playlist.setTrackCount(playlist.getTrackCount()-1);
        return this.update(playlist);
    }

    public Playlist findById(String id) {
        GetResult getResult = playlistCollection.get(id);
        Playlist playlist = getResult.contentAs(Playlist.class);
        return playlist;
    }

    public Optional<List<Playlist>> findByUserIdOptional(String id) {


        try {

            String statement = String.format("Select id, name, tracks From playlist Where userId=\"%s\"",id);
            QueryResult query = couchbaseCluster.query(statement);

            List<Playlist> result = query.rowsAs(Playlist.class);

            return Optional.of(result);

        } catch (DocumentNotFoundException exception) {
            return Optional.empty();
        }
    }

    public List<Playlist> findAll () {

        String statement = "Select id, name , tracks from playlist";
        QueryResult query = couchbaseCluster.query(statement);
        List<Playlist> result =  query.rowsAs(Playlist.class);
        return result;
    }

}
