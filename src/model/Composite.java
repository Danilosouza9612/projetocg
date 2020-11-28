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

	@Override
	public Transformation inverseTransformation() {
		Composite composite = new Composite();
		while(!this.transformations.end()) {
			composite.addTransformation(this.transformations.pop().inverseTransformation());
		}
		this.transformations.resetPos();
		return composite;
	}

	@Override
	public String toString() {
		return "Composta";
	}
}
