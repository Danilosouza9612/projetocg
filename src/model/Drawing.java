package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;

import service.AppService;

public class Drawing {
	private List<TransformCallBack> transforms;
	private List<DrawingItem> drawings;
	private MaterialList materials;
	private List<float[]> vertices;
	private List<float[]> normals;
	private List<float[]> originalVertices;
	private List<float[]> originalNormals;
	private float[][] axisCoordinates;
	private boolean initialized;
	private boolean showOriginal;
	private String filePath;
	
	public Drawing(String filePath) {
		this.drawings = new ArrayList<DrawingItem>();
		this.vertices = new ArrayList<float[]>();
		this.originalVertices = new ArrayList<float[]>();
		this.originalNormals = new ArrayList<float[]>();
		this.normals = new ArrayList<float[]>();
		this.transforms = new ArrayList<TransformCallBack>();
		this.filePath = filePath;
	}

	public void addDrawing(DrawingItem item) {
		this.drawings.add(item);
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
	
	public float[][] getNormalsPointMatrix() {
		float[][] mat = new float[4][normals.size()];
		for(int i=0; i<normals.size(); i++) {
			for(int j=0; j<3; j++) {
				mat[j][i] = this.normals.get(i)[j];
			}
			mat[3][i] = 1;
		}
		return mat;
	}
	
	public void toVertexList(float[][] vertices) {
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
	
	public void toNormalList(float[][] normals) {
		this.normals = new ArrayList<float[]>();
		float[] point;
		for(int i=0; i<normals[0].length; i++) {
			point = new float[3];
			for(int j=0; j<3; j++) {
				point[j] = normals[j][i];
			}
			this.normals.add(point);
		}
	}
	
	public boolean isShowOriginal() {
		return this.showOriginal;
	}
	
	public void setShowOriginal(boolean value) {
		this.showOriginal = value;
	}
	
	public void addTransform(TransformCallBack transform) {
		this.transforms.add(transform);
	}
	
	public void draw(GL2 gl) {
		this.drawAxis(gl);
		this.light(gl);
		for(DrawingItem item : drawings) {
			item.draw(gl, this.vertices, this.normals, this.materials);
		}
	    gl.glDisable(GL2.GL_LIGHT0);
	    gl.glDisable(GL2.GL_LIGHTING);
	}
	
	public void init() {
		if(!this.initialized) {
			this.initIfNotInitialized();
			this.initialized=true;
		}
	}
	
	private void initIfNotInitialized() {
		File file = new File(filePath);
		FileReader fileReader=null;
		BufferedReader bReader=null;
		String line, prefix;
		DrawingItem drawingItem=null;
		float[] point;
		String parentPath;
		String[] pointsParser;
		try {
			fileReader = new FileReader(file);
			bReader = new BufferedReader(fileReader);
			while((line=bReader.readLine())!=null) {
				prefix = line.substring(0,2);
				if(prefix.equals("o ")){
					if(drawingItem!=null) {
						this.drawings.add(drawingItem);
					}
					drawingItem = new DrawingItem();
				}
				else if(prefix.equals("v ")) {
					pointsParser = line.substring(2, line.length()).split(" ");
					point = new float[3];
					for(int i=0; i<point.length; i++) {
						point[i] = Float.parseFloat(pointsParser[i]);
					}
					this.vertices.add(point);
					this.originalVertices.add(point);
				}else if(prefix.equals("vn")) {
					pointsParser = line.substring(3, line.length()).split(" ");
					point = new float[3];
					for(int i=0; i<point.length; i++) {
						point[i] = Float.parseFloat(pointsParser[i]);
					}
					this.normals.add(point);
					this.originalNormals.add(point);
				}else if(prefix.equals("f ")) {
					drawingItem.addFace(new Face(line.substring(2, line.length())));
				}else if(line.length()==0) {
					continue;
				}else if(line.length()>=7 && line.substring(0, 7).equals("usemtl ")) {
					drawingItem.setMaterialName(line.substring(7, line.length()));
				}else if(line.length()>=7 && line.substring(0, 7).equals("mtllib ")) {
					parentPath = file.getParent()==null ? "" : file.getParent();
					this.materials = Material.ParserMaterialFile(parentPath+"//"+line.substring(7, line.length()));
				}
			}
			this.drawings.add(drawingItem);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(bReader!=null) {
					bReader.close();
				}
				if(fileReader!=null) {
					fileReader.close();
				}
				this.initDrawAxis();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void initDrawAxis() {
		float[] defVertex = this.originalVertices.get(1);
		float maxX=defVertex[0], maxY=defVertex[1], maxZ=maxY=defVertex[2];
		float minX=defVertex[0], minY=defVertex[1], minZ=maxY=defVertex[2];
		float dx, dy, dz;
		float dmax, centerX, centerY, centerZ;
		for(float[] vertex : this.originalVertices) {
			if(vertex[0]>maxX) {
				maxX=vertex[0];
			}else if(vertex[0]<minX) {
				minX=vertex[0];
			}
			if(vertex[1]>maxY) {
				maxY=vertex[1];
			}else if(vertex[1]<minY) {
				minY=vertex[1];
			}
			if(vertex[2]>maxZ) {
				maxZ=vertex[2];
			}else if(vertex[2]<minZ) {
				minZ=vertex[2];
			}
		}
		
		dx = maxX-minX;
		dy = maxY-minY;
		dz = maxZ-minZ;
		
		centerX = (maxX+minX)/2;
		centerY = (maxY+minY)/2;
		centerZ = (maxZ+minZ)/2;
		
		dmax=dx;
		
		if(dy>dx && dy>dz) {
			dmax=dy;
		}
		if(dz>dx && dz>dy) {
			dmax=dz;
		}

		float[][] coords = {
			{centerX, centerX+dmax+1, centerX,         centerX},
			{minY,    minY,           centerY+dmax+1,  minY},
			{centerZ, centerZ,        centerZ,         centerZ+dmax+1},
			{1,       1,              1,               1},
		};
		
		this.axisCoordinates = coords;
	}
	
	private void drawAxis(GL2 gl) {
	    gl.glLineWidth(4);
		
		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_LIGHT0);
	    gl.glBegin(GL2.GL_LINE_LOOP);
	    gl.glColor3d(1, 0, 0);
	    gl.glVertex3f(axisCoordinates[0][0], axisCoordinates[1][0], axisCoordinates[2][0]);
	    gl.glVertex3f(axisCoordinates[0][1], axisCoordinates[1][1], axisCoordinates[2][1]);
	    gl.glEnd();
	    
	    gl.glBegin(GL2.GL_LINE_LOOP);
	    gl.glColor3d(0, 1, 0);
	    gl.glVertex3f(axisCoordinates[0][0], axisCoordinates[1][0], axisCoordinates[2][0]);
	    gl.glVertex3f(axisCoordinates[0][2], axisCoordinates[1][2], axisCoordinates[2][2]);
	    gl.glEnd();
	    
	    gl.glBegin(GL2.GL_LINE_LOOP);
	    gl.glColor3d(0, 0, 1);
	    gl.glVertex3f(axisCoordinates[0][0], axisCoordinates[1][0],  axisCoordinates[2][0]);
	    gl.glVertex3f(axisCoordinates[0][3], axisCoordinates[1][3], axisCoordinates[2][3]);
	    gl.glEnd();
	   
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
	    gl.glLineWidth(1);
	}
	
	public float[][] getAxisPoints(){
		return this.axisCoordinates;
	}
	
	public void setAxisPoints(float[][] axisCoordinates) {
		this.axisCoordinates = axisCoordinates;
		for(int i=0; i<axisCoordinates.length; i++) {
			//System.out.p
		}
	}
	
	private void light(GL2 gl) {
		UserView userView = AppService.getInstance().getUserView();
	    //float[] position = { 0, 20, 30, 1 };
	    float[] position = { 0, 0, -30};
		float[] global = { 0.1f, 0.1f, 0.1f, 1f};
		float[] ambient = { 0.4f, 0.4f, 0.4f, 1f};
		float[] diffuse = { 0.9f, 0.9f, 0.9f, 1f};
		float[] specular = { 1f, 1f, 1f, 1f};
		float[] direction = {-userView.getAngleX(), -userView.getAngleY(), 0};
	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glEnable(GL2.GL_LIGHT0);
	    gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, global, 0);
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, specular, 0);
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position, 0);
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPOT_DIRECTION, direction,0);
	}
	
	public void reset() {
		this.vertices = this.originalVertices;
		this.normals = this.originalNormals;
		this.initDrawAxis();
	}
	
	public float[][] getMaxMin(){
		float[] defVertex = this.vertices.get(0);
		float maxX=defVertex[0], maxY=defVertex[1], maxZ=maxY=defVertex[2];
		float minX=defVertex[0], minY=defVertex[1], minZ=maxY=defVertex[2];
		for(float[] vertex : this.vertices) {
			if(vertex[0]>maxX) {
				System.out.println(vertex[0]);
				maxX=vertex[0];
			}else if(vertex[0]<minX) {
				minX=vertex[0];
			}
			
			if(vertex[1]>maxY) {
				maxY=vertex[1];
			}else if(vertex[1]<minY) {
				minY=vertex[1];
			}
			
			if(vertex[2]>maxZ) {
				maxZ=vertex[2];
			}else if(vertex[2]<minZ) {
				minZ=vertex[2];
			}
		}
		
		float[][] ret = {
			{maxX, maxY, maxZ},
			{minX, minY, minZ}
		};
		return ret;
	}
	
	public float[] getCenterOfMassCoord() {
		float[][] maxMin = this.getMaxMin();
		float centerX, centerY, centerZ;
		centerX = (maxMin[0][0]+maxMin[1][0])/2;
		centerY = (maxMin[0][1]+maxMin[1][1])/2;
		centerZ = (maxMin[0][2]+maxMin[1][2])/2;
		
		float[] ret = {centerX, centerY, centerZ};
		return ret;
	}
	
	public float[] getNearestPointOriginDistance() {
		float[][] maxMin = this.getMaxMin();
		
		float[] ret = {
			Math.abs(maxMin[0][0])<Math.abs(maxMin[1][0]) ? Math.abs(maxMin[0][0]) : Math.abs(maxMin[1][0]),
			Math.abs(maxMin[0][1])<Math.abs(maxMin[1][1]) ? Math.abs(maxMin[0][1]) : Math.abs(maxMin[1][1]),
			Math.abs(maxMin[0][2])<Math.abs(maxMin[1][2]) ? Math.abs(maxMin[0][2]) : Math.abs(maxMin[1][2]),
		};
		
		return ret;
	}
}
