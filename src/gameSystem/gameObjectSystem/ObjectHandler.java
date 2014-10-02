package gameSystem.gameObjectSystem;

import gameObject.tower.MovingObject;
import gameObject.tower.Object;
import gameObject.tower.Tank;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfo;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfoReader;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfo.TYPE;

import java.util.Vector;

import android.R.integer;
import android.renderscript.Type;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Vector3d;

public class ObjectHandler {
	
	Vector<Object> Soldiers;
	ObjectInfoReader reader;
	
	public ObjectHandler() {
		// TODO Auto-generated constructor stub
		reader = new ObjectInfoReader();
	}
	
	public void createObject(String name,IGeometry model,int coordinateSystemID,int x,int y){
		ObjectInfo info = reader.getSoldierInfoByName(name);
		if(ObjectInfo.TYPE.Soldier.equals(info.getType())){
			Soldiers.add(new Tank(model,coordinateSystemID,new Vector3d(3.5f),x,y,info.getSpeed(),3.5f,info.getHp(),3.5f,info.getAtk(),info.getRange()) {
			});
		}else{
			
		}
		
	}		
}

