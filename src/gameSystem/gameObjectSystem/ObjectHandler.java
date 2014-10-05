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
import gameObject.tower.Object;
import gameObject.tower.Tank;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfoReader;

public class ObjectHandler {
	
	public Vector<Object> objects;
	public ObjectInfoReader OIR;
	public IMetaioSDKAndroid sdk;
	public MetaioSurfaceView view;
	public createObJectThread test;
	
	private class createObJectThread implements Runnable{
		
		public Vector<Object> objects;
		public ObjectInfoReader OIR;
		public IMetaioSDKAndroid sdk;
		public MetaioSurfaceView view;
		public int x,y;
		public int coordinateSystemID;
		public IGeometry model;
		String modelPath;
		
		public createObJectThread(IMetaioSDKAndroid sdk,String modelPath, int coordinateSystemID) {
			this.coordinateSystemID = coordinateSystemID;
			this.modelPath = modelPath;
			this.sdk = sdk;
		}
		
		public void setObjcetInfo(String name,IGeometry model, int coordinateSystemID, int x, int y,IMetaioSDKAndroid sdk, MetaioSurfaceView view,Vector<Object> objects){
			this.coordinateSystemID = coordinateSystemID;
			this.x = x;
			this.y = y;
			this.model = model;
			this.sdk = sdk;
			this.OIR = OIR;
			this.objects = objects;
			this.view = view;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//objects.add(new Tank(model, coordinateSystemID, new Vector3d(3.5f), x, y,3.5f , 3.5f, 100, 100, 3.5f));
			new Thread(new Tank(sdk.createGeometry(modelPath), 1, new Vector3d(35.0f), new Vector3d(0, 0, 0), 100,  100, 20)).start();
		}
	}
	
	public ObjectHandler(IMetaioSDKAndroid sdk, MetaioSurfaceView view){
		OIR =new ObjectInfoReader();
		this.sdk = sdk;
		this.view = view;
		
	}
	
	public boolean creatObject(String name,IGeometry model, int coordinateSystemID, int x, int y) {
//		objects.add(new Tank(model, coordinateSystemID, new Vector3d(3.5f), x, y,3.5f , 3.5f, 100, 100, 3.5f));
		test.setObjcetInfo(name, model, coordinateSystemID, x, y, sdk, view, objects);
		view.queueEvent(test);
		
		return false;
		
	}
	
	public boolean creatObject(String name,String modelPath, int coordinateSystemID, int x, int y) {
//		objects.add(new Tank(model, coordinateSystemID, new Vector3d(3.5f), x, y,3.5f , 3.5f, 100, 100, 3.5f));
		//test.setObjcetInfo(name, modelPath, coordinateSystemID, x, y, sdk, view, objects);
		//view.queueEvent(test);
		view.queueEvent(new createObJectThread(sdk, modelPath, coordinateSystemID));
		return false;
		
	}
		
}
