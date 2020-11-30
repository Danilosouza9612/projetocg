package renderer;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.concurrent.Semaphore;

import model.AppState;

public class DragRotation {
	private static DragRotation instance;
	private int iex;
	private int iey;
	private boolean dragRunning;
	private Semaphore semaphore;
	
	private DragRotation() {
		this.semaphore = new Semaphore(1);
	}
	
	public static DragRotation getInstance() {
		if(instance==null) instance = new DragRotation();
		return instance;
	}
	
	public void initializeDraggie(Point mouse) {
		this.iex = mouse.x;
		this.iey = mouse.y;
		this.setDragRunning(true);
		this.drag();
	}
	
	public void drag() {
		new Thread(()->{
			AppState appState = AppState.getInstance();
			PointerInfo pointerInfo;
			Point point, tempPoint=MouseInfo.getPointerInfo().getLocation();
			int dX, dY;
			while(dragRunning) {
				pointerInfo = MouseInfo.getPointerInfo();
				point = pointerInfo.getLocation();
				if(point.getX()==tempPoint.getX()) {
					this.iex = (int) point.getX();
				}else {
					dX = (int) (point.getX()-iex);
					appState.rotateY(dX);
					tempPoint=point;
				}
				/*if(point.getY()==tempPoint.getY()) {
					this.iey = (int) point.getY();
				}else {
					dY = (int) (point.getY()-iey);
					appState.rotateX(dY);
					tempPoint = point;
				}*/
			}
		}).start();
	}
	
	public void setDragRunning(boolean running) {
		try {
			this.semaphore.acquire();
			this.dragRunning = running;
			this.semaphore.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
