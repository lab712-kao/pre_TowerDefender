package gameObject.tower;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public abstract class moveObject extends object {
	
	protected float moveSpeed;
	protected float moveAngle;
	public float getMoveSpeed() {
		return moveSpeed;
	}
	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	public moveObject(IGeometry model, float x, float y, float moveSpeed,float moveAngle,float health) {
		super(model, x, y, health);
		this.moveSpeed = moveSpeed;
		this.moveAngle = moveAngle;
		// TODO Auto-generated constructor stub
	}
	public moveObject(IGeometry model, float x, float y,float health) {
		super(model, x, y, health);
		this.moveSpeed = 1;
		this.moveAngle = 0;
		// TODO Auto-generated constructor stub
	}
	
	public void move() {
		double speedX = moveSpeed*Math.sin(moveAngle);
		double speedY = moveSpeed*Math.cos(moveAngle);
		this.xy[0]+=speedX;
		this.xy[0]+=speedY;
		model.setTranslation(new Vector3d(this.xy[0], this.xy[0], 0));
	}
}
