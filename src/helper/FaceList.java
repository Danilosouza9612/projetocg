package helper;

import java.util.ArrayList;
import java.util.List;

public class FaceList {
	private List<int[]> faceCoordinates;
	
	public FaceList() {
		this.faceCoordinates = new ArrayList<int[]>();
	}
	
	public void addFaceCoordinate(int a, int b, int c) {
		int[] faceCoordinate = new int[3];
		faceCoordinate[0]=a;
		faceCoordinate[1]=b;
		faceCoordinate[2]=c;
		faceCoordinates.add(faceCoordinate);
	}
}
