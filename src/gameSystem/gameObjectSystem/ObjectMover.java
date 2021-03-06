package gameSystem.gameObjectSystem;

import gameObject.tower.MovingObject;
import gameObject.tower.DefaultObject;
import gameSystem.gameObjectSystem.Path.PathPoint;

import java.io.InputStream;
import java.util.ArrayList;

import tw.edu.ttu.pre_towerdefender.R;

import android.media.AudioManager;
import android.media.SoundPool;
import android.net.rtp.AudioStream;
import android.util.Log;

import com.metaio.sdk.jni.BoundingBox;
import com.metaio.sdk.jni.Vector3d;

public class ObjectMover implements Runnable {

	private IDType TYPE,OTHERIDTYPE;
	private DoubleArrayList<MovingObject> objects = null;
	private Thread thread;
	private Vector3d enTowerPosition = new Vector3d(0, -1, 0);
//	private PathPlaner pathPlaner;
//	private ArrayList<Vector3d> path;
	private Path path;

	private Vector3d begin = new Vector3d(0, 0, 0);
	private Vector3d end = new Vector3d((float)100.0, (float)100.0, (float)0.0);
	
	private final Boolean _STOP = true;
	private int enermyBlood = 100;
	
	public ObjectMover(IDType type, DoubleArrayList<MovingObject> objects) {
		super();
		TYPE = type;
		OTHERIDTYPE = type==IDType.O? IDType.E:IDType.O;
		this.objects = objects;
//		pathPlaner = new PathPlaner(begin, goal);
//		path = pathPlaner.getPath();
		path = new Path(begin, end);
		
		thread = new Thread(this);
		thread.start();
	}
	public ObjectMover(IDType type, DoubleArrayList<MovingObject> objects,Vector3d begin,Vector3d end) {
		super();
		TYPE = type;
		OTHERIDTYPE = type==IDType.O? IDType.E:IDType.O;
		this.objects = objects;
		this.begin = begin;
		this.end = end;
//		pathPlaner = new PathPlaner(this.begin, this.goal);
		path = new Path(begin, end);
		
		thread = new Thread(this);
		thread.start();
	}
	public void close(){
		if(thread.isAlive()){
			thread.interrupt();
			path.clear();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			int i = 0;
			try {
				for (i = 0; i < objects.size(TYPE); i++) {
					move(i);
					
					//Log.d("MOVER", "<<<<<<<moving++++++++++++++++++++++++++++");
					//Thread.sleep(10);
				}
				Thread.sleep(80);
				// Log.d("MOVER",
				// "<<<<<<<SLEEP>>>>>"+objects.size(TYPE)+"<<++++++++++++++++++++++++++++");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Log.d("MOVER", e
				//		+ "<<<<<<<exception++++++++++++++++++++++++++++");
			}
		}

	}

	public IDType getTYPE() {
		return TYPE;
	}

	public void setTYPE(IDType tYPE) {
		TYPE = tYPE;
	}

	public void setObjects(DoubleArrayList<MovingObject> objects) {
		this.objects = objects;
	}
	
	public void setEnTowerPos(Vector3d enPos) {
		enTowerPosition = enPos;
	}
	public void addPosition(Vector3d pos){
//		pathPlaner.addPassPos(pos);
		path.addPathPoint(pos);
	}
	public void reovePosition(PathPoint pos){
//		pathPlaner.removePassPos(pos);
		path.removePathPoint(pos);
	}
	
	public void reovePosition(Vector3d pos){
//		pathPlaner.removePassPos(pos);
		path.removePathPoint(pos);
	}
	@Deprecated
	private ArrayList getCube(BoundingBox box) {

		ArrayList<Vector3d> cubePoint = new ArrayList<Vector3d>();
		Vector3d min = box.getMin();
		Vector3d max = box.getMax();
		float x = 0, y = 0, z = 0;

		for (int i = 0; i < 2; i++) {
			x = i == 0 ? min.getX() : max.getX();
			for (int k = 0; k < 2; k++) {
				y = k == 0 ? min.getY() : max.getY();
				for (int t = 0; t < 2; t++) {
					z = t == 0 ? min.getZ() : max.getZ();
					cubePoint.add(new Vector3d(x, y, z));
				}
			}
		}

		return cubePoint;
	}
	@Deprecated
	private Boolean collisionDetection(DefaultObject A, DefaultObject B) {

		if (A == null || B == null)
			return false;
		BoundingBox AB = A.getModelBoundingBox();
		BoundingBox BB = B.getModelBoundingBox();
		ArrayList<Vector3d> cubeAsPoint = getCube(A.getModelBoundingBox());
		ArrayList<Vector3d> cubeBsPoint = getCube(B.getModelBoundingBox());
		boolean insideX = false;
		boolean insideY = false;
		boolean insideZ = false;

		for (Vector3d V : cubeAsPoint) {
			insideX = V.getX() <= BB.getMax().getX()
					&& V.getX() >= BB.getMin().getX();
			insideY = V.getY() <= BB.getMax().getY()
					&& V.getY() >= BB.getMin().getY();
			insideZ = V.getZ() <= BB.getMax().getZ()
					&& V.getZ() >= BB.getMin().getZ();
			if (insideX && insideY && insideZ) {
				return true;
			}
		}
		for (Vector3d V : cubeBsPoint) {
			insideX = V.getX() <= AB.getMax().getX()
		 			&& V.getX() >= AB.getMin().getX();
			insideY = V.getY() <= AB.getMax().getY()
					&& V.getY() >= AB.getMin().getY();
			insideZ = V.getZ() <= AB.getMax().getZ()
					&& V.getZ() >= AB.getMin().getZ();
			if (insideX && insideY && insideZ) {
				return true;
			}
		}

		return false;
	}

