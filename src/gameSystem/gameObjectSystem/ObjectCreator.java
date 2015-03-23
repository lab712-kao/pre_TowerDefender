package gameSystem.gameObjectSystem;

import gameObject.tower.MovingObject;
import gameObject.tower.Soldier;
import gameObject.tower.tower;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfo;
import android.R.integer;
import android.util.Log;
import com.metaio.sdk.jni.IGeometry;
import java.util.Vector;

import com.metaio.sdk.jni.BoundingBox;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.Vector3d;
public class ObjectCreator implements Runnable {


	private DoubleArrayList<MovingObject> objects;

	private IMetaioSDKAndroid sdk;
	private int x, y;
	private Vector3d pos = new Vector3d(0f);
	private ObjectInfo objectInfo;
	private int coordinateSystemID;
	private IDType id = IDType.O;

	private String modelPath;
	
	private final Vector3d SIZE = new Vector3d(1.0f);
	
	private final float DEFAULTANGLE = 0.0f;
	public final int SOLIDER=1,BUILDING=2;
	private int kinds=SOLIDER;

	//for tower
	public ObjectCreator(IMetaioSDKAndroid sdk, String modelPath,
			int coordinateSystemID, ObjectInfo objectInfo, int x, int y,int kinds){
		this.coordinateSystemID = coordinateSystemID;
		this.modelPath = modelPath;
		this.sdk = sdk;
		this.objectInfo = objectInfo;
		this.kinds = kinds;
		this.pos = new Vector3d(x,y,0f);
	}
	//for solider
	public ObjectCreator(IMetaioSDKAndroid sdk, String modelPath,
			int coordinateSystemID, DoubleArrayList<MovingObject> objects,
			ObjectInfo objectInfo) {
		this.coordinateSystemID = coordinateSystemID;
		this.modelPath = modelPath;
		this.sdk = sdk;
		this.objects = objects;
		this.objectInfo = objectInfo;
	}

	public ObjectCreator(IMetaioSDKAndroid sdk, String modelPath,
			int coordinateSystemID, DoubleArrayList<MovingObject> objects,
			ObjectInfo objectInfo, int x, int y) {
		this.coordinateSystemID = coordinateSystemID;
		this.modelPath = modelPath;
		this.sdk = sdk;
		this.objects = objects;
		this.objectInfo = objectInfo;
		
		this.pos = new Vector3d(x,y,0f);
	}

	public ObjectCreator(IMetaioSDKAndroid sdk, String modelPath,
			int coordinateSystemID, DoubleArrayList<MovingObject> objects,
			ObjectInfo objectInfo, IDType id) {
		this.coordinateSystemID = coordinateSystemID;
		this.modelPath = modelPath;
		this.sdk = sdk;
		this.objects = objects;
		this.objectInfo = objectInfo;
		this.id = id;
	}

	public ObjectCreator(IMetaioSDKAndroid sdk, String modelPath,
			int coordinateSystemID, DoubleArrayList<MovingObject> objects,
			ObjectInfo objectInfo, int x, int y, IDType id) {
		this.coordinateSystemID = coordinateSystemID;
		this.modelPath = modelPath;
		this.sdk = sdk;
		this.objects = objects;
		this.objectInfo = objectInfo;
		this.id = id;
		
		this.pos = new Vector3d(x,y,0f);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*
		 * boolean complete = false; complete = objects.add(new
		 * Tank(sdk.createGeometry(modelPath), coordinateSystemID, new
		 * Vector3d(3.5f), x, y, 3.5f, 3.5f, 100, 100, 3.5f));
		 * 
		 * // new Thread(new Tank(sdk.createGeometry(modelPath), 1, new
		 * Vector3d( // 35.0f), new Vector3d(0, 0, 0), 100, 100, 20)).start();
		 */
		Log.d("moveStart", "inCreator+++++++++++++++++++++++=");
		if(kinds == SOLIDER){
			objects.push(
				new Soldier(sdk.createGeometry(modelPath), coordinateSystemID,
						SIZE, pos, objectInfo.getSpeed(),
						DEFAULTANGLE, objectInfo.getHp(), objectInfo.getAtk(),
						objectInfo.getRange()), id);
		}else if(kinds == BUILDING){
			new tower(sdk.createGeometry(modelPath), coordinateSystemID, SIZE, pos, objectInfo.getHp());
		}
//		BoundingBox bb = objects.seek(0, IDType.O).getModelBoundingBox();
//		Log.d("creator", "maxX: "+bb.getMax().getX() + " , maxY: "+bb.getMax().getY());
//		Log.d("creator", "minX: "+bb.getMin().getX() + " , minY: "+bb.getMin().getY());
		// Tank(IGeometry model,int coordinateSystemID, Vector3d size, Vector3d
		// position, float moveSpeed,
		// float moveAngle,float health ,float atk,float atkRang)
		// new Tank(sdk.createGeometry(modelPath), coordinateSystemID,
		// new Vector3d(3.5f), x, y, 3.5f, objectInfo.getSpeed(),
		// objectInfo.getHp(), objectInfo.getAtk(), objectInfo.getRange());

	}
}
