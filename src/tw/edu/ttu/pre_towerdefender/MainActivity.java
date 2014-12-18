package tw.edu.ttu.pre_towerdefender;

import gameviews.view.MainMenuView;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	ImageButton startBtn,playerBtn;
	private MediaPlayer Player;
	public int playerChange = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startBtn = (ImageButton)findViewById(R.id.startBtn2);
		playerBtn = (ImageButton)findViewById(R.id.sound);
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
