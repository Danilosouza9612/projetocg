package renderer;

import com.jogamp.opengl.GL2;

import model.Composite;
import model.Scale;
import model.TestPoints;

public class Test3D implements DrawEvent{
	@Override
	public void draw(GL2 gl) {
		Composite composite = new Composite();
		composite.addTransformation(new Scale(2,2,2));
		composite.addTransformation(new Scale(1,1,2));
		TestPoints test = new TestPoints();
		test.applyTransform(composite);
		test.draw(gl);
	}
}
