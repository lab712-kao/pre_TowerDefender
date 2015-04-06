package gameSystem.gameObjectSystem;

import com.metaio.sdk.jni.Vector3d;

public class Hermite {
	
	public Hermite() {
		// TODO Auto-generated constructor stub
	}
	
	public static Vector3d evalHermite(double t, final Vector3d P1, 
			final Vector3d P2, final Vector3d T1, final Vector3d T2) {
		
		double t2 = t*t;
		double t3 = t2*t;
		
		//the blending(baisc) function of Hermite
		double h1 = 2*t3 - 3*t2 + 1;
		double h2 = -2*t3 + 3*t2;
		double h3 = t3 - 2*t2 + t;
		double h4 = t3 -t2;
		
		//Hermite function is P(s) = P1h1(s) + P2h2(s) + T1h2(s) + T2h4(s)
		//each point of x = s ,and y = P(s)
		//then vector of each point is P'(s) = P1h1'(s) + P2h2'(s) + T1h2'(s) + T2h4'(s)
		
		Vector3d P = P1.multiply((float) h1)
				.add(P2.multiply((float) h2))
				.add(T1.multiply((float) h3))
				.add(T2.multiply((float) h4));

		/*
		Vector3d P = new Vector3d();
		double x = (2*t*t*t - 3*t*t + 1) * P1.getX()
				+ (t*t*t - 2*t*t + t) * T1.getX()
                + (-2*t*t*t + 3*t*t) * P2.getX()
                + (t*t*t - t*t) * T2.getX();
        double y = (2*t*t*t - 3*t*t + 1) * P1.getY()
                + (t*t*t - 2*t*t + t) * T1.getY()
                + (-2*t*t*t + 3*t*t) * P2.getY()
                + (t*t*t - t*t) * T2.getY();
		P.setX((float)x);
		P.setY((float)y);
		P.setZ(0);
        */
		return P;
	}
	public static Vector3d evalTangentVectorOfHermite(double t, final Vector3d P1, 
			final Vector3d P2, final Vector3d T1, final Vector3d T2){
		//the differential of Hermite's blending function
		
		double t2 = t*t;
		double h1Prime = 6*t2 - t ;
		double h2Prime = -6*t2 - 6*t;
		double h3Prime = 3*t2 - 4*t + 1;
		double h4Prime = 3*t2 - 2*t;
		
		Vector3d PPrime = P1.multiply((float) h1Prime)
				.add(P2.multiply((float) h2Prime))
				.add(T1.multiply((float) h3Prime))
				.add(T2.multiply((float) h4Prime));

		return PPrime;
	}

	
	
}
