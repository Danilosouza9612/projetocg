package model;

public class RotateX extends Transformation{
	private float[][] mat;
	
	public RotateX(int deg) {
		double rad = Math.toRadians(deg);
		this.mat = new float[4][4];
		mat[0][0] = 1;
		mat[3][3] = 1;
		if(deg%360==0) {
			mat[1][1] = 1;
			mat[1][2] = 0;
			mat[2][1] = 0;
			mat[2][2] = 1;
			mat[3][3] = 1;
		}else if(deg%360==90) {
			mat[1][1] = (float) 0;
			mat[1][2] = (float) -1;
			mat[2][1] = (float) 1;
			mat[2][2] = (float) 0;
		}else if(deg%360==180) {
			mat[1][1] = (float) -1;
			mat[1][2] = (float) 0;
			mat[2][1] = (float) 0;
			mat[2][2] = (float) -1;
		}
		else if(deg%360==270) {
			mat[1][1] = (float) 0;
			mat[1][2] = (float) 1;
			mat[2][1] = (float) -1;
			mat[2][2] = (float) 0;
		}else {
			mat[1][1] = (float) Math.cos(rad);
			mat[1][2] = (float) -Math.sin(rad);
			mat[2][1] = (float) Math.sin(rad);
			mat[2][2] = (float) Math.cos(rad);
		}
	}
	
	@Override
	public float[][] getTransformationMatrix() {
		// TODO Auto-generated method stub
		return this.mat;
	}

	@Override
	public Transformation inverseTransformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
