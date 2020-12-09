package model;

import helper.MatrixHelper;

public abstract class Reflexion extends Transformation{
	public Reflexion() {
		super(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void transform(Drawing drawing) {
		super.transform(drawing);
		float[][] result = MatrixHelper.multiplyMatrices(this.getMatrix(), drawing.getNormalsPointMatrix());
		drawing.toNormalList(result);
	}
	
	public float[][] getNormalMatrix() {
		return this.getMatrix();
	}
}
