package com.slangme.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;
//import android.widget.Toast;










import com.slangme.model.SlangControllerVariables;
import com.slangme.model.SlangTerm;

public class SlangController extends Thread{

	private SlangControllerVariables controllerVars;
	private Activity act;
	private ExecutorService service;
	
	
	public SlangController(Activity param){
		// AudioManager audio settings for adjusting the volume		
		act = param;
		controllerVars = new SlangControllerVariables();		
		}

	@Override
    public void run() {
		setControllerVariables();
	}
	
	@SuppressWarnings("deprecation")
	private void setControllerVariables() {
		// TODO Auto-generated method stub
		controllerVars.setAudioManager((AudioManager) act.getSystemService(Context.AUDIO_SERVICE));
		controllerVars.setActVolume((float) controllerVars.getAudioManager().getStreamVolume(AudioManager.STREAM_MUSIC));
		controllerVars.setMaxVolume((float) controllerVars.getAudioManager().getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		controllerVars.setVolume(controllerVars.getActVolume() / controllerVars.getMaxVolume());

		//Hardware buttons setting to adjust the media sound
		act.setVolumeControlStream(AudioManager.STREAM_MUSIC);

		// the counter will help us recognize the stream id of the sound played  now
		controllerVars.setCounter(0);

		// Load the sounds
		controllerVars.setSoundPool(new SoundPool(20, AudioManager.STREAM_MUSIC, 0));
		controllerVars.getSoundPool().setOnLoadCompleteListener(new OnLoadCompleteListener() {
		    @Override
		    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		    	controllerVars.setLoaded(true);
		    }
		});		
	}

	public void LoadSlang(Context ctx,int soundId) {
		controllerVars.setMusicID(soundId);
		controllerVars.setSoundID(controllerVars.getSoundPool().load(act, soundId, 1));
		controllerVars.setMediaPlayer(MediaPlayer.create(ctx, soundId));		
	}
	
	public void UnloadSlang(){
		//while(!controllerVars.isLoaded()){
		controllerVars.getSoundPool().unload(controllerVars.getMusicID());
		controllerVars.setPlays(false);
	}
	
	public List<SlangTerm> readXml(List<SlangTerm> list)
			throws XPathExpressionException {
		// TODO Auto-generated method stub
		// Load XML for parsing.
		AssetManager assetManager = act.getAssets();

		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();
			NodeList shows = (NodeList) xPath.evaluate("/Slang/SlangTerm",
					new InputSource(assetManager.open("slangterm.xml")),
					XPathConstants.NODESET);
			for (int i = 0; i < shows.getLength(); i++) {
				Element slangElement = (Element) shows.item(i);
				SlangTerm slang = new SlangTerm();
				slang.setSlangId(Integer.parseInt(slangElement
						.getAttribute("id")));
				slang.setSlangTerm(xPath.evaluate("Term", slangElement));
				slang.setSlangDescription(xPath.evaluate("Description",
						slangElement));
				slang.setSlangAudio(xPath.evaluate("AudioFile", slangElement));

				list.add(slang);
			}

			return list;
		} catch (IOException e) {
			Log.e("tag", e.getMessage());
			return new ArrayList<SlangTerm>();
		}
	}
	
	public void requestSlang(){
		if(controllerVars.isPlays())
			stopSound();
		else
			playSound();
	}

	private void playSound() {
		// Is the sound loaded does it already play?
		if (controllerVars.isLoaded() && !controllerVars.isPlays()) {
			controllerVars.getSoundPool().play(controllerVars.getSoundID(), controllerVars.getVolume(), controllerVars.getVolume(), 1, 0, 1f);
			controllerVars.COUNTER++;
			//Toast.makeText(act, "Played sound", Toast.LENGTH_SHORT).show();optional Playing message 
			controllerVars.setPlays(true);
			onComplete(controllerVars.getMediaPlayer().getDuration());
		}
		//controllerVars.getMediaPlayer().start();
		//controllerVars.setPlays(true);
	}

	private void onComplete(int duration) {
		// TODO Auto-generated method stub
		
		service = Executors.newSingleThreadExecutor();

		try {
		    Runnable r = new Runnable() {
		        @Override
		        public void run() {
		            // Database task
		        	controllerVars.setPlays(false);
		        }
		    };
		    Future<?> f = service.submit(r);

		    f.get(duration, TimeUnit.MILLISECONDS);     // attempt the task for two minutes
		}
		catch (final InterruptedException e) {
		    // The thread was interrupted during sleep, wait or join
		}
		catch (final TimeoutException e) {
		    // Took too long!
		}
		catch (final ExecutionException e) {
		    // An exception from within the Runnable task
		}
		finally {
		    service.shutdown();
		}		
	}

	private void stopSound() {
			if(service != null)
				service.shutdown();
			
			controllerVars.getSoundPool().stop(controllerVars.getSoundID());
			controllerVars.getSoundPool().unload(controllerVars.getSoundID());
			controllerVars.setSoundID(controllerVars.getSoundPool().load(act, controllerVars.getMusicID(), controllerVars.COUNTER));
			controllerVars.COUNTER--;
			//Toast.makeText(act, "Stop sound", Toast.LENGTH_SHORT).show(); optional Stop message
			controllerVars.setPlays(false);
		//controllerVars.getMediaPlayer().stop();
		//controllerVars.setPlays(false);
	}

}
