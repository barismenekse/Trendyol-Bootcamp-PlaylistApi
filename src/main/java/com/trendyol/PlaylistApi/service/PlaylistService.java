package com.trendyol.PlaylistApi.service;

import com.trendyol.PlaylistApi.domain.Playlist;
import com.trendyol.PlaylistApi.domain.Track;
import com.trendyol.PlaylistApi.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist insert(Playlist playlist) {
        if(playlist.getId()==null || playlist.getId().length()<1)playlist.setId(UUID.randomUUID().toString());
        for (Track track : playlist.getTracks()) {
            if(track.getId()==null || track.getId().length()<1)track.setId(UUID.randomUUID().toString());
        }
        playlistRepository.insert(playlist);
        return playlist;
    }

    public Playlist update(Playlist playlist) {
        playlistRepository.update(playlist);
        return playlist;
    }

    public void delete(String id) {
        playlistRepository.delete(id); }

    public Playlist addTrackToPlayList(Track track, String playListId){
        if(track.getId()==null || track.getId().length()<1)track.setId(UUID.randomUUID().toString());
        return playlistRepository.addTrackToPlayList(track,playListId);
    }

    public Playlist removeTrackToPlayList(Track track,String playListId){
       return playlistRepository.removeTrackToPlayList(track,playListId);
    }

    public Playlist findById(String id) {
        return playlistRepository.findById(id);
    }

    public List<Playlist> PlaylistsfindByUserIdOptional(String id) {
        Optional<List<Playlist>> optionalPlayLists = playlistRepository.findByUserIdOptional(id);
        if (optionalPlayLists.isEmpty()) {
            return null;
        }
        List<Playlist> playlists = optionalPlayLists.get();
        return playlists;
    }

    public List<Playlist> findAll () {
        List<Playlist> result = playlistRepository.findAll();
       return result;
    }
}
