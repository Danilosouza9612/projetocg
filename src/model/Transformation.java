package model;

import helper.IdHelper;
import helper.MatrixHelper;

public abstract class Transformation {
	private int id;
	
	public Transformation() {
		this.id = IdHelper.buildId();
	}
	
	public float[][] transform(float[][] points){
		return MatrixHelper.multiplyMatrices(this.getTransformationMatrix(), points);
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		Transformation transformation = (Transformation)o;
		return transformation.id==this.id;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	public abstract float[][] getTransformationMatrix();
	public abstract Transformation inverseTransformation();
	public abstract String toString();
}
