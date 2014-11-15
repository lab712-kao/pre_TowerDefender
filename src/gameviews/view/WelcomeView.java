

package gameviews.view;

import gameviews.constants.Constant;
import tw.edu.ttu.pre_towerdefender.MainActivity;
import tw.edu.ttu.pre_towerdefender.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WelcomeView extends SurfaceView 
implements SurfaceHolder.Callback{
	
	MainActivity activity;
	Paint paint;
	
	Bitmap[] logos = new Bitmap[1];
	Bitmap currentLogo ;
int currentAlpha=0;//目前的不透明值
	
	int screenWidth=Constant.Screen_Width;//螢幕寬度
	int screenHeight=Constant.Screen_Height;//螢幕高度
	
	int sleepSpan=100;//動畫的時延ms
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

	public void onDraw(Canvas canvas){
		//繪制黑填充矩形清背景
		paint.setColor(Color.BLACK);//設定畫筆彩色
		paint.setAlpha(255);
		canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
		
		//進行平面貼圖
		if(currentLogo==null)return;
		paint.setAlpha(currentAlpha);		
		canvas.drawBitmap(currentLogo, currentX, currentY, paint);	
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		new Thread()
		{
			@SuppressLint("WrongCall") public void run()
			{
				for(Bitmap bm:logos)
				{
					currentLogo=bm;
					//計算圖片位置
					currentX=screenWidth/2-bm.getWidth()/2;
					currentY=screenHeight/2-bm.getHeight()/2;
					
					for(int i=255;i>-10;i=i-10)
					{//動態變更圖片的透明度值並不斷重繪	
						currentAlpha=i;
						if(currentAlpha<0)
						{
							currentAlpha=0;
						}
						SurfaceHolder myholder=WelcomeView.this.getHolder();
						Canvas canvas = myholder.lockCanvas();//取得畫布
						try{
							synchronized(myholder){
								onDraw(canvas);//繪制
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
							{//若是新圖片，多等待一會
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
				
				activity.gotoMainMenu();
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
