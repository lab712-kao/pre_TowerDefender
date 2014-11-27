package tw.edu.ttu.pre_towerdefender;

import gameObject.tower.IGObject;
import gameObject.tower.IGSoldier;
import gameObject.tower.Tank;
import gameObject.tower.tower;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executor;
import gameSystem.gameObjectSystem.ObjectHandler;
import java.io.IOException;
import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDK;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.TrackingValues;
import com.metaio.sdk.jni.TrackingValuesVector;
import com.metaio.sdk.jni.Vector2d;
import com.metaio.sdk.jni.Vector2di;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.SystemInfo;
import com.metaio.tools.io.AssetsManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
//ya
import android.view.SurfaceHolder;

public class GameScreenActivity extends ARViewActivity{
	private IGeometry tower_1, tower_2,tank;
	
	Tank tanks;
	private tower T;
	private ObjectHandler OBHL;
	private MetaioSDKCallbackHandler mMetaioHandler;
	private Vector3d enTran = new Vector3d();
	private IGeometry enTower;
	private IGeometry ttt;
	private IGeometry aChess, bChess;
	private SurfaceHolder myHolder;
	int[] trackingState = {0, 0, 0, 0};
	private MyView drawView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		mMetaioHandler = new MetaioSDKCallbackHandler();
		//drawView = new MyView(this);
		/*
		setContentView(drawView);
		myHolder = drawView.getHolder();
		*/
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		mMetaioHandler = null;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_screen, menu);
		return true;
	}
	
	@Override
	protected void startCamera() {
		// Select the back facing camera by default
		final int cameraIndex = SystemInfo.getCameraIndex(CameraInfo.CAMERA_FACING_BACK);
		this.mCameraResolution = metaioSDK.startCamera(cameraIndex, 1280, 760);
	}
		
	@Override
	protected int getGUILayout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
		// TODO Auto-generated method stub
		
		return mMetaioHandler;
	}

	@Override
	protected void loadContents() {
		try {
			AssetsManager.extractAllAssets(this, true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setRequestedOrientation(0);
		
		if(myHolder == null) Log.d("holder", "holder is null - 1");
		else Log.d("holder", "holder here is not null!?");
		
		//OBHL = new ObjectHandler(metaioSDK, mSurfaceView);
		
		try {
			String trackingConfigFile = AssetsManager.getAssetPath("TrackingData_MarkerlessFast.xml");
			boolean result = metaioSDK.setTrackingConfiguration(trackingConfigFile); 
			MetaioDebug.log("Tracking data loaded: " + result);
			
			Log.d("trackingData", "Tracking data loaded: " + result);
			
			String towerModel1 = AssetsManager.getAssetPath("saintriqT3DS.obj");
			String towerModel2 = AssetsManager.getAssetPath("FIRSTtower.obj");
			String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
			String chessModel = AssetsManager.getAssetPath("chess.obj");
			//tanks = new Tank(metaioSDK.createGeometry(tankModel), 1, new Vector3d(35.0f), new Vector3d(0, 0, 0), 100,  100, 20);
			
			ttt = metaioSDK.createGeometry(tankModel);
			ttt.setCoordinateSystemID(1);
			ttt.setScale(35.0f);
			ttt.setTranslation(new Vector3d(0, 0, 0));
			ttt.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
			
			enTower = metaioSDK.createGeometry(towerModel1);
			enTower.setCoordinateSystemID(1);
			enTower.setScale(20.5f);
			enTower.setTranslation(new Vector3d(10, 10, 0));
			enTower.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
			enTower.setVisible(false);
			
			aChess = metaioSDK.createGeometry(chessModel);
			aChess.setCoordinateSystemID(3);
			aChess.setScale(10.0f);
			aChess.setTranslation(new Vector3d(0, 0, 0));
			aChess.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
			
			bChess = metaioSDK.createGeometry(chessModel);
			bChess.setCoordinateSystemID(4);
			bChess.setScale(10.0f);
			bChess.setTranslation(new Vector3d(0, 0, 0));
			bChess.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
			
			//T = 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		// TODO Auto-generated method stub	
		Log.d("moveStart","+++++++++++++++++++++++click+++++++++++++++++++");
		String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
		OBHL.creatObject("tank", tankModel,1, 0, 0);
		
//		new Thread(tanks).start() ;
//		this.mSurfaceView.queueEvent(new Runnable() {
//			
//			String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub 
//				new Thread(new Tank(metaioSDK.createGeometry(tankModel), 1, new Vector3d(35.0f), new Vector3d(0, 0, 0), 100,  100, 20)).start();
//			}
//		});

//		tanks.move();
	}
	
	@Override
	public void onDrawFrame() {
		super.onDrawFrame();
		
		/*
		Vector2d a, b;
		Paint paint = new Paint();
		Canvas canvas = new Canvas();
		try {
			canvas = myHolder.lockCanvas();
			a = metaioSDK.getScreenCoordinatesFrom3DPosition(3, new Vector3d(0, 0, 0));
			b = metaioSDK.getScreenCoordinatesFrom3DPosition(4, new Vector3d(0, 0, 0));
			
			paint.setColor(0x00ff00ff);
			paint.setAntiAlias(true);
			paint.setStyle(Paint.Style.STROKE);
	        
	//		Log.i("screen point", "a = " + a.getX() + ", " + a.getY());
	//		Log.i("screen point", "b = " + b.getX() + ", " + b.getY());
			
			canvas.drawLine(a.getX(), a.getY(), a.getX(), b.getY(), paint);
			canvas.drawLine(a.getX(), a.getY(), b.getX(), a.getY(), paint);
			canvas.drawLine(b.getX(), b.getY(), a.getX(), b.getY(), paint);
			canvas.drawLine(b.getX(), b.getY(), b.getX(), a.getY(), paint);
			
			//this.mSurfaceView.draw(canvas);
			myHolder.unlockCanvasAndPost(canvas);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
	@Override
	public void onSurfaceCreated() {
		super.onSurfaceCreated();
		myHolder = this.mSurfaceView.getHolder();
		Log.d("surface create", "createeeeeeeeeeeeeeeeee");
		/*;
		myHolder.addCallback(this.mSurfaceView);
		
		myHolder = this.mSurfaceView.getHolder()
		Log.d("onSC", "onSCCCC");
		
		Thread testThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				while(i < 5000) {
					try {
						Canvas cc = myHolder.lockCanvas();
						Paint paint = new Paint();
					
						paint.setColor(0x00ff00ff);
						paint.setAntiAlias(true);
						paint.setStyle(Paint.Style.STROKE);
					
						if(cc == null) Log.d("canvas", "canvas null");
						else {
							cc.drawCircle(300, 300, 50, paint);
							myHolder.unlockCanvasAndPost(cc);
						}
						
						try {
							Thread.sleep(50);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					
					i++;
				}
			}
			
		});
		
		testThread.start();
		
		Canvas cc = myHolder.lockCanvas();
		Paint paint = new Paint();
		
		paint.setColor(0x00ff00ff);
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		
		if(cc == null) Log.d("canvas", "canvas null");
		else {
			cc.drawCircle(300, 300, 50, paint);
			myHolder.unlockCanvasAndPost(cc);
		}*/
	}
	
	final class MetaioSDKCallbackHandler extends IMetaioSDKCallback
	{
		
		@Override
		public void onTrackingEvent(TrackingValuesVector trackingValues)
		{
			Vector3d co1 = new Vector3d();
			Vector3d co2 = new Vector3d();
			
			new Thread(new testThread()).start();
			
			//Log.d("pre-dd", "Tracking Event");
			for (int i=0; i<trackingValues.size(); i++)
			{
				final TrackingValues v = trackingValues.get(i);
				//MetaioDebug.log("Tracking state for COS "+v.getCoordinateSystemID()+" is "+v.getState());
				//System.out.println("Tracking state for COS "+v.getCoordinateSystemID()+" is "+v.getState());
				Log.d("pre-dd", "Tracking state for COS "+v.getCoordinateSystemID()+" is "+v.getState());
				if(v.getState() == com.metaio.sdk.jni.ETRACKING_STATE.ETS_FOUND) {
					Log.d("pre-dd", "found found");
					trackingState[v.getCoordinateSystemID() - 1] = 1;
				}
				else if(v.getState() == com.metaio.sdk.jni.ETRACKING_STATE.ETS_LOST) {
					trackingState[v.getCoordinateSystemID() - 1] = 0;
				}
			}
			
			if(trackingState[2] == 1 && trackingState[3] == 1) {
				Log.d("pre-dd", "map prepare");
			}
			
			if(trackingState[0] == 1 && trackingState[2] == 1 && trackingState[3] == 1) {
				boolean success;
				Random ran = new Random();
				TrackingValues theRelation1 = new TrackingValues();
				TrackingValues theRelation2 = new TrackingValues();
				int x, y;
				
				success = metaioSDK.getCosRelation(1, 3, theRelation1);
				if(success) {
					co1 = theRelation1.getTranslation();
				}
				
				success = metaioSDK.getCosRelation(1, 4, theRelation2);
				if(success) {
					co2 = theRelation2.getTranslation();
				}
				
				Log.d("pre-dd", "co1.x = " + co1.getX() + " co1.y = " + co1.getY());
				Log.d("pre-dd", "co2.x = " + co2.getX() + " co2.y = " + co2.getY());
				
				x = (int) (co1.getX() - co2.getX());
				if(x < 0) x *= -1;
				
				y = (int) (co1.getY() - co2.getY());
				if(y < 0) y *= -1;
				
				int tmpx, tmpy;
				
				tmpx = (int) (co1.getX() < 0 ? co1.getX() : co2.getX());
				tmpy = (int) (co1.getY() < 0 ? co1.getY() : co2.getY());
				Log.d("pre-dd", "xx = " + (ran.nextInt(x) + tmpx) + "yy = " + (ran.nextInt(y) + tmpy));
				
				enTran.setX(ran.nextInt(x) + tmpx);
				enTran.setY(ran.nextInt(y) + tmpy);
				enTran.setZ(0);
				
				Log.d("pre-dd", "x = " + enTran.getX() + " y = " + enTran.getY() + " z = " + enTran.getZ());
				
				enTower.setTranslation(enTran);
				enTower.setVisible(true);
				
			}
			
		}

	}
	
	class testThread implements Runnable{
		  
        @Override
        public void run() {
            Canvas canvas = myHolder.lockCanvas();//get canvas
            Paint mPaint = new Paint();
            mPaint.setColor(Color.BLUE);
            Log.d("test", "run draw rect");
            if(canvas == null) {
            	Log.d("canvas null", "canvas is null!?");
            }
            else {
            	canvas.drawRect(new RectF(40,60,80,80), mPaint);
            	myHolder.unlockCanvasAndPost(canvas);//unlock canvas
            }
        }
    } 

	class MyView extends SurfaceView implements SurfaceHolder.Callback{  
		  
        SurfaceHolder holder;  
        public MyView(Context context) {  
            super(context);  
            holder = this.getHolder();//get holder  
            holder.addCallback(this);  
            setFocusable(true);  
            Log.d("test view", "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        }  
  
        @Override  
        public void surfaceChanged(SurfaceHolder holder, int format, int width,  
                int height) {  
              
        }  
  
        @Override  
        public void surfaceCreated(SurfaceHolder holder) {  
            new Thread(new MyThread()).start();
            Log.d("test thread start", "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        }  
  
        @Override  
        public void surfaceDestroyed(SurfaceHolder holder) {  
              
        }  
          
        
        class MyThread implements Runnable{
  
            @Override
            public void run() {
                Canvas canvas = holder.lockCanvas(null);//get canvas
                Paint mPaint = new Paint();  
                mPaint.setColor(Color.BLUE);  
                Log.d("test", "run draw rect");
                canvas.drawRect(new RectF(40,60,80,80), mPaint);  
                holder.unlockCanvasAndPost(canvas);//unlock canvas
                  
            }  
              
        }  
          
    }  
	//button event(call handler new igometry <- this )
}
