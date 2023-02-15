package com.ncaoduc.UpAndDownFile.service;

import com.ncaoduc.UpAndDownFile.models.Song;
import org.springframework.stereotype.Service;

@Service
public interface SongService {

    Song addSong(Song song);

}
