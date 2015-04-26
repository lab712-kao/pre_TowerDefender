package gameObject.tower;

import gameSystem.gameObjectSystem.Hermite;
import gameSystem.gameObjectSystem.Path.PathPoint;

import android.util.Log;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public abstract class MovingObject extends DefaultObject {
	
	protected float moveSpeed;
	protected float moveAngle;
	protected Vector3d lastTimePos = null;
	protected Vector3d nextPos = null;
	protected Boolean isStop = false,pointSet = false;
	protected PathPoint point = null;

	protected double tInc = 0;

	protected double t = 0.0f;//part of Hermite
	protected double timeSlice = 0.02f;

	public static final int NO_PATH_SET = 1, AT_END = 2, SUCC_MOVE = 3, STOPING = 4;
	private Vector3d forCalVec = new Vector3d();
	private Vector3d lastPos = new Vector3d();
	private Vector3d startAng = new Vector3d();
	private Vector3d endAng = new Vector3d();
	private Vector3d rotAng = new Vector3d();
	
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
	
	private void calInc(){
		double x = position.getX()-point.getPosition().getX();
		double y = position.getY()-point.getPosition().getY();
		x = Math.abs(x);
		y = Math.abs(y);
		double len = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
		
		double sizeX = model.getBoundingBox().getMax().getX()*size.getX()-model.getBoundingBox().getMin().getX()*size.getX();
		double sizeY = model.getBoundingBox().getMax().getY()*size.getY()-model.getBoundingBox().getMin().getY()*size.getY();
		double size = sizeX>sizeY? sizeX:sizeY;
		size *= 0.8;
		tInc = 1/((len/size)*5);
	}
	
	public void setPathPoint(PathPoint point){
		lastTimePos = position;
		
		this.point = point;
		calInc();
	}
	
	//this function do smooth moving between point and point 
	public int moveByPathPoint(){
		
		if(isStop)
			return STOPING;
		
		if(point == null && !pointSet){
			pointSet = true;
			return NO_PATH_SET;//need to setPathPoint
		}else if(point == null && pointSet){
			Log.d("movingObject", "inin end");
			return AT_END;

		}else if(point.isIgnore()||t==1){
			//t=0.0f;

			if(!point.isIgnore()) {
				t = 0.0f;
				lastTimePos = point.getPosition();
			}
			else {
				t = 0.0f;
				lastTimePos = this.getModelPosition();
			}
			
			point = point.getNextPoint();
			Log.d("moveingObject move", "in the not end");
			while(point!=null&&point.isIgnore()){
				//lastTimePos = point.getPosition();
				point = point.getNextPoint();	
			}
			if(point!=null)
				calInc();
			
		}
		
		if(t == 0.0f && point != null) {
			forCalVec = new Vector3d();
			forCalVec.setX(point.getPosition().getX() - lastTimePos.getX());
			forCalVec.setY(point.getPosition().getY() - lastTimePos.getY());
			forCalVec.setZ(0);
			
			if(Math.abs((forCalVec.getX())) > Math.abs((forCalVec.getY()))){
				startAng.setX(forCalVec.getX()/Math.abs(forCalVec.getX())*500);
				startAng.setY(forCalVec.getY()/Math.abs(forCalVec.getX())*500);
				startAng.setZ(0);
			}
			else {
				startAng.setX(forCalVec.getX()/Math.abs(forCalVec.getY())*500);
				startAng.setY(forCalVec.getY()/Math.abs(forCalVec.getY())*500);
				startAng.setZ(0);
			}
			
			endAng.setX((float)(Math.cos(point.getAngle())*500));
			endAng.setY((float)(Math.sin(point.getAngle())*500));
			endAng.setZ(0.0f);
			t += tInc;
			
		}
		
		if(point!=null){//the last point is at position end ,so if null that mean 'at end'
			//lastTimePos = position;
			lastPos = position;
			/*
			Log.d("moveingObj move", "lastPos: "+lastTimePos.toString());
			Log.d("moveingObj move", "nextPos "+point.getPosition().toString());
			Log.d("moveingObj move", "startAng: "+startAng.toString());
			Log.d("moveingObj move", "endAng: "+endAng.toString());
			*/
			//Hermite p = (t,P1,P2,T1,T2)
			Vector3d p = Hermite.evalHermite(t, lastTimePos, point.getPosition(), 
				startAng, 
				endAng);//
			
			position = p;
//			Hermite.evalTangentVectorOfHermite(t, position, point.getPosition(), 
//					new Vector3d((float)Math.cos(faceAngle), (float)Math.sin(faceAngle), (float)0.0), new Vector3d( (float) Math.cos(point.getNextPoint().getAngle()), (float) Math.sin(point.getNextPoint().getAngle()), (float)0.0));//
				
			this.setModelFaceAngle((float) Math.atan2(p.getY()-lastPos.getY(), p.getX()-lastPos.getX()));
			if(this.getWalkShack() >= -1*(Math.PI/6) && this.getWalkShack() < 0) {
				this.setWalkShack(this.getWalkShack() + (float)(Math.PI/36));
			}
			else if(this.getWalkShack() <= 1*(Math.PI/6) && this.getWalkShack() >= 0){
				this.setWalkShack(this.getWalkShack() - (float)(Math.PI/36));
			}
			model.setTranslation(p);
			//Log.d("point","{X:"+p.getX()+" Y:"+p.getY()+"}");

			t += tInc;

//			t += timeSlice;

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
		int i;
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
	
	public void realDead() {
		if (model != null) {
			model.delete();
			model = null;
		}
		this.lastTimePos = null;
		this.nextPos = null;
		this.point = null;
		this.position = null;
		this.size = null;
	}
	
	public void attackAnimate(){ 
		IGeometry tmpModel = this.getModel();
		Vector3d tmpPos = tmpModel.getTranslation();
		/*
		Math.sin(faceAngle);
		Math.cos(faceAngle);
		*/
		if(tmpModel != null) {
			for(int i = 0; i < 25; i++) {
				Vector3d calPos = tmpModel.getTranslation();
				calPos.setX((float)(calPos.getX() + Math.cos(faceAngle)));
				calPos.setY((float)(calPos.getY() + Math.sin(faceAngle)));
				tmpModel.setTranslation(calPos);
			}
			
			for(int i = 0; i < 25; i++) {
				Vector3d calPos = tmpModel.getTranslation();
				calPos.setX((float)(calPos.getX() - Math.cos(faceAngle)));
				calPos.setY((float)(calPos.getY() - Math.sin(faceAngle)));
				tmpModel.setTranslation(calPos);
			}
		}
		
		tmpModel.setTranslation(tmpPos);
	}
}
