package gameObject.tower;

import android.util.Log;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.LLACoordinate;
import com.metaio.sdk.jni.Vector3d;

public class tank extends moveObject{
	
	private float atk;
	public float getAtk() {
		return atk;
	}

	public void setAtk(float atk) {
		this.atk = atk;
	}

	public tank(IGeometry model, float x, float y, float moveSpeed,
			float moveAngle,float health ,float atk) {
		super(model, x, y, moveSpeed, moveAngle, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
	}

	public tank(IGeometry model, float x, float y,float health,float atk) {
		super(model, x, y, health);
		// TODO Auto-generated constructor stub
		this.atk = atk;
	}
	
	public void attack(object other){
		if(other.getHealth()-atk<=0){
			other.dead();
			other.setHealth((float) 0.0);
		}else{
			other.setHealth(other.getHealth()-atk);
		}
		
	}

}
