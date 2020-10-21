package renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import config.AppConfig;

import com.jogamp.opengl.GLAutoDrawable;

public class Renderer implements GLEventListener {
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		DrawEventQueue queue = DrawEventQueue.getInstance();
		DrawEvent event;
		while(!queue.isEmpty()) {
			event = queue.deQueue();
			event.draw(gl);
		}
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		AppConfig appConfig = AppConfig.getInstance();
		gl.glClearColor(1, 1, 1, 0);
		gl.glOrtho(0, appConfig.getLastMesh(), 0, appConfig.getLastMesh(), 0, 1);
		gl.glViewport(0, 0, 400, 400);
		DrawEventQueue.getInstance().enQueueNotNotify(new ReRenderDrawEvent());
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		/*GL2 gl = drawable.getGL().getGL2();
		AppConfig appConfig = AppConfig.getInstance();
		appConfig.calculateMessLength(w, h);
		gl.glClearColor(1, 1, 1, 0);
		gl.glOrtho(0, appConfig.getLastMesh(), 0, appConfig.getLastMesh(), 0, 1);
		gl.glViewport(0, 0, w, h);*/
	}
}