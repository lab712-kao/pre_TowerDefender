

package gameviews.view;

import gameviews.constants.Constant;
import tw.edu.ttu.pre_towerdefender.MainActivity;
import tw.edu.ttu.pre_towerdefender.R;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WelcomeView extends SurfaceView 
implements SurfaceHolder.Callback{
	
	MainActivity activity;
	Paint paint;
	
	Bitmap[] logos = new Bitmap[1];
	Bitmap currentLogo ;
int currentAlpha=0;//�ثe�����z���
	
	int screenWidth=Constant.Screen_Width;//�ù��e��
	int screenHeight=Constant.Screen_Height;//�ù�����
	
	int sleepSpan=100;//�ʵe���ɩ�ms
	int currentX;
	int currentY;
	public WelcomeView(MainActivity activity) {
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);
		
		paint = new Paint();
		paint.setAntiAlias(true);
		
		logos[0]=BitmapFactory.decodeResource(activity.getResources(), R.drawable.welcomepage); 
		
		for(int i=0;i<logos.length;i++){
			logos[i]=PictureLoadUtili.scaleToFitFullScreen(logos[i], Constant.wRatio, Constant.hRatio);
		}
	}

	@Override
	public void onDraw(Canvas canvas){
		//ø��¶�R�x�βM�I��
		paint.setColor(Color.BLACK);//�]�w�e���m��
		paint.setAlpha(255);
		canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
		
		//�i�業���K��
		if(currentLogo==null)return;
		paint.setAlpha(currentAlpha);		
		canvas.drawBitmap(currentLogo, currentX, currentY, paint);	
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		new Thread()
		{
			@Override
			@SuppressLint("WrongCall") public void run()
			{
				for(Bitmap bm:logos)
				{
					currentLogo=bm;
					//�p��Ϥ��m
					currentX=screenWidth/2-bm.getWidth()/2;
					currentY=screenHeight/2-bm.getHeight()/2;
					
					for(int i=255;i>-10;i=i-10)
					{//�ʺA�ܧ�Ϥ�z��׭Ȩä��_��ø	
						currentAlpha=i;
						if(currentAlpha<0)
						{
							currentAlpha=0;
						}
						SurfaceHolder myholder=WelcomeView.this.getHolder();
						Canvas canvas = myholder.lockCanvas();//��o�e��
						try{
							synchronized(myholder){
								onDraw(canvas);//ø��
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
						finally{
							if(canvas != null){
								myholder.unlockCanvasAndPost(canvas);
							}
						}						
						try
						{
							if(i==255)
							{//�Y�O�s�Ϥ�A�h���ݤ@�|
								Thread.sleep(1000);
							}
							Thread.sleep(sleepSpan);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				
				//activity.gotoGameActivity();
			}
		}.start();
		
	}


	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	

}
