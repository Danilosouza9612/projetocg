package model;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;

public abstract class Drawing {
	protected List<float[]> vertices;
	protected List<float[]> normals;
	protected List<Face> faces;
	protected boolean initialized;
	
	public Drawing() {
		this.vertices = new ArrayList<float[]>();
		this.normals = new ArrayList<float[]>();
		this.faces = new ArrayList<Face>();
	}
	
	public float[][] getVertexPointsMatrix() {
		float[][] mat = new float[4][vertices.size()];
		for(int i=0; i<vertices.size(); i++) {
			for(int j=0; j<3; j++) {
				mat[j][i] = this.vertices.get(i)[j];
			}
			mat[3][i] = 1;
		}
		return mat;
	}
	
	private void toVertexList(float[][] vertices) {
		this.vertices = new ArrayList<float[]>();
		float[] point;
		for(int i=0; i<vertices[0].length; i++) {
			point = new float[3];
			for(int j=0; j<3; j++) {
				point[j] = vertices[j][i];
			}
			this.vertices.add(point);
		}
	}
	
	public void applyTransform(Transformation transformation){
		float[][] mat = this.getVertexPointsMatrix();
		mat = transformation.transform(mat);
		this.toVertexList(mat);
	}
	
	public void draw(GL2 gl) {
		for(Face face : this.faces) {
			face.draw(gl, this.vertices, this.normals);
		}
	}
	
	private void initPointers(List<float[]> pointersList, float[][] pointers) {
		for(int i=0; i<pointers.length; i++) {
			pointersList.add(pointers[i]);
		}
	}
	
	protected void initVertices(float[][] vertices) {
		this.initPointers(this.vertices, vertices);
	}
	
	protected void initNormals(float[][] normals) {
		this.initPointers(this.normals, normals);
	}
	
	protected void initFaces(Face[] faces) {
		for(Face face : faces) {
			this.faces.add(face);
		}
	}
	
	public void init() {
		if(!this.initialized) {
			this.initIfNotInitialized();
			this.initialized = true;
		}
	}
	public abstract void initIfNotInitialized();
}
