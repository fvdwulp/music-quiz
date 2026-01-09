package com.example.music_quiz;

import com.example.music_quiz.domain.Song;
import com.example.music_quiz.domain.User;
import com.example.music_quiz.repository.SongRepository;
import com.example.music_quiz.service.song.SongServiceImpl;
import com.example.music_quiz.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {

    @Mock
    SongRepository songRepository;

    @Mock
    UserService userService;

    @InjectMocks
    SongServiceImpl songService;

    User user;
    User anotherUser;

    @BeforeEach
    void setup() {
        user = new User();
        user.setId(1);
        user.setUsername("fabian");
    }

    @Test
    void findAll_returnsSongsOfCurrentUser() {

        Song song = new Song();
        song.setOwner(user);
        List<Song> songs = List.of(song);

        when(userService.getCurrentUser()).thenReturn(user);
        when(songRepository.findByOwner(user))
                .thenReturn(songs);

        List<Song> result = songService.findAll();

        assertThat(result)
                .hasSize(1)
                .allMatch(s -> s.getOwner().equals(user));

        verify(songRepository).findByOwner(user);
        verify(songRepository, never()).findAll();
    }

    @Test
    void findById_throwsException_whenSongNotOwnedByUser() {

        when(userService.getCurrentUser()).thenReturn(user);
        when(songRepository.findByIdAndOwner(99, user))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                songService.findById(99)
        );
    }

    @Test
    void save_setsOwnerAutomatically() {
        Song song = new Song();
        song.setSongName("Fix You");

        when(userService.getCurrentUser()).thenReturn(user);
        when(songRepository.save(any(Song.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Song saved = songService.save(song);

        assertEquals(user, saved.getOwner());
    }
}

