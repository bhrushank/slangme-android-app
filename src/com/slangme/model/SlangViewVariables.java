package com.slangme.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ToggleButton;

public class SlangViewVariables {
	private ToggleButton slangToggle;
	private ImageButton slangClose;
	private ImageButton slangPlay;
	private List<SlangTerm> slanglist;
	private List<Integer> slangplaylist;
	private PopupWindow pwindo;
	private boolean popup;
	
	public final Random randomGenerator = new Random();
	
	/**
	 * @return the slangToggle
	 */
	public ToggleButton getSlangToggle() {
		return slangToggle;
	}
	/**
	 * @param slangToggle the slangToggle to set
	 */
	public void setSlangToggle(ToggleButton slangToggle) {
		this.slangToggle = slangToggle;
	}
	/**
	 * @return the slangClose
	 */
	public ImageButton getSlangClose() {
		return slangClose;
	}
	/**
	 * @param slangClose the slangClose to set
	 */
	public void setSlangClose(ImageButton slangClose) {
		this.slangClose = slangClose;
	}
	/**
	 * @return the slangPlay
	 */
	public ImageButton getSlangPlay() {
		return slangPlay;
	}
	/**
	 * @param slangPlay the slangPlay to set
	 */
	public void setSlangPlay(ImageButton slangPlay) {
		this.slangPlay = slangPlay;
	}
	/**
	 * @return the slanglist
	 */
	public List<SlangTerm> getSlanglist() {
		return slanglist;
	}
	/**
	 * @param slanglist the slanglist to set
	 */
	public void setSlanglist(List<SlangTerm> slanglist) {
		this.slanglist = slanglist;
	}
	/**
	 * @return the pwindo
	 */
	public PopupWindow getPwindo() {
		return pwindo;
	}
	/**
	 * @param pwindo the pwindo to set
	 */
	public void setPwindo(PopupWindow pwindo) {
		this.pwindo = pwindo;
	}
	/**
	 * @return the popup
	 */
	public boolean isPopup() {
		return popup;
	}
	/**
	 * @param popup the popup to set
	 */
	public void setPopup(boolean popup) {
		this.popup = popup;
	}
	/**
	 * @return the slangplaylist
	 */
	public int getSlangplayIndex(int index) {
		int result = slangplaylist.get(index);
		slangplaylist.remove(index);
		
		//reset playlist case its in its end
		if(slangplaylist.size() < 1)
			setSlangplaylist(slanglist.size());
		
		return result;
	}
	
	public List<Integer> getSlangplaylist() {
		return slangplaylist;
	}
	
	/**
	 * @param slangplaylist the slangplaylist to set
	 */
	public void setSlangplaylist(int slangplaylist) {
		this.slangplaylist = new ArrayList<Integer>();
		for (int i = 0; i < slangplaylist; i++) {
			this.slangplaylist.add(i);			
		}
	}
}
