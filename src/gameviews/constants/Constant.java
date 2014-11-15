package gameviews.constants;

public class Constant {
	
	public static int Screen_Width;//�ù����e��
	public static int Screen_Height;//�ù�������
	
	public static float wRatio;//�A�����ù����Y����
	public static float hRatio;
	
	public static final float screenRatio800x480=1.667f;
	public static final float screenRatio854x480=1.779f;
	
	//2D�ɭ��۾A���̮ɪ��`��
		public static final int screenWidthTest=800;//���վ��ù��e��
		public static final int screenHeightTest=480;//���վ��ù�����
		
	public static boolean isInitFlag = false;//��k�O�_�Q�I�s�L���ЧӦ�	
	public static void initConst(int screenWidth,int screenHeight)
	{
		//�Y�G��k�w�g����L�A�h���A����
		if(isInitFlag == true){
			return;
		}
		
		Screen_Width=screenWidth;//�ù����ؤo
		Screen_Height=screenHeight;
		//�A�����ù����Y����
		wRatio=screenWidth/(float)screenWidthTest;
		hRatio=screenHeight/(float)screenHeightTest;
		
		//�Хܤ�k�w�Q����L
		isInitFlag = true;
	}
	
	

}
