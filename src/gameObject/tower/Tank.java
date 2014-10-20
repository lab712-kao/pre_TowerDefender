package gameObject.tower;

import android.R.integer;
import android.os.SystemClock;
import android.util.Log;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.LLACoordinate;
import com.metaio.sdk.jni.Vector3d;

public class Tank extends MovingObject implements Runnable{
	
	private float atk;
	private float atkRange;
	public float getAtk() {
		return atk;
	}

	public void setAtk(float atk) {
		this.atk = atk;
	}
//-----------------------------------have face angle----------------------------------------------------------------
	public Tank(IGeometry model,int coordinateSystemID, Vector3d size,float x, float y, float moveSpeed,
			float moveAngle,float health ,float faceAngle, float atk, float atkRang) {
		super(model, coordinateSystemID,size, x, y, moveSpeed, moveAngle, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}

	public Tank(IGeometry model,int coordinateSystemID,  Vector3d size,float x, float y,float health,float faceAngle,float atk,float atkRang) {
		super(model, coordinateSystemID, size,x, y, health,faceAngle);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}
//---------------------------------------------------------------------------------------------------------------------------
	public Tank(IGeometry model,int coordinateSystemID, Vector3d size, Vector3d position, float moveSpeed,
			float moveAngle,float health , float faceAngle,float atk,float atkRang) {
		super(model, coordinateSystemID,size, position, moveSpeed, moveAngle, health,faceAngle);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}

	public Tank(IGeometry model,int coordinateSystemID,  Vector3d size, Vector3d position,float health,float faceAngle,float atk,float atkRang) {
		super(model, coordinateSystemID, size,position, health,faceAngle);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}
	
	//----------------------------------------- none face angle----------------------------------------------------------
	public Tank(IGeometry model,int coordinateSystemID, Vector3d size,float x, float y, float moveSpeed,
			float moveAngle,float health ,float atk,float atkRang) {
		super(model, coordinateSystemID,size, x, y, moveSpeed, moveAngle, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}
	//---------------------------------------------------------------------------------------------------------------------------
	public Tank(IGeometry model,int coordinateSystemID,  Vector3d size,float x, float y,float health,float atk,float atkRang) {
		super(model, coordinateSystemID, size,x, y, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}
	public Tank(IGeometry model,int coordinateSystemID, Vector3d size, Vector3d position, float moveSpeed,
			float moveAngle,float health ,float atk,float atkRang) {
		super(model, coordinateSystemID,size, position, moveSpeed, moveAngle, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}

	public Tank(IGeometry model,int coordinateSystemID,  Vector3d size, Vector3d position,float health,float atk,float atkRang) {
		super(model, coordinateSystemID, size,position, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}
	
	//--------------------------------construct end---------------------------------------------------//
	public void attack(Object other){
		if(other.getHealth()-atk<=0){
			other.dead();
			other.setHealth((float) 0.0);
		}else{
			other.setHealth(other.getHealth()-atk);
		}
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!isDead()){
			SystemClock.sleep(1000);
			Log.d("moveStart","++++++++++++++++++++++++start+++++++++++++++++++++");
			move();
		}
	}
}
