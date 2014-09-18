package gameObject.tower;

import com.metaio.sdk.jni.Vector3d;

public class IGMoveObject extends IGObject{

	protected float moveSpeed;
	protected float moveAngle;
	
	public IGMoveObject(long cPtr, boolean cMemoryOwn) {
		super(cPtr, cMemoryOwn);
		// TODO Auto-generated constructor stub
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public float getMoveAngle() {
		return moveAngle;
	}
	public void setMoveAngle(float moveAngle) {
		this.moveAngle = moveAngle;
	}

	public void move() {
		float shiftX = (float)(moveSpeed*Math.cos(moveAngle)+this.getTranslation().getX());
		float shiftY = (float)(moveSpeed*Math.sin(moveAngle)+this.getTranslation().getY());
		
		this.setTranslation(new Vector3d(shiftX, shiftY, this.getTranslation().getZ()));
	}
}
