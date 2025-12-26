package com.example.afix.controller;


import com.example.afix.model.Song;
import com.example.afix.service.song.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SongController {

    SongService songService;

    public SongController(SongService theSongService){
        songService = theSongService;
    }

    @GetMapping("/songs")
    public String quiz(Model model) {
        List<Song> allSongs = songService.findAll();
        model.addAttribute("songs", allSongs);
        model.addAttribute("pageTitle", "Nummers");

        return "dashboard/songs/list";
    }

    @GetMapping("/songs/add")
    public String addSong(Model model){
        Song newSong = new Song();
        model.addAttribute("song", newSong);
        model.addAttribute("pageTitle", "Nummer toevoegen");

        return "dashboard/songs/add";
    }

    @GetMapping("/songs/edit/{id}")
    public String editSong(@PathVariable int id, Model model) {
        Song existingSong = songService.findById(id);
        if (existingSong == null) {
            return "redirect:/songs";
        }

        model.addAttribute("song", existingSong);
        model.addAttribute("pageTitle", "Nummer aanpassen");

        return "dashboard/songs/add";
    }

    @PostMapping("/songs/save")
    public String saveSong(@ModelAttribute Song theSong, Model model){
        Song savedSong;
        if (theSong.getId() != null) {
            Song existing = songService.findById(theSong.getId());
            if (existing != null) {
                existing.setSongName(theSong.getSongName());
                existing.setArtist(theSong.getArtist());
                existing.setTrackId(theSong.getTrackId());
                savedSong = songService.save(existing);
            } else {
                savedSong = songService.save(theSong);
            }
        } else {
            // New song — no ID
            savedSong = songService.save(theSong);
        }

        model.addAttribute("song", savedSong);

        return "redirect:/songs";
    }

    @GetMapping("/songs/delete")
    public String deleteSong(@RequestParam int id){

        Song song = songService.findById(id);
        if(song == null)
            return "redirect:/songs";

        songService.deleteById(id);

        return "redirect:/songs";

    }
}
