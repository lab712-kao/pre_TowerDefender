package gameSystem.gameObjectSystem;



public class Effect {
	
	private String effectName;
	public final int ZOOM = 1, SMALL = 2, HEAL = 3, DEAD = 4;
	private int effectNumber;
	private float powerOfEffect;
	private String description;
	private int liveTime;
	private int ableQuantity;

	
	public Effect() {
		// TODO Auto-generated constructor stub
	}
	
	public Effect(String effectName, int effectNumber, float powerOfEffect,
			String description) {
		super();
		this.effectName = effectName;
		this.effectNumber = effectNumber;
		this.powerOfEffect = powerOfEffect;
		this.description = description;
	}

	public String getEffectName() {
		return effectName;
	}

	public void setEffectName(String effectName) {
		this.effectName = effectName;
	}

	public int getEffectNumber() {
		return effectNumber;
	}

	public void setEffectNumber(int effectNumber) {
		this.effectNumber = effectNumber;
	}

	public float getPowerOfEffect() {
		return powerOfEffect;
	}

	public void setPowerOfEffect(float powerOfEffect) {
		this.powerOfEffect = powerOfEffect;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(int liveTime) {
		this.liveTime = liveTime;
	}

	public int getAbleQuantity() {
		return ableQuantity;
	}

	public void setAbleQuantity(int ableQuantity) {
		this.ableQuantity = ableQuantity;
	}

	
	
}
