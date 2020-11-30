package model;

import pattern.observer.Observable;
import pattern.observer.Observer;

public class AppState implements Observable{
	private static AppState instance;
	private boolean showXZGrid;
	private boolean showYZgrid;
	private boolean showXYgrid;
	private boolean showAxis;
	private Composite transformations;
	private Drawing drawing;
	private Observer observer;
	private UserView userView;
	
	private AppState() {
		this.showXZGrid = true;
		Composite composite = new Composite();
		composite.addTransformation(new Scale(2,2,2));
		composite.addTransformation(new Scale(1,1,2));
		this.transformations = composite;
		this.drawing = new CubeDrawing();
		this.userView = new UserView();
	}
	
	public static AppState getInstance() {
		if(instance==null) instance = new AppState();
		return instance;
	}
	
	public boolean isShowXZGrid() {
		return showXZGrid;
	}
	
	public void setShowXZGrid(boolean showXZGrid) {
		this.showXZGrid = showXZGrid;
		this.notificar();
	}
	
	public boolean isShowYZgrid() {
		return showYZgrid;
	}
	
	public void setShowYZgrid(boolean showYZgrid) {
		this.showYZgrid = showYZgrid;
		this.notificar();
	}
	
	public boolean isShowXYgrid() {
		return showXYgrid;
	}
	
	public void setShowXYgrid(boolean showXYgrid) {
		this.showXYgrid = showXYgrid;
		this.notificar();
	}
	
	public Composite getTransformation() {
		return this.transformations;
	}
	
	public void addTransformation(Transformation transformation) {
		this.transformations.addTransformation(transformation);
		this.notificar();
	}
	
	public void removeTransformation(Transformation transformation) {
		this.transformations.removeTransformation(transformation);
		this.notificar();
	}

	public boolean isShowAxis() {
		return showAxis;
	}

	public void setShowAxis(boolean showAxis) {
		this.showAxis = showAxis;
		this.notificar();
	}

	public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}
	

	public Drawing getDrawing() {
		return drawing;
	}

	public void setDrawing(Drawing drawing) {
		this.drawing = drawing;
	}
	
	
	public UserView getUserView() {
		return userView;
	}
	
	public void rotateX(int deg) {
		this.userView.rotateX(-deg/16);
		this.notificar();
	}
	
	public void rotateY(int deg) {
		this.userView.rotateY(-deg/16);
		this.notificar();
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}

	@Override
	public void notificar() {
		if(this.observer !=null)
			this.observer.atualizar();
	}
}
