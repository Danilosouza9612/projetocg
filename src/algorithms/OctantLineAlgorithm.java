package algorithms;

import com.jogamp.opengl.GL2;

public abstract class OctantLineAlgorithm extends LineDrawer{	
	public OctantLineAlgorithm(GL2 gl) {
		super(gl);
	}
	
	public void drawCircle(int x, int y) {
		this.gl.glColor3f(color[0], color[1], color[2]);
		int lados = 70;
		double raio = 0.25;
		double PI = 3.1415;
			
		gl.glBegin(GL2.GL_POLYGON);
	    for(int i = 0; i < lados; i++){
	    	gl.glVertex2d(x + raio*Math.cos(i*2*PI/lados), y + raio*Math.sin(i*2*PI/lados));
	    }
	    gl.glEnd();
	}
	public void draw(int x1, int y1, int x2, int y2) {
		int dx = x2-x1;
		int dy = y2-y1;
		double m;
		
		if(dx>0 && dy>0) {
			m = (double)dy/dx;
			if(m<=1) {
				this.drawFirstOctant(x1, y1, x2, y2);
			}else{
				this.drawSecondOctant(x1, y1, x2, y2);
			}
		}else if(dx>0 && dy<0) {
			m = (double)dy/dx;
			if(m<-1) {
				this.drawThirdOctant(x1, y1, x2, y2);
			}else{
				this.drawFourthOctant(x1, y1, x2, y2);
			}
		}else if(dx<0 && dy<0) {
			m = (double)dy/dx;
			if(m<=1) {
				this.drawFifthOctant(x1, y1, x2, y2);
			}else {
				this.drawSixthOctant(x1, y1, x2, y2);
			}
		}else if(dx<0 && dy>0) {
			m = (double)dy/dx;
			if(m<-1) {
				this.drawSeventhOctant(x1, y1, x2, y2);
			}else {
				this.drawEighthOctant(x1, y1, x2, y2);
			}
		}else if(dx>0 && dy==0) {
			this.constantLineHorizontal(x1, x2, y1);
		}else if(dx<0 && dy==0) {
			this.constantLineHorizontal(x2, x1, y1);
		}else if(dx==0 && dy>0) {
			this.constantLineVertical(y1, y2, x1);
		}else{
			this.constantLineVertical(y2, y1, x1);
		}
	}
	
	public void constantLineHorizontal(int x1, int x2, int y) {
		for(int x=x1; x<=x2; x++) {
			this.drawCircle(x, y);
		}
	}
	
	public void constantLineVertical(int y1, int y2, int x) {
		for(int y=y1; y<=y2; y++) {
			this.drawCircle(x, y);
		}
	}
	
	public abstract void drawFirstOctant(int x1, int y1, int x2, int y2);
	public abstract void drawSecondOctant(int x1, int y1, int x2, int y2);
	public abstract void drawThirdOctant(int x1, int y1, int x2, int y2);
	public abstract void drawFourthOctant(int x1, int y1, int x2, int y2);
	public abstract void drawFifthOctant(int x1, int y1, int x2, int y2);
	public abstract void drawSixthOctant(int x1, int y1, int x2, int y2);
	public abstract void drawSeventhOctant(int x1, int y1, int x2, int y2);
	public abstract void drawEighthOctant(int x1, int y1, int x2, int y2);
}
