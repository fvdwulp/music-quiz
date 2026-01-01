package com.example.afix.repository;

import com.example.afix.model.Song;
import com.example.afix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findByOwner(User owner);
    Optional<Song> findByIdAndOwner(Integer id, User owner);
    void deleteByIdAndOwner(Integer id, User owner);
}
