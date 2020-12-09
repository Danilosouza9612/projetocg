package model;

public class RotateY extends Rotate{
	private float[][] matrix;
	
	public RotateY(float angle) {
		float angleRad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(angleRad);
		float sin = (float) Math.sin(angleRad);
		float[][] matrix = {
			{cos,   0, 	  sin, 0},
			{0, 	1, 	    0, 0},
			{-sin, 	0,    cos, 0},
			{0, 	0, 		0, 1},
		};
		this.matrix=matrix;
	}
	
	@Override
	public float[][] getMatrix() {
		// TODO Auto-generated method stub
		return this.matrix;
	}
	
}
