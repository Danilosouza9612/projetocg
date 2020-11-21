package helper;

import java.util.ArrayList;
import java.util.List;

public class PointerList {
	private List<Float[]> pointers;
	
	public PointerList() {
		this.pointers = new ArrayList<Float[]>();
	}
	
	public PointerList(float[][] pointers) {
		this.pointers = new ArrayList<Float[]>();
		Float[] point;
		for(int i=0; i<pointers[0].length; i++) {
			point = new Float[4];
			for(int j=0; j<4; j++) {
				point[j]=pointers[j][i];
			}
			this.pointers.add(point);
		}
	}
	
	public void addPoint(float x, float y, float z) {
		Float[] point = new Float[4];
		point[0]=x;
		point[1]=y;
		point[2]=z;
		point[3]=1.0f;
		this.pointers.add(point);
	}
	
	public float[][] toArray(){
		float[][] mat = new float[4][pointers.size()];
		for(int i=0; i<pointers.size(); i++) {
			for(int j=0; j<4; j++) {
				mat[j][i]=pointers.get(i)[j];
			}
		}
		return mat;
	}
}
