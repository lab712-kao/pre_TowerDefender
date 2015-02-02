package tw.edu.ttu.pre_towerdefender;


import gameObject.tower.Soldier;

import java.io.IOException;
import java.util.Random;

import gameSystem.gameObjectSystem.ObjectHandler;
import java.util.Timer;
import java.util.TimerTask;
import gameviews.constants.Constant;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfoReader;
import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;

import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.TrackingValues;
import com.metaio.sdk.jni.TrackingValuesVector;

import com.metaio.sdk.jni.Vector2di;
import com.metaio.sdk.jni.Vector3d;


import com.metaio.tools.SystemInfo;
import com.metaio.tools.io.AssetsManager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Camera.CameraInfo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.view.SurfaceView;

import android.view.SurfaceHolder;

public class GameScreenActivity extends ARViewActivity { 

	private Soldier tanks;

	public int bound = 100;
	public int levelCost = 0;
	public int cost = 0;
	public int flag_bound = 1;
	public int playerChange = 0; 
	public ImageView num_hun, num_ten, num_one, 
					 levelnum, levelnum_hun, levelnum_ten, levelnum_single,
					 bound_hun;
	public ImageButton levelUP, musicBtn;
	private MediaPlayer Player;
	private ObjectHandler OBHL = null;
	private MetaioSDKCallbackHandler mMetaioHandler;
	private ObjectInfoReader OIR = null;
	private String tankModel = null;
	private Vector3d enTran = new Vector3d();
	private IGeometry enTower;
	private IGeometry ttt;
	private IGeometry margin1, margin2;
	private SurfaceHolder myHolder;
	int[] trackingState = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//10
	private Button setEnTowerBtn, OKBtn;

	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen);
		mMetaioHandler = new MetaioSDKCallbackHandler();	
		setAutoFocus();
		Player = new MediaPlayer();
		Player = MediaPlayer.create(GameScreenActivity.this, R.raw.sample);
		Player.start();
	}
	
/* This parts for dynamic time present, 
 * and use timer to control the blood of the armies*/
	
 	private Timer timer = null;
	Handler timerhandler = new Handler();

	public void setAutoFocus(){

		timer = new Timer(false);
		
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				timerhandler.post(new Runnable(){
					@Override
					public void run() {
						costAndBound();
					}
				});
			}
		}, 0, 300);//end of timer schedule
	}
	
	public void timerStop(View v){
		flag_bound ^= 1;		
	}
	public void costAndBound(){
		
		if(flag_bound == 1 && cost < bound) {
			cost += bound/75;
			if(cost > bound) cost = cost - cost%bound;
		}
		num_hun=(ImageView)findViewById(R.id.cost_hun);
		num_ten=(ImageView)findViewById(R.id.cost_ten);
		num_one=(ImageView)findViewById(R.id.cost_single);		
		bound_hun=(ImageView)findViewById(R.id.bound_hun);
		
	 	
	 	num_hun.setImageResource(Constant.images[cost/100%10]);	
	 	num_ten.setImageResource(Constant.images[cost/10%10]);	
	 	num_one.setImageResource(Constant.images[cost%10]);	
	 	
	 	bound_hun.setImageResource(Constant.images[bound/100%10]);		
	}	
	public void levelUpOnclick(View v){
		if(cost - Constant.Levelup_cost[levelCost] >=0)
			if(levelCost<7){
			
				levelnum=(ImageView)findViewById(R.id.levelnum);
				levelnum_hun=(ImageView)findViewById(R.id.level_hun);
				levelnum_single=(ImageView)findViewById(R.id.level_single);
				levelnum_ten=(ImageView)findViewById(R.id.level_ten);
				levelnum_single.setImageResource(R.drawable.num_zero);
				bound+=100;
				cost-=Constant.Levelup_cost[levelCost];
				levelCost++;
				showNum(Constant.levels,Constant.Levelup_cost[levelCost]/100%10, Constant.Levelup_cost[levelCost]/10%10, levelCost+1,
						levelnum_hun, levelnum_ten, levelnum);
			}
			else{
				levelnum_hun.setImageResource(0);
				levelnum_ten.setImageResource(0);
				levelnum_single.setImageResource(0);
				levelUP=(ImageButton)findViewById(R.id.levelUP);
				levelUP.setEnabled(false);	
			}
			
	}
	public void tankOnclick(View v){
		if(cost - 100 >=0 && tankModel!=null&&OBHL!=null)
		{
			cost-=100;
			OBHL.creatObject("tank",  tankModel , 1);
		}
	}
	public void domOnclick(View v){
		if(cost - 50 >=0)
		{
			cost-=50;
		}
	}
	
	public void peanutsOnclick(View v){
		if(cost - 150 >=0)
		{
			cost-=150;
		}
	}
	public void MusicControl(View v){
		musicBtn = (ImageButton)findViewById(R.id.musicBtn);
		if(playerChange == 0){
			playerChange = 1;
			musicBtn.setImageResource(R.drawable.button_soundoff_game);
			Player.pause();
		}
		else{
			playerChange = 0;
			musicBtn.setImageResource(R.drawable.button_sound_game);
			Player.start();
			
			
		}
			
	}
	public void MusicOff(View v){
		
			Player.pause();
		
	}
	public void showNum( int[] x , 
						 int index_hun, int index_ten, int index_single, 
						 ImageView v, ImageView v2, ImageView v3){		
		v.setImageResource(x[index_hun]);
		v2.setImageResource(x[index_ten]);
		v3.setImageResource(x[index_single]);
	}
