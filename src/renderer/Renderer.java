package renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import com.jogamp.opengl.GLAutoDrawable;

public class Renderer implements GLEventListener {
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl2 = drawable.getGL().getGL2();
	    gl2.glClearColor(0.1f, 0.1f, 0.1f, 0);
	    gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		GLU glu = new GLU();
	    gl2.glEnable(GL2.GL_DEPTH_TEST);

	    gl2.glMatrixMode(GL2.GL_MODELVIEW);
	    gl2.glMatrixMode(GL2.GL_PROJECTION);
	    gl2.glLoadIdentity();

		glu.gluPerspective(30,1,1,1000);
		glu.gluLookAt(-10,10,-30, 0,0,29, 0,1,0);
		
	    this.light(gl2);
	    
	    Test3D test3D = new Test3D();
	    test3D.draw(gl2);
	    
	    //this.wall1(gl2);
	    //this.wall2(gl2);
	    //this.wall3(gl2);
	    
	    
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
	
	private void wall1(GL2 gl2) {
	    float[] colors = { 0, 1, 0, 1 };
	    gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, colors, 0);

	    gl2.glBegin(GL2.GL_QUADS);

	    for (int i = 0; i < 20; i++) {
		    for (int j = 0; j < 20; j++) {
		        gl2.glNormal3d(0, 0, 1);
		        gl2.glVertex3f(i, j, 0);
	
		        gl2.glNormal3d(0, 0, 1);
		        gl2.glVertex3f(i + 1, j, 0);
	
		        gl2.glNormal3d(0, 0, 1);
		        gl2.glVertex3f(i + 1, j + 1, 0);
	
		        gl2.glNormal3d(0, 0, 1);
		        gl2.glVertex3f(i, j + 1, 0);
		    }
	    }

	    gl2.glEnd();
	}

	private void wall2(GL2 gl2) {
	    float[] colors = { 0, 1, 0, 1 };
	    gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, colors, 0);

	    gl2.glBegin(GL2.GL_QUADS);

	    for (int i = 0; i < 20; i++) {
		    for (int j = 0; j < 40; j++) {
		        gl2.glNormal3d(-1, 0, 0);
		        gl2.glVertex3d(20, i, j);
	
		        gl2.glNormal3d(-1, 0, 0);
		        gl2.glVertex3d(20, i + 1, j);
	
		        gl2.glNormal3d(-1, 0, 0);
		        gl2.glVertex3d(20, i + 1, j + 1);
	
		        gl2.glNormal3d(-1, 0, 0);
		        gl2.glVertex3d(20, i, j + 1);
		    }
	    }

	    gl2.glEnd();
	}

	private void wall3(GL2 gl2) {
	    float[] colors = { 0, 1, 0, 1 };
	    gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, colors, 0);

	    gl2.glBegin(GL2.GL_QUADS);

	    for (int i = 0; i < 20; i++) {
		    for (int j = 0; j < 20; j++) {
		        gl2.glNormal3d(0, 0, -1);
		        gl2.glVertex3d(20 - i, j, 40);
	
		        gl2.glNormal3d(0, 0, -1);
		        gl2.glVertex3d(20 - i - 1, j, 40);
	
		        gl2.glNormal3d(0, 0, -1);
		        gl2.glVertex3d(20 - i - 1, j + 1, 40);
	
		        gl2.glNormal3d(0, 0, -1);
		        gl2.glVertex3d(20 - i, j + 1, 40);
		    }
	    }

	    gl2.glEnd();
	}
}