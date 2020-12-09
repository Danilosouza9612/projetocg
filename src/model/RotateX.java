package model;

public class RotateX extends Rotate{
	private float[][] matrix;
	
	public RotateX(float angle) {
		float angleRad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(angleRad);
		float sin = (float) Math.sin(angleRad);
		float[][] matrix = {
			{1, 0, 		0, 0},
			{0, cos, -sin, 0},
			{0, sin,  cos, 0},
			{0, 0, 		0, 1},
		};
		this.matrix=matrix;
	}
	
	@Override
	public float[][] getMatrix() {
		// TODO Auto-generated method stub
		return this.matrix;
	}
}
