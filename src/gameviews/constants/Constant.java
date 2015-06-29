
package gameviews.constants;

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
		//{w, h, X, Y}
		{ 24, 22, 82,  89 },  //1.cost_hun
		{ 24, 22, 97,  89 },  //2.cost_ten
		{ 24, 22, 112, 89 },  //3.cost_one
		{ 24, 22, 130, 89 },  //4.desh
		{ 24, 22, 148, 89 },  //5.bound_hun
		{ 24, 22, 163, 89 },  //6.bound_ten
		{ 24, 22, 178, 89 },  //7.bound_one
		{ 75, 22, 128, 34 },   //8.level
		{ 21, 18, 194, 36 },
		{ 21, 18, 194, 36 },
		{ 21, 18, 194, 36 },   //9.level_num
		{ 150, 70, 70, 65 },		//cost_back
		{ 150, 60, 70, 15 },	 //13.Level_UP_button
		{ 115, 75, 42, 122 },	 //14.domdom_button
		{ 115, 75, 42, 202 },  //15.tank_button
		{ 115, 75, 42, 282 },  //16.peanut_button
		

		
		{ 100, 100, 57, 352 },	 //17.sound_control
		{ 100, 100, 57 ,412 },  //18.time_pause_button
		{ 300, 70, 250, 2 },   //19.power_bar
		
		//{ 21, 24, 320, 50 },  //20.blood_hun
		//{ 21, 24, 335, 50 },  //21.blood_ten
		//{ 21, 24, 350, 50 },  //22.blood_one
		
		{ 200, 100, 0, 0 },   //23.start_game_button
		{ 60, 60, 0, 0 }   //24.music_control_StartPage
		
	};

}
