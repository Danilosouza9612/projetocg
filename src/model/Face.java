package model;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;

public class Face {
	private List<Integer> vertexIndex;
	private List<Integer> normalIndex;
	
	public Face(String faceStringParser) {
		this.vertexIndex = new ArrayList<Integer>();
		this.normalIndex = new ArrayList<Integer>();
		int faceCoord;
		int faceCounter=0;
		String[] facesParser, facesVertexParser;
		facesParser = faceStringParser.split(" ");
		for(String faceParser : facesParser) {
			facesVertexParser = faceParser.split("/");
			for(String faceVertexParser : facesVertexParser) {
				faceCoord = Integer.parseInt(faceVertexParser)-1;
				switch(faceCounter) {
				case 0:
					this.vertexIndex.add(faceCoord);
					break;
				case 1:
					break;
				case 2:
					this.normalIndex.add(faceCoord);
					break;
				}
				faceCounter=(faceCounter+1)%3;
			}
		}
	}
	
	public Face(int[] vertexIndexes, int[] normalIndexes) {
		this.vertexIndex = new ArrayList<Integer>();
		this.normalIndex = new ArrayList<Integer>();
		for(int vertexIndex : vertexIndexes) {
			this.vertexIndex.add(vertexIndex);
		}
		for(int normalIndex : normalIndexes) {
			this.normalIndex.add(normalIndex);
		}
	}
	
	public void draw(GL2 gl, List<float[]> vertexCoord, List<float[]> normalCoord) {
		float[] color = {0.2f, 0.7f, 0.3f, 0.5f};
		float[] point, normalPoint;
	    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, color, 0);
		gl.glBegin(GL2.GL_QUADS);
		if(vertexIndex.size()==3) {
			gl.glBegin(GL2.GL_TRIANGLES);
		}else if(vertexIndex.size()==4) {
			gl.glBegin(GL2.GL_QUADS);
		}else {
			gl.glBegin(GL2.GL_POLYGON);
		}
		for(int i=0; i<vertexIndex.size(); i++) {
			point = vertexCoord.get(this.vertexIndex.get(i));
			normalPoint = normalCoord.get(this.normalIndex.get(i));
			gl.glNormal3f(normalPoint[0], normalPoint[1], normalPoint[2]);
			gl.glVertex3f(point[0], point[1], point[2]);
		}
		gl.glEnd();
	}
}
