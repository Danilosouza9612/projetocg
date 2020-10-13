package algorithms;

import com.jogamp.opengl.GL2;

public class DDA extends OctantLineAlgorithm{

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
		this.gl.glPointSize(1.0f);
		this.gl.glBegin(GL2.GL_POINTS);
		for(int k = 1; k <= steps; k++) {
			this.gl.glColor3f(this.color[0],this.color[1],this.color[2]);
			this.gl.glVertex2d(Math.round(x), Math.round(y));
			x += xInc;
			y += yInc;
		}
		this.gl.glVertex2d(x, y);
		this.gl.glEnd();
		this.gl.glFlush();
	}
	
	public void lineDesc(int x1, int y1, int x2, int y2) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		int aux;
		int steps;
		
		System.out.println(Math.abs(dx));
		System.out.println(Math.abs(dy));
		
		if(Math.abs(dx) > Math.abs(dy)) {
			steps = Math.abs(dx);
		}
		else {
			System.out.println("Entrou aqui");
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
		this.gl.glPointSize(1.0f);
		this.gl.glBegin(GL2.GL_POINTS);
		for(int k = 1; k <= steps; k++) {
			this.gl.glColor3f(this.color[0],this.color[1],this.color[2]);
			this.gl.glVertex2d(Math.round(x), Math.round(y));
			x += xInc;
			y += yInc;
		}
		this.gl.glVertex2d(x, y);
		this.gl.glEnd();
		this.gl.glFlush();
	}

	@Override
	public void drawFirstOctant(int x1, int y1, int x2, int y2) {
		this.lineAsc(x1, y1, x2, y2);
		
	}

	@Override
	public void drawSecondOctant(int x1, int y1, int x2, int y2) {
		this.lineAsc(x1, y1, x2, y2);
		
	}

	@Override
	public void drawThirdOctant(int x1, int y1, int x2, int y2) {
		this.lineDesc(x1, y1, x2, y2);
		
	}

	@Override
	public void drawFourthOctant(int x1, int y1, int x2, int y2) {
		this.lineDesc(x1, y1, x2, y2);
		
	}

	@Override
	public void drawFifthOctant(int x1, int y1, int x2, int y2) {
		this.lineAsc(x2, y2, x1, y1);
		
	}

	@Override
	public void drawSixthOctant(int x1, int y1, int x2, int y2) {
		this.lineAsc(x2, y2, x1, y1);
		
	}

	@Override
	public void drawSeventhOctant(int x1, int y1, int x2, int y2) {
		this.lineDesc(x2, y2, x1, y1);
		
	}

	@Override
	public void drawEighthOctant(int x1, int y1, int x2, int y2) {
		this.lineDesc(x2, y2, x1, y1);
	}

}
