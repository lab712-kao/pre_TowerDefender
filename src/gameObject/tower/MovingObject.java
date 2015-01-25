package gameObject.tower;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public abstract class MovingObject extends DefaultObject {
	
	protected float moveSpeed;
	protected float moveAngle;
	protected Vector3d lastTimePos = null;
	
	public MovingObject(IGeometry model, int coordinateSystemID, Vector3d size,float x, float y, float faceAngle,float moveSpeed,float moveAngle,float health) {
		super(model, coordinateSystemID,size,x, y, health,faceAngle);
		this.moveSpeed = moveSpeed;
		this.moveAngle = moveAngle;
		// TODO Auto-generated constructor stub
	}
	public MovingObject(IGeometry model, int coordinateSystemID, Vector3d size, float x, float y, float moveSpeed,float moveAngle,float health) {
		super(model, coordinateSystemID,size,x, y, health);
		this.moveSpeed = moveSpeed;
		this.moveAngle = moveAngle;
		// TODO Auto-generated constructor stub
	}
	public MovingObject(IGeometry model,int coordinateSystemID, Vector3d size, float x, float y,float health, float faceAngle) {
		super(model, coordinateSystemID,size,x, y, health,faceAngle);
		this.moveSpeed = 10;
		this.moveAngle =  (float)(Math.PI/2);
		// TODO Auto-generated constructor stub
	}
	public MovingObject(IGeometry model, int coordinateSystemID,  Vector3d size, float x, float y,float health) {
		super(model, coordinateSystemID,size,x, y, health);
		this.moveSpeed = 10;
		this.moveAngle =  (float)(Math.PI/2);
		// TODO Auto-generated constructor stub
	}
	public MovingObject(IGeometry model, int coordinateSystemID, Vector3d size,Vector3d position, float faceAngle,float moveSpeed,float moveAngle,float health) {
		super(model, coordinateSystemID,size,position,health,faceAngle);
		this.moveSpeed = moveSpeed;
		this.moveAngle = moveAngle;
		// TODO Auto-generated constructor stub
	}
	public MovingObject(IGeometry model, int coordinateSystemID, Vector3d size, Vector3d position, float moveSpeed,float moveAngle,float health) {
		super(model, coordinateSystemID,size,position, health);
		this.moveSpeed = moveSpeed;
		this.moveAngle = moveAngle;
		// TODO Auto-generated constructor stub
	}
	public MovingObject(IGeometry model,int coordinateSystemID, Vector3d size, Vector3d position,float health, float faceAngle) {
		super(model, coordinateSystemID,size,position, health,faceAngle);
		this.moveSpeed = 10;
		this.moveAngle =  (float)(Math.PI/2);
		// TODO Auto-generated constructor stub
	}
	public MovingObject(IGeometry model, int coordinateSystemID,  Vector3d size, Vector3d position, float health) {
		super(model, coordinateSystemID,size,position, health);
		this.moveSpeed = 10;
		this.moveAngle =  (float)(Math.PI/2);
		// TODO Auto-generated constructor stub
	}
	
	public float getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	
	public void move() {
		float speedX = (float)(moveSpeed*Math.sin(moveAngle)+position.getX());
		float speedY = (float)(moveSpeed*Math.cos(moveAngle)+position.getY());
		
		lastTimePos =  new Vector3d(position.getX(), position.getY(), 0);
		position = new Vector3d(speedX, speedY, 0);
		model.setTranslation(position);
	}
	public void moveToXY(float x,float y){
		lastTimePos =  new Vector3d(position.getX(), position.getY(), 0);
		position = new Vector3d(x, y, 0);
		model.setTranslation(position);
	}
	public void back(){
		
		if(lastTimePos != null){
			model.setTranslation(lastTimePos);
		}else{
			
		}
	}
}
