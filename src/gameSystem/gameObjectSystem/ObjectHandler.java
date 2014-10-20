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
import gameObject.tower.Object;
import gameObject.tower.Tank;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfoReader;

public class ObjectHandler  {

	public Vector<Object> objects;
	public ObjectInfoReader OIR;
	public IMetaioSDKAndroid sdk;
	public MetaioSurfaceView view;
	public ObjectCreator test;
	public Handler creatorHandler;
	public ObjectHandler(IMetaioSDKAndroid sdk, MetaioSurfaceView view) {
		OIR = new ObjectInfoReader();
		this.sdk = sdk;
		this.view = view;
		creatorHandler = new Handler();

	}

	public boolean creatObject(String name, String modelPath,
			int coordinateSystemID, int x, int y) {
		view.queueEvent(new ObjectCreator(sdk, modelPath, coordinateSystemID,
				objects, x, y));
		return false;

	}

	public boolean creatObject(String name, String modelPath,
			int coordinateSystemID) {
		view.queueEvent(new ObjectCreator(sdk, modelPath, coordinateSystemID,
				objects));
		return false;

	}

}
