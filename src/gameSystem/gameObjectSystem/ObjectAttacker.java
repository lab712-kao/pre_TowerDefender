package gameSystem.gameObjectSystem;

import java.util.ArrayList;

import com.metaio.sdk.jni.BoundingBox;
import com.metaio.sdk.jni.Vector3d;

import android.util.Log;
import gameObject.tower.DefaultObject;
import gameObject.tower.MovingObject;
import gameObject.tower.Soldier;

public class ObjectAttacker implements Runnable {

	private Thread thread = null;
	private IDType type;
	private DoubleArrayList<MovingObject> objects = null;

	public ObjectAttacker(IDType type, DoubleArrayList<MovingObject> objects) {
		super();
		this.type = type;
		this.objects = objects;
		thread = new Thread(this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			int i = 0;
			try {
				for (i = 0; i < objects.size(type); i++) {
					attack(i);
					Log.d("MOVER", "<<<<<<<moving++++++++++++++++++++++++++++");
					Thread.sleep(10);
				}
				Thread.sleep(500);
				// Log.d("MOVER",
				// "<<<<<<<SLEEP>>>>>"+objects.size(TYPE)+"<<++++++++++++++++++++++++++++");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("MOVER", e
						+ "<<<<<<<exception++++++++++++++++++++++++++++");
			}
		}
	}
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

	private float getPointDistance(Vector3d a, Vector3d b){
		

		return (float) Math.sqrt(Math.pow(a.getX()-b.getX(), 2)+Math.pow(a.getY()-b.getY(), 2)+Math.pow(a.getZ()-b.getZ(), 2));
		
	}
	private boolean checkAttackRange(MovingObject attacker, DefaultObject victims) {
		
		float attackRange = ((Soldier)attacker).getAtkRange();
		ArrayList<Vector3d> cubePoint = getCube(victims.getModelBoundingBox());
		
		Vector3d attackerPos = attacker.getModelPosition();
		
		for(Vector3d p:cubePoint){
			if(getPointDistance(attackerPos, p)<attackRange){
				return true;
			}
		}
		
		
		return false;
	}

	private void attack(int index) {
		IDType otherIdType = type == IDType.O ? IDType.E : IDType.O;

		if (objects.seek(index, type).getClass() == Soldier.class) {
			for (int i = 0; i < objects.size(otherIdType); i++) {
				if (checkAttackRange(objects.seek(index, type),
						objects.seek(i, otherIdType))) {
					((Soldier) objects.seek(index, type)).attack(objects.seek(i,
							otherIdType));
				}
			}
		}
	}

}
