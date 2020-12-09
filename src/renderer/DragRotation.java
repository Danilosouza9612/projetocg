package renderer;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.concurrent.Semaphore;

import service.AppService;

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
			AppService appState = AppService.getInstance();
			PointerInfo pointerInfo;
			Point point, tempPoint=MouseInfo.getPointerInfo().getLocation();
			int dX, dY;
			int tempX = (int) tempPoint.getX(), tempY = (int) tempPoint.getY();
			while(dragRunning) {
				pointerInfo = MouseInfo.getPointerInfo();
				point = pointerInfo.getLocation();
				if(point.getX()==tempX) {
					this.iex = (int) point.getX();
				}else {
					dX = (int) (point.getX()-iex);
					appState.rotateY(dX);
					tempX=(int) point.getX();
				}
				if(point.getY()==tempY) {
					this.iey = (int) point.getY();
				}else {
					dY = (int) (point.getY()-iey);
					appState.rotateX(dY);
					tempY=(int) point.getY();
				}
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
