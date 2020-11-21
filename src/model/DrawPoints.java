package model;

import com.jogamp.opengl.GL2;

import helper.PointerList;

public abstract class DrawPoints {
	protected PointerList list;
	
	public DrawPoints() {
		this.list = new PointerList();
	}
	public PointerList getPoints() {
		// TODO Auto-generated method stub
		return list;
	}
	public void applyTransform(Transformation transformation){
		list = new PointerList(transformation.transform(this.getPoints().toArray()));
	}
	public abstract void draw(GL2 gl);
}
