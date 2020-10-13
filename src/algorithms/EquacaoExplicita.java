package algorithms;

import com.jogamp.opengl.GL2;

public class EquacaoExplicita extends OctantLineAlgorithm{

	public EquacaoExplicita(GL2 gl) {
		super(gl);
		// TODO Auto-generated constructor stub
	}
	
	public float[] defineColor() {
		float[] color = {0.0f,1.0f,0.0f};
		return color;
	}

	public void line(int x1,int y1, int x2,int y2) {
		int y_aux;
		
		int x;
		float m, y;
		int dx, dy;
		dx = x2 - x1;
		dy = y2 - y1;
		m = (float)dy/dx;
		x = x1;
		this.gl.glPointSize(1.0f);
		this.gl.glBegin(GL2.GL_POINTS);
		while(x <= x2) {
			y = m * (x - x1) + y1;
			y_aux = Math.round(y);
			this.gl.glColor3f(this.color[0],this.color[1],this.color[2]);
			this.gl.glVertex2d(x, y_aux);
			x++;
		}
		this.gl.glEnd();
		this.gl.glFlush();
	}
	
	public void lineFunctionX(int x1,int y1, int x2,int y2) {
		int y_aux;
		
		int x;
		float m, y;
		int dx, dy;
		dx = x2 - x1;
		dy = y2 - y1;
		m = (float)dy/dx;
		x = x1;
		this.gl.glPointSize(1.0f);
		this.gl.glBegin(GL2.GL_POINTS);
		while(x <= x2) {
			y = m * (x - x1) + y1;
			y_aux = Math.round(y);
			this.gl.glColor3f(this.color[0],this.color[1],this.color[2]);
			this.gl.glVertex2d(y_aux, x);
			x++;
		}
		this.gl.glEnd();
		this.gl.glFlush();
	}

	@Override
	public void drawFirstOctant(int x1, int y1, int x2, int y2) {
		line(x1, y1, x2, y2);
	}

	@Override
	public void drawSecondOctant(int x1, int y1, int x2, int y2) {
		lineFunctionX(y1, x1, y2, x2);
	}

	@Override
	public void drawThirdOctant(int x1, int y1, int x2, int y2) {
		lineFunctionX(y2, x2, y1, x1);
	}

	@Override
	public void drawFourthOctant(int x1, int y1, int x2, int y2) {
		line(x1, y1, x2, y2);
	}

	@Override
	public void drawFifthOctant(int x1, int y1, int x2, int y2) {
		line(x2, y2, x1, y1);
	}

	@Override
	public void drawSixthOctant(int x1, int y1, int x2, int y2) {
		lineFunctionX(y2, x2, y1, x1);
	}

	@Override
	public void drawSeventhOctant(int x1, int y1, int x2, int y2) {
		lineFunctionX(y1, x1, y2, x2);
	}

	@Override
	public void drawEighthOctant(int x1, int y1, int x2, int y2) {
		line(x2, y2, x1, y1);
	}
}


