package gameObject.tower;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public class tower extends DefaultObject{
	
	private float atk;
	public tower(IGeometry model, int coordinateSystemID,  Vector3d size,float x, float y, float health, float atk) {
		super(model,coordinateSystemID,size,x, y, health);
		this.atk = atk; 
		// TODO Auto-generated constructor stub
	}
	public tower(IGeometry model ,int coordinateSystemID, Vector3d size,float x, float y, float health) {
		super(model,coordinateSystemID, size,x, y, health);
		this.atk = 0;
		// TODO Auto-generated constructor stub
	}
	public tower(IGeometry model, int coordinateSystemID,  Vector3d size,Vector3d position, float health, float atk) {
		super(model,coordinateSystemID,size,position, health);
		this.atk = atk; 
		// TODO Auto-generated constructor stub
	}
	public tower(IGeometry model ,int coordinateSystemID, Vector3d size,Vector3d position, float health) {
		super(model,coordinateSystemID, size,position, health);
		this.atk = 0;
		// TODO Auto-generated constructor stub
	}
	public void attack(DefaultObject other){
		if(other.getHealth()-atk<=0){
			other.dead();
			other.setHealth((float) 0.0);
		}else{
			other.setHealth(other.getHealth()-atk);
		}
		
	}

}
