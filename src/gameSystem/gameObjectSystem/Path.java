package gameSystem.gameObjectSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;



import com.metaio.sdk.jni.Vector3d;

public class Path {
	
	public class PathPoint implements Comparator<PathPoint>{
		private Vector3d position,begin,end;
		private PathPoint nextPoint = null;
		private float angle;
		private boolean ignore = false;
		private boolean xAscending = true;
		private boolean yAscending = true;
		
		public PathPoint(Vector3d position,float angle){
			this.position = position;
			this.angle = angle;
		}
		public PathPoint(Vector3d begin, Vector3d end){
			if(begin.getX()>end.getX()){
				xAscending = false;//mean Descending
			}
			if(begin.getY()>end.getY()){
				yAscending = false;
			}
			this.begin = begin;
			this.end = end;
		}
		public float getAngle(){
			return angle;
		}
		public void setAngle(float angle){
			this.angle = angle;
		}
		public Vector3d getPosition(){
			return position;
		}
		public boolean isIgnore(){
			return ignore;
		}
		public void setIgnore(boolean ignore){
			this.ignore = ignore;
		}
		public void setNextPoint(PathPoint nextPoint){
			this.nextPoint = nextPoint;
		}
		public PathPoint getNextPoint(){
			return nextPoint;
		}
		@Override
		public int compare(PathPoint lhs, PathPoint rhs) {
			// TODO Auto-generated method stub
			if(lhs.getPosition().getX() == rhs.getPosition().getX() 
			&& lhs.getPosition().getY() == rhs.getPosition().getY()){
				lhs.setIgnore(true);
				return 0;
			}
			
			if(xAscending){//left to right 
				if(yAscending){//bottom to top
					if(lhs.getPosition().getX() == rhs.getPosition().getX()){
						return (int)(lhs.getPosition().getY() - rhs.getPosition().getY());
					}
					return (int)(lhs.getPosition().getX() - rhs.getPosition().getX());
				}else{//top to bottom
					if(lhs.getPosition().getX() == rhs.getPosition().getX()){
						return (int)(rhs.getPosition().getY() - lhs.getPosition().getY());
					}
					return (int)(lhs.getPosition().getX() - rhs.getPosition().getX());
				}
			}else{//right to left
				if(yAscending){//bottom to top
					if(lhs.getPosition().getX() == rhs.getPosition().getX()){
						return (int)(lhs.getPosition().getY() - rhs.getPosition().getY());
					}
					return (int)(rhs.getPosition().getX() - lhs.getPosition().getX());
				}else{//top to bottom
					if(lhs.getPosition().getX() == rhs.getPosition().getX()){
						return (int)(rhs.getPosition().getY() - lhs.getPosition().getY());
					}
					return (int)(rhs.getPosition().getX() - lhs.getPosition().getX());
				}
			}
		//end function
		}
		
	}
	
	private ArrayList<PathPoint> way;
	private Vector3d begin,end;
	private final float DEFAULT_ANGLE = 0.0f;
	private PathPoint compator;
	public Path(Vector3d begin, Vector3d end) {
		way = new ArrayList<PathPoint>();
		this.begin = begin;
		this.end = end;
		compator = new PathPoint(begin, end);
		// TODO Auto-generated constructor stub		
	}
	
	public void addPathPoint(Vector3d pos){
		way.add(new PathPoint(pos, DEFAULT_ANGLE));
		calPath();
	}
	private float calAngle(Vector3d A){
		
		if(A!=null)
			return (float) Math.atan2(A.getY(), A.getX());
		return DEFAULT_ANGLE;
		
	}
	private void calPath(){
		PathPoint point[] = new PathPoint[way.size()];
		Arrays.sort(point, compator);
		way.clear();
		for (int i = 0; i < point.length-1; i++) {
			point[i].setNextPoint(point[i+1]);
			point[i].setAngle(calAngle(point[i+1].getPosition()));
			way.add(point[i]);
		}
		point[point.length-1].setAngle(calAngle(end));
	}
	public PathPoint getNextPathPoint(PathPoint nowPoint){
		
		if(nowPoint == null){//it mean in the begin
			return way.get(0);
		}
		
		int i = way.indexOf(nowPoint);
		if(i==-1 || i == way.size())// no this point
		 return null;
		else if(way.get((i+1)%way.size()).isIgnore() == true)//if ignore get nextPoit
			return getNextPathPoint(way.get((i+1)%way.size()));
		
		return way.get((i+1)%way.size());
		
	}
	public void removePathPoint(PathPoint point){
		int i = way.indexOf(point);
		if(i!=-1){//fake delete
			way.get(i).setIgnore(true);
		}
		calPath();
	}
}
