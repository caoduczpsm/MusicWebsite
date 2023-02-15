package com.ncaoduc.UpAndDownFile.controller;

import com.ncaoduc.UpAndDownFile.models.Song;
import com.ncaoduc.UpAndDownFile.service.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private SongServiceImpl songService;

    @GetMapping()
    public ModelAndView index(Model model){
        Song song = new Song();
        model.addAttribute("song", song);
        return new ModelAndView("admin/index");
    }


    @PostMapping()
    public ModelAndView addSong(@ModelAttribute("song") Song song, @RequestParam("mp3File") MultipartFile mp3File, @RequestParam("photoFile") MultipartFile photoFile) throws IOException {
        Song newSong = new Song(song.getTitle(), Arrays.toString(mp3File.getBytes()), Arrays.toString(photoFile.getBytes()), song.getArtist());
        songService.addSong(newSong);
        return new ModelAndView("admin/index");
    }

}
