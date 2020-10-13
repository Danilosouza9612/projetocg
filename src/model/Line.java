package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Line {
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private List<LineAlgorithm> algorithms;
	
	public Line(int x1, int y1, int x2, int y2) {
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.algorithms = new ArrayList<LineAlgorithm>();
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public List<LineAlgorithm> getAlgorithms() {
		return algorithms;
	}
	
	public void addAlgorithm(LineAlgorithm algorithm) {
		this.algorithms.add(algorithm);
		this.algorithms.sort(new Comparator<LineAlgorithm>() {
			@Override
			public int compare(LineAlgorithm o1, LineAlgorithm o2) {
				if(o1.getOrder()>o2.getOrder()) {
					return -1;
				}
				if(o1.getOrder()==o2.getOrder()) {
					return 0;
				}
				return 1;
			}
		});
	}
}
