package com.slangme.View;

import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;

import com.slangme.R;
import com.slangme.Controller.SlangController;
import com.slangme.model.SlangTerm;
import com.slangme.model.SlangViewVariables;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity implements OnTouchListener{	
	
	private SlangViewVariables slangVars;
	private SlangController slangController;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try {
	        
        	//slangController.setRunning(true);        	
        	slangController = new SlangController(this);
        	slangController.start();
	        
        	slangVars = new SlangViewVariables();
	        
	        slangVars.setSlangToggle((ToggleButton) findViewById(R.id.slang_btn));
	        slangVars.getSlangToggle().setOnTouchListener(this);
	        
	        slangVars.setPopup(true);        
        
			slangVars.setSlanglist(slangController.readXml(new ArrayList<SlangTerm>()));
			slangVars.setSlangplaylist(slangVars.getSlanglist().size());
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }  

	@SuppressLint("ClickableViewAccessibility")
	@Override
    public boolean onTouch(View v, MotionEvent event) {
    	// TODO Auto-generated method stub		
    	switch(event.getAction())
    	{
    		case MotionEvent.ACTION_DOWN:
    			switch(v.getId())
    			{
    				case R.id.slang_btn:
					slangVars.getSlangToggle().setChecked(true);
    				//Call function for pop-up    					
					createPopUp();    												
    					//break;    				
    			}
    	}
    	return false;
    }

	private void createPopUp() {
		if(slangVars.isPopup())
		{
			LayoutInflater inflater = (LayoutInflater) MainActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.slang_popup,
						(ViewGroup) findViewById(R.id.popup_element));
			slangVars.setPwindo(new PopupWindow(layout, 0, 0, true));
			slangVars.getPwindo().setWidth(WindowManager.LayoutParams.MATCH_PARENT);
			slangVars.getPwindo().setHeight(WindowManager.LayoutParams.MATCH_PARENT);
			
			SlangTerm slang = getslang();
			
			((TextView)layout.findViewById(R.id.slang_term)).setText(slang.getSlangTerm());
			((TextView)layout.findViewById(R.id.slang_description)).setText(slang.getSlangDescription());
			
			ButtonActions(layout);
	        
	        View p = this.findViewById(R.id.slang_parent);
	        
	        slangVars.getPwindo().showAtLocation(p, Gravity.CENTER, 0, 0);
	        slangVars.setPopup(false);
		}
	}

	private SlangTerm getslang() {		
		SlangTerm slang = null;
		int slangId = 0;
		
		while (slangId == 0) {			
			slang = getRandomSlang();
			slangId = this.getResources().getIdentifier(slang.getSlangAudio(), "raw", this.getPackageName());
		}
		
		slangController.LoadSlang(this,slangId);
		return slang;		
	}

	private void ButtonActions(View layout) {
		slangVars.setSlangClose((ImageButton) layout.findViewById(R.id.closeButton));
		slangVars.getSlangClose().setOnTouchListener(new View.OnTouchListener() {
			
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				slangController.UnloadSlang();
				slangVars.getPwindo().dismiss();
				slangVars.setPopup(true);
				return false;
			}
		});
		
		slangVars.setSlangPlay((ImageButton) layout.findViewById(R.id.btn_play));
		slangVars.getSlangPlay().setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//Play sound code, audio files must go to raw folder
				//http://examples.javacodegeeks.com/android/android-soundpool-example/
				switch (event.getAction()) {
			    case MotionEvent.ACTION_UP:
			    	v.performClick();
			    	slangController.requestSlang();
			    	//			        
			        break;
			    default:
			        break;
			    }				
				return true;
			}
		});
		
		final Activity act = this;
		
		ImageButton slangPlus = (ImageButton) layout.findViewById(R.id.btn_plus);
		slangPlus.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
			    case MotionEvent.ACTION_UP:
			    	v.performClick();
			    	Toast.makeText(act, "Under Development", Toast.LENGTH_SHORT).show();
			        break;
			    default:
			        break;
			    }				
				return false;
			}
			
		});
		
		ImageButton slangMinus = (ImageButton) layout.findViewById(R.id.btn_minus);
		slangMinus.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {			    
			    case MotionEvent.ACTION_UP:
			    	v.performClick();
			    	Toast.makeText(act, "Under Development", Toast.LENGTH_SHORT).show();
			        break;
			    default:
			        break;
			    }				
				return false;
			}
			
		});
		
	}

	private SlangTerm getRandomSlang() {
		// TODO Auto-generated method stub
		
		int playIndex = slangVars.getSlangplayIndex(slangVars.randomGenerator.nextInt(slangVars.getSlangplaylist().size()));
		
		return slangVars.getSlanglist().get(playIndex);
	}
}