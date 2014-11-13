package gameSystem.gameObjectSystem;

import gameObject.tower.MovingObject;
import gameObject.tower.Object;

import java.util.ArrayList;
import java.util.Vector;

import android.R.integer;

import com.metaio.sdk.jni.BoundingBox;
import com.metaio.sdk.jni.Vector3d;

public class ObjectMover implements Runnable{

	
	private IDType TYPE;
	private DoubleArrayList<MovingObject> objects = null;
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public IDType getTYPE() {
		return TYPE;
	}

	public void setTYPE(IDType tYPE) {
		TYPE = tYPE;
	}

	public void setObjects(DoubleArrayList<MovingObject> objects) {
		this.objects = objects;
	}

	private ArrayList getCube(BoundingBox box){
		
		ArrayList<Vector3d> cubePoint = new ArrayList<Vector3d>();
		Vector3d min = box.getMin();
		Vector3d max = box.getMax();
		float x = 0,y = 0,z = 0;
		
		for(int i = 0; i<2 ; i++){
			x = i==0?min.getX():max.getX();
			for(int k = 0; k<2; k++){
				y = k==0?min.getY():max.getY();
				for(int t = 0; t<2; t++){
					z = t==0?min.getZ():max.getZ();
					cubePoint.add(new Vector3d(x,y,z));
				}
			}
		}
		
		return cubePoint;
	}
	private Boolean collisionDetection(Object A,Object B){
		
		BoundingBox AB= A.getModelBoundingBox();
		BoundingBox BB= B.getModelBoundingBox();
		ArrayList<Vector3d> cubeAsPoint = getCube(A.getModelBoundingBox());
		ArrayList<Vector3d> cubeBsPoint = getCube(B.getModelBoundingBox());
		boolean insideX = false;
		boolean insideY = false;
		boolean insideZ = false;
		for(Vector3d V :cubeAsPoint){
			insideX = V.getX()<BB.getMax().getX()&&V.getX()>BB.getMin().getX();
			insideY = V.getY()<BB.getMax().getY()&&V.getY()>BB.getMin().getY();
			insideZ = V.getZ()<BB.getMax().getZ()&&V.getZ()>BB.getMin().getZ();
			if(insideX&&insideY&&insideZ){
				return true;
			}
		}
		for(Vector3d V :cubeBsPoint){
			insideX = V.getX()<AB.getMax().getX()&&V.getX()>AB.getMin().getX();
			insideY = V.getY()<AB.getMax().getY()&&V.getY()>AB.getMin().getY();
			insideZ = V.getZ()<AB.getMax().getZ()&&V.getZ()>AB.getMin().getZ();
			if(insideX&&insideY&&insideZ){
				return true;
			}
		}
		
		return false;
	}
	private boolean move(MovingObject obj){
		
		int i=0;
		float moveAngel = obj.getModelFaceAngle();
		float moveSpeed = obj.getMoveSpeed();
		Vector3d originePos = obj.getModelPosition();
		obj.getModel().setTranslation( new Vector3d((moveSpeed*(float)Math.cos(moveAngel)), moveSpeed* (float)Math.sin(moveAngel), (float)0.0) );
		if(TYPE == IDType.O){

			if(collisionDetection(obj, objects.seek(0, IDType.E))||collisionDetection(obj, objects.seek(objects.getIndexOf(obj, IDType.O), IDType.O)))
				obj.getModel().setTranslation(originePos);
				return false;
			
		}else if(TYPE == IDType.E){	
			
			if(collisionDetection(obj, objects.seek(0, IDType.O))||collisionDetection(obj, objects.seek(objects.getIndexOf(obj, IDType.E), IDType.E)))
				obj.getModel().setTranslation(originePos);
				return false;
		}
		return true;
	}

}
