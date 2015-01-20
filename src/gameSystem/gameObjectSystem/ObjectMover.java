package gameSystem.gameObjectSystem;

import gameObject.tower.MovingObject;
import gameObject.tower.DefaultObject;

import java.util.ArrayList;
import java.util.Collection;

import android.R.bool;
import android.R.integer;
import android.util.Log;

import com.metaio.sdk.jni.BoundingBox;
import com.metaio.sdk.jni.Vector3d;

public class ObjectMover implements Runnable {

	private IDType TYPE;
	private DoubleArrayList<MovingObject> objects = null;
	private Thread thread;

	public ObjectMover(IDType tYPE, DoubleArrayList<MovingObject> objects) {
		super();
		TYPE = tYPE;
		this.objects = objects;
		thread = new Thread(this);
		thread.start();
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
					thread.sleep(10);
				}
				thread.sleep(250);
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

	private boolean move(int index) {

		objects.seek(index, TYPE).move();
		boolean succ=true;
		// �拚�撠蝣唳�瑼Ｘ
		if (collisionDetection(objects.seek(index, TYPE),
				objects.seek(0, TYPE == IDType.O ? IDType.E : IDType.O))) {
			//Log.d("moveStart", "collision!!!!!!!!!!!!!!!!!!!!!!!!!");
			objects.seek(index, TYPE).back();
			//return false;
			succ = false;

		} else {// �折蝣唳�瑼Ｘ
			for (int i = 0; i < objects.size(TYPE); i++) {

				if (collisionDetection(objects.seek(index, TYPE), objects.seek(i, TYPE)) && i!=index) {
					objects.seek(index, TYPE).back();
					//Log.d("moveStart", "collision!!!!!!!!!!!!!!!!!!!!!!!!!");
					//return false;
					succ = false;
				}

			}
		}

		return succ;
	}

}
