package algorithms;

import com.jogamp.opengl.GL2;

public class CircleDrawerFactory {
	public CircleDrawer createInstance(GL2 gl, boolean functionX) {
		if(functionX) {
			return new FunctionXCircleDrawer(gl);
		}
		return new FunctionYCircleDrawer(gl);
	}
	public CircleDrawer createInstance(GL2 gl) {
		return new FunctionYCircleDrawer(gl);
	}
}
