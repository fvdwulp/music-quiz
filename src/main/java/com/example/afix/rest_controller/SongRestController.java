package com.example.afix.rest_controller;

import com.example.afix.model.Song;
import com.example.afix.service.song.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SongRestController {

    SongService songService;

    public SongRestController(
            SongService songService
    ) {
        this.songService = songService;
    }

    @GetMapping("/songs/preview/{trackId}")
    public String getSongPreviewUrl(@PathVariable int trackId) {
        return songService.getPreviewUrl(trackId);
    }

}
