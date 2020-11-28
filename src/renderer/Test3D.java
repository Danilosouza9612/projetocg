package renderer;

import com.jogamp.opengl.GL2;

import model.AppState;
import model.Composite;
import model.CubeDrawing;
import model.Drawing;
import model.ObjDrawing;
import model.Scale;
import model.Transformation;

public class Test3D implements DrawEvent{
	@Override
	public void draw(GL2 gl) {
		AppState appState = AppState.getInstance();
		if(appState.isShowXYgrid()) {
			this.drawXYMesh(gl);
		}
		if(appState.isShowXZGrid()) {
			this.drawXZMesh(gl);
		}
		if(appState.isShowYZgrid()) {
			this.drawYZMesh(gl);
		}
		if(appState.isShowAxis()) {
			this.drawAxis(gl);
		}
		Composite composite = AppState.getInstance().getTransformation();
		Drawing drawing = AppState.getInstance().getDrawing();
		drawing.init();
		drawing.applyTransform(composite);
		drawing.draw(gl);
	}
	
	public void drawAxis(GL2 gl) {
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3d(-100, 0, 0);
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3d(100, 0, 0);
		gl.glEnd();

		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3d(0, -100, 0);
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3d(0, 100, 0);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glVertex3d(0, 0, -100);
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glVertex3d(0, 0, 100);
		gl.glEnd();
		
	}
	
	public void drawXYMesh(GL2 gl) {
		AppState appState = AppState.getInstance();
	    gl.glDisable(GL2.GL_LIGHTING);
	    gl.glDisable(GL2.GL_LIGHT0);
		for(int i=-100; i<100; i++) {
			if(i==0 && appState.isShowAxis()) i++;
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(-100, i, 0);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(100, i, 0);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(i, -100, 0);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(i, 100, 0);
			gl.glEnd();
		}
	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glEnable(GL2.GL_LIGHT0);
	}
	
	public void drawXZMesh(GL2 gl) {
		AppState appState = AppState.getInstance();
	    gl.glDisable(GL2.GL_LIGHTING);
	    gl.glDisable(GL2.GL_LIGHT0);
		for(int i=-100; i<100; i++) {
			if(i==0 && appState.isShowAxis()) i++;
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(-100, 0, i);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(100, 0, i);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(i, 0, -100);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(i, 0, 100);
			gl.glEnd();
		}
	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glEnable(GL2.GL_LIGHT0);
	}
	
	public void drawYZMesh(GL2 gl) {
		AppState appState = AppState.getInstance();
	    gl.glDisable(GL2.GL_LIGHTING);
	    gl.glDisable(GL2.GL_LIGHT0);
		for(int i=-100; i<100; i++) {
			if(i==0 && appState.isShowAxis()) i++;
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.8f, 0.8f, 0.8f);
			gl.glVertex3d(0, i, -100);
			gl.glColor3f(0.8f, 0.8f, 0.8f);
			gl.glVertex3d(0, i, 100);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.8f, 0.8f, 0.8f);
			gl.glVertex3d(0, -100, i);
			gl.glColor3f(0.8f, 0.8f, 0.8f);
			gl.glVertex3d(0, 100, i);
			gl.glEnd();
		}
	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glEnable(GL2.GL_LIGHT0);
	}

}
