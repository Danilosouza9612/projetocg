package renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import model.UserView;
import service.AppService;

import com.jogamp.opengl.GLAutoDrawable;

public class Renderer implements GLEventListener {
	@Override
	public void display(GLAutoDrawable drawable) {
		AppService appState = AppService.getInstance();
		UserView userView = appState.getUserView();
		GL2 gl2 = drawable.getGL().getGL2();
	    gl2.glClearColor(0.1f, 0.1f, 0.1f, 0);
	    gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		GLU glu = new GLU();
	    gl2.glEnable(GL2.GL_DEPTH_TEST);

	    gl2.glMatrixMode(GL2.GL_MODELVIEW);
	    gl2.glMatrixMode(GL2.GL_PROJECTION);
	    gl2.glLoadIdentity();

		glu.gluPerspective(30,1,1,1000);
		//glu.gluLookAt(0,10,10, 0,0,0, 0,1,0);
		glu.gluLookAt(userView.getX(), userView.getY(), userView.getZ(), 0,0,0, 0,userView.getUpY(),0);

	    this.light(gl2);
	    
	    RenderObject test3D = new RenderObject();
	    test3D.draw(gl2);
	    	    
	    gl2.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	}
	
	private void light(GL2 gl) {
	    float[] position = { 0, 20, 30, 1 };
	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position, 0);

	    gl.glEnable(GL2.GL_LIGHT0);
	}
}