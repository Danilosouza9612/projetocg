package model;

import helper.MatrixHelper;

public abstract class Transformation {
	protected boolean applyToNormal;
	
	public Transformation(boolean applyToNormal) {
		this.applyToNormal = applyToNormal;
	}
	
	public void transform(Drawing drawing) {
		float[][] points = drawing.getVertexPointsMatrix();
		float[][] result = MatrixHelper.multiplyMatrices(this.getMatrix(), points);
		drawing.toVertexList(result);
		result = MatrixHelper.multiplyMatrices(this.getMatrix(), drawing.getAxisPoints());
		drawing.setAxisPoints(result);
		if(this.applyToNormal) {
			result = MatrixHelper.multiplyMatrices(this.getNormalMatrix(), drawing.getNormalsPointMatrix());
			drawing.toNormalList(result);
		}
	}
	
	public abstract float[][] getMatrix();
	public abstract float[][] getNormalMatrix();
}
