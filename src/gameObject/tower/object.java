package gameObject.tower;

import android.R.integer;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.Vector3d;

public abstract class object {
	protected IGeometry model = null;
	protected static float[]xy= new float[2];
	protected float health;
	protected float faceAngle;
	protected int coordinateSystemID;
	protected Vector3d size;
	protected Vector3d position;
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	//----------------------------------------- have face angle----------------------------------------------------------
	public object(IGeometry model,int coordinateSystemID ,Vector3d size,float x, float y, float health) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.coordinateSystemID = coordinateSystemID;
		this.size = size;
		this.position = new Vector3d(x,y,0);
		this.health = health;
		faceAngle = (float)(Math.PI/2);
		
		model.setCoordinateSystemID(coordinateSystemID);
		model.setScale(size);
		model.setTranslation(position);
		model.setRotation(new Rotation( faceAngle, 0.0f, 0.0f) );
		
	}
	
	public object(IGeometry model,int coordinateSystemID ,Vector3d size,Vector3d position,float health) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.coordinateSystemID = coordinateSystemID;
		this.size = size;
		this.position = position;
		this.health = health;
		faceAngle = (float)(Math.PI/2);
		
		model.setCoordinateSystemID(coordinateSystemID);
		model.setScale(size);
		model.setTranslation(position);
		model.setRotation(new Rotation( faceAngle, 0.0f, 0.0f) );
		
	}
	//----------------------------------------- none face angle----------------------------------------------------------
	public object(IGeometry model ,int coordinateSystemID,Vector3d size,float x, float y, float health, float faceAngel) {
		this.model = model;
		this.coordinateSystemID = coordinateSystemID;
		this.size = size;
		this.position = new Vector3d(x,y,0);
		this.health = health;
		this.faceAngle = faceAngle;
		
		model.setCoordinateSystemID(coordinateSystemID);
		model.setScale(size);
		model.setTranslation(position);
		model.setRotation(new Rotation( faceAngle, 0.0f, 0.0f) );
	}

	public object(IGeometry model ,int coordinateSystemID,Vector3d size,Vector3d position, float health, float faceAngel) {
		this.model = model;
		this.coordinateSystemID = coordinateSystemID;
		this.size = size;
		this.position = position;
		this.health = health;
		this.faceAngle = faceAngle;
		
		model.setCoordinateSystemID(coordinateSystemID);
		model.setScale(size);
		model.setTranslation(position);
		model.setRotation(new Rotation( faceAngle, 0.0f, 0.0f) );
	}
	public void  dead() {
		if(model!=null){
			model.delete();
			model = null;
		}		
	}
	public Boolean isDead(){
		if(model!=null)return false;
		else return true;
	}
}
