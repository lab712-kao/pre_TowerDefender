package gameSystem.gameObjectSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


import com.metaio.sdk.jni.Vector3d;

public class PathPlaner implements Comparator<Vector3d>{
	private ArrayList<Vector3d> path;
	private ArrayList<Vector3d> passPos;
	private final float avaLength = (float) 4.0;
	private Vector3d goal,begin;
	
	public PathPlaner( Vector3d begin,Vector3d goal){
		path = new ArrayList<Vector3d>();
		passPos = new ArrayList<Vector3d>();
		this.goal = goal;
		this.begin = begin;
	}
	
	public ArrayList<Vector3d> getPath(){
		calPath();
		return path;
	}
	
	public void addPassPos(Vector3d Pos){
		passPos.add(Pos);
		calPath();
	}
	
	private void calPath(){
		path.clear();
		path.add(begin);
		Vector3d[] arr = new Vector3d[passPos.size()];
		Arrays.sort(passPos .toArray(arr),this);
		System.out.println(arr.length);
		if(arr.length!=0)
			for (Vector3d tmp : arr ) {
				//fillPos(tmp);
				path.add(tmp);
			}
//		fillPos(goal);
		path.add(goal);
	}
	@Deprecated
	private void fillPos(Vector3d end){
		
		

		float m = Math.abs(path.get(path.size()-1).getY()-end.getY())/Math.abs(path.get(path.size()-1).getX()-end.getX());
		float k = calDistance(path.get(path.size()-1), end);
		float tmpX,tmpY;
		Vector3d subPos = null;
		System.out.println(path.get(path.size()-1));
		System.out.println(k);
		if(k<=avaLength){
			path.add(end);
			return;
		}else{
			for(;;){
				tmpX = (float) (path.get(path.size()-1).getX()+(avaLength/Math.sqrt(Math.pow(m, 2.0)+1.0)));
				tmpY = (float) (path.get(path.size()-1).getY()+(m*avaLength)/Math.sqrt(Math.pow(m, 2.0)+1.0));
				
				subPos = new Vector3d(tmpX, tmpY, end.getZ());
				k = calDistance(subPos, end);
				if(k<=avaLength){
					path.add(end);
					return;
				}else{
					path.add(subPos);
				}
			}
		}
		
	}
	private float calDistance(Vector3d a, Vector3d b){
		float sum = (float) (Math.pow(a.getX()-b.getX(), 2))+ (float) (Math.pow(a.getY()-b.getY(), 2))+ (float) (Math.pow(a.getZ()-b.getZ(), 2));
		return (float) Math.sqrt(sum);
	}

	@Override
	public int compare(Vector3d lhs, Vector3d rhs) {
		// TODO Auto-generated method stub
		if(calDistance(begin, lhs) == calDistance(begin, rhs))
			return 0;
		else if(calDistance(begin, lhs) > calDistance(begin, rhs))
				return 1;
		else
				return -1;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s="";
		
		for(Vector3d tmp:path){
			s+=tmp.toString();
		}
		
		return s;
	}
	
}
