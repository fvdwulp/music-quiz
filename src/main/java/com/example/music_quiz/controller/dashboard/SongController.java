package com.example.music_quiz.controller.dashboard;


import com.example.music_quiz.domain.Song;
import com.example.music_quiz.service.song.SongService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService theSongService){
        songService = theSongService;
    }

    @GetMapping("")
    public String quiz(Model model) {
        List<Song> allSongs = songService.findAll();
        model.addAttribute("songs", allSongs);
        model.addAttribute("pageTitle", "Nummers");

        return "dashboard/songs/list";
    }

    @GetMapping("/add")
    public String addSong(Model model){
        model.addAttribute("song", songService.newSong());
        model.addAttribute("pageTitle", "Nummer toevoegen");

        return "dashboard/songs/add";
    }

    @GetMapping("/edit/{id}")
    public String editSong(@PathVariable Integer id, Model model) {
        Song existingSong = songService.findById(id);
        if (existingSong == null) {
            return "redirect:/songs";
        }

        model.addAttribute("song", existingSong);
        model.addAttribute("pageTitle", "Nummer aanpassen");

        return "dashboard/songs/add";
    }

    @PostMapping("/save")
    public String saveSong(
            @Valid @ModelAttribute Song theSong,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            model.addAttribute("song", theSong);
            model.addAttribute("pageTitle", theSong.getId() == null ? "Nummer toevoegen" : "Nummer aanpassen");
            return "dashboard/songs/add";
        }

        songService.save(theSong);

        return "redirect:/songs";
    }

    @PostMapping("/{id}/delete")
    public String deleteSong(@PathVariable Integer id){
        Song song = songService.findById(id);
        if(song == null)
            return "redirect:/songs";

        songService.deleteById(id);

        return "redirect:/songs";

    }
}
