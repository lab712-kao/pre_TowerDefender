
package gameviews.constants;

import tw.edu.ttu.pre_towerdefender.R;

public class Constant {
	
	
	
	// for the screen settings
	public static int Screen_Width;//ø√πı™∫ºe´◊
	public static int Screen_Height;//ø√πı™∫∞™´◊
	
	public static float wRatio;//æA¿≥•˛ø√πı™∫¡Y©Ò§Ò®“
	public static float hRatio;
	
	public static final float screenRatio800x480=1.667f;
	public static final float screenRatio854x480=1.779f;

	public static final int screenWidthTest=800;//¥˙∏’æ˜ø√πıºe´◊
	public static final int screenHeightTest=480;//¥˙∏’æ˜ø√πı∞™´◊

	public static boolean isInitFlag = false;
	public static void initConst(int screenWidth,int screenHeight)
	{
		
		if(isInitFlag == true){
			return;
		}
		
		Screen_Width=screenWidth;
		Screen_Height=screenHeight;
		wRatio=screenWidth/(float)screenWidthTest;
		hRatio=screenHeight/(float)screenHeightTest;
	
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

}
