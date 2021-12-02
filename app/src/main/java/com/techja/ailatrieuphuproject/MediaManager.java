package com.techja.ailatrieuphuproject;

import android.media.MediaPlayer;

public class MediaManager {
    private static MediaManager instance;
    private MediaPlayer bgPlayer;
    //    private MediaPlayer voicebgPlayer;
//    private MediaPlayer voicerulePlayer;
//    private MediaPlayer voicereadyPlayer;
    private MediaPlayer voiceSong;

    public static MediaManager getInstance() {
        if (instance == null) {
            instance = new MediaManager();
        }
        return instance;
    }

    public void playBgPlayer(int song, boolean isLooping) {
        if (this.bgPlayer != null) {
            this.bgPlayer.reset();
        }
        bgPlayer = play(song, isLooping);
    }

//    public void playvoiceBgPlayer(int song) {
//        playvoiceBgPlayer(song, false);
//    }
//
//    public void playvoiceBgPlayer(int song, boolean isLooping) {
//        if (this.voicebgPlayer != null) {
//            this.voicebgPlayer.reset();
//        }
//        voicebgPlayer = play(song, isLooping);
//    }
//
//    public void playreadyBgPlayer(int song) {
//        playreadyBgPlayer(song, false);
//    }
//
//    public void playreadyBgPlayer(int song, boolean isLooping) {
//        if (this.voicereadyPlayer != null) {
//            this.voicereadyPlayer.reset();
//        }
//        voicereadyPlayer = play(song, isLooping);
//    }
//
//
//    public void playvoiceRulePlayer(int song, boolean isLooping, MediaPlayer.OnCompletionListener event) {
//        if (this.voicerulePlayer != null) {
//            this.voicerulePlayer.reset();
//        }
//        voicerulePlayer = play(song, isLooping);
//        voicerulePlayer.setOnCompletionListener(event);
//    }
//
//    public void playvoiceRulePlayer(int song, MediaPlayer.OnCompletionListener event) {
//        playvoiceRulePlayer(song, false, event);
//    }
//
//    public MediaPlayer play(int song, boolean isLooping) {
//
//        MediaPlayer player = MediaPlayer.create(App.getInstance(), song);
//        player.setLooping(isLooping);
//        player.start();
//        return player;
//    }


    public void voiceSong(int song, boolean isLooping, MediaPlayer.OnCompletionListener event) {
        if (this.voiceSong != null) {
            this.voiceSong.reset();
        }
        voiceSong = play(song, isLooping);
        voiceSong.setOnCompletionListener(event);
    }

    public void voiceSong(int song, MediaPlayer.OnCompletionListener event) {
        voiceSong(song, false, event);
    }

    public MediaPlayer play(int song, boolean isLooping) {

        MediaPlayer player = MediaPlayer.create(App.getInstance(), song);
        player.setLooping(isLooping);
        player.start();
        return player;
    }


    private void play(MediaPlayer player) {
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }

    public void playBG() {
        play(bgPlayer);
    }

    public void playvoice() {
        play(voiceSong);
    }


    public void pauseBG() {
        pause(bgPlayer);
    }

    public void pausevoiceBG() {
        pause(voiceSong);
    }

    private void pause(MediaPlayer player) {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    public void stopBG() {
        bgPlayer = stop(bgPlayer);
    }

    public void stopvoiceBG() {
        voiceSong = stop(voiceSong);
    }

    private MediaPlayer stop(MediaPlayer player) {
        if (player != null) {
            player.reset();
        }
        return null;
    }
}
