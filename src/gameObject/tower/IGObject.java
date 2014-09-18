package gameObject.tower;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public class IGObject extends IGeometry{
	
	protected float health;
	protected float faceAngle;
	
	public IGObject(long cPtr, boolean cMemoryOwn) {
		super(cPtr, cMemoryOwn);
		// TODO Auto-generated constructor stub
	}
/*
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	
	
	public float getFaceAngle() {
		return faceAngle;
	}
	public void setFaceAngle(float faceangle) {
		this.faceAngle = faceangle;
	}
	*/
}