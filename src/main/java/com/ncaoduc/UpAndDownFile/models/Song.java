package com.ncaoduc.UpAndDownFile.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    private String title;
    private String urlMp3;
    private String urlPhoto;
    private String artist;

}
