package gameObject.tower;

import android.R.integer;
import android.util.Log;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.LLACoordinate;
import com.metaio.sdk.jni.Vector3d;

public class Tank extends moveObject{
	
	private float atk;
	public float getAtk() {
		return atk;
	}

	public void setAtk(float atk) {
		this.atk = atk;
	}
//-----------------------------------have face angle----------------------------------------------------------------
	public Tank(IGeometry model,int coordinateSystemID, Vector3d size,float x, float y, float moveSpeed,
			float moveAngle,float health ,float faceAngle, float atk) {
		super(model, coordinateSystemID,size, x, y, moveSpeed, moveAngle, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
	}

	public Tank(IGeometry model,int coordinateSystemID,  Vector3d size,float x, float y,float health,float faceAngle,float atk) {
		super(model, coordinateSystemID, size,x, y, health,faceAngle);
		// TODO Auto-generated constructor stub
		this.atk = atk;
	}
//---------------------------------------------------------------------------------------------------------------------------
	public Tank(IGeometry model,int coordinateSystemID, Vector3d size, Vector3d position, float moveSpeed,
			float moveAngle,float health , float faceAngle,float atk) {
		super(model, coordinateSystemID,size, position, moveSpeed, moveAngle, health,faceAngle);
		// TODO Auto-generated constructor stub
		this.atk = atk;
	}

	public Tank(IGeometry model,int coordinateSystemID,  Vector3d size, Vector3d position,float health,float faceAngle,float atk) {
		super(model, coordinateSystemID, size,position, health,faceAngle);
		// TODO Auto-generated constructor stub
		this.atk = atk;
	}
	
	//----------------------------------------- none face angle----------------------------------------------------------
	public Tank(IGeometry model,int coordinateSystemID, Vector3d size,float x, float y, float moveSpeed,
			float moveAngle,float health ,float atk) {
		super(model, coordinateSystemID,size, x, y, moveSpeed, moveAngle, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
	}
	//---------------------------------------------------------------------------------------------------------------------------
	public Tank(IGeometry model,int coordinateSystemID,  Vector3d size,float x, float y,float health,float atk) {
		super(model, coordinateSystemID, size,x, y, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
	}
	public Tank(IGeometry model,int coordinateSystemID, Vector3d size, Vector3d position, float moveSpeed,
			float moveAngle,float health ,float atk) {
		super(model, coordinateSystemID,size, position, moveSpeed, moveAngle, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
	}

	public Tank(IGeometry model,int coordinateSystemID,  Vector3d size, Vector3d position,float health,float atk) {
		super(model, coordinateSystemID, size,position, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
	}
	
	//--------------------------------construct end---------------------------------------------------//
	public void attack(object other){
		if(other.getHealth()-atk<=0){
			other.dead();
			other.setHealth((float) 0.0);
		}else{
			other.setHealth(other.getHealth()-atk);
		}		
	}

}
