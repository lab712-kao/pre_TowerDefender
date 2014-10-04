package gameObject.tower;

public class IGSoldier extends IGMoveObject{

	private float atk;
	private float atkRange;
	
	public IGSoldier(long cPtr, boolean cMemoryOwn) {
		super(cPtr, cMemoryOwn);
		// TODO Auto-generated constructor stub
	}

	public float getAtk() {
		return atk;
	}
	public void setAtk(float atk) {
		this.atk = atk;
	}

	public float getAtkRange() {
		return atkRange;
	}
	public void setAtkRange(float atkRange) {
		this.atkRange = atkRange;
	}

	
	
}
