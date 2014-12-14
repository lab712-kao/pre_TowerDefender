package gameviews.view;

import tw.edu.ttu.pre_towerdefender.GameScreenActivity;
import tw.edu.ttu.pre_towerdefender.MainActivity;
import tw.edu.ttu.pre_towerdefender.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenuView extends Activity{

	Button startBtn;
	ImageButton startBtn2;
	MainActivity activity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startBtn2 = (ImageButton)findViewById(R.id.startBtn);
	
		
		if(startBtn2 == null) {
			System.out.println("where is the button?");
		}
		else {
			startBtn2.setOnClickListener(new Button.OnClickListener(){
				@Override
				public void onClick(View v) {
					gotoGameActivity(v);
				}         
	        });
		}
		
	}
<<<<<<< HEAD
	
=======
>>>>>>> 6d599d2851b62d93586158a59ac5fb78b0864450

	public void gotoGameActivity(View v) {
		
		Intent it = new Intent();
		it.setClass(this, GameScreenActivity.class);
		startActivity(it);
	}

	
}
