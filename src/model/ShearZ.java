package model;

public class ShearZ extends Transformation{
	private float[][] matrix;
	
	public ShearZ(float a, float b) {
		super(false);
		float[][] matrix = {
			{1, 0, a, 0},
			{0, 1, b, 0},
			{0, 0, 1, 0},
			{0, 0, 0, 1},
		};
		this.matrix = matrix;
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
