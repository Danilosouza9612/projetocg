package algorithms;

import com.jogamp.opengl.GL2;

public abstract class LineDrawer {
	protected GL2 gl;
	protected float[] color;
	
	public LineDrawer(GL2 gl) {
		this.gl = gl;
		this.color = this.defineColor();
	}
	
	public abstract float[] defineColor();
	public abstract void draw(int x1, int y1, int x2, int y2);
}
