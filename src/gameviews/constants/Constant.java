<<<<<<< HEAD

package gameviews.constants;

import tw.edu.ttu.pre_towerdefender.R;

public class Constant {
	
	
	
	// for the screen settings
=======
package gameviews.constants;

public class Constant {
	
>>>>>>> 6d599d2851b62d93586158a59ac5fb78b0864450
	public static int Screen_Width;//�ù����e��
	public static int Screen_Height;//�ù�������
	
	public static float wRatio;//�A�����ù����Y����
	public static float hRatio;
	
	public static final float screenRatio800x480=1.667f;
	public static final float screenRatio854x480=1.779f;
<<<<<<< HEAD

	public static final int screenWidthTest=800;//���վ��ù��e��
	public static final int screenHeightTest=480;//���վ��ù�����

	public static boolean isInitFlag = false;
	public static void initConst(int screenWidth,int screenHeight)
	{
		
=======
	
	//2D�ɭ��۾A���̮ɪ��`��
		public static final int screenWidthTest=800;//���վ��ù��e��
		public static final int screenHeightTest=480;//���վ��ù�����
		
	public static boolean isInitFlag = false;//��k�O�_�Q�I�s�L���ЧӦ�	
	public static void initConst(int screenWidth,int screenHeight)
	{
		//�Y�G��k�w�g����L�A�h���A����
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
		Screen_Width=screenWidth;//�ù����ؤo
		Screen_Height=screenHeight;
		//�A�����ù����Y����
		wRatio=screenWidth/(float)screenWidthTest;
		hRatio=screenHeight/(float)screenHeightTest;
		
		//�Хܤ�k�w�Q����L
		isInitFlag = true;
	}
	
	
>>>>>>> 6d599d2851b62d93586158a59ac5fb78b0864450

}
