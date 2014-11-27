package gameSystem.gameObjectSystem;

import gameObject.tower.Object;
import gameObject.tower.Tank;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfo;
import java.util.Vector;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.Vector3d;

public class ObjectCreator implements Runnable {
	private Vector<Object> objects;
	private IMetaioSDKAndroid sdk;
	private int x, y;
	private ObjectInfo objectInfo;
	private int coordinateSystemID;	
	private String modelPath;

	public ObjectCreator(IMetaioSDKAndroid sdk, String modelPath,
			int coordinateSystemID, Vector<Object> objects,ObjectInfo objectInfo) {
		this.coordinateSystemID = coordinateSystemID;
		this.modelPath = modelPath;
		this.sdk = sdk;
		this.objects = objects;
		this.x = 0;
		this.y = 0;
		this.objectInfo =objectInfo ;
	}

	public ObjectCreator(IMetaioSDKAndroid sdk, String modelPath,
			int coordinateSystemID, Vector<Object> objects,ObjectInfo objectInfo, int x, int y) {
		this.coordinateSystemID = coordinateSystemID;
		this.modelPath = modelPath;
		this.sdk = sdk;
		this.objects = objects;
		this.x = x;
		this.y = y;
		this.objectInfo = objectInfo;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*
		boolean complete = false;
		complete = objects.add(new Tank(sdk.createGeometry(modelPath), coordinateSystemID,
				new Vector3d(3.5f), x, y, 3.5f, 3.5f, 100, 100, 3.5f));
        
		// new Thread(new Tank(sdk.createGeometry(modelPath), 1, new Vector3d(
		// 35.0f), new Vector3d(0, 0, 0), 100, 100, 20)).start();
		*/
		objects.add(new Tank(sdk.createGeometry(modelPath), coordinateSystemID,
				new Vector3d(3.5f), x, y, 3.5f, objectInfo.getSpeed(), objectInfo.getHp(), objectInfo.getAtk(), objectInfo.getRange()));
		

	}
}
