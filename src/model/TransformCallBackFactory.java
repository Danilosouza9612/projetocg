package model;

import com.jogamp.opengl.GL2;

import service.AppService;

public class TransformCallBackFactory {
	
	public TransformCallBack createTranslate(float x, float y, float z) {
		return new TransformCallBack() {
			private boolean transformed=false;
			@Override
			public void execute(GL2 gl) {
				if(!this.transformed) {
					Drawing drawing = AppService.getInstance().getDrawing();
					Translate translate = new Translate(x, y, z);
					translate.applyTransformation(drawing);
					this.transformed = true;
				}
				//gl.glTranslatef(x, y, z);
			}
		};
	}
	
	public TransformCallBack createScale(float xf, float yf, float zf) {
		return (gl) -> {
			gl.glScalef(xf, yf, zf);
		};
	}
	
	public TransformCallBack createRotateX(float angle) {
		return (gl) -> {
			gl.glRotatef(angle, 1, 0, 0);
		};
	}
	
	public TransformCallBack createRotateY(float angle) {
		return (gl) -> {
			gl.glRotatef(angle, 0, 1, 0);
		};
	}
	
	public TransformCallBack createRotateZ(float angle) {
		return (gl) -> {
			gl.glRotatef(angle, 0, 0, 1);
		};
	}
	public TransformCallBack createShearX(float a, float b) {
		return new TransformCallBack() {
			float[] shearMatrix = {
				1, 0, 0, 0,
				a, 1, 0, 0,
				b, 0, 1, 0,
				0, 0, 0, 1,
			};
			@Override
			public void execute(GL2 gl) {
				gl.glMultMatrixf(this.shearMatrix, 0);
			}
		};
	}
	
	public TransformCallBack createShearY(float a, float b) {
		return new TransformCallBack() {
			float[] shearMatrix = {
				1, a, 0, 0,
				0, 1, 0, 0,
				0, b, 1, 0,
				0, 0, 0, 1,
			};
			@Override
			public void execute(GL2 gl) {
				gl.glMultMatrixf(this.shearMatrix, 0);
			}
		};
	}
	
	public TransformCallBack createShearZ(float a, float b) {
		return new TransformCallBack() {
			float[] shearMatrix = {
				1, 0, a, 0,
				0, 1, b, 0,
				0, 0, 1, 0,
				0, 0, 0, 1,
			};
			@Override
			public void execute(GL2 gl) {
				gl.glMultMatrixf(this.shearMatrix, 0);
			}
		};
	}
	
	public TransformCallBack createReflexionXY() {
		return new TransformCallBack() {
			float[] shearMatrix = {
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, -1, 0,
				0, 0, 0, 1,
			};
			@Override
			public void execute(GL2 gl) {
				gl.glMultMatrixf(this.shearMatrix, 0);
			}
		};
	}
	
	public TransformCallBack createReflexionXZ() {
		return new TransformCallBack() {
			float[] shearMatrix = {
				1, 0, 0, 0,
				0, -1, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1,
			};
			@Override
			public void execute(GL2 gl) {
				gl.glMultMatrixf(this.shearMatrix, 0);
			}
		};
	}
	
	public TransformCallBack createReflexionYZ() {
		return new TransformCallBack() {
			float[] shearMatrix = {
				-1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1,
			};
			@Override
			public void execute(GL2 gl) {
				//gl.glTranslatef(-9, 0, 0);
				gl.glMultMatrixf(this.shearMatrix, 0);
				//gl.glTranslatef(9, 0, 0);
			}
		};
	}
}
