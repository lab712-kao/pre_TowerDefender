package tw.edu.ttu.pre_towerdefender;

import gameObject.tower.Tank;
import gameObject.tower.tower;
import java.io.IOException;

import gameSystem.gameObjectSystem.ObjectHandler;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfoReader;


import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;

import com.metaio.sdk.jni.IMetaioSDKCallback;

import com.metaio.sdk.jni.TrackingValuesVector;
import com.metaio.sdk.jni.Vector2di;

import com.metaio.tools.SystemInfo;
import com.metaio.tools.io.AssetsManager;


import android.hardware.Camera.CameraInfo;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
//ya

public class GameScreenActivity extends ARViewActivity { 
	private IGeometry tower_1, tower_2,tank;
	
	Tank tanks;
	private tower T;
	private ObjectHandler OBHL;
	private MetaioSDKCallbackHandler mMetaioHandler;
	private ObjectInfoReader OIR = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		mMetaioHandler = new MetaioSDKCallbackHandler();		
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
		Vector2di mCameraResolution = metaioSDK.startCamera(cameraIndex, 1280, 760);
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
			Log.d("moveStart",e+ "<<<<<<<exception++++++++++++++++++++++++++++");		
			
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
//		String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
//		OBHL.creatObject("qwe", tankModel,1, 0, 0);
		
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
