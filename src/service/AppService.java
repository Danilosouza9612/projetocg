package service;
import config.AppConfig;
import model.Line;
import model.LineAlgorithmEnum;
import renderer.DrawEventQueue;
import renderer.ReRenderDrawEvent;

public class AppService {
	private static AppService instance;
	
	private AppService() {
	}
	
	public static AppService getInstance() {
		if(instance==null) {
			instance = new AppService();
		}
		return instance;
	}
	
	public void draw(int x1, int y1, int x2, int y2, boolean drawDDA, boolean drawPontoMedio, boolean drawEquacao) {
		Line line = new Line(x1, y1, x2, y2);
		if(!drawDDA && !drawPontoMedio && !drawEquacao) {
			throw new IllegalArgumentException("Selecione pelo menos um algoritmo de rasterização");
		}
		if(drawDDA) {
			line.addAlgorithm(LineAlgorithmEnum.DDA);
		}
		if(drawPontoMedio){
			line.addAlgorithm(LineAlgorithmEnum.PONTO_MEDIO);
		}
		if(drawEquacao) {
			line.addAlgorithm(LineAlgorithmEnum.EQUACAO_EXPLICITA);
		}
		AppConfig.getInstance().setLine(line);
	}
	
	public void setShowMess(boolean showMess) {
		AppConfig.getInstance().setMess(showMess);
		DrawEventQueue.getInstance().enQueue(new ReRenderDrawEvent());
	}
	
	public void setOriginalLine(boolean originalLine) {
		AppConfig.getInstance().setOriginalLine(originalLine);
		DrawEventQueue.getInstance().enQueue(new ReRenderDrawEvent());
	}
	
	public void setMessLength(int messLength) {
		AppConfig.getInstance().setMessLength(messLength);
		DrawEventQueue.getInstance().enQueue(new ReRenderDrawEvent());
	}
}

