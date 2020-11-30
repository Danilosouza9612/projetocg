package model;

public class UserView{
	private float X;
	private float Y;
	private float Z;
	private int angleX;
	
	public UserView() {
		this.X = 0;
		this.Y = 0;
		this.Z = 31;
		this.applyTransform(new RotateX(-19));
		System.out.println(X);
		System.out.println(Y);
		System.out.println(Z);
		//this.applyTransform(new RotateY(45));
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
			return -1;
		}
		return Z;
	}

	public void setZ(float z) {
		Z = z;
	}
	
	public void rotateX(int deg) {
		this.applyTransform(new RotateZ(deg));
	}
	
	public void rotateY(int deg) {
		this.applyTransform(new RotateY(deg));
	}
	
	public void applyTransform(Transformation transformation){
		float[][] mat = {{this.X}, {this.Y}, {this.Z}, {1}};
		mat = transformation.transform(mat);
		this.X = mat[0][0];
		this.Y = mat[1][0];
		this.Z = mat[2][0];
	}
}
