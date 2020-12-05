package service;

import model.Composite;
import model.Drawing;
import model.ObjDrawing;
import model.TransformCallBack;
import model.TransformCallBackFactory;
import model.UserView;
import pattern.observer.Observable;
import pattern.observer.Observer;

public class AppService implements Observable{
	private static AppService instance;
	private boolean showXZGrid;
	private boolean showYZgrid;
	private boolean showXYgrid;
	private boolean showAxis;
	private Composite transformations;
	private Drawing drawing;
	private Observer observer;
	private UserView userView;
	
	private AppService() {
		TransformCallBackFactory factory = new TransformCallBackFactory();
		this.showXZGrid = true;
		this.showAxis = true;
		this.drawing = new ObjDrawing("src//diverso.obj");
		this.userView = new UserView();
	}
	
	public static AppService getInstance() {
		if(instance==null) instance = new AppService();
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
	
	public void addTransformation(TransformCallBack transformation) {
		this.drawing.addTransform(transformation);
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
		this.notificar();
	}
	
	public void setOriginal(boolean value) {
		this.drawing.setShowOriginal(value);
		this.notificar();
	}
	
	public UserView getUserView() {
		return userView;
	}
	
	public void rotateX(int deg) {
		this.userView.rotateX(deg);
		this.notificar();
	}
	
	public void rotateY(int deg) {
		this.userView.rotateY(deg);
		this.notificar();
	}
	
	public void zoom(int deg) {
		this.userView.zoom(deg);
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
