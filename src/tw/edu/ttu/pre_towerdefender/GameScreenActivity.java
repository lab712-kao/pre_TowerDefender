package tw.edu.ttu.pre_towerdefender;

import gameObject.tower.tank;
import gameObject.tower.tower;

import java.io.IOException;
import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.ArelCall;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.MetaioSDK;
import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.io.AssetsManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
//ya

public class GameScreenActivity extends ARViewActivity {
	private IGeometry tower_1, tower_2,tank;
	private tower T;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_screen, menu);
		return true;
	}

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
				
				tank = metaioSDK.createGeometry(tankModel);
				if(tank!=null){
					tank.setScale(new Vector3d(35.0f, 35.0f, 35.0f));
					tank.setRotation(new Rotation((float)(Math.PI/2), 0.0f, 0.0f));
					

					tank.setCoordinateSystemID(1);					
					
				}
				else {
					MetaioDebug.log(Log.ERROR, "Error loading geometry: " + tank);
				}
				
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
		
	}

}
