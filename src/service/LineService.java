package service;

import config.AppConfig;
import model.LineAlgorithmEnum;
import renderer.DrawEventQueue;
import renderer.ReRenderDrawEvent;

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
	
	public void setX1(int x1) {
		AppConfig.getInstance().getOrCreateLine().setX1(x1);
		DrawEventQueue.getInstance().enQueue(new ReRenderDrawEvent());
	}
	
	public void setX2(int x2) {
		AppConfig.getInstance().getOrCreateLine().setX2(x2);
		DrawEventQueue.getInstance().enQueue(new ReRenderDrawEvent());
	}
	
	public void setY1(int y1) {
		AppConfig.getInstance().getOrCreateLine().setY1(y1);
		DrawEventQueue.getInstance().enQueue(new ReRenderDrawEvent());
	}
	
	public void setY2(int y2) {
		AppConfig.getInstance().getOrCreateLine().setY2(y2);
		DrawEventQueue.getInstance().enQueue(new ReRenderDrawEvent());
	}
	
	public void addLineAlgorithm(LineAlgorithmEnum lineAlgorithm) {
		AppConfig.getInstance().getOrCreateLine().addAlgorithm(lineAlgorithm);
		DrawEventQueue.getInstance().enQueue(new ReRenderDrawEvent());
	}
	
	public void removeLineAlgorithm(LineAlgorithmEnum lineAlgorithm) {
		AppConfig.getInstance().getOrCreateLine().removeAlgorithm(lineAlgorithm);
		DrawEventQueue.getInstance().enQueue(new ReRenderDrawEvent());
	}
}
