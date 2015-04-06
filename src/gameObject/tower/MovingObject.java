package gameObject.tower;

import gameSystem.gameObjectSystem.Hermite;
import gameSystem.gameObjectSystem.Path.PathPoint;

import java.util.ArrayList;

import android.R.integer;
import android.util.Log;

import com.metaio.sdk.jni.BoundingBox;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public abstract class MovingObject extends DefaultObject {
	
	protected float moveSpeed;
	protected float moveAngle;
	protected Vector3d lastTimePos = null;
	protected Vector3d nextPos = null;
	protected Boolean isStop = false,pointSet = false;
	protected PathPoint point = null;
	protected double t = 0.1;//part of Hermite
	public static final int NO_PATH_SET = 1, AT_END = 2, SUCC_MOVE = 3, STOPING = 4;
	
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
		lastTimePos = position;
		this.point = point;
	}
	
	//this function do smooth moving between point and point 
	public int moveByPathPoint(){
		
		if(isStop)
			return STOPING;
		
		if(point == null && !pointSet){
			pointSet = true;
			return NO_PATH_SET;//need to setPathPoint
		}else if(point == null && pointSet){
			return AT_END;
		}else if(point.isIgnore()||t==1){
			t=0.1f;
			lastTimePos = point.getPosition();
			point = point.getNextPoint();
			
			while(point!=null&&point.isIgnore()){
				lastTimePos = point.getPosition();
				point = point.getNextPoint();
				
			}
		}
		
		if(point!=null){//the last point is at position end ,so if null that mean 'at end'
			//lastTimePos = position;
			Vector3d localLastPos = position;
			
			Vector3d tmp = new Vector3d();
			tmp.setX(point.getPosition().getX() - lastTimePos.getX());
			tmp.setY(point.getPosition().getY() - lastTimePos.getY());
			tmp.setZ(0);
			Vector3d startAng = new Vector3d();
			
			if(Math.abs((tmp.getX())) > Math.abs((tmp.getY()))){
				startAng.setX(tmp.getX()/Math.abs(tmp.getX())*500);
				startAng.setY(tmp.getY()/Math.abs(tmp.getX())*500);
				startAng.setZ(0);
			}
			else {
				startAng.setX(tmp.getX()/Math.abs(tmp.getY())*500);
				startAng.setY(tmp.getY()/Math.abs(tmp.getY())*500);
				startAng.setZ(0);
			}
			Log.d("moveingObj move", "lastPos: "+lastTimePos.toString());
			Log.d("moveingObj move", "nextPos "+point.getPosition().toString());
			Log.d("moveingObj move", "startAng: "+startAng.toString());
			Log.d("moveingObj move", "endAng: "+new Vector3d( (float) Math.cos(point.getAngle())*500, (float) Math.sin(point.getAngle())*500, (float)0.0).toString());
			
			//Hermite p = (t,P1,P2,T1,T2)
			Vector3d p = Hermite.evalHermite(t, lastTimePos, point.getPosition(), 
				startAng, 
				new Vector3d( (float) Math.cos(point.getAngle())*500, (float) Math.sin(point.getAngle())*500, (float)0.0));//
			
			position = p;
//			Hermite.evalTangentVectorOfHermite(t, position, point.getPosition(), 
//					new Vector3d((float)Math.cos(faceAngle), (float)Math.sin(faceAngle), (float)0.0), new Vector3d( (float) Math.cos(point.getNextPoint().getAngle()), (float) Math.sin(point.getNextPoint().getAngle()), (float)0.0));//
				
			this.setModelFaceAngle((float) Math.atan2(p.getY()-localLastPos.getY(), p.getX()-localLastPos.getX()));
			model.setTranslation(p);
			//Log.d("point","{X:"+p.getX()+" Y:"+p.getY()+"}");
			t += 0.02;
			if(t > 1) t = 1;
			return SUCC_MOVE;
		}
				
		//if return that mean should get nextPoint
		return AT_END;
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
