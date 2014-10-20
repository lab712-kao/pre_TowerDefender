package gameSystem.gameObjectSystem;

import gameObject.tower.Object;
import gameObject.tower.Tank;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfoReader;

import java.util.Vector;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.metaio.sdk.MetaioSurfaceView;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.Vector3d;

public class ObjectCreator implements Runnable {
	public Vector<Object> objects;
	public ObjectInfoReader OIR;
	public IMetaioSDKAndroid sdk;
	public int x, y;
	public int coordinateSystemID;
	
	
	String modelPath;

	public ObjectCreator(IMetaioSDKAndroid sdk, String modelPath,
			int coordinateSystemID, Vector<Object> objects) {
		this.coordinateSystemID = coordinateSystemID;
		this.modelPath = modelPath;
		this.sdk = sdk;
		this.objects = objects;
		this.x = 0;
		this.y = 0;

	}

	public ObjectCreator(IMetaioSDKAndroid sdk, String modelPath,
						 int coordinateSystemID, Vector<Object> objects, int x, int y) {
		this.coordinateSystemID = coordinateSystemID;
		this.modelPath = modelPath;
		this.sdk = sdk;
		this.objects = objects;
		this.x = x;
		this.y = y;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*
		boolean complete = false;
		complete = objects.add(new Tank(sdk.createGeometry(modelPath), coordinateSystemID,
				new Vector3d(3.5f), x, y, 3.5f, 3.5f, 100, 100, 3.5f));
		
        
       	*/
		// new Thread(new Tank(sdk.createGeometry(modelPath), 1, new Vector3d(
		// 35.0f), new Vector3d(0, 0, 0), 100, 100, 20)).start();
	}
}
