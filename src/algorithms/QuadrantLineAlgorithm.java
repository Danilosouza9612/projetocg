package algorithms;

import com.jogamp.opengl.GL2;

public abstract class QuadrantLineAlgorithm extends LineAlgorithm{

	public QuadrantLineAlgorithm(GL2 gl) {
		super(gl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(int x1, int y1, int x2, int y2) {
		int dx = x2-x1;
		int dy = y2-y1;
		
		if(dx>0 && dy>0) {
			this.drawFirstQuadrant(x1, y1, x2, y2);
		}else if(dx>0 && dy<0) {
			this.drawSecondQuadrant(x1, y1, x2, y2);
		}else if(dx<0 && dy<0) {
			this.drawThirdQuadrant(x1, y1, x2, y2);
		}else if(dx<0 && dy>0) {
			this.drawFourthQuadrant(x1, y1, x2, y2);
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
	
	public abstract void drawFirstQuadrant(int x1, int y1, int x2, int y2);
	public abstract void drawSecondQuadrant(int x1, int y1, int x2, int y2);
	public abstract void drawThirdQuadrant(int x1, int y1, int x2, int y2);
	public abstract void drawFourthQuadrant(int x1, int y1, int x2, int y2);

}
