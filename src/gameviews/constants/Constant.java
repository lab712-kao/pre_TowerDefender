<<<<<<< HEAD

package gameviews.constants;

import tw.edu.ttu.pre_towerdefender.R;

public class Constant {
	
	
	
	// for the screen settings
=======
package gameviews.constants;

public class Constant {
	
>>>>>>> 6d599d2851b62d93586158a59ac5fb78b0864450
	public static int Screen_Width;//螢幕的寬度
	public static int Screen_Height;//螢幕的高度
	
	public static float wRatio;//適應全螢幕的縮放比例
	public static float hRatio;
	
	public static final float screenRatio800x480=1.667f;
	public static final float screenRatio854x480=1.779f;
<<<<<<< HEAD

	public static final int screenWidthTest=800;//測試機螢幕寬度
	public static final int screenHeightTest=480;//測試機螢幕高度

	public static boolean isInitFlag = false;
	public static void initConst(int screenWidth,int screenHeight)
	{
		
=======
	
	//2D界面自適應屏時的常數
		public static final int screenWidthTest=800;//測試機螢幕寬度
		public static final int screenHeightTest=480;//測試機螢幕高度
		
	public static boolean isInitFlag = false;//方法是否被呼叫過的標志位	
	public static void initConst(int screenWidth,int screenHeight)
	{
		//若果方法已經執行過，則不再執行
>>>>>>> 6d599d2851b62d93586158a59ac5fb78b0864450
		if(isInitFlag == true){
			return;
		}
		
<<<<<<< HEAD
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
=======
		Screen_Width=screenWidth;//螢幕的尺寸
		Screen_Height=screenHeight;
		//適應全螢幕的縮放比例
		wRatio=screenWidth/(float)screenWidthTest;
		hRatio=screenHeight/(float)screenHeightTest;
		
		//標示方法已被執行過
		isInitFlag = true;
	}
	
	
>>>>>>> 6d599d2851b62d93586158a59ac5fb78b0864450

}
