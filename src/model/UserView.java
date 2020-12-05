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
		this.applyTransform(new RotateY(this.angleY));
		this.applyTransform(new RotateX(this.angleX));
	}
	
	private void initialPos() {
		this.X = 0;
		this.Y = 0;
		this.Z = (float) (Math.exp(this.zoom)/Math.pow(2, this.zoom))+9;
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
		this.applyTransform(new RotateY(-this.angleY));
		this.applyTransform(new RotateX(deg));
		this.applyTransform(new RotateY(this.angleY));
		this.angleX+=deg;
	}
	
	public void rotateY(int deg) {
		deg = -(deg/16);
		this.applyTransform(new RotateY(deg));
		this.angleY+=deg;
	}
	
	public void zoom(int zoom) {
		if((this.zoom+zoom)<17) {
			this.zoom+=zoom;
			this.initialPos();
			this.applyTransform(new RotateX(this.angleX));
			this.applyTransform(new RotateY(this.angleY));
		}
	}
	
	public void applyTransform(Transformation transformation){
		float[][] mat = {{this.X}, {this.Y}, {this.Z}, {1}};
		mat = transformation.transform(mat);
		this.X = mat[0][0];
		this.Y = mat[1][0];
		this.Z = mat[2][0];
	}
}
