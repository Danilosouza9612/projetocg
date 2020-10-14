package service;
import config.AppConfig;
import model.Line;
import model.LineAlgorithm;

public class LineService {
	private static LineService instance;
	
	private LineService() {
	}
	
	public static LineService getInstance() {
		if(instance==null) {
			instance = new LineService();
		}
		return instance;
	}
	
	public void draw(int x1, int y1, int x2, int y2, boolean drawDDA, boolean drawPontoMedio, boolean drawEquacao) {
		Line line = new Line(x1, y1, x2, y2);
		if(!drawDDA && !drawPontoMedio && !drawEquacao) {
			throw new IllegalArgumentException("Selecione pelo menos um algoritmo de rasterização");
		}
		if(drawDDA) {
			line.addAlgorithm(LineAlgorithm.DDA);
		}
		if(drawPontoMedio){
			line.addAlgorithm(LineAlgorithm.PONTO_MEDIO);
		}
		if(drawEquacao) {
			line.addAlgorithm(LineAlgorithm.EQUACAO_EXPLICITA);
		}
		AppConfig.getInstance().setLine(line);
	}
	
	public void setShowMess(boolean showMess) {
		AppConfig.getInstance().setMess(showMess);
	}
	
	public void setOriginalLine(boolean originalLine) {
		AppConfig.getInstance().setOriginalLine(originalLine);
	}
	
	public void setMessLength(int messLength) {
		AppConfig.getInstance().setMessLength(messLength);
	}
}

