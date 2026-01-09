package com.example.music_quiz.repository;

import com.example.music_quiz.domain.Song;
import com.example.music_quiz.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findByOwner(User owner);
    Optional<Song> findByIdAndOwner(Integer id, User owner);
    void deleteByIdAndOwner(Integer id, User owner);
}