	@Deprecated
	private float culAngle(Vector3d a, Vector3d b) {
	
		//Math.atan2(b.getY() - a.getY(), b.getX() - a.getX());
		return (float)Math.atan2(b.getY() - a.getY(), b.getX() - a.getX());
	}
	
	@Deprecated
	private Boolean isOver(MovingObject moveObject){
				
		Vector3d v = moveObject.getModelPosition().subtract(begin);
		
		Vector3d nextPos = moveObject.getNextPos();
		if(nextPos == null){
//			pathPlaner.getNextPos(moveObject.getModelPosition(), TYPE);
			return true;
		}
		Vector3d nextPosv = nextPos.subtract(begin);
		
		Vector3d nowPos = moveObject.getModelPosition();

		v = v.cross(nextPosv);
						
		if(v.getZ()>=0){//right
			if(nowPos.getX()>=nextPos.getX()&&nowPos.getY()>=nextPos.getY())
				return true;
			return false;
		}
		else{//left
			Log.d("moveStart", "nextPos"+nextPos);
			if(nowPos.getX()<=nextPos.getX()&&nowPos.getY()>=nextPos.getY())
				return true;
			return false;
		}
		
//		return true;
	}

	private boolean move(int index) {

		//Log.d("path",path.toString());
		MovingObject movingObject = objects.seek(index, TYPE);
		if(movingObject.isDead()){
			movingObject.realDead();
			movingObject = null;
			objects.remove(index, TYPE);
			return false;
		}
		
		switch (movingObject.moveByPathPoint()) {
			case MovingObject.STOPING:
				movingObject.startMove();
				Log.d("point", "Restart move");
				break;
			case MovingObject.AT_END:
				Log.d("point", "In the end");
				if(enermyBlood > 0) {
					enermyBlood-=5;
				}
				movingObject.dead();
				//objects.remove(index, TYPE);
				
				return true;
			case MovingObject.NO_PATH_SET:
				movingObject.setPathPoint(path.getNextPathPoint(null));
				movingObject.moveByPathPoint();
				Log.d("point", "Set Point:{X:"+path.getNextPathPoint(null).getPosition().getX()+" Y:"+path.getNextPathPoint(null).getPosition().getY()+"}");
				break;
			default:
				break;
		}
		//collision detection
		for( int i = objects.getIndexOf(movingObject, TYPE); i<objects.size(TYPE); i++ ){
			if(objects.getIndexOf(movingObject, TYPE)==i)continue;

			if(inRange(movingObject.getModelPosition(), objects.seek(i, TYPE).getModelPosition(), 100))
			if(!objects.seek(i, TYPE).isDead() && !movingObject.isDead()) {
				if( objects.seek(i, TYPE).checkCollision(movingObject, 1) ){
					//if collision unit is own unit and stop this movingObject move until startMove() << be called
					movingObject.stopMove();
					
					break;
				}

			}
		}
		
		for( int i = 0; i<objects.size(OTHERIDTYPE); i++ ){
			//if(objects.getIndexOf(movingObject, TYPE)==i)continue;
			if(inRange(movingObject.getModelPosition(), objects.seek(i, OTHERIDTYPE).getModelPosition(), 100))
			if(!objects.seek(i, OTHERIDTYPE).isDead() && !movingObject.isDead()) {
				if( objects.seek(i, OTHERIDTYPE).checkCollision(movingObject, 1.3) ){
					//if collision unit is enemy unit and stop this movingObject move until startMove() << be called
					//and HP = HP -1
					//if (HP<=0) means this object is dead 
					movingObject.stopMove();
					Vector3d otherModelPos = objects.seek(i, OTHERIDTYPE).getModelPosition();
					//Math.atan2(otherModelPos.getY()-movingObject.getModelPosition().getY(), otherModelPos.getX()-movingObject.getModelPosition().getX())
					movingObject.setModelFaceAngle((float)Math.atan2(otherModelPos.getY()-movingObject.getModelPosition().getY(), otherModelPos.getX()-movingObject.getModelPosition().getX()));
					//movingObject.back();
					//movingObject.setHealth(movingObject.getHealth()-10f);
					movingObject.attackAnimate();
					objects.seek(i, OTHERIDTYPE).setHealth(objects.seek(i, OTHERIDTYPE).getHealth() - 10.0f);
					Log.d("objectMover", "Attack");
	
					if(objects.seek(i, OTHERIDTYPE).getHealth()<=0){
						objects.seek(i, OTHERIDTYPE).dead();
						//objects.remove(objects.getIndexOf(movingObject, TYPE), TYPE);
						return false;
					}
				}
			}
		}
		
		return true;
	}

	public int getEnBlood() {
		return enermyBlood;
	}
	
	private boolean inRange(Vector3d a, Vector3d b, int r) {
		double xsqr = Math.pow(a.getX() - b.getX(), 2);
		double ysqr = Math.pow(a.getY() - b.getY(), 2);
		
		return (xsqr + ysqr) < (r * r);
	}
}
