package tw.edu.ttu.pre_towerdefender;

import java.io.IOException;
import java.util.Random;
import gameSystem.gameObjectSystem.IDType;
import gameSystem.gameObjectSystem.ObjectHandler;
import java.util.ArrayList;
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
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.SystemInfo;
import com.metaio.tools.io.AssetsManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.hardware.Camera.CameraInfo;
import android.media.AudioManager;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class GameScreenActivity extends ARViewActivity { 

	//private Soldier tanks;

	public int bound = 100;
	public int blood_evil = 100;
	public int levelCost = 0, blood = 0;
	public int cost = 0;
	public int flag_bound = 1, minus_flag = 0,minus_flag_evil = 0;
	public int playerChange = 0; 
	
	public ImageView num_hun, num_ten, num_one, level_text,
					 levelnum, levelnum_hun, levelnum_ten, levelnum_single,
					 bound_ten, bound_one, bound_hun, 
					 levelUP, desh, domdom, tank, peanut,
					 musicBtn, pause, power, blood_hun, blood_ten, blood_one,mask,setEnTowerBtn,OKBtn,cost_back;
	
	ProgressBar myProgressBar,evilProgressBar;
	private MediaPlayer Player;
	private ObjectHandler OBHL = null;
	private MetaioSDKCallbackHandler mMetaioHandler;
	private ObjectInfoReader OIR = null;
	private String tankModel = null;
	private Vector3d enTran = new Vector3d();
	private IGeometry enTower;
	private IGeometry ttt;
	private IGeometry margin1, margin2;
	private IGeometry target1, target2;
	private IGeometry spot1, spot2;
	private SurfaceHolder myHolder;
	int[] trackingState = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//10
	private boolean enPosReady = false;
	GameInterface GI = null;
	public ArrayList<ImageView> imageArray = new ArrayList<ImageView>();
	private EnermyProcess enProcess;
	private String domdomModel = null;
	private String peanutModel = null;
	private TextView de;
	private Vector3d range;
	private boolean gameStart = false;
	
	private boolean[] available = {true, true};
	private Vector3d[] addedTowerPosition = {new Vector3d(0, 0, 0), new Vector3d(0, 0, 0)};
	private int[] setPositionCount = {0, 0};
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen);
		mMetaioHandler = new MetaioSDKCallbackHandler();
		
		GI = new GameInterface(imageArray);
		setAutoFocus();
		Player = new MediaPlayer();
		
		Player = MediaPlayer.create(GameScreenActivity.this, R.raw.game_music_battle);
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
						initial();
						costAndBound();			
					}
				});
			}
		}, 0, 100);//end of timer schedule
	}
	
	public void timerStop(View v){
		flag_bound ^= 1;		
	}

	public void minus_blood( View v ){
		minus_flag = 1;
		if( blood<=100 )
			blood+=5;
		
		Log.d("position of enTower", "enTower: " + metaioSDK.getScreenCoordinatesFrom3DPosition(3, enTran).toString());
		
	}
	private int alertId1 = 0,alertId2= 0,alertId3= 0,alertId4=0;
	private SoundPool soundPool1,soundPool2,soundPool3,soundPool4;
	public void minus_blood_evil( View v ){
		
		minus_flag_evil = 1;
		if( blood_evil>=0 )
			blood_evil-=5;
		
	}
	public void initial(){
		
		Drawable draw=getResources().getDrawable(R.drawable.progress_style);
		Drawable draw_evil=getResources().getDrawable(R.drawable.progress_style_evil);
		myProgressBar = (ProgressBar)findViewById(R.id.progress_blood);
		evilProgressBar = (ProgressBar)findViewById(R.id.evil_blood);

		myProgressBar.setProgressDrawable(draw);
		evilProgressBar.setProgressDrawable(draw_evil);
		
		myProgressBar.getLayoutParams().width = (int) ((Constant.imageSize[18][0]-30)/2*Constant.wRatio);
		myProgressBar.getLayoutParams().height = (int) ((Constant.imageSize[18][1]/7)*Constant.hRatio);
		myProgressBar.setX((Constant.imageSize[18][2]+10)*Constant.wRatio);
		myProgressBar.setY((Constant.imageSize[18][3]+32)*Constant.hRatio);
		
		evilProgressBar.getLayoutParams().width = (int) ((Constant.imageSize[18][0]-30)/2*Constant.wRatio);
		evilProgressBar.getLayoutParams().height = (int) ((Constant.imageSize[18][1]/7)*Constant.hRatio);
		evilProgressBar.setX((Constant.imageSize[18][2]+20+(Constant.imageSize[18][0]-30)/2)*Constant.wRatio);
		evilProgressBar.setY((Constant.imageSize[18][3]+30)*Constant.hRatio);
		
		setEnTowerBtn = (ImageView)findViewById(R.id.setEnTowerBtn);
		OKBtn = (ImageView)findViewById(R.id.OKBtn);
		//OKBtn.setX(160);
		//OKBtn.setY(300);
		
		/*
		setEnTowerBtn.setX(Constant.wRatio - 50);
		setEnTowerBtn.setY(Constant.hRatio * 0.3f);
		
		OKBtn.setX(Constant.wRatio - 50);
		OKBtn.setY(Constant.hRatio * 0.4f);
		*/
		num_hun = (ImageView)findViewById(R.id.cost_hun);
		num_ten = (ImageView)findViewById(R.id.cost_ten);
		num_one = (ImageView)findViewById(R.id.cost_single);
		desh =    (ImageView)findViewById(R.id.desh);	
		bound_hun = (ImageView)findViewById(R.id.bound_hun);
		bound_ten = (ImageView)findViewById(R.id.bound_ten);
		bound_one = (ImageView)findViewById(R.id.bound_single);
		level_text =  (ImageView)findViewById(R.id.level);
		levelnum = (ImageView)findViewById(R.id.levelnum);		
		//levelnum_hun=(ImageView)findViewById(R.id.level_hun);
		//levelnum_ten=(ImageView)findViewById(R.id.level_ten);
		//levelnum_single=(ImageView)findViewById(R.id.level_single);
		levelUP=(ImageView)findViewById(R.id.levelUP);
		domdom = (ImageView)findViewById(R.id.dom);
		tank = (ImageView)findViewById(R.id.tank);
		peanut = (ImageView)findViewById(R.id.nut);	
		musicBtn = (ImageView)findViewById(R.id.musicBtn);
		pause  = (ImageView)findViewById(R.id.pause);
		power = (ImageView)findViewById(R.id.power);
		//blood_hun = (ImageView)findViewById(R.id.blood_hun);
		//blood_ten = (ImageView)findViewById(R.id.blood_ten);
		//blood_one = (ImageView)findViewById(R.id.blood_one);
		cost_back =  (ImageView)findViewById(R.id.cost_back);
		//de = (TextView) findViewById(R.id.textView1);
		
		imageArray.add(num_hun);
		imageArray.add(num_ten);
		imageArray.add(num_one);
		imageArray.add(desh);
		imageArray.add(bound_hun);
		imageArray.add(bound_ten);
		imageArray.add(bound_one);
		imageArray.add(level_text);
		imageArray.add(levelnum);
		imageArray.add(levelnum);
		imageArray.add(levelnum);
		imageArray.add(cost_back);
		/*imageArray.add(levelnum);
		 * imageArray.add(levelnum_single);
		imageArray.add(levelnum_ten);*/
		imageArray.add(levelUP);
		imageArray.add(domdom);
		imageArray.add(tank);
		imageArray.add(peanut);
		imageArray.add(musicBtn);
		imageArray.add(pause);
		imageArray.add(power);
		//imageArray.add(blood_hun);
		//imageArray.add(blood_ten);
		//imageArray.add(blood_one);
		
		for (int i = 0; i < 19; i++) {
			LayoutParams params = imageArray.get(i).getLayoutParams();
			params.width = (int)(Constant.imageSize[i][0] * Constant.wRatio);
	        params.height =(int)( Constant.imageSize[i][1] * Constant.hRatio);
	        imageArray.get(i).setLayoutParams(params);
	        imageArray.get(i).setX(Constant.imageSize[i][2]*Constant.wRatio);
	        imageArray.get(i).setY(Constant.imageSize[i][3]*Constant.hRatio);
	        
		}		
		
	}
	
	private void checkMarkerAdd(int coodID) {
		if(trackingState[coodID - 1] == 1 && trackingState[2] == 1) {
 			TrackingValues theRelation = new TrackingValues();
			Vector3d co = null;
			
			//Log.d("gameScreen addmarker", "get to check");
			
			boolean succ = metaioSDK.getCosRelation(coodID, 3, theRelation);
			if(succ) {
				co = theRelation.getTranslation();
				co.setZ(0);	
				
				//Log.d("gameScreen addmarker", "succ");
				
				if(co.getX() < range.getX()*-1 && co.getX() > 0 &&
				   co.getY() < range.getY()*-1 && co.getY() > 0){
					
					//Log.d("gameScreen addmarker", "in range");
					if(available[coodID - 1]) {
	 					if(setPositionCount[coodID - 1] == 0) {
	 						addedTowerPosition[coodID - 1] = new Vector3d(co);
	 						setPositionCount[coodID - 1]++;
			 			}
			 			else{
			 				if(Math.abs(co.getX() - addedTowerPosition[coodID - 1].getX())<=10 &&
			 				   Math.abs(co.getY() - addedTowerPosition[coodID - 1].getY())<=10) {
			 					setPositionCount[coodID - 1]++;
			 					//Log.d("gameScreen addmarker", "coodID: " + coodID + "add 1, " + setPositionCount[coodID - 1]);
			 				}else {
			 					setPositionCount[coodID - 1] = 0;
			 				}
			 			}
	 					
	 					if(setPositionCount[coodID - 1] >= 15) {
	 						Vector3d dontGC = new Vector3d(co);
	 						OBHL.addPosition(dontGC);
	 						if(coodID == 1) {
	 							target1.setVisible(true);
	 							target1.setTranslation(dontGC);
	 						}
	 						else {
	 							target2.setVisible(true);
	 							target2.setTranslation(dontGC);
	 						}
	 						available[coodID - 1] = false;
	 						addedTowerPosition[coodID - 1] = dontGC;
	 					}
					}
 				}else if(!available[coodID - 1]){
 					//Log.d("gameScreen addmarker", "out of range" + co.toString() + range.toString());
 					//delete this spot
 					//available = true
 					//Log.d("gameScreen addmarker", "out of range, [" + target1.getTranslation().toString() + "], [" + addedTowerPosition[coodID-1].toString() + "]");
 					OBHL.removePosition(addedTowerPosition[coodID - 1]);
 					available[coodID - 1] = true;
 					
 					if(coodID == 1) {
						target1.setVisible(false);
						target1.setTranslation(new Vector3d(0, 0, 0));
					}
					else {
						target2.setVisible(false);
						target2.setTranslation(new Vector3d(0, 0, 0));
					}
 				}
			}
 		}
	}
	
	int  myProgress = 0;
	public void costAndBound(){
		
		if(OBHL != null) {
			blood_evil = OBHL.getEnermyBlood();
			blood = OBHL.getMyBlood();
			
			if(blood_evil <=0 || blood <= 0){
				OBHL.endGame();
				timer.cancel();
				Intent it = new Intent();
				Bundle bu = new Bundle();
				bu.putString("KEY_WIN", (blood_evil<=0?"Player":"Enermy"));
				it.putExtras(bu);
				it.setClass(this, ResultActivit.class);
				startActivity(it);
			}
		}
	
	    myProgressBar.setProgress(blood);
	    evilProgressBar.setProgress(blood_evil);
	    
		if(flag_bound == 1 && cost < bound) {
			cost += 1;
			if(cost > bound) cost = cost - cost%bound;
		}
		if(flag_bound == 1 && cost < bound) {
			cost += 1;
			if(cost > bound) cost = cost - cost%bound;
		}
		if(cost >= Constant.Levelup_cost[levelCost]){
			levelUP.setImageResource(R.drawable.levelup);
			levelUP.setEnabled(true);
		}
		else
			levelUP.setImageResource(R.drawable.levelup_enable);
			
	 	num_hun.setImageResource(Constant.images[cost/100%10]);	
	 	num_ten.setImageResource(Constant.images[cost/10%10]);	
	 	num_one.setImageResource(Constant.images[cost%10]);	
	 	//levelnum_single.setImageResource(R.drawable.num_zero);
	 	bound_hun.setImageResource(Constant.images[bound/100%10]);	
	 	
	 	//blood_hun.setImageResource(Constant.images[blood/100%10]);
	 	//blood_ten.setImageResource(Constant.images[blood/10%10]);	
	 	//blood_one.setImageResource(Constant.images[blood%10]);
	 	
	 	//0123
	 	
	 	if(trackingState[2] == 1 && trackingState[3] == 1) {
			boolean success;
			TrackingValues theRelation = new TrackingValues();
			
			success = metaioSDK.getCosRelation(3, 4, theRelation);
			if(success) {
				range = new Vector3d(theRelation.getTranslation());
			}
		}
	 	
	 	if(gameStart) {
	 		checkMarkerAdd(1);
	 		checkMarkerAdd(2);
	 	}
	 	
	}	
	public void levelUpOnclick(View v){
		
			if(levelCost<7){
			
				
				if(cost - Constant.Levelup_cost[levelCost] >=0){
				bound+=100;
				cost-=Constant.Levelup_cost[levelCost];
				levelCost++;
				//showNum(Constant.levels,Constant.Levelup_cost[levelCost]/100%10, Constant.Levelup_cost[levelCost]/10%10, levelCost+1,
						//levelnum_hun, levelnum_ten, levelnum);
			}
			else{
				//levelnum_hun.setImageResource(0);
				//levelnum_ten.setImageResource(0);
				//levelnum_single.setImageResource(0);
				levelUP.setEnabled(false);	
			}
			}
			
	}
	public void tankOnclick(View v){
		
		if(cost - 100 >=0 && tankModel!=null&&OBHL!=null)
		{

			cost-=100;

			OBHL.creatObject("tank",  tankModel , 3);
		}
	}
	public void domOnclick(View v){
		if(cost - 50 >=0 && domdomModel!=null&&OBHL!=null)

		{
			cost-=50;

			OBHL.creatObject("moai",  domdomModel , 3);
		}
	}
	
	public void peanutsOnclick(View v){
		if(cost - 150 >=0 && peanutModel!=null&&OBHL!=null)
		{

			//cost-=150;

			OBHL.creatObject("tank",  peanutModel , 3);
		}
	}
	public void MusicControl(View v){
		musicBtn = (ImageView)findViewById(R.id.musicBtn);
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
		//Inflate the menu; this adds items to the action bar if it is present.
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
		
		setEnTowerBtn.setOnClickListener(new OnClickListener(){

			private int randInt(int min, int max) {
				Random rand = new Random();
				
				int randomNum = rand.nextInt((max - min) + 1) + min;

			    return randomNum;
			}
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Vector3d co = new Vector3d();
				
				if(trackingState[2] == 1 && trackingState[3] == 1) {
					boolean success;
					TrackingValues theRelation = new TrackingValues();
					
					success = metaioSDK.getCosRelation(3, 4, theRelation);
					if(success) {
						co = theRelation.getTranslation();
						range = new Vector3d(co);
					}
								
					enTran.setX(randInt(Math.abs((int)(co.getX()*0.6)), Math.abs((int)co.getX())));
					enTran.setY(randInt(Math.abs((int)(co.getY()*0.6)), Math.abs((int)co.getY())));
					enTran.setZ(0);
					
					//Log.d("pre-dd", "x = " + enTran.getX() + " y = " + enTran.getY() + " z = " + enTran.getZ());
					
					enTower.setTranslation(enTran);
					enTower.setVisible(true);
					enPosReady = true;
				}
			}});
		OKBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				OBHL.setEnermyTowerPosition(enTran);
				/*
				Vector3d tmp = new Vector3d(enTran);
				tmp.setY((float)(tmp.getY()*0.3));
				tmp.setX((float)(tmp.getX()*0.5));
				*/
				//OBHL.addPosition(new Vector3d(200, 250, 0));
				//OBHL.addPosition(new Vector3d(450, 125, 0));
				setEnTowerBtn.setVisibility(View.INVISIBLE);
				OKBtn.setVisibility(View.INVISIBLE);
				enProcess.startEnermyProcess();
				gameStart = true;
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
			String marginPic1 = AssetsManager.getAssetPath("side.png");
			String marginPic2 = AssetsManager.getAssetPath("side2.png");
			String spotPic = AssetsManager.getAssetPath("spot.png");
			String smallTower = AssetsManager.getAssetPath("playerTower.obj");
			tankModel = AssetsManager.getAssetPath("tankNorm.obj");
			domdomModel = AssetsManager.getAssetPath("domdombone2.obj");
			peanutModel = AssetsManager.getAssetPath("peanuts.obj");
			//tanks = new Tank(metaioSDK.createGeometry(tankModel), 1, new Vector3d(35.0f), new Vector3d(0, 0, 0), 100,  100, 20);
			
			
			ttt = metaioSDK.createGeometry(towerModel1);
			ttt.setCoordinateSystemID(3);
			ttt.setScale(15.0f);
			ttt.setTranslation(new Vector3d(0, 0, 0));
			ttt.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
			//ttt.setVisible(false);
			
			enTower = metaioSDK.createGeometry(towerModel1);
			enTower.setCoordinateSystemID(3);
			enTower.setScale(15.0f);
			enTower.setTranslation(new Vector3d(10, 10, 0));
			enTower.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
			enTower.setVisible(false);
			
			margin1 = metaioSDK.createGeometryFromImage(marginPic2);
			margin1.setCoordinateSystemID(3);
			margin1.setScale(5.0f);
			margin1.setTranslation(new Vector3d(0, 0, -5));
			
			margin2 = metaioSDK.createGeometryFromImage(marginPic1);
			margin2.setCoordinateSystemID(4);
			margin2.setScale(5.0f);
			margin2.setTranslation(new Vector3d(0, 0, -5));
			
			spot1 = metaioSDK.createGeometryFromImage(spotPic);
			spot1.setCoordinateSystemID(1);
			spot1.setScale(2.0f);
			spot1.setTranslation(new Vector3d(0, 0, -5));
			
			spot2 = metaioSDK.createGeometryFromImage(spotPic);
			spot2.setCoordinateSystemID(2);
			spot2.setScale(2.0f);
			spot2.setTranslation(new Vector3d(0, 0, -5));
			
			target1 = metaioSDK.createGeometry(smallTower);
			target1.setCoordinateSystemID(3);
			target1.setScale(10.0f);
			target1.setTranslation(new Vector3d(0, 0, 0));
			target1.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
			target1.setVisible(false);
			
			target2 = metaioSDK.createGeometry(smallTower);
			target2.setCoordinateSystemID(3);
			target2.setScale(10.0f);
			target2.setTranslation(new Vector3d(0, 0, 0));
			target2.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
			target2.setVisible(false);
			
			//Log.d("what is the Path", tankModel);
			enProcess = new EnermyProcess();
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


		String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
		//Log.d("moveStart",tankModel + "<<<<<<<tankModel ++++++++++++++++++++++++++++");	
		OBHL = new ObjectHandler(metaioSDK, mSurfaceView,OIR);
