package helper;

public class ObjectToPrimitive {
	public static float[][] toFloatMatrixPrimitive(Float[][] mat){
		float[][] ret = new float[mat.length][0];
		for(int i=0; i<mat.length; i++) {
			ret[i] = toFloatArrayPrimitive(mat[i]);
		}
		return ret;
	}
	
	public static float[] toFloatArrayPrimitive(Float[] mat) {
		float[] ret = new float[mat.length];
		for(int i=0; i<mat.length; i++) {
			ret[i]=mat[i];
		}
		return ret;
	}
	
	public static Float[][] toFloatMatrixObject(float[][] mat){
		Float[][] ret = new Float[mat.length][0];
		for(int i=0; i<mat.length; i++) {
			ret[i] = toFloatArrayObject(mat[i]);
		}
		return ret;
	}
	
	public static Float[] toFloatArrayObject(float[] mat) {
		Float[] ret = new Float[mat.length];
		for(int i=0; i<mat.length; i++) {
			ret[i]=mat[i];
		}
		return ret;
	}
}
