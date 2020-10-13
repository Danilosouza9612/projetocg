package algorithms;

import com.jogamp.opengl.GL2;

public class OriginalLine extends LineDrawer{

	public OriginalLine(GL2 gl) {
		super(gl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float[] defineColor() {
		// TODO Auto-generated method stub
		float[] color = {1.0f, 0.0f, 0.0f};
		return color;
	}

	@Override
	public void draw(int x1, int y1, int x2, int y2) {
		this.gl.glBegin(GL2.GL_LINES);
		this.gl.glColor3d(this.color[0], this.color[1], this.color[2]);
		this.gl.glVertex2d(x1, y1);
		this.gl.glColor3d(this.color[0], this.color[1], this.color[2]);
		this.gl.glVertex2d(x2, y2);
		this.gl.glEnd();
		this.gl.glFlush();
	}

}
