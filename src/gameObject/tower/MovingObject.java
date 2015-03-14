package gameObject.tower;

import gameSystem.gameObjectSystem.Hermite;
import gameSystem.gameObjectSystem.Path.PathPoint;

import java.util.ArrayList;

import com.metaio.sdk.jni.BoundingBox;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public abstract class MovingObject extends DefaultObject {
	
	protected float moveSpeed;
	protected float moveAngle;
	protected Vector3d lastTimePos = null;
	protected Vector3d nextPos = null;
	protected Boolean isStop = false;
	protected PathPoint point = null;
	
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
		this.moveSpeed = 3;
		this.moveAngle =  (float)(Math.PI/2);
		// TODO Auto-generated constructor stub
	}
	public MovingObject(IGeometry model, int coordinateSystemID,  Vector3d size, float x, float y,float health) {
		super(model, coordinateSystemID,size,x, y, health);
		this.moveSpeed = 3;
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
		this.moveSpeed = 3;
		this.moveAngle =  (float)(180/Math.PI);
		// TODO Auto-generated constructor stub
	}
	public MovingObject(IGeometry model, int coordinateSystemID,  Vector3d size, Vector3d position, float health) {
		super(model, coordinateSystemID,size,position, health);
		this.moveSpeed = 3;
		this.moveAngle =  (float)(180/Math.PI);
		// TODO Auto-generated constructor stub
	}
	public Vector3d getNextPos(){
		return nextPos;
	}
	public void setNextPos(Vector3d nextPos){
		this.nextPos = nextPos;
	}
	public float getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	public Boolean getMoveStatus(){
		return isStop;	
	}
	public void stopMove(){
		isStop = true;
	}
	public void startMove(){
		isStop = false;
	}
	
	public void setPathPoint(PathPoint point){
		this.point = point;
	}
	
	//this function do smooth moving between point and point 
	public boolean moveByPathPoint(){
		
		double t = moveSpeed*Math.cos(faceAngle)*0.01;
		
		if(point == null){
			return false;//need to setPathPoint
		}
		else if(point.isIgnore() == true || point.getPosition().equals(position)){
			while(point.isIgnore() == true){
				lastTimePos = point.getPosition();
				point = point.getNextPoint();
				
			}
		}
		if(point.getNextPoint().getPosition()!=null){
			lastTimePos = position;
			Vector3d p = Hermite.evalHermite(t, position, point.getPosition(), 
				position.subtract(point.getPosition()), point.getNextPoint().getPosition().subtract(point.getPosition()));//
			position = p;
			model.setTranslation(p);
			return true;
		}
				
		//if return that mean should get nextPoint
		return false;
	}
	@Deprecated
	public void move() {
		float speedX = (float)(moveSpeed*Math.cos(faceAngle)+position.getX());
		float speedY = (float)(moveSpeed*Math.sin(faceAngle)+position.getY());
		
		if(isStop)
			return;

		
		lastTimePos =  new Vector3d(position.getX(), position.getY(), 0);
		position = new Vector3d(speedX, speedY, 0);
		model.setTranslation(position);
	}
	@Deprecated
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
