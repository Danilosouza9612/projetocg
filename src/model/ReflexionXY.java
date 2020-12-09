package model;

public class ReflexionXY extends Reflexion{
	private float[][] matrix;
	
	public ReflexionXY() {
		float[][] matrix = {
			{1, 0, 0,  0},
			{0, 1, 0,  0},
			{0, 0, -1, 0},
			{0, 0, 0,  1},
		};
		this.matrix = matrix;
	}

	@Override
	public float[][] getMatrix() {
		// TODO Auto-generated method stub
		return this.matrix;
	}
}
