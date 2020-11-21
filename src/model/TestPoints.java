package model;

import com.jogamp.opengl.GL2;

import helper.Color;

public class TestPoints extends DrawPoints {
	public TestPoints() {
		this.list.addPoint(1, 1, 0);
		this.list.addPoint(-1, 1, 0);
		this.list.addPoint(-1, -1, 0);
		this.list.addPoint(1, -1, 0);
		
		this.list.addPoint(1, 1, 2);
		this.list.addPoint(-1, 1, 2);
		this.list.addPoint(-1, -1, 2);
		this.list.addPoint(1, -1, 2);
		
		this.list.addPoint(-1, 1, 2);
		this.list.addPoint(-1, 1, 0);
		this.list.addPoint(-1, -1, 0);
		this.list.addPoint(-1, -1, 2);
		
		this.list.addPoint(1, 1, 2);
		this.list.addPoint(1, 1, 0);
		this.list.addPoint(1, -1, 0);
		this.list.addPoint(1, -1, 2);
	}

	@Override
	public void draw(GL2 gl) {
		float[][] mat = this.list.toArray();
		gl.glLoadIdentity();                 // Reset the model-view matrix
		gl.glBegin(GL2.GL_QUADS);
		this.drawQuadr(gl, mat, 12, 16, new Color(1, 0, 0));
		this.drawQuadr(gl, mat, 4, 8, new Color(0, 0, 1));
		this.drawQuadr(gl, mat, 8, 12, new Color(1, 0, 0));
		this.drawQuadr(gl, mat, 0, 4, new Color(0, 1, 0));
		gl.glEnd();
		gl.glFlush();
	}
	
	private void drawQuadr(GL2 gl, float[][] mat, int begin, int end, Color color) {
		gl.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		for(int i=begin; i<end; i++) {
			gl.glVertex3f(mat[0][i], mat[1][i], mat[2][i]);
		}
	}
}
