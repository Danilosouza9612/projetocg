package model;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;

public class DrawingItem{
	private List<Face> faces;
	private String materialName;
	
	public DrawingItem() {
		this.faces = new ArrayList<Face>();
	}
	
	public void draw(GL2 gl, List<float[]> vertices, List<float[]> normals, MaterialList materials) {
		Material material=null;
		material = Material.getDefaultMaterial();
		if(this.materialName!=null) {
			material = materials.searchByName(this.materialName);
		}
		if(material==null) {
			material = Material.getDefaultMaterial();
		}
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT_AND_DIFFUSE, material.getDiffuse(), 0);
	    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, material.getSpecular(), 0);
	    gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 128);
		for(Face face : this.faces) {
			face.draw(gl, vertices, normals);
		}
	}
	
	public void addFace(Face face) {
		this.faces.add(face);
	}
	
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	protected void initFaces(Face[] faces) {
		for(Face face : faces) {
			this.faces.add(face);
		}
	}
}
