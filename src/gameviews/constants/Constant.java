
package gameviews.constants;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import tw.edu.ttu.pre_towerdefender.R;

public class Constant {
	
	
	
	// for the screen settings

	public static int Screen_Width;
	public static int Screen_Height;
	
	public static float wRatio;

	public static float hRatio;
	public static float Hdp;
	public static float Wdp;
	
	public static final float screenRatio800x480=1.667f;
	public static final float screenRatio854x480=1.779f;


	public static final int screenWidthStander=800;
	public static final int screenHeightStander=480;
	
	
	public static final int standerWidth = 1024;
	public static final int standerHeight = 552;
	

	public static boolean isInitFlag = false;
	
	public static void initConst(int screenWidth,int screenHeight )
	{
		
		if(isInitFlag == true){
			return;
		}
		
		Screen_Width = screenWidth;
		Screen_Height = screenHeight;
		
		wRatio = screenWidth/(float)standerWidth;
		hRatio = screenHeight/(float)standerHeight;
		
		isInitFlag = true;
	}
	

/*-------------------------------------------------------
 This part is for the timer setting in the game.
 The flag for control timer, which in the whole game.
 The bound for bounding the max of the player's energy.
 --------------------------------------------------------*/
	public static int[] images = new int[]{
        R.drawable.number_zero,
        R.drawable.number_one,
        R.drawable.number_two,
        R.drawable.number_three,
        R.drawable.number_four,
        R.drawable.number_five,
        R.drawable.number_six,
        R.drawable.number_seven,
        R.drawable.number_eight,
        R.drawable.number_nine,
        R.drawable.number_desh
	};
	
	
/*-------------------------------------------------------
	For control the cost of the buttons
 --------------------------------------------------------*/	
	public static int[] Levelup_cost = new int[]{
      50,100,150,200,250,300,350,400
	};
	public static int[] levels = new int[]{      
		R.drawable.num_zero,
        R.drawable.num_one,
        R.drawable.num_two,
        R.drawable.num_three,
        R.drawable.num_four,
        R.drawable.num_five,
        R.drawable.num_six,
        R.drawable.num_seven,
        R.drawable.num_eight
       
	};
	public static int[] buttons_cost = new int[]{
	  50,100,150,200
	};
	
/*---------------------------------------------------
 	store the images and buttons' size
  ------------------------------------------------*/
	public static int[][] imageSize = new int[][]{
		{ 24, 32, 143, 62 },  //1.cost_hun
		{ 24, 32, 163, 62 },  //2.cost_ten
		{ 24, 32, 183, 62 },  //3.cost_one
		{ 24, 32, 206, 62 },  //4.desh
		{ 24, 32, 226, 62 },  //5.bound_hun
		{ 24, 32, 246, 62 },  //6.bound_ten
		{ 24, 32, 266, 62 },  //7.bound_one
		{ 75, 60, 132, 8 },   //8.level
		{ 21, 24, 204, 26 },  //9.level_num
		{ 21, 28, 228, 24 },  //10.level_hun
		{ 21, 28, 268, 24 },  //11.level_ten
		{ 21, 28, 248, 24 },  //12.level_one
		{ 280, 120, 32, 17 },	 //13.Level_UP_button
		{ 115, 140, 31, 122 },	 //14.domdom_button
		{ 115, 140, 31, 244 },  //15.tank_button
		{ 115, 138, 31, 368 },  //16.peanut_button
		
		
		{ 120, 120, 890, 85 },	 //17.sound_control
		{ 120, 120, 890 ,17 },  //18.time_pause_button
		{ 240, 60, 320, 58 },   //19.power_bar
		
		{ 21, 24, 320, 50 },  //20.blood_hun
		{ 21, 24, 335, 50 },  //21.blood_ten
		{ 21, 24, 350, 50 },  //22.blood_one
		
		{ 200, 100, 0, 0 },   //23.start_game_button
		{ 60, 60, 0, 0 }   //24.music_control_StartPage
		
	};

}
