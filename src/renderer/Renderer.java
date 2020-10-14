package renderer;

import java.util.List;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;

import algorithms.DDA;
import algorithms.EquacaoExplicita;
import algorithms.OriginalLine;
import algorithms.PontoMedio;
import config.AppConfig;
import model.Line;
import model.LineAlgorithm;

import com.jogamp.opengl.GLAutoDrawable;

public class Renderer implements GLEventListener {
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		AppConfig appConfig = AppConfig.getInstance();
		PontoMedio pontoMedio = new PontoMedio(gl);
		EquacaoExplicita equacao = new EquacaoExplicita(gl);
		DDA dda = new DDA(gl);
		OriginalLine originalLine = new OriginalLine(gl);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		if(appConfig.isShowMess()) {
			this.drawMalha(gl);
		}
		Line line = appConfig.getLine();
		if(line!=null) {
			List<LineAlgorithm> algorithms = line.getAlgorithms();
			for(LineAlgorithm algorithm : algorithms) {
				switch(algorithm) {
				case DDA:
					dda.draw(line.getX1(), line.getY1(), line.getX2(), line.getY2());
					break;
				case PONTO_MEDIO:
					pontoMedio.draw(line.getX1(), line.getY1(), line.getX2(), line.getY2());
					break;
				case EQUACAO_EXPLICITA:
					equacao.draw(line.getX1(), line.getY1(), line.getX2(), line.getY2());
					break;
				}
			}
			if(appConfig.isOriginalLine()) {
				originalLine.draw(line.getX1(), line.getY1(), line.getX2(), line.getY2());
			}
		}
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		AppConfig appConfig = AppConfig.getInstance();
		gl.glViewport(0, 0, appConfig.getViewPortWidth(), appConfig.getViewPortHeight());
		gl.glClearColor(1, 1, 1, 0);
		gl.glOrtho(0, appConfig.getLastMesh(), 0, appConfig.getLastMesh(), 0, 1);
	}
	
	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
	}
	
	public void drawMalha(GL2 gl) {
		AppConfig appConfig = AppConfig.getInstance();
		gl.glColor3f(0.8f,0.8f,0.8f);
		for(int i=0; i<=appConfig.getLastMesh(); i++) {
			gl.glBegin(GL2.GL_LINES);
			gl.glColor3f(0.8f,0.8f,0.8f);
			gl.glVertex2d(i, 0);
			gl.glColor3f(0.8f,0.8f,0.8f);
			gl.glVertex2d(i, appConfig.getLastMesh());
			gl.glEnd();
		}
		for(int i=0; i<=appConfig.getLastMesh(); i++) {
			gl.glBegin(GL2.GL_LINES);
			gl.glColor3f(0.8f,0.8f,0.8f);
			gl.glVertex2d(0, i);
			gl.glColor3f(0.8f,0.8f,0.8f);
			gl.glVertex2d(appConfig.getLastMesh(), i);
			gl.glEnd();
		}
	}
}