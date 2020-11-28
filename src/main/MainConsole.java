package main;

import helper.TestHelpers;
import model.Drawing;
import model.ObjDrawing;

public class MainConsole {

	public static void main(String[] args) {
		/*Composite composite = new Composite();
		composite.addTransformation(new Scale(2,2,2));
		composite.addTransformation(new Scale(1,2,1));
		TestPoints points = new TestPoints();
		points.applyTransform(composite);
		TestHelpers.printPoints(points.getPoints());
		Transformation inverse = composite.inverseTransformation();
		points.applyTransform(inverse);
		TestHelpers.printPoints(points.getPoints());
		*/
		
		Drawing drawing = new ObjDrawing("src\\untitled.obj");
		drawing.init();
		float[][] matrix = drawing.getVertexPointsMatrix();
		TestHelpers.printMatrix(matrix);
	}

}
