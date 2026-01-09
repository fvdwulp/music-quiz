package com.example.music_quiz.service.song;

import com.example.music_quiz.domain.Song;

import java.util.List;

public interface SongService {
    Song newSong();
    List<Song> findAll();
    Song findById(int id);
    Song save(Song theSong);
    void deleteById(int id);
    String getPreviewUrl(int trackId);

}
