package renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;

import com.jogamp.opengl.GLAutoDrawable;

public class Renderer implements GLEventListener {
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl2 = drawable.getGL().getGL2();
		RenderConfig renderConfig = new RenderConfig();
	    RenderObject renderObject = new RenderObject();
		renderConfig.draw(gl2);
		renderObject.draw(gl2);
	    gl2.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	}

}