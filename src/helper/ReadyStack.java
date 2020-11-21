package helper;

import java.util.LinkedList;
import java.util.List;

public class ReadyStack<T> {
	private List<T> items;
	private int pos;
	
	public ReadyStack(){
		this.items = new LinkedList<T>();
	}
	
	public void add(T item) {
		if(pos==items.size()-1) {
			pos=items.size();
		}
		this.items.add(item);
	}
	
	public void remove(T item) {
		this.items.remove(item);
		this.resetPos();
	}
	
	public T pop() {
		T item = this.items.get(pos);
		this.pos--;
		return item;
	}
	
	public boolean end() {
		return this.pos==-1;
	}
	
	public void resetPos() {
		this.pos=items.size()-1;
	}
}
