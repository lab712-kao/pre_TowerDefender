package gameSystem.gameObjectSystem;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import android.R.integer;
import android.provider.Telephony.Mms.Addr;
import android.text.GetChars;

import com.metaio.sdk.jni.Vector3d;
import com.unity3d.player.a.l;

public class Path {
	
	private class PathPoint implements Comparator<PathPoint>{
		private Vector3d position,begin,end;
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
		public Vector3d getPosition(){
			return position;
		}
		public boolean isIgnore(){
			return ignore;
		}
		public void setIgnore(boolean ignore){
			this.ignore = ignore;
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
		
	}
	private void calPath(){
		PathPoint point[] = new PathPoint[way.size()];
		Arrays.sort(point, compator);
		
	}
	
}
