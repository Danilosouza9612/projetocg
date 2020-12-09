package model;

public abstract class Rotate extends Transformation{
	public Rotate() {
		super(true);
		// TODO Auto-generated constructor stub
	}

	public float[][] getNormalMatrix() {
		return this.getMatrix();
	}
}
