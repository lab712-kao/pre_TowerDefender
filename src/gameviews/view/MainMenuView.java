package gameviews.view;

import tw.edu.ttu.pre_towerdefender.GameScreenActivity;
import tw.edu.ttu.pre_towerdefender.MainActivity;
import tw.edu.ttu.pre_towerdefender.R;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenuView extends Activity{

	
	ImageButton startBtn2, soundControl;
	MainActivity activity;
	private MediaPlayer Player;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startBtn2 = (ImageButton)findViewById(R.id.startBtn2);
		
		Player = new MediaPlayer();
		Player = MediaPlayer.create(MainMenuView.this, R.raw.sample);
		Player.start();
		
		if(startBtn2 == null) {
			System.out.println("where is the button?");
		}
		else {
			startBtn2.setOnClickListener(new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					startBtn2.setImageResource(R.drawable.buttun_start_click);
					gotoGameActivity(v);
				}         
	        });
		}
		
	}
	public void MusicOff(View v){
		
		Player.pause();
	
}

	public void gotoGameActivity(View v) {
		
		Intent it = new Intent();
		it.setClass(this, GameScreenActivity.class);
		startActivity(it);
	}

	
}
