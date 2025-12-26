package com.example.afix.repository;

import com.example.afix.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SongRepository extends JpaRepository<Song, Integer> {


}
