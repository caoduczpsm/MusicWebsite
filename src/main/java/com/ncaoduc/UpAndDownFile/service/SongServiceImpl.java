package com.ncaoduc.UpAndDownFile.service;

import com.ncaoduc.UpAndDownFile.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class SongServiceImpl implements SongService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Song addSong(Song song) {
        String sql = "INSERT INTO songs(title, mp3Url, photoUrl, artist) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, song.getTitle(), song.getUrlMp3(), song.getUrlPhoto(), song.getArtist());
        return new Song(song.getTitle(), song.getUrlMp3(), song.getUrlPhoto(), song.getArtist());
    }

}
