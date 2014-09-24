package gameSystem.gameObjectSystem.gameObjectInfo;

import android.R.integer;

public class ObjectInfo {
	enum TYPE{Soldier,Tower};
	
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public float getRange() {
		return range;
	}
	public void setRange(float range) {
		this.range = range;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private TYPE type;
	private int hp;
	private int atk;
	private int speed;
	private float range;
	private String path;
	private String name;
	
	public ObjectInfo(TYPE type, int hp, int atk, int speed, float range,
			String path, String name) {
		super();
		this.type = type;
		this.hp = hp;
		this.atk = atk;
		this.speed = speed;
		this.range = range;
		this.path = path;
		this.name = name;
	}
	
}
