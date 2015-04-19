package tw.edu.ttu.pre_towerdefender;

import java.util.ArrayList;

import gameviews.constants.Constant;
import gameviews.view.MainMenuView;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	ImageView startBtn,playerBtn;
	
	
	private MediaPlayer Player;
	public int playerChange = 0;
	public ArrayList<ImageView> imageArray = new ArrayList<ImageView>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		screenInit();
		
		startBtn = (ImageView)findViewById(R.id.startBtn2);
		playerBtn = (ImageView)findViewById(R.id.sound);
		
		/*	imageArray.add(startBtn);
		imageArray.add(playerBtn);
		
		for (int i = 17; i < 19; i++) {
			//LayoutParams params = imageArray.get(i).getLayoutParams();
			//params.width = (int)(Constant.imageSize[i][0] * Constant.wRatio);
	        //params.height =(int)( Constant.imageSize[i][1] * Constant.hRatio);
	        //imageArray.get(i).setLayoutParams(params);
	       // imageArray.get(i).setX((float)Constant.imageSize[i][2]*Constant.wRatio);
	        //imageArray.get(i).setY((float) (Constant.imageSize[i][3]*Constant.hRatio));
	        
		}	*/	
		
		Player = new MediaPlayer();
		Player = MediaPlayer.create(MainActivity.this, R.raw.sample);
		Player.start();
	}
	public void startGame( View v ){
		startBtn.setImageResource(R.drawable.buttun_start_click);
		gotoGameActivity(v);
		
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
		Player.pause();
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
