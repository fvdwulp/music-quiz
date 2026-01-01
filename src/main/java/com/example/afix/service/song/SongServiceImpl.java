package com.example.afix.service.song;

import com.example.afix.model.User;
import com.example.afix.repository.SongRepository;
import com.example.afix.model.Song;
import com.example.afix.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;
    private UserService userService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public SongServiceImpl(
            SongRepository songRepository,
            UserService userService
    ) {
        this.songRepository = songRepository;
        this.userService = userService;
    }

    @Override
    public Song newSong() {
        return new Song();
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Song findById(int id) {
        Optional<Song> song = songRepository.findById(id);
        if(song.isPresent())
            return song.get();
        else
            throw new RuntimeException("Did not find song id - " + id);
    }

    @Override
    public Song save(Song theSong) {
        User user = userService.getCurrentUser();
        theSong.setOwner(user);
        return songRepository.save(theSong);

    }

    @Transactional
    @Override
    public void deleteById(int id) {
        songRepository.deleteById(id);
    }

    @Override
    public String getPreviewUrl(int trackId) {
        String url = "https://api.deezer.com/track/" + trackId;
        try {
            ResponseEntity<DeezerTrackResponse> response =
                    restTemplate.getForEntity(url, DeezerTrackResponse.class);

            DeezerTrackResponse body = response.getBody();
            if (body != null && body.getPreview() != null) {
                return body.getPreview();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("Failed to fetch preview from Deezer: " + e.getMessage());
            return null;
        }
    }

    private static class DeezerTrackResponse {
        private String preview;
        public String getPreview() { return preview; }
        public void setPreview(String preview) { this.preview = preview; }
    }

}
