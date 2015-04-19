package gameSystem.gameObjectSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



import android.util.Log;

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
		public PathPoint(Vector3d begin, Vector3d end){// this construct is only use in Comparator
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
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String string= "{X:"+position.getX()+", Y:"+position.getY()+"},face"+angle;
			return string;
		}
		
	}
	
	private ArrayList<PathPoint> way;
	private Vector3d begin,end;
	private final float DEFAULT_ANGLE = 0.0f;//default angle in every new Path Point
	private PathPoint DEFAULT_POINT;//the default Path Point form begin to end
	private PathPoint compator;//it is use to sort the others Path Point 
	public Path(Vector3d begin, Vector3d end) {
		way = new ArrayList<PathPoint>();
		this.begin = begin;
		this.end = end;
		compator = new PathPoint(begin, end);
//		DEFAULT_POINT = new PathPoint(begin, calAngle(begin,end));
//		DEFAULT_POINT.setNextPoint(new PathPoint(end, calAngle(begin,end)));
		DEFAULT_POINT = new PathPoint(end, calAngle(begin,end));
//		DEFAULT_POINT.setNextPoint(new PathPoint(end, calAngle(begin,end)));
	}
	
	public void addPathPoint(Vector3d pos){
		way.add(new PathPoint(pos, DEFAULT_ANGLE));
		Log.d("Path addPathPoint", "way size: " + way.size());

		calPath();
//		for(int i = 0; i < way.size(); i++) {
//			Log.d("Path addPathPoint", "way item"+"["+i+"]: "+way.get(i).position.toString());
//		}
		PathPoint p = getNextPathPoint(null);
		int i = 0;
		while(p!=null){
			
			Log.d("Path addPathPoint", "way item"+"["+i+"]: "+p.getPosition().toString());
			p=p.getNextPoint();
			i++;
		}
	}
	private float calAngle(Vector3d base,Vector3d A){
		
		if(A!=null)
			return (float) Math.atan2(A.getY()-base.getY(), A.getX()-base.getX());
		return DEFAULT_ANGLE;
		
	}
	private void calPath(){//sort the Path Point and set every Path Point 's angle & next position
//		PathPoint point[] = new PathPoint[way.size()];
//		point = way.toArray(point);
//		Arrays.sort(point, compator);
		Collections.sort(way, compator);

		
//		way.clear();
		
		for (int i = 0; i < way.size()-1; i++) {
			way.get(i).setNextPoint(way.get(i+1));
			way.get(i).setAngle(calAngle(way.get(i).getPosition(),way.get(i+1).getPosition()));
//			way.add(point[i]);
		}
		if(way.size()>=1){
			way.get(way.size()-1).setAngle(calAngle(way.get(way.size()-1).getPosition(),end));
			DEFAULT_POINT.setAngle(calAngle(way.get(way.size()-1).getPosition(),end));
			way.get(way.size()-1).setNextPoint(DEFAULT_POINT);
//			way.add(point[point.length-1]);
		}
	}
	public PathPoint getNextPathPoint(PathPoint nowPoint){
		
		if(nowPoint == null){//it mean in the begin
			if(way.isEmpty())
				return DEFAULT_POINT;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "";
		
		for(PathPoint p:way){
			s+=p.toString()+'\t';
		}
		//s+=way.get(way.size()-1).toString();
		return s;
	}
}
