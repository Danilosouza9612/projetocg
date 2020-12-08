package model;

public class UserView{
	private float X;
	private float Y;
	private float Z;
	private int angleX;
	private int angleY;
	private int zoom;
	
	public UserView() {
		this.angleX = -10;
		this.angleY = 0;
		this.zoom = 10;
		this.initialPos();
	}
	
	private void initialPos() {
		this.X = 0;
		this.Y = 0;
		this.Z = ((float) (Math.exp(this.zoom)/Math.pow(2, this.zoom))+9);
	}

	public float getX() {
		return X;
	}

	public void setX(float x) {
		X = x;
	}

	public float getY() {
		return Y;
	}

	public void setY(float y) {
		Y = y;
	}

	public float getZ() {
		if(Z==0) {
			return -0.1f;
		}
		return Z;
	}

	public void setZ(float z) {
		Z = z;
	}
	
	public int getUpY() {
		int angle = this.angleX%360;
		if(angle>270) {
			return 1;
		}
		if(angle>90) {
			return -1;
		}
		if(angle<=-270) {
			return 1;
		}
		if(angle<=-90) {
			return -1;
		}
		return 1;
	}
	
	public void rotateX(int deg) {
		deg = -(deg/16);
		this.angleX+=deg;
	}
	
	public void rotateY(int deg) {
		deg = (deg/16);
		this.angleY+=deg;
	}
	
	public void zoom(int zoom) {
		if((this.zoom+zoom)<17) {
			this.zoom+=zoom;
			this.initialPos();
		}
	}

	public int getAngleX() {
		return angleX;
	}

	public void setAngleX(int angleX) {
		this.angleX = angleX;
	}

	public int getAngleY() {
		return angleY;
	}

	public void setAngleY(int angleY) {
		this.angleY = angleY;
	}
	

}
