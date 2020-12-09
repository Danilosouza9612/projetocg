package renderer;

import com.jogamp.opengl.GL2;

import model.Drawing;
import service.AppService;

public class RenderObject implements DrawEvent{
	@Override
	public void draw(GL2 gl) {
		AppService appState = AppService.getInstance();
		Drawing drawing = appState.getDrawing();
		drawing.init();
		drawing.draw(gl);
	}
}
