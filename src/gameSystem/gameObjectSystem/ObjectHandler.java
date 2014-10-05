package gameSystem.gameObjectSystem;

import java.util.Vector;

import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioSurfaceView;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDK;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.Vector3d;

import android.R.integer;
import android.R.string;
import gameObject.tower.Object;
import gameObject.tower.Tank;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfoReader;

public class ObjectHandler {
	
	public Vector<Object> objects;
	public ObjectInfoReader OIR;
	public IMetaioSDKAndroid sdk;
	public MetaioSurfaceView view;
	
	public ObjectHandler(IMetaioSDKAndroid sdk, MetaioSurfaceView view){
		OIR =new ObjectInfoReader();
		this.sdk = sdk;
		this.view = view;
	}
	public boolean creatObject(String name,IGeometry model, int coordinateSystemID, int x, int y) {
		
		view.equals(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//objects.add(new Tank(model, coordinateSystemID, new Vector3d(3.5f), x, y,3.5f , 3.5f, 100, 100, 3.5f));

			}
			
		});
		
		return false;
		
	}
		
}
