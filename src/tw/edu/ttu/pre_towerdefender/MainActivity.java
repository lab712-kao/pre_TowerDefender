package tw.edu.ttu.pre_towerdefender;

import gameviews.constants.Constant;
import gameviews.view.MainMenuView;
import android.R.integer;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewDebug.IntToString;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	ImageButton startBtn,playerBtn;
	TextView height,width;
	
	private MediaPlayer Player;
	public int playerChange = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		screenInit();
		startBtn = (ImageButton)findViewById(R.id.startBtn2);
		playerBtn = (ImageButton)findViewById(R.id.sound);
		height = ( TextView )findViewById(R.id.screenHight);
		width = ( TextView )findViewById(R.id.screenWidth);
		
		height.setText(String.valueOf(Constant.Screen_Height));
		width.setText(String.valueOf(Constant.Screen_Width));
		
		Player = new MediaPlayer();
		Player = MediaPlayer.create(MainActivity.this, R.raw.sample);
		Player.start();
		
		if(startBtn == null) {
			System.out.println("where is the button?");
		}
		else {
			startBtn.setOnClickListener(new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					startBtn.setImageResource(R.drawable.buttun_start_click);
					gotoGameActivity(v);
				}         
	        });
		}
	}
	public void screenInit(){
		
		DisplayMetrics dm = new DisplayMetrics();
		
		float dpHeight = dm.heightPixels / dm.density;
        float dpWidth = dm.widthPixels / dm.density;
		
		
	     getWindowManager().getDefaultDisplay().getMetrics( dm );
	     
	     Constant.initConst( dm.widthPixels, dm.heightPixels );
				
	}
	public void MusicOff(View v){
		
		if(playerChange == 0){
			playerChange = 1;
			playerBtn.setImageResource(R.drawable.button_sound_off);
			Player.pause();
		}
		else{
			playerChange = 0;
			playerBtn.setImageResource(R.drawable.button_sound);
			Player.start();	
		}	
	}
	public void gotoGameActivity(View v) {
		Intent it = new Intent();
		it.setClass(this, GameScreenActivity.class);
		startActivity(it);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void gotoMainMenu() {
		Intent it = new Intent();
		it.setClass(this, MainMenuView.class);
		startActivity(it);
	}

}
