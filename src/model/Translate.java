package model;

public class Translate extends Transformation{
	private float[][] matrix;
	
	public Translate(float dx, float dy, float dz) {
		super(false);
		float[][] matrix = {
				{1, 0, 0, dx},
				{0, 1, 0, dy},
				{0, 0, 1, dz},
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
