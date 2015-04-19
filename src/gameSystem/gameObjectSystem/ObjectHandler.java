package gameSystem.gameObjectSystem;

import com.metaio.sdk.MetaioSurfaceView;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.Vector3d;

import gameObject.tower.MovingObject;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfoReader;

public class ObjectHandler  {

//	private Vector<Object> objects;
	private DoubleArrayList<MovingObject> objects;
	private ObjectInfoReader OIR;
	private ObjectMover OBMO,OBME;
	private IMetaioSDKAndroid sdk;
	private MetaioSurfaceView view;
	private ObjectAttacker OBAO,OBAE;
	private int enermyBlood = 100;
	private double screenZoom =1.0f;
	
	public ObjectHandler(IMetaioSDKAndroid sdk, MetaioSurfaceView view,ObjectInfoReader OIR) {
		this.OIR = OIR;
		this.sdk = sdk;
		this.view = view;
		objects = new DoubleArrayList<MovingObject>();
		OBMO = null;
		OBME = null;
//		OBMO = new ObjectMover(IDType.O,objects);
//		OBMO.addPosition(new Vector3d(20, 75, 0));
//		OBME = new ObjectMover(IDType.E,objects);
//		OBAO = new ObjectAttacker(IDType.O, objects);
//		OBAE = new ObjectAttacker(IDType.E, objects);	
	}

	public boolean creatObject(String name, String modelPath,
			int coordinateSystemID, int x, int y) {
		
		view.queueEvent(new ObjectCreator(sdk, modelPath, coordinateSystemID,
				objects, OIR.getSoldierInfoByName(name),x, y));
		return false;

	}

	public boolean creatObject(String name, String modelPath,
			int coordinateSystemID, int x, int y,IDType id) {
		
		view.queueEvent(new ObjectCreator(sdk, modelPath, coordinateSystemID,
				objects, OIR.getSoldierInfoByName(name),x, y,id));
		return false;

	}
	
	public boolean creatObject(String name, String modelPath,
			int coordinateSystemID) {
		view.queueEvent(new ObjectCreator(sdk, modelPath, coordinateSystemID,
				objects,OIR.getSoldierInfoByName(name)));
		return false;

	}
	public boolean creatObject(String name, String modelPath,
			int coordinateSystemID,IDType id) {
		view.queueEvent(new ObjectCreator(sdk, modelPath, coordinateSystemID,
				objects,OIR.getSoldierInfoByName(name),id));
		return false;

	}

	public void setEnermyTowerPosition(Vector3d enTower) {
		if(OBMO == null){
			OBMO = new ObjectMover(IDType.O,objects,new Vector3d(0f),enTower);
			//OBMO.addPosition(new Vector3d(20, 0, 0));
		}
		if(OBME == null){
			OBME = new ObjectMover(IDType.E,objects,enTower, new Vector3d(0f));
			//OBMO.addPosition(new Vector3d(20, 0, 0));
		}
//		OBMO.setEnTowerPos(enTower);
	}
	
	public void addPosition(Vector3d pos) {
		if(OBMO != null) {
			OBMO.addPosition(pos);
		}
		if(OBME != null) {
			OBME.addPosition(pos);
		}
		
	}
	public void zoom(double screenZoom) {
		this.screenZoom = screenZoom;
	}
	
	public int getEnermyBlood() {
		if(OBMO != null) {
			enermyBlood = OBMO.getEnBlood();
		}
		return enermyBlood;
	}
}
