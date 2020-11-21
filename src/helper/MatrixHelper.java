package helper;

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
}
