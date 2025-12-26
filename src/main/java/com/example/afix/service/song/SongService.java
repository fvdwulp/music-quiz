package com.example.afix.service.song;

import com.example.afix.model.Song;
import com.example.afix.model.User;

import java.util.List;

public interface SongService {
    List<Song> findAll();
    Song findById(int id);
    Song save(Song theSong);
    void deleteById(int id);
    String getPreviewUrl(int trackId);

}
