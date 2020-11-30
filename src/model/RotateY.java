package model;

public class RotateY extends Transformation{
	private float[][] mat;
	
	public RotateY(int deg) {
		double rad = Math.toRadians(deg);
		this.mat = new float[4][4];
		this.mat[0][0] = (float) Math.cos(rad);
		this.mat[1][1] = 1;
		this.mat[0][2] = (float) Math.sin(rad);
		this.mat[2][0] = (float) -Math.sin(rad);
		this.mat[2][2] = (float) Math.cos(rad);
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
