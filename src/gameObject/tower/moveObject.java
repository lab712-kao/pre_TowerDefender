package gameObject.tower;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public abstract class moveObject extends object {
	
	protected float moveSpeed;
	protected float moveAngle;
	public float getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	public moveObject(IGeometry model, int coordinateSystemID, Vector3d size,float x, float y, float faceAngle,float moveSpeed,float moveAngle,float health) {
		super(model, coordinateSystemID,size,x, y, health,faceAngle);
		this.moveSpeed = moveSpeed;
		this.moveAngle = moveAngle;
		// TODO Auto-generated constructor stub
	}
	public moveObject(IGeometry model, int coordinateSystemID, Vector3d size, float x, float y, float moveSpeed,float moveAngle,float health) {
		super(model, coordinateSystemID,size,x, y, health);
		this.moveSpeed = moveSpeed;
		this.moveAngle = moveAngle;
		// TODO Auto-generated constructor stub
	}
	public moveObject(IGeometry model,int coordinateSystemID, Vector3d size, float x, float y,float health, float faceAngle) {
		super(model, coordinateSystemID,size,x, y, health,faceAngle);
		this.moveSpeed = 1;
		this.moveAngle = 0;
		// TODO Auto-generated constructor stub
	}
	public moveObject(IGeometry model, int coordinateSystemID,  Vector3d size, float x, float y,float health) {
		super(model, coordinateSystemID,size,x, y, health);
		this.moveSpeed = 1;
		this.moveAngle = 0;
		// TODO Auto-generated constructor stub
	}
	public moveObject(IGeometry model, int coordinateSystemID, Vector3d size,Vector3d position, float faceAngle,float moveSpeed,float moveAngle,float health) {
		super(model, coordinateSystemID,size,position,health,faceAngle);
		this.moveSpeed = moveSpeed;
		this.moveAngle = moveAngle;
		// TODO Auto-generated constructor stub
	}
	public moveObject(IGeometry model, int coordinateSystemID, Vector3d size, Vector3d position, float moveSpeed,float moveAngle,float health) {
		super(model, coordinateSystemID,size,position, health);
		this.moveSpeed = moveSpeed;
		this.moveAngle = moveAngle;
		// TODO Auto-generated constructor stub
	}
	public moveObject(IGeometry model,int coordinateSystemID, Vector3d size, Vector3d position,float health, float faceAngle) {
		super(model, coordinateSystemID,size,position, health,faceAngle);
		this.moveSpeed = 1;
		this.moveAngle = 0;
		// TODO Auto-generated constructor stub
	}
	public moveObject(IGeometry model, int coordinateSystemID,  Vector3d size, Vector3d position, float health) {
		super(model, coordinateSystemID,size,position, health);
		this.moveSpeed = 1;
		this.moveAngle = 0;
		// TODO Auto-generated constructor stub
	}
	public void move() {
		float speedX = (float)(moveSpeed*Math.sin(moveAngle));
		float speedY = (float)(moveSpeed*Math.cos(moveAngle));
		
		this.position.setX(speedX+this.position.getX());
		this.position.setY(speedY+this.position.getY());

	}
}
