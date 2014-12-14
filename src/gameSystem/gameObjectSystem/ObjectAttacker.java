package gameSystem.gameObjectSystem;

import android.R.bool;
import android.R.integer;
import android.util.Log;
import gameObject.tower.MovingObject;
import gameObject.tower.Tank;

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
					thread.sleep(10);
				}
				thread.sleep(500);
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

	private boolean checkAttackRange(MovingObject movingObject, Object victims) {
		return false;
	}

	private void attack(int index) {
		IDType otherIdType = type == IDType.O ? IDType.E : IDType.O;

		if (objects.seek(index, type).getClass() == Tank.class) {
			for (int i = 0; i < objects.size(otherIdType); i++) {
				if (checkAttackRange(objects.seek(index, type),
						objects.seek(i, otherIdType))) {
					((Tank) objects.seek(index, type)).attack(objects.seek(i,
							otherIdType));
				}
			}
		}
	}

}
