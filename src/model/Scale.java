package model;

public class Scale extends Transformation{
	private float[][] matrix;
	
	public Scale(float fx, float fy, float fz) {
		super(false);
		float[][] matrix = {
			{fx, 0,  0,  0},
			{0,  fy, 0,  0},
			{0,  0,  fz, 0},
			{0,  0,  0,  1},
		};
		this.matrix=matrix;
	}
	
	@Override
	public float[][] getMatrix() {
		// TODO Auto-generated method stub
		return this.matrix;
	}

	@Override
	public float[][] getNormalMatrix() {
		// TODO Auto-generated method stub
		return null;
	}
}
