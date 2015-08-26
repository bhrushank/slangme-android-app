package com.slangme.model;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SlangControllerVariables {
	
	private SoundPool soundPool;	
	private int soundID;	
	private boolean plays = false, loaded = false;	
	private float actVolume, maxVolume, volume;	
	private AudioManager audioManager;	
	private int counter;
	private int musicID;
	public  int COUNTER = 0;
	private MediaPlayer mediaPlayer = null;
	
	/**
	 * @return the soundPool
	 */
	public SoundPool getSoundPool() {
		return soundPool;
	}
	/**
	 * @param soundPool the soundPool to set
	 */
	public void setSoundPool(SoundPool soundPool) {
		this.soundPool = soundPool;
	}
	/**
	 * @return the soundID
	 */
	public int getSoundID() {
		return soundID;
	}
	/**
	 * @param soundID the soundID to set
	 */
	public void setSoundID(int soundID) {
		this.soundID = soundID;
	}
	/**
	 * @return the audioManager
	 */
	public AudioManager getAudioManager() {
		return audioManager;
	}
	/**
	 * @param audioManager the audioManager to set
	 */
	public void setAudioManager(AudioManager audioManager) {
		this.audioManager = audioManager;
	}
	/**
	 * @return the actVolume
	 */
	public float getActVolume() {
		return actVolume;
	}
	/**
	 * @param actVolume the actVolume to set
	 */
	public void setActVolume(float actVolume) {
		this.actVolume = actVolume;
	}
	/**
	 * @return the maxVolume
	 */
	public float getMaxVolume() {
		return maxVolume;
	}
	/**
	 * @param maxVolume the maxVolume to set
	 */
	public void setMaxVolume(float maxVolume) {
		this.maxVolume = maxVolume;
	}
	/**
	 * @return the volume
	 */
	public float getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}
	/**
	 * @return the plays
	 */
	public boolean isPlays() {
		return plays;
	}
	/**
	 * @param plays the plays to set
	 */
	public void setPlays(boolean plays) {
		this.plays = plays;
	}
	/**
	 * @return the loaded
	 */
	public boolean isLoaded() {
		return loaded;
	}
	/**
	 * @param loaded the loaded to set
	 */
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}
	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}
	/**
	 * @return the musicID
	 */
	public int getMusicID() {
		return musicID;
	}
	/**
	 * @param musicID the musicID to set
	 */
	public void setMusicID(int musicID) {
		this.musicID = musicID;
	}
	/**
	 * @return the mediaPlayer
	 */
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	/**
	 * @param mediaPlayer the mediaPlayer to set
	 */
	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	
}
