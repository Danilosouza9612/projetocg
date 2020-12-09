package renderer;

import java.util.LinkedList;
import java.util.Queue;

import pattern.observer.Observable;
import pattern.observer.Observer;

public class DrawEventQueue implements Observable{
	private static DrawEventQueue instance;
	private Observer observer;
	private Queue<DrawEvent> queue;
	
	private DrawEventQueue() {
		this.queue = new LinkedList<DrawEvent>();
	}
	
	public static DrawEventQueue getInstance() {
		if(instance==null) {
			instance = new DrawEventQueue();
		}
		return instance;
	}

	@Override
	public void notificar() {
		this.observer.atualizar();	
	}
	
	public void enQueueNotNotify(DrawEvent event) {
		this.queue.add(event);
	}
	
	public void enQueue(DrawEvent event) {
		this.queue.add(event);
		this.notificar();
	}
	
	public DrawEvent deQueue() {
		return this.queue.poll();
	}
	
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}
	
	public Observer getObserver() {
		return observer;
	}
	
	public void setObserver(Observer observer) {
		this.observer = observer;
	}
}
