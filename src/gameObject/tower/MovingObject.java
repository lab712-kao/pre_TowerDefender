package gameObject.tower;

import java.util.ArrayList;

import com.metaio.sdk.jni.BoundingBox;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public abstract class MovingObject extends DefaultObject {
	
	protected float moveSpeed;
	protected float moveAngle;static 
	protected Vector3d lastTimePos = null;
	protected Vector3d nextPos = null;
	protected Boolean isStop = false;
	
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
		this.moveAngle =  (float)(Math.PI/2);
		// TODO Auto-generated constructor stub
	}
	public MovingObject(IGeometry model, int coordinateSystemID,  Vector3d size, Vector3d position, float health) {
		super(model, coordinateSystemID,size,position, health);
		this.moveSpeed = 3;
		this.moveAngle =  (float)(Math.PI/2);
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
	public Boolean checkCollision(DefaultObject aObject){
		if (aObject == null){
			return false;
		}else{
			Vector3d max = this.getModel().getBoundingBox().getMax().multiply(size.getX());
			Vector3d min = this.getModel().getBoundingBox().getMin().multiply(size.getX());
			ArrayList<Vector3d> cubeAsPoint = aObject.getModelBundingPointArrayList();
			boolean insideX = false;
			boolean insideY = false;
			boolean insideZ = false;

			for (Vector3d V : cubeAsPoint) {
				insideX = V.getX() <= max.getX()
						&& V.getX() >= min.getX();
				insideY = V.getY() <= max.getY()
						&& V.getY() >= min.getY();
				insideZ = V.getZ() <= max.getZ()
						&& V.getZ() >= min.getZ();
				if (insideX && insideY && insideZ) {
					return true;
				}
			}
			
		}
		return false;
	}
}
