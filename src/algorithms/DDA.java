package algorithms;

import com.jogamp.opengl.GL2;

public class DDA extends QuadrantLineAlgorithm{

	public DDA(GL2 gl) {
		super(gl);
	}
	
	public float[] defineColor() {
		float[] color = {0.0f,0.0f,1.0f};
		return color;
	}
	
	public void lineAsc(int x1, int y1, int x2, int y2) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		int steps;
		CircleDrawer circleDrawer = new CircleDrawerFactory().createInstance(this.gl);
		
		if(Math.abs(dx) > Math.abs(dy)) {
			steps = Math.abs(dx);
		}
		else {
			steps = Math.abs(dy);
		}
		float xInc = (float) dx / (float) steps;
		float yInc = (float) dy / (float) steps;
		float x = x1;
		float y = y1;
		for(int k = 0; k <= steps; k++) {
			circleDrawer.drawCircle(Math.round(x), Math.round(y), this.color);
			x += xInc;
			y += yInc;
		}
	}
	
	public void lineDesc(int x1, int y1, int x2, int y2) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		int aux;
		int steps;
		CircleDrawer circleDrawer = new CircleDrawerFactory().createInstance(this.gl);
		
		if(Math.abs(dx) > Math.abs(dy)) {
			steps = Math.abs(dx);
		}
		else {
			steps = Math.abs(dy);
			aux=x1;
			x1=x2;
			x2=aux;
			
			aux=y1;
			y1=y2;
			y2=aux;
			
			dx = x2 - x1;
			dy = y2 - y1;
		}
		float xInc = (float) dx / (float) steps;
		float yInc = (float) dy / (float) steps;
		float x = x1;
		float y = y1;
		for(int k = 0; k <= steps; k++) {
			circleDrawer.drawCircle(Math.round(x), Math.round(y), this.color);
			x += xInc;
			y += yInc;
		}
	}

	@Override
	public void drawFirstQuadrant(int x1, int y1, int x2, int y2) {
		this.lineAsc(x1, y1, x2, y2);
	}

	@Override
	public void drawSecondQuadrant(int x1, int y1, int x2, int y2) {
		this.lineDesc(x1, y1, x2, y2);
	}

	@Override
	public void drawThirdQuadrant(int x1, int y1, int x2, int y2) {
		this.lineAsc(x2, y2, x1, y1);
	}
	
	@Override
	public void drawFourthQuadrant(int x1, int y1, int x2, int y2) {
		this.lineDesc(x2, y2, x1, y1);	
	}
}