//		OBHL.creatObject("tank",  tankModel , 1);

		
//		Log.d("moveStart",OIR.getSoldierInfoByName("tank").getAtk()+"++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		OIR.getSoldierInfoByName("soldier")
		setListener();

	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		// TODO Auto-generated method stub	
		//Log.d("moveStart","+++++++++++++++++++++++click+++++++++++++++++++");
		//String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
		//OBHL.creatObject("tank", tankModel,1, 0, 0);
		/*
		int coodSysNum = geometry.getCoordinateSystemID();
		if(coodSysNum == 1 || coodSysNum == 2) {
			boolean success;
			TrackingValues theRelation = new TrackingValues();
			Vector3d co;
			
			success = metaioSDK.getCosRelation(coodSysNum, 3, theRelation);
			
			if(success) {
				co = theRelation.getTranslation();
				co.setZ(0);
				Vector3d fuckinGC = new Vector3d();
				fuckinGC.setX(co.getX());
				fuckinGC.setY(co.getY());
				fuckinGC.setZ(0);
				Log.d("ScreenAc onGeoTouch", "coodId: "+coodSysNum+", tran: " + fuckinGC);
				de.setText("CoodSysNum: "+coodSysNum+", Position: "+fuckinGC.toString()+", Tower Set.");
				OBHL.addPosition(fuckinGC);			
			}
		}
		*/
	}
	
	@Override
	public void onDrawFrame() {
		super.onDrawFrame();
	}
	
	@Override
	public void onSurfaceCreated() {
		super.onSurfaceCreated();
		myHolder = this.mSurfaceView.getHolder();
		//Log.d("surface create", "createeeeeeeeeeeeeeeeee");
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
					//Log.d("pre-dd", "found found");
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
	
	class EnermyProcess implements Runnable {

		Thread enermyThread = null;
		
		public EnermyProcess() {
			enermyThread = new Thread(this);
		}
		
		public void startEnermyProcess() {
			if(enermyThread != null) {
				enermyThread.start();
			}
		}
		
		public void stopEnermyProcess() {
			if(enermyThread != null) {
				enermyThread.interrupt();
			}
		}
		
		@Override
		public void run() {
			
			while(true) {
				try {
					Thread.sleep(2000);
					OBHL.creatObject("tank", tankModel, 3, (int)enTran.getX(), (int)enTran.getY(), IDType.E);
					//OBHL.creatObject("tank", tankModel, 3, IDType.E);
					Thread.sleep(5000);
					OBHL.creatObject("moai", domdomModel, 3, (int)enTran.getX(), (int)enTran.getY(), IDType.E);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
