package gameSystem.gameObjectSystem;

public class CatmullRom {
	
	public CatmullRom() {
		// TODO Auto-generated constructor stub
	}
	
	public static Vector3d evalCatmullRom(double t, final Vector3d P0, 
			final Vector3d P1, final Vector3d P2, final Vector3d P3, 
			double t0, double t1, double t2, double t3){
		
		//the function of Catmull-Rom spline is calculate by Barry-Goldman pyramidal 
		//A1 = P0*(t1-t)/(t1-t0) + P1*(t-t0)/(t1-t0)
		//A2 = P1*(t2-t)/(t2-t1) + P2*(t-t1)/(t2-t1)
		//A3 = P2*(t3-t)/(t3-t2) + P3*(t-t2)/(t3-t2)
		//B1 = A1*(t2-t)/(t2-t0) + A2*(t-t0)/(t2-t0)
		//B2 = A2*(t3-t)/(t3-t1) + A3*(t-t1)/(t3-t1)
		//C  = B1*(t2-t)/(t2-t1) + B2*(t-t1)/(t2-t1)
		//then the C is the answer
		Vector3d A1 = P0.multiply((float) (t1-t)).add(P1.multiply((float) (t-t0))).divide((float) (t1-t0));
		Vector3d A2 = P1.multiply((float) (t2-t)).add(P2.multiply((float) (t-t1))).divide((float) (t2-t1));
		Vector3d A3 = P2.multiply((float) (t3-t)).add(P3.multiply((float) (t-t2))).divide((float) (t3-t2));
		
		Vector3d B1 = A1.multiply((float) (t2-t)).add(A2.multiply((float) (t-t0))).divide((float) (t2-t0));
		Vector3d B2 = A2.multiply((float) (t3-t)).add(A3.multiply((float) (t-t1))).divide((float) (t3-t1));
		
		return B1.multiply((float) (t2-t)).add(B2.multiply((float) (t-t1))).divide((float) (t2-t1));
		
	}
	public static Vector3d evalTangentVectorOfCatmullRom(double t, final Vector3d P0, 
			final Vector3d P1, final Vector3d P2, final Vector3d P3, 
			double t0, double t1, double t2, double t3){
		return null;
	}
}
