package gameviews.constants;

public class Constant {
	
	public static int Screen_Width;//螢幕的寬度
	public static int Screen_Height;//螢幕的高度
	
	public static float wRatio;//適應全螢幕的縮放比例
	public static float hRatio;
	
	public static final float screenRatio800x480=1.667f;
	public static final float screenRatio854x480=1.779f;
	
	//2D界面自適應屏時的常數
		public static final int screenWidthTest=800;//測試機螢幕寬度
		public static final int screenHeightTest=480;//測試機螢幕高度
		
	public static boolean isInitFlag = false;//方法是否被呼叫過的標志位	
	public static void initConst(int screenWidth,int screenHeight)
	{
		//若果方法已經執行過，則不再執行
		if(isInitFlag == true){
			return;
		}
		
		Screen_Width=screenWidth;//螢幕的尺寸
		Screen_Height=screenHeight;
		//適應全螢幕的縮放比例
		wRatio=screenWidth/(float)screenWidthTest;
		hRatio=screenHeight/(float)screenHeightTest;
		
		//標示方法已被執行過
		isInitFlag = true;
	}
	
	

}
