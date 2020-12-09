package model;

import helper.MatrixHelper;
import helper.ReadyStack;

public class Composite extends Transformation{
	private ReadyStack<Transformation> transformations;
	
	public Composite() {
		super(false);
		this.transformations = new ReadyStack<Transformation>();
	}
	
	@Override
	public float[][] getMatrix() {
		float[][] mat = MatrixHelper.identityMatrix(4);
		Transformation transformation;
		while(!transformations.end()) {
			transformation = transformations.pop();
			mat = MatrixHelper.multiplyMatrices(mat, transformation.getMatrix());
		}
		transformations.resetPos();
		return mat;
	}
	
	public void addTransformation(Transformation transformation) {
		this.transformations.add(transformation);
		this.applyToNormal|=transformation.applyToNormal;
	}

	@Override
	public float[][] getNormalMatrix() {
		// TODO Auto-generated method stub
		float[][] mat = MatrixHelper.identityMatrix(4);
		Transformation transformation;
		while(!transformations.end()) {
			transformation = transformations.pop();
			if(transformation.getNormalMatrix()!=null) {
				mat = MatrixHelper.multiplyMatrices(mat, transformation.getNormalMatrix());
			}
		}
		transformations.resetPos();
		return mat;
	}

}
