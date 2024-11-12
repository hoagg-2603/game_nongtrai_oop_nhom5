package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameMusic { //  Nhạc nền
    private Music music;
    // Âm hiệu
    private Sound waterSound, harvestSound, removeSound,moveSound,seedSound;

    private Boolean isSoundPlaying;

    public GameMusic(Music music, Sound waterSound, Sound harvestSound, Sound removeSound,Sound moveSound){
        this.music = music;
        this.waterSound = waterSound;
        this.harvestSound = harvestSound;
        this.removeSound = removeSound;
        this.moveSound = moveSound;
    }
    public GameMusic(){
        music= Gdx.audio.newMusic(Gdx.files.internal("../assets/music/1.mp3"));//Nhạc nền
        music.setLooping(true);
        isSoundPlaying=false;
        waterSound=Gdx.audio.newSound(Gdx.files.internal("../assets/music/water.mp3"));//Âm hiệu tưới nước
        harvestSound=Gdx.audio.newSound(Gdx.files.internal("../assets/music/get.mp3"));//Âm hiệu thu hoạch
        moveSound=Gdx.audio.newSound(Gdx.files.internal("../assets/music/walk.mp3"));//Âm hiệu nhân vật di chuyển
        removeSound=Gdx.audio.newSound(Gdx.files.internal("../assets/music/remove.mp3"));//Âm hiệu xóa bỏ
        seedSound=Gdx.audio.newSound(Gdx.files.internal("../assets/music/dirt.mp3"));
    }
    // Cài đặt âm lượng nhạc
    public void setMusicVolume(float volume) {
        music.setVolume(volume);
    }
    public float getMusicVolume() {
        return music.getVolume();
    }
    public void setMusicPlaying() {
        music.play();
    }
    public void setMusicPause() {
        music.pause();
    }
    public void setMusicStop(){
        music.stop();
    }
    public boolean Musicplaying(){
        return music.isPlaying();
    }
    public void setMusicLooping(){
        music.setLooping(true);
    }

    //Cài đặt âm lượng âm hiệu
    public void setSoundVolume(long a,float volume) {
        waterSound.setVolume(a,volume);
        harvestSound.setVolume(a,volume);
        removeSound.setVolume(a,volume);
        moveSound.setVolume(a,volume);
        seedSound.setVolume(a, volume);
    }
    public void setwaterSoundPlaying(){
        waterSound.play();
    }
    public void setharvestSoundPlaying(){
        harvestSound.play();
    }
    public void setremoveSoundPlaying(){
        removeSound.play();
    }
    public void setSeedSoundPlaying(){seedSound.play();}

    public Boolean isSoundPlaying() {
    	return isSoundPlaying;
    }
    public void setIsSoundPlaying(Boolean isSoundPlaying) {
    	this.isSoundPlaying=isSoundPlaying;
    }
    public Sound getHarvestSound() {
    	return harvestSound;
    }
    public Sound getWaterSound() {
    	return waterSound;
    }
    public Sound getRemoveSound() {
    	return removeSound;
    }
}
