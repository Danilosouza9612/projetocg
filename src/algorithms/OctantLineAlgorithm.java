package algorithms;

import com.jogamp.opengl.GL2;

public abstract class OctantLineAlgorithm extends LineDrawer{	
	public OctantLineAlgorithm(GL2 gl) {
		super(gl);
	}
	
	public void drawCircle() {
		//Desenhar o pixel centrado aqui
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
