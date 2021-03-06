package gameObject.tower;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public class Soldier extends MovingObject{
	
	private float atk;
	private float atkRange;
	public float getAtk() {
		return atk;
	}

	public void setAtk(float atk) {
		this.atk = atk;
	}
public float getAtkRange() {
		return atkRange;
	}

	public void setAtkRange(float atkRange) {
		this.atkRange = atkRange;
	}

	//-----------------------------------have face angle----------------------------------------------------------------
	public Soldier(IGeometry model,int coordinateSystemID, Vector3d size,float x, float y, float moveSpeed,
			float moveAngle,float health ,float faceAngle, float atk, float atkRang) {
		super(model, coordinateSystemID,size, x, y, moveSpeed, moveAngle, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}

	public Soldier(IGeometry model,int coordinateSystemID,  Vector3d size,float x, float y,float health,float faceAngle,float atk,float atkRang) {
		super(model, coordinateSystemID, size,x, y, health,faceAngle);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}
//---------------------------------------------------------------------------------------------------------------------------
	public Soldier(IGeometry model,int coordinateSystemID, Vector3d size, Vector3d position, float moveSpeed,
			float moveAngle,float health , float faceAngle,float atk,float atkRang) {
		super(model, coordinateSystemID,size, position, moveSpeed, moveAngle, health,faceAngle);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}

	public Soldier(IGeometry model,int coordinateSystemID,  Vector3d size, Vector3d position,float health,float faceAngle,float atk,float atkRang) {
		super(model, coordinateSystemID, size,position, health,faceAngle);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}
	
	//----------------------------------------- none face angle----------------------------------------------------------
	public Soldier(IGeometry model,int coordinateSystemID, Vector3d size,float x, float y, float moveSpeed,
			float moveAngle,float health ,float atk,float atkRang) {
		super(model, coordinateSystemID,size, x, y, moveSpeed, moveAngle, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}
	//---------------------------------------------------------------------------------------------------------------------------
	public Soldier(IGeometry model,int coordinateSystemID,  Vector3d size,float x, float y,float health,float atk,float atkRang) {
		super(model, coordinateSystemID, size,x, y, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}
	public Soldier(IGeometry model,int coordinateSystemID, Vector3d size, Vector3d position, float moveSpeed,
			float moveAngle,float health ,float atk,float atkRang) {
		super(model, coordinateSystemID,size, position, moveSpeed, moveAngle, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}

	public Soldier(IGeometry model,int coordinateSystemID,  Vector3d size, Vector3d position,float health,float atk,float atkRang) {
		super(model, coordinateSystemID, size,position, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
		this.atkRange = atkRang;
	}
	
	//--------------------------------construct end---------------------------------------------------//
	public void attack(DefaultObject other){
		if(other.getHealth()-atk<=0){
			other.dead();
			other.setHealth((float) 0.0);
		}else{
			other.setHealth(other.getHealth()-atk);
		}
	}
}
