package algorithms;

import com.jogamp.opengl.GL2;

public abstract class CircleDrawer {
	protected GL2 gl;
	
	public CircleDrawer(GL2 gl){
		this.gl = gl;
	}
	
	public abstract void drawCircle(int x, int y, float[] color);
}
