package helper;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import Jama.Matrix;

public class MatrixHelper {
	public static float[][] multiplyMatrices(float[][] firstMatrix, float[][] secondMatrix) {
		float[][] result = new float[firstMatrix.length][secondMatrix[0].length];
	    for (int row = 0; row < result.length; row++) {
	        for (int col = 0; col < result[row].length; col++) {
	            result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
	        }
	    }
	    return result;
	}
	
	public static float multiplyMatricesCell(float[][] firstMatrix, float[][] secondMatrix, int row, int col) {
		float cell = 0;
	    for (int i = 0; i < secondMatrix.length; i++) {
	        cell += firstMatrix[row][i] * secondMatrix[i][col];
	    }
	    return cell;
	}
	
	public static float[][] identityMatrix(int length){
		float[][] mat = new float[length][length];
		for(int i=0; i<length; i++) {
			mat[i][i]=1;
		}
		return mat;
	}
	
	public static float[][] inverseMatrix(float matrix[][]){
		Matrix I = new Matrix(toDoubleMatrix(matrix));
		return toFloatMatrix(I.inverse().getArray());
	}
	
	private static double[][] toDoubleMatrix(float[][] matrix) {
		double[][] ret = new double[matrix.length][matrix[0].length];
		for(int i=0; i<matrix.length; i++) {
			ret[i] = toDoubleArray(matrix[i]);
		}
		return ret;
	}
	
	private static float[][] toFloatMatrix(double[][] matrix){
		float[][] ret = new float[matrix.length][matrix[0].length];
		for(int i=0; i<matrix.length; i++) {
			ret[i] = toFloatArray(matrix[i]);
		}
		return ret;
	}
	
	private static double[] toDoubleArray(float[] array) {
		DoubleStream ds = IntStream.range(0, array.length)
                .mapToDouble(i -> array[i]);
		return ds.toArray();
	}
	
	private static float[] toFloatArray(double[] array) {
		float[] ret = new float[array.length];
		int cont=0;
		for(double item : array) {
			ret[cont]=(float) item;
		}
		return ret;
	}
}
