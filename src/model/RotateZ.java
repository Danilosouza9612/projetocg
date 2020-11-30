package model;

public class RotateZ extends Transformation {
	public float[][] mat;
	
	public RotateZ(int deg) {
		double rad = Math.toRadians(deg);
		this.mat = new float[4][4];
		this.mat[0][0] = (float) Math.cos(rad);
		this.mat[0][1] = (float) -Math.sin(rad);
		this.mat[1][0] = (float) Math.sin(rad);
		this.mat[1][1] = (float) Math.cos(rad);
		this.mat[2][2] = 1;
		this.mat[3][3] = 1;

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
