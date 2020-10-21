package config;

import model.Line;

public class AppConfig{
	private static AppConfig instance;
	
	private boolean showMess;
	private Line line;
	private int messLength;
	private boolean originalLine;
	
	private AppConfig() {
		this.showMess = true;
		this.messLength=25;
	}
	
	public static AppConfig getInstance() {
		if(instance==null)
			instance = new AppConfig();
		
		return instance;
	}
	
	public void setMess(boolean showMess) {
		this.showMess = showMess;
	}
	
	public boolean isShowMess() {
		return this.showMess;
	}
	
	public void setLine(Line line) {
		this.line = line;
	}
	
	public Line getLine() {
		return this.line;
	}
	
	public Line getOrCreateLine() {
		if(line==null) {
			this.line = new Line(0,0,0,0);
		}
		return this.line;
	}
	
	public int getMessLength() {
		return this.messLength;
	}
	
	public void setMessLength(int messLength) {
		if(messLength<=0) {
			throw new IllegalArgumentException("O tamanho da malha deverá ser maior que zero");
		}
		this.messLength = messLength;
	}

	public boolean isOriginalLine() {
		return originalLine;
	}

	public void setOriginalLine(boolean originalLine) {
		this.originalLine = originalLine;
	}
	
	public int getLastMesh() {
		return messLength-1;
	}
	
	public int getMeshSize() {
		return 20 * this.getLastMesh();
	}
}
