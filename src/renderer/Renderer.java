package renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
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
		GLU glu = new GLU();
		gl.glViewport(0, 0, 400, 400);
		gl.glClearColor(1, 1, 1, 0);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		// define projeção perspectiva. Deve ser colocado ANTES do gluLookAt
		glu.gluPerspective(30,1,1,1000);
		// Define a posição do observador no cenário 3D
		glu.gluLookAt(-10,10,-30, 0,0,29, 0,1,0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		DrawEventQueue.getInstance().enQueueNotNotify(new Test3D());
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