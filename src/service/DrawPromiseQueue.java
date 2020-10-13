package service;

import java.util.LinkedList;
import java.util.Queue;

public class DrawPromiseQueue {
	private Queue<DrawPromise> drawPromises;
	private static DrawPromiseQueue instance;
	
	private DrawPromiseQueue() {
		this.drawPromises = new LinkedList<DrawPromise>();
	}
	
	public static DrawPromiseQueue getInstance() {
		if(instance==null)
			instance = new DrawPromiseQueue();
		
		return instance;
	}
	
	public void queue(DrawPromise drawPromise) {
		this.drawPromises.add(drawPromise);
	}
	
	public DrawPromise deQueue() {
		return this.drawPromises.poll();
	}
	
	public boolean isEmpty() {
		return this.drawPromises.isEmpty();
	}
}