/* end of the part referent to the time and button*/
	
	
	
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
		this.metaioSDK.startCamera(cameraIndex, 1280, 760);

		
	}
		
	@Override
	protected int getGUILayout() {
	
		return R.layout.activity_game_screen;
	}

	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
		// TODO Auto-generated method stub
		
		return mMetaioHandler;
	}

	private void setListener(){
		
		setEnTowerBtn = (Button)findViewById(R.id.setEnTowerBtn);
		OKBtn = (Button)findViewById(R.id.OKBtn);
		
		setEnTowerBtn.setOnClickListener(new OnClickListener(){

			private int randInt(int min, int max) {
				Random rand = new Random();
				
				int randomNum = rand.nextInt((max - min) + 1) + min;

			    return randomNum;
			}
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Vector3d co1 = new Vector3d();
				Vector3d co2 = new Vector3d();
				if(trackingState[0] == 1 && trackingState[2] == 1 && trackingState[3] == 1) {
					boolean success;
					TrackingValues theRelation1 = new TrackingValues();
					TrackingValues theRelation2 = new TrackingValues();
					
					success = metaioSDK.getCosRelation(1, 3, theRelation1);
					if(success) {
						co1 = theRelation1.getTranslation();
					}
					
					success = metaioSDK.getCosRelation(1, 4, theRelation2);
					if(success) {
						co2 = theRelation2.getTranslation();
					}
					
					
					int maxX = 0, maxY = 0, minX = 0, minY = 0;
					
					if(co1.getX() > co2.getX()) {
						maxX = (int)co1.getX();
						minX = (int)co2.getX();
					}
					else {
						maxX = (int)co2.getX();
						minX = (int)co1.getX();
					}
					
					if(co1.getY() > co2.getY()) {
						maxY = (int)co1.getY();
						minY = (int)co2.getY();
					}
					else {
						maxY = (int)co2.getY();
						minY = (int)co1.getY();
					}
					
					enTran.setX(randInt(minX, maxX)*-1);
					enTran.setY(randInt(minY, maxY)*-1);
					enTran.setZ(0);
					
					Log.d("pre-dd", "x = " + enTran.getX() + " y = " + enTran.getY() + " z = " + enTran.getZ());
					
					enTower.setTranslation(enTran);
					enTower.setVisible(true);
					
				}
			}});
		OKBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				OBHL.setEnermyTowerPosition(enTran);
			}
			
		});
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
			tankModel = AssetsManager.getAssetPath("tankNorm.obj");
			String chessModel = AssetsManager.getAssetPath("chess.obj");
			String marginPic1 = AssetsManager.getAssetPath("side.png");
			String marginPic2 = AssetsManager.getAssetPath("side2.png");
			//tanks = new Tank(metaioSDK.createGeometry(tankModel), 1, new Vector3d(35.0f), new Vector3d(0, 0, 0), 100,  100, 20);
			
			ttt = metaioSDK.createGeometry(towerModel1);
			ttt.setCoordinateSystemID(1);
			ttt.setScale(10.0f);
			ttt.setTranslation(new Vector3d(0, 0, 0));
			ttt.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
			
			enTower = metaioSDK.createGeometry(towerModel1);
			enTower.setCoordinateSystemID(1);
			enTower.setScale(10.0f);
			enTower.setTranslation(new Vector3d(10, 10, 0));
			enTower.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
			enTower.setVisible(false);
			

			margin1 = metaioSDK.createGeometryFromImage(marginPic1);
			margin1.setCoordinateSystemID(3);
			margin1.setScale(5.0f);
			margin1.setTranslation(new Vector3d(0, 0, 0));
			
			margin2 = metaioSDK.createGeometryFromImage(marginPic2);
			margin2.setCoordinateSystemID(4);
			margin2.setScale(5.0f);
			margin2.setTranslation(new Vector3d(0, 0, 0));
			
			//T = 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			OIR = new ObjectInfoReader(this.getAssets().open("unitinfo.xml") );
			String tankModel = AssetsManager.getAssetPath("tankNorm.obj");	
			OBHL = new ObjectHandler(metaioSDK, mSurfaceView, OIR);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("moveStart", e
					+ "<<<<<<<exception++++++++++++++++++++++++++++");
			
		}
		
//		Log.d("moveStart",OIR.getSoldierInfoByName("tank").getAtk()+"++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		OIR.getSoldierInfoByName("soldier")
		setListener();
	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		// TODO Auto-generated method stub	
		Log.d("moveStart","+++++++++++++++++++++++click+++++++++++++++++++");
		String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
		OBHL.creatObject("tank", tankModel,1, 0, 0);
	}
	
	@Override
	public void onDrawFrame() {
		super.onDrawFrame();
	}
	
	@Override
	public void onSurfaceCreated() {
		super.onSurfaceCreated();
		myHolder = this.mSurfaceView.getHolder();
		Log.d("surface create", "createeeeeeeeeeeeeeeeee");
	}
	
	final class MetaioSDKCallbackHandler extends IMetaioSDKCallback
	{
		
		@Override
		public void onTrackingEvent(TrackingValuesVector trackingValues)
		{
			for (int i=0; i<trackingValues.size(); i++)
			{
				final TrackingValues v = trackingValues.get(i);
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
	
}
