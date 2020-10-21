package algorithms;

import com.jogamp.opengl.GL2;

public class FunctionXCircleDrawer extends CircleDrawer{
	FunctionXCircleDrawer(GL2 gl) {
		super(gl);
	}

	@Override
	public void drawCircle(int x, int y, float[] color) {
		this.gl.glColor3f(color[0], color[1], color[2]);
		int lados = 70;
		double raio = 0.25;
		double PI = 3.1415;
			
		gl.glBegin(GL2.GL_POLYGON);
	    for(int i = 0; i < lados; i++){
	    	gl.glVertex2d(y + raio*Math.cos(i*2*PI/lados), x + raio*Math.sin(i*2*PI/lados));
	    }
	    gl.glEnd();
	    gl.glFlush();
	}
}
