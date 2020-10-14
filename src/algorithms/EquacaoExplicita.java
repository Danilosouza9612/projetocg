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

	public void line(int x1,int y1, int x2,int y2, boolean functionX) {
		int y_aux;
		CircleDrawer circleDrawer = new CircleDrawerFactory().createInstance(this.gl, functionX);
		int x;
		float m, y;
		int dx, dy;
		dx = x2 - x1;
		dy = y2 - y1;
		m = (float)dy/dx;
		x = x1;
		while(x <= x2) {
			y = m * (x - x1) + y1;
			y_aux = Math.round(y);
			circleDrawer.drawCircle(Math.round(x), Math.round(y_aux), this.color);
			x++;
		}
	}

	@Override
	public void drawFirstOctant(int x1, int y1, int x2, int y2) {
		line(x1, y1, x2, y2, false);
	}

	@Override
	public void drawSecondOctant(int x1, int y1, int x2, int y2) {
		line(y1, x1, y2, x2, true);
	}

	@Override
	public void drawThirdOctant(int x1, int y1, int x2, int y2) {
		line(y2, x2, y1, x1, true);
	}

	@Override
	public void drawFourthOctant(int x1, int y1, int x2, int y2) {
		line(x1, y1, x2, y2, false);
	}

	@Override
	public void drawFifthOctant(int x1, int y1, int x2, int y2) {
		line(x2, y2, x1, y1, false);
	}

	@Override
	public void drawSixthOctant(int x1, int y1, int x2, int y2) {
		line(y2, x2, y1, x1, true);
	}

	@Override
	public void drawSeventhOctant(int x1, int y1, int x2, int y2) {
		line(y1, x1, y2, x2, true);
	}

	@Override
	public void drawEighthOctant(int x1, int y1, int x2, int y2) {
		line(x2, y2, x1, y1, false);
	}
}


