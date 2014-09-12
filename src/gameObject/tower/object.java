package gameObject.tower;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public abstract class object {
	protected IGeometry model = null;
	protected static float[]xy= new float[2];
	protected float health;
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	public object(IGeometry model ,float x, float y, float health) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.xy[0] = x;
		this.xy[1] = y;
		this.health = health;
		model.setTranslation(new Vector3d(this.xy[0], this.xy[0], 0));
	}
	public void  dead() {
		if(model!=null){
			model.delete();
			model = null;
		}		
	}
	
}
