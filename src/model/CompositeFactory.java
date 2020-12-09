package model;

public class CompositeFactory {
	private Drawing drawing;
	
	public CompositeFactory(Drawing drawing) {
		this.drawing=drawing;
	}
	
	public Composite createOriginTransformation(Transformation transformation) {
		Composite composite = new Composite();
		float[] centerOfMass = drawing.getCenterOfMassCoord();
		composite.addTransformation(new Translate(-centerOfMass[0], -centerOfMass[1], -centerOfMass[2]));
		composite.addTransformation(transformation);
		composite.addTransformation(new Translate(centerOfMass[0], centerOfMass[1], centerOfMass[2]));
		return composite;
	}
	
	public Composite createOriginNearestPointTransformation(Transformation transformation) {
		Composite composite = new Composite();
		float[] origin = drawing.getNearestPointOriginDistance();
		composite.addTransformation(new Translate(-origin[0], -origin[1], -origin[2]));
		composite.addTransformation(transformation);
		composite.addTransformation(new Translate(origin[0], origin[1], origin[2]));
		return composite;
	}
}
