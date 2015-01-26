package gameSystem.gameObjectSystem;

import java.util.Vector;

import junit.framework.Test;

import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioSurfaceView;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDK;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.Vector3d;

import android.R.integer;
import android.R.string;
import android.graphics.drawable.Drawable.Callback;
import android.os.Handler;
import android.util.Log;
import gameObject.tower.MovingObject;
import gameObject.tower.DefaultObject;
import gameObject.tower.Soldier;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfoReader;

public class ObjectHandler  {

//	private Vector<Object> objects;
	private DoubleArrayList<MovingObject> objects;
	private ObjectInfoReader OIR;
	private ObjectMover OBMO,OBME;
	private IMetaioSDKAndroid sdk;
	private MetaioSurfaceView view;
	private ObjectAttacker OBAO,OBAE;
	
	public ObjectHandler(IMetaioSDKAndroid sdk, MetaioSurfaceView view,ObjectInfoReader OIR) {
		this.OIR = OIR;
		this.sdk = sdk;
		this.view = view;
		objects = new DoubleArrayList<MovingObject>();
		OBMO = new ObjectMover(IDType.O,objects);
		OBME = new ObjectMover(IDType.E,objects);
		OBAO = new ObjectAttacker(IDType.O, objects);
		OBAE = new ObjectAttacker(IDType.E, objects);	
	}

	public boolean creatObject(String name, String modelPath,
			int coordinateSystemID, int x, int y) {
		if(name == null || modelPath == null  )
			return false;
		
		view.queueEvent(new ObjectCreator(sdk, modelPath, coordinateSystemID,
				objects, OIR.getSoldierInfoByName(name),x, y));
		return true;

	}

	public boolean creatObject(String name, String modelPath,
			int coordinateSystemID, int x, int y,IDType id) {
		if(name == null || modelPath == null  )
			return false;
		view.queueEvent(new ObjectCreator(sdk, modelPath, coordinateSystemID,
				objects, OIR.getSoldierInfoByName(name),x, y));
		return true;

	}
	
	public boolean creatObject(String name, String modelPath,
			int coordinateSystemID) {
		if(name == null || modelPath == null  )
			return false;
		view.queueEvent(new ObjectCreator(sdk, modelPath, coordinateSystemID,
				objects,OIR.getSoldierInfoByName(name)));
		return true;

	}
	public boolean creatObject(String name, String modelPath,
			int coordinateSystemID,IDType id) {
		if(name == null || modelPath == null  )
			return false;
		view.queueEvent(new ObjectCreator(sdk, modelPath, coordinateSystemID,
				objects,OIR.getSoldierInfoByName(name)));
		return true;

	}

	public void setEnermyTowerPosition(Vector3d enTower) {
		OBMO.setEnTowerPos(enTower);
	}
}
