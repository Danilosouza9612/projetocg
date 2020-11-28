package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ObjDrawing extends Drawing{
	private String filePath;
	
	public ObjDrawing(String filePath) {
		super();
		this.filePath = filePath;
	}
	
	@Override
	public void initIfNotInitialized() {
		FileReader fileReader=null;
		BufferedReader bReader=null;
		String line, prefix;
		float[] point;
		String[] pointsParser;
		try {
			fileReader = new FileReader(filePath);
			bReader = new BufferedReader(fileReader);
			while((line=bReader.readLine())!=null) {
				prefix = line.substring(0,2);
				if(prefix.equals("v ")) {
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
					this.faces.add(new Face(line.substring(2, line.length())));
				}else if(prefix.equals("vt")) {

				}
			}
			
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

}
