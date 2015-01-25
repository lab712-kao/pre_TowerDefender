package tw.edu.ttu.pre_towerdefender;

import java.util.ArrayList;
import java.util.Iterator;
import android.os.Handler;
import android.util.Log;
import com.metaio.sdk.jni.ETRACKING_STATE;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.sdk.jni.TrackingValues;
import com.metaio.sdk.jni.Vector3d;


public class PositionUpdater {
	
	private class Models {
		private String name;
		private IGeometry model;
		private Vector3d position;
		
		public Models(IGeometry model, String name) {
			this.model = model;
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public IGeometry getModel() {
			return model;
		}

		public void setModel(IGeometry model) {
			this.model = model;
		}

		public Vector3d getPosition() {
			return position;
		}
		
		public void setPosition(Vector3d pos) {
			position = pos;
		}
	}
	private IMetaioSDKAndroid metaioSDK;
	private ArrayList<Models> modelPos;
	private Handler handler = new Handler();
	
	public PositionUpdater(IMetaioSDKAndroid mSDK) {
		metaioSDK = mSDK;
	}
	
	public void addGeometry(IGeometry model, String name) {
		Models m = new Models(model, name);
		m.setPosition(new Vector3d(0, 0, 0));
		modelPos.add(m);
	}
	
	public void startPosUpdate() {
		handler.removeCallbacks(updateTimer);
		handler.postDelayed(updateTimer, 300);
	}
	
	public Vector3d getPositionByName(String name) {
		synchronized(modelPos) {
			for(Models m : modelPos) {
				if(m.getName().equals(name)) {
					return m.getPosition();
				}
			}
		}
		
		Log.d("getPosByName", "name: " + name + ", not found");
		return new Vector3d(0, 0 , 0);
	}

	private Runnable updateTimer = new Runnable() {
		public void run() {
			synchronized(modelPos) {
				Iterator<Models> it = modelPos.iterator();
				while(it.hasNext()) {
					IGeometry model = it.next().getModel();
					TrackingValues tv = metaioSDK.getTrackingValues(model.getCoordinateSystemID());
					TrackingValues theRelation = new TrackingValues();
					if(tv.getState() == ETRACKING_STATE.ETS_FOUND) {
						boolean success = metaioSDK.getCosRelation(1, model.getCoordinateSystemID(), theRelation);
						if(success) {
							it.next().setPosition(theRelation.getTranslation());
						}
						else {
							Log.d("update", "update position, get relation cos fail");
						}
					}
				}
			}
			handler.postDelayed(this, 300);
		}
	};
	
}