package gameObject.tower;

import android.R.integer;
import android.util.Log;

import com.metaio.sdk.jni.BoundingBox;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.MetaioSDK;
import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.Vector3d;

public abstract class Object {
	protected IGeometry model = null;
	protected float health;
	protected float faceAngle;
	protected Vector3d position;

	//----------------------------------------- have face angle----------------------------------------------------------
	public Object(IGeometry model,int coordinateSystemID ,Vector3d size,float x, float y, float health) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.position = new Vector3d(x,y,0);
		this.health = health;
		faceAngle = (float)(Math.PI/2);
		
		model.setCoordinateSystemID(coordinateSystemID);
		model.setScale(size);
		model.setTranslation(position);
		model.setRotation(new Rotation( faceAngle, 0.0f, 0.0f) );
		
	}
	
	public Object(IGeometry model,int coordinateSystemID ,Vector3d size,Vector3d position,float health) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.position = position;
		this.health = health;
		faceAngle = (float)(Math.PI/2);
		
		model.setCoordinateSystemID(coordinateSystemID);
		model.setScale(size);
		model.setTranslation(position);
		model.setRotation(new Rotation( faceAngle, 0.0f, 0.0f) );
		
	}
	//----------------------------------------- none face angle----------------------------------------------------------
	public Object(IGeometry model ,int coordinateSystemID,Vector3d size,float x, float y, float health, float faceAngel) {
		this.model = model;
		this.position = new Vector3d(x,y,0);
		this.health = health;
		this.faceAngle = faceAngle;
		
		model.setCoordinateSystemID(coordinateSystemID);
		model.setScale(size);
		model.setTranslation(position);
		model.setRotation(new Rotation( faceAngle, 0.0f, 0.0f) );
	}

	public Object(IGeometry model ,int coordinateSystemID,Vector3d size,Vector3d position, float health, float faceAngel) {
		this.model = model;
		this.position = position;
		this.health = health;
		this.faceAngle = faceAngle;
		
		model.setCoordinateSystemID(coordinateSystemID);
		model.setScale(size);
		model.setTranslation(position);
		model.setRotation(new Rotation( faceAngle, 0.0f, 0.0f) );
	}
	
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	
	public IGeometry getModel() {
		return model;
	}
	public void setModel(IGeometry model) {
		this.model = model;
	}

	public float getModelFaceAngle() {
		return faceAngle;
	}
	public void setModelFaceAngle(float faceAngle) {
		this.faceAngle = faceAngle;
		model.setRotation(new Rotation( faceAngle, 0.0f, 0.0f) );
	}

	public Vector3d getModelPosition() {
		return position;
	}
	public void setModelPosition(Vector3d position) {
		this.position = position;
		model.setTranslation(position);
	}

	public int getModelCoordinateSystemID() {
		if(model == null) {
			Log.e("Object-getCood", "model is null");
			return 0;
		}
		else {
			return model.getCoordinateSystemID();
		}
	}
	public void setModelCoordinateSystemID(int coordinateSystemID) {
		if(model == null) {
			Log.e("object-setCood", "model is null");
			return;
		}
		else {
			model.setCoordinateSystemID(coordinateSystemID);
		}
		return ;
	}
	
	public BoundingBox getModelBoundingBox() {
		if(model == null) {
			Log.e("Object-getB-Box", "model is null");
			return null;
		}
		else {
			return model.getBoundingBox();
		}
	}
	
	public void  dead() {
		if(model != null){
			model.delete();
			model = null;
		}		
	}
	public Boolean isDead(){
		if(model != null)return false;
		else return true;
	}
}
