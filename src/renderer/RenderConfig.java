package renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

import model.UserView;
import service.AppService;

public class RenderConfig implements DrawEvent{

	@Override
	public void draw(GL2 gl2) {
		AppService appState = AppService.getInstance();
		UserView userView = appState.getUserView();
	    gl2.glClearColor(0.1f, 0.1f, 0.1f, 0);
	    gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		GLU glu = new GLU();
		gl2.glEnable(GL2.GL_MULTISAMPLE);
	    gl2.glEnable(GL2.GL_DEPTH_TEST);

	    gl2.glMatrixMode(GL2.GL_MODELVIEW);
	    gl2.glMatrixMode(GL2.GL_PROJECTION);
	    
	    gl2.glEnable(GL2.GL_BLEND);
	    gl2.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
	    gl2.glLoadIdentity();

		glu.gluPerspective(30,1,1,1000);
		//glu.gluLookAt(0,10,10, 0,0,0, 0,1,0);
		glu.gluLookAt(userView.getX(), userView.getY(), userView.getZ(), 0,0,0, 0,1,0);
	    
		//gl2.glTranslatef(userView.getX(), userView.getY(), userView.getZ());
		gl2.glRotatef(-userView.getAngleX(), 1, 0, 0);
		gl2.glRotatef(userView.getAngleY(), 0, 1, 0);
		if(appState.isShowXYgrid()) {
			this.drawXYMesh(gl2);
		}
		if(appState.isShowXZGrid()) {
			this.drawXZMesh(gl2);
		}
		if(appState.isShowYZgrid()) {
			this.drawYZMesh(gl2);
		}
	}
	
	public void drawXYMesh(GL2 gl) {
	    this.drawXYLine(gl);
		for(int i=-15; i<15; i++) {
			if(i==0) i++;
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
	}
	
	public void drawXZMesh(GL2 gl) {
	    gl.glDisable(GL2.GL_LIGHTING);
	    gl.glDisable(GL2.GL_LIGHT0);
	    this.drawXZLine(gl);
		for(int i=-15; i<15; i++) {
			if(i==0) i++;
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
	    gl.glDisable(GL2.GL_LIGHTING);
	    gl.glDisable(GL2.GL_LIGHT0);
	    this.drawYZLine(gl);
		for(int i=-15; i<15; i++) {
			if(i==0) i++;
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
	
	public void drawYZLine(GL2 gl) {
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0, 0, 1f);
		gl.glVertex3d(0, 0, -15);
		gl.glColor3f(0, 0, 1f);
		gl.glVertex3d(0, 0, 14);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0, 1, 0);
		gl.glVertex3d(0, -15, 0);
		gl.glColor3f(0, 1, 0);
		gl.glVertex3d(0, 14, 0);
		gl.glEnd();
	}
	
	public void drawXZLine(GL2 gl) {
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(1, 0, 0);
		gl.glVertex3d(-15, 0, 0);
		gl.glColor3f(1, 0, 0);
		gl.glVertex3d(14, 0, 0);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0, 0, 1);
		gl.glVertex3d(0, 0, -15);
		gl.glColor3f(0, 0, 1);
		gl.glVertex3d(0, 0, 14);
		gl.glEnd();
	}
	
	public void drawXYLine(GL2 gl) {
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(1, 0, 0);
		gl.glVertex3d(-15, 0, 0);
		gl.glColor3f(1, 0, 0);
		gl.glVertex3d(14, 0, 0);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(0, 1, 0);
		gl.glVertex3d(0, -15, 0);
		gl.glColor3f(0, 1, 0);
		gl.glVertex3d(0, 14, 0);
		gl.glEnd();
	}
}
