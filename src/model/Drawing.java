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
	private boolean initialized;
	private boolean showOriginal;
	private String filePath;
	
	public Drawing(String filePath) {
		this.drawings = new ArrayList<DrawingItem>();
		this.vertices = new ArrayList<float[]>();
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
		if(!this.showOriginal) {
			for(TransformCallBack item : transforms) {
				item.execute(gl);
			}
		}
		TransformCallBackFactory factory = new TransformCallBackFactory();
		//factory.createRotateY(45).execute(gl);
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
				}else if(prefix.equals("vn")) {
					pointsParser = line.substring(3, line.length()).split(" ");
					point = new float[3];
					for(int i=0; i<point.length; i++) {
						point[i] = Float.parseFloat(pointsParser[i]);
					}
					this.normals.add(point);
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void drawAxis(GL2 gl) {
		float maxX=Float.MIN_VALUE, maxY=Float.MIN_VALUE, maxZ=Float.MIN_VALUE;
		float minX=Float.MAX_VALUE, minY=Float.MIN_VALUE, minZ=Float.MIN_VALUE;
		float dx, dy, dz;
		float dmax, centerX, centerY, centerZ;
		float drawMax;
		for(float[] vertex : vertices) {
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
		
	    gl.glLineWidth(4);
	   
		drawMax = centerX+dmax+1;
		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_LIGHT0);
	    gl.glBegin(GL2.GL_LINE_LOOP);
	    gl.glColor3d(1, 0, 0);
	    gl.glVertex3f(drawMax, minY, centerZ);
	    gl.glVertex3f(centerX, minY, centerZ);
	    gl.glEnd();
	    
		drawMax = centerY+dmax+1;
	    gl.glBegin(GL2.GL_LINE_LOOP);
	    gl.glColor3d(0, 1, 0);
	    gl.glVertex3f(centerX, drawMax, centerZ);
	    gl.glVertex3f(centerX, minY, centerZ);
	    gl.glEnd();
	    
		drawMax = centerZ+dmax+1;
	    gl.glBegin(GL2.GL_LINE_LOOP);
	    gl.glColor3d(0, 0, 1);
	    gl.glVertex3f(centerX, minY, drawMax);
	    gl.glVertex3f(centerX, minY, centerZ);
	    gl.glEnd();
	   
	    gl.glLineWidth(1);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
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
}
