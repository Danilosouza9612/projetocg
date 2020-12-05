package renderer;

import com.jogamp.opengl.GL2;

import model.Composite;
import model.Drawing;
import service.AppService;

public class RenderObject implements DrawEvent{
	@Override
	public void draw(GL2 gl) {
		AppService appState = AppService.getInstance();
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
		Composite composite = AppService.getInstance().getTransformation();
		Drawing drawing = AppService.getInstance().getDrawing();
		drawing.init();
		//drawing.applyTransform(composite);
		drawing.draw(gl);
	}
	
	public void drawAxis(GL2 gl) {
	    gl.glDisable(GL2.GL_LIGHTING);
	    gl.glDisable(GL2.GL_LIGHT0);
		
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3d(-15, 0, 0);
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glVertex3d(14, 0, 0);
		gl.glEnd();

		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3d(0, -15, 0);
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3d(0, 14, 0);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glVertex3d(0, 0, -15);
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glVertex3d(0, 0, 14);
		gl.glEnd();
		
	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glEnable(GL2.GL_LIGHT0);
		
	}
	
	public void drawXYMesh(GL2 gl) {
		AppService appState = AppService.getInstance();
	    gl.glDisable(GL2.GL_LIGHTING);
	    gl.glDisable(GL2.GL_LIGHT0);
		for(int i=-15; i<15; i++) {
			if(i==0 && appState.isShowAxis()) i++;
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(-15, i, 0);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(14, i, 0);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(i, -15, 0);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(i, 14, 0);
			gl.glEnd();
		}
	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glEnable(GL2.GL_LIGHT0);
	}
	
	public void drawXZMesh(GL2 gl) {
		AppService appState = AppService.getInstance();
	    gl.glDisable(GL2.GL_LIGHTING);
	    gl.glDisable(GL2.GL_LIGHT0);
		for(int i=-15; i<15; i++) {
			if(i==0 && appState.isShowAxis()) i++;
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(-15, 0, i);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(14, 0, i);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(i, 0, -15);
			gl.glColor3f(0.4f, 0.4f, 0.4f);
			gl.glVertex3d(i, 0, 14);
			gl.glEnd();
		}
	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glEnable(GL2.GL_LIGHT0);
	}
	
	public void drawYZMesh(GL2 gl) {
		AppService appState = AppService.getInstance();
	    gl.glDisable(GL2.GL_LIGHTING);
	    gl.glDisable(GL2.GL_LIGHT0);
		for(int i=-15; i<15; i++) {
			if(i==0 && appState.isShowAxis()) i++;
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.8f, 0.8f, 0.8f);
			gl.glVertex3d(0, i, -15);
			gl.glColor3f(0.8f, 0.8f, 0.8f);
			gl.glVertex3d(0, i, 14);
			gl.glEnd();
			
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glColor3f(0.8f, 0.8f, 0.8f);
			gl.glVertex3d(0, -15, i);
			gl.glColor3f(0.8f, 0.8f, 0.8f);
			gl.glVertex3d(0, 14, i);
			gl.glEnd();
		}
	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glEnable(GL2.GL_LIGHT0);
	}

}
