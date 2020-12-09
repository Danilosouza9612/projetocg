package model;

import helper.MatrixHelper;

public class RotateZ extends Rotate{
	private float[][] matrix;
	
	public RotateZ(float angle) {
		float angleRad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(angleRad);
		float sin = (float) Math.sin(angleRad);
		float[][] matrix = {
			{cos,  -sin, 	0, 0},
			{sin, 	cos, 	0, 0},
			{0, 	0,  	1, 0},
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
