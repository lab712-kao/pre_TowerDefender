package tw.edu.ttu.pre_towerdefender;

import gameObject.tower.IGObject;
import gameObject.tower.IGSoldier;
import gameObject.tower.Tank;
import gameObject.tower.tower;
import java.io.IOException;
import java.util.concurrent.Executor;
import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.Vector2di;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.SystemInfo;
import com.metaio.tools.io.AssetsManager;
import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.util.Log;
import android.view.Menu;
//ya

public class GameScreenActivity extends ARViewActivity {
	private IGeometry tower_1, tower_2,tank;
	private IGObject soldier;
	
	Tank tanks;
	private tower T;
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
	
	/*
	@SuppressLint("InlinedApi")
	@Override
	public void onSurfaceCreated() {
		// Setup auto-focus
        Camera camera = IMetaioSDKAndroid.getCamera(this);
        Camera.Parameters params = camera.getParameters();
        params.setFocusMode(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        camera.setParameters(params);
	}
	
	@SuppressLint("InlinedApi")
	@Override
	public void onSurfaceChanged(int width, int height)
	{
	     Camera camera = IMetaioSDKAndroid.getCamera(this);
	     Parameters params = camera.getParameters();
	     params.setFocusMode(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
	     camera.setParameters(params);
	}
	*/
	
	@Override
	protected int getGUILayout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
		// TODO Auto-generated method stub
		return null;
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
		
		
		
		try {
			String trackingConfigFile = AssetsManager.getAssetPath("TrackingData_MarkerlessFast.xml");
			boolean result = metaioSDK.setTrackingConfiguration(trackingConfigFile); 
			MetaioDebug.log("Tracking data loaded: " + result);
			
			Log.d("trackingData", "Tracking data loaded: " + result);
			
			String towerModel1 = AssetsManager.getAssetPath("saintriqT3DS.obj");
			String towerModel2 = AssetsManager.getAssetPath("FIRSTtower.obj");
			String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
			
			if (tankModel != null) {
				tanks = new Tank(metaioSDK.createGeometry(tankModel), 1, new Vector3d(35.0f), new Vector3d(0, 0, 0), 100,  100, 20);

			}
			
			if(towerModel1 != null){
				tower_1 = metaioSDK.createGeometry(towerModel1);
				tower_1.setScale(new Vector3d(35.0f, 35.0f, 35.0f));
				tower_1.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
				tower_1.setCoordinateSystemID(2);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		// TODO Auto-generated method stub	
		Log.d("moveStart","+++++++++++++++++++++++click+++++++++++++++++++");

		new Thread(tanks).start() ;
		this.mSurfaceView.queueEvent(new Runnable() {
			
			String tankModel = AssetsManager.getAssetPath("tankNorm.obj");
			@Override
			public void run() {
				// TODO Auto-generated method stub 
				new Thread(new Tank(metaioSDK.createGeometry(tankModel), 1, new Vector3d(35.0f), new Vector3d(0, 0, 0), 100,  100, 20)).start();
			}
		});

//		tanks.move();
	}

	//button event(call handler new igometry <- this )
}
