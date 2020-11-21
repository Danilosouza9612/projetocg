package model;

import helper.MatrixHelper;
import helper.ReadyStack;

public class Composite extends Transformation{
	private ReadyStack<Transformation> transformations;
	
	public Composite() {
		super();
		this.transformations = new ReadyStack<Transformation>();
	}
	
	@Override
	public float[][] getTransformationMatrix() {
		// TODO Auto-generated method stub
		float[][] mat = MatrixHelper.identityMatrix(4);
		while(!this.transformations.end()) {
			mat = MatrixHelper.multiplyMatrices(mat, transformations.pop().getTransformationMatrix());
		}
		this.transformations.resetPos();
		return mat;
	}
	
	public void addTransformation(Transformation transformation) {
		this.transformations.add(transformation);
	}
	
	public void removeTransformation(Transformation transformation) {
		this.transformations.remove(transformation);
	}

}
