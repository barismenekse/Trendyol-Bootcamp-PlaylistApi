package com.trendyol.PlaylistApi.controller;

import com.trendyol.PlaylistApi.domain.Playlist;
import com.trendyol.PlaylistApi.domain.Track;
import com.trendyol.PlaylistApi.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }


    @GetMapping()
    public ResponseEntity<List<Playlist>> getAll() {
        return ResponseEntity.ok().body(playlistService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getById(@PathVariable String id) {
        Playlist result = playlistService.findById(id);
        return  new ResponseEntity<Playlist>(result, HttpStatus.OK);
    }

    @GetMapping("/findAllByUserId/{id}")
    public ResponseEntity<List<Playlist>> findAllByUserId(@PathVariable String id) {
        List<Playlist> result = playlistService.PlaylistsfindByUserIdOptional(id);
        return  new ResponseEntity<List<Playlist>>(result, HttpStatus.OK);
    }

    @PostMapping("/{id}/addTrack")
    public ResponseEntity<Void> addTrackToPlaylist(@PathVariable String id , @RequestBody Track track) {
        playlistService.addTrackToPlayList(track,id);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<String> addPlaylist(@RequestBody Playlist playlist) {
        Playlist result =playlistService.insert(playlist);
        URI location = URI.create(String.format("/playlist/%s", result.getId()));
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/removeTrack")
    public ResponseEntity<Void> removeTrackToPlaylist(@PathVariable String id , @RequestBody Track track) {
        playlistService.removeTrackToPlayList(track,id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePlaylist(@PathVariable String id ) {
        playlistService.delete(id);
        return ResponseEntity.ok().build();
    }
}
