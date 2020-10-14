package algorithms;

import com.jogamp.opengl.GL2;

public abstract class LineAlgorithm extends LineDrawer{
	public LineAlgorithm(GL2 gl) {
		super(gl);
		// TODO Auto-generated constructor stub
	}

	public void constantLineHorizontal(int x1, int x2, int y) {
		CircleDrawer circleDrawer = new CircleDrawerFactory().createInstance(this.gl);
		for(int x=x1; x<=x2; x++) {
			circleDrawer.drawCircle(x, y, this.color);
		}
	}
	
	public void constantLineVertical(int y1, int y2, int x) {
		CircleDrawer circleDrawer = new CircleDrawerFactory().createInstance(this.gl);
		for(int y=y1; y<=y2; y++) {
			circleDrawer.drawCircle(x, y, this.color);
		}
	}
}
