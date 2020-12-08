package model;

import helper.MatrixHelper;
import service.AppService;

public class Translate {
	private float[][] matrix;
	
	public Translate(float dx, float dy, float dz) {
		float[][] matrix = {
				{1, 0, 0, dx},
				{0, 1, 0, dy},
				{0, 0, 1, dz},
				{0, 0, 0, 1},
		};
		this.matrix = matrix;
	}
	
	public void applyTransformation(Drawing drawing) {
		float[][] points = drawing.getVertexPointsMatrix();
		float[][] result = MatrixHelper.multiplyMatrices(this.matrix, points);
		drawing.toVertexList(result);
	}
}
