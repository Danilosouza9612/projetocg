package model;

public class Scale extends Transformation{
	private float[][] transformationMatrix;
	
	public Scale(float xFactor, float yFactor, float zFactor) {
		super();
		this.transformationMatrix = new float[4][4];
		this.transformationMatrix[0][0]=xFactor;
		this.transformationMatrix[1][1]=yFactor;
		this.transformationMatrix[2][2]=zFactor;
		this.transformationMatrix[3][3]=1;
	}


	@Override
	public float[][] getTransformationMatrix() {
		return this.transformationMatrix;
	}

	@Override
	public Transformation inverseTransformation() {
		// TODO Auto-generated method stub
		return new Scale(1/this.transformationMatrix[0][0], 1/this.transformationMatrix[1][1], 1/this.transformationMatrix[2][2]);
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Escala ("+transformationMatrix[0][0]+", "+this.transformationMatrix[1][1]+", "+this.transformationMatrix[2][2]+")";
	}
}
