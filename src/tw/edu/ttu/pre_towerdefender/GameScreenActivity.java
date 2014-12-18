package tw.edu.ttu.pre_towerdefender;

import gameObject.tower.Tank;

import gameObject.tower.tower;
import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import gameSystem.gameObjectSystem.ObjectHandler;
import gameviews.constants.Constant;

import java.io.IOException;



import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfoReader;


import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;

import com.metaio.sdk.jni.IMetaioSDKCallback;

import com.metaio.sdk.jni.TrackingValuesVector;
import com.metaio.sdk.jni.Vector2di;


import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.Layout;
import com.metaio.tools.SystemInfo;
import com.metaio.tools.io.AssetsManager;

import android.R.bool;
import android.R.layout;
import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.media.MediaPlayer;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameScreenActivity extends ARViewActivity { 
	private IGeometry tower_1, tower_2,tank;
	
	Tank tanks;
	private tower T;

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
	//private ObjectHandler OBHL;
	private MetaioSDKCallbackHandler mMetaioHandler;
	private ObjectInfoReader OIR = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
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
		}, 0, 100);//end of timer schedule
	}
	
	public void timerStop(View v){
		flag_bound ^= 1;		
	}
	public void costAndBound(){
		
		if(flag_bound == 1 && cost < bound)
			cost += 1;
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
		if(cost - 100 >=0)
		{
			cost-=100;
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
		Vector2di mCameraResolution = metaioSDK.startCamera(cameraIndex, 1280, 760);
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

	
	@Override
	protected void loadContents() {
		try {
			AssetsManager.extractAllAssets(this, true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setRequestedOrientation(0);
		
		//OBHL = new ObjectHandler(metaioSDK, mSurfaceView);
		
		try {
			String trackingConfigFile = AssetsManager.getAssetPath("TrackingData_MarkerlessFast.xml");
			boolean result = metaioSDK.setTrackingConfiguration(trackingConfigFile); 
			MetaioDebug.log("Tracking data loaded: " + result);
			
			Log.d("trackingData", "Tracking data loaded: " + result);
			
			String towerModel1 = AssetsManager.getAssetPath("saintriqT3DS.obj");
			String towerModel2 = AssetsManager.getAssetPath("FIRSTtower.obj");
			String tankModel = AssetsManager.getAssetPath("tankNorm.obj");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			OIR = new ObjectInfoReader(this.getAssets().open("unitinfo.xml") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("moveStart", e
					+ "<<<<<<<exception++++++++++++++++++++++++++++");
			
		}
		String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
		//Log.d("moveStart",tankModel + "<<<<<<<tankModel ++++++++++++++++++++++++++++");	
		OBHL = new ObjectHandler(metaioSDK, mSurfaceView,OIR);
		OBHL.creatObject("tank",  tankModel , 1);
		Log.d("moveStart",OIR.getSoldierInfoByName("tank").getAtk()+"++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		OIR.getSoldierInfoByName("soldier")
		
	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		// TODO Auto-generated method stub	
		Log.d("moveStart","+++++++++++++++++++++++click+++++++++++++++++++");


		String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
		//.creatObject("qwe", tankModel,1, 0, 0);


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
	
	final class MetaioSDKCallbackHandler extends IMetaioSDKCallback
	{
		
		@Override
		public void onTrackingEvent(TrackingValuesVector trackingValues)
		{
			//Log.d("pre-dd", "Tracking Event");
//			for (int i=0; i<trackingValues.size(); i++)
//			{
//				final TrackingValues v = trackingValues.get(i);
//				//MetaioDebug.log("Tracking state for COS "+v.getCoordinateSystemID()+" is "+v.getState());
//				//System.out.println("Tracking state for COS "+v.getCoordinateSystemID()+" is "+v.getState());
//				Log.d("pre-dd", "Tracking state for COS "+v.getCoordinateSystemID()+" is "+v.getState());
//			}
		}

	}

	//button event(call handler new igometry <- this )
}
