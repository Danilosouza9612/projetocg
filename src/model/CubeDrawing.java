package model;

public class CubeDrawing extends Drawing{

	@Override
	public void initIfNotInitialized() {
		float[][] vertices = {
			{1f, 1f, -1f}, //0
			{-1f, 1f,-1f}, //1
			{-1f, -1f, -1f}, //2
			{1f, -1f, -1f}, //3
			{1f, 1f, 1f}, //4
			{-1f, 1f, 1f}, //5
			{-1f, -1f, 1f}, //6
			{1f, -1f, 1f}, //7
		};
        //  x    y 
		//{1f, 1f, -1f}, //0
		//{-1f, 1f,-1f}, //1
		//{-1f, -1f, -1f}, //2
		//{1f, -1f, -1f}, //3
		//      y     z  
		//{1f,  1f, -1f}, //0
		//{1f,  1f,  1f}, //4
		//{1f, -1f,  1f}, //7
		//{1f, -1f, -1f}, //3
		//       y    z       
		//{ -1f, 1f, -1f}, //1
		//{ -1f, 1f,  1f}, //5
		//   x      z
		// {1f, 1f, 1f} 4
		// {-1f, 1f, 1f} 5
		// {-1f, 1f,-1f} 1
		// {1f, 1f, -1f} 0
		




		
		float[][] normals = {
			{0.5f, 1, 0},
			{-1f, 0, 0},
			{0, 0.5f, 1},
			{0, -1, -0.5f},
			{0.5f, 1f, 0},
		};
		
		int[][] vertexIndexes = {
			{0, 1, 2, 3},
			{4, 5, 6, 7},
			{1, 5, 6, 2},
			{0, 4, 7, 3},
			{4, 5, 1, 0},
		};
		
		int[][] normalIndexes = {
			{0, 0, 0, 0},
			{1, 1, 1, 1},
			{2, 2, 2, 2},
			{3, 3, 3, 3},
			{4, 4, 4, 4},
		};
		
		Face[] faces = {
			new Face(vertexIndexes[0], normalIndexes[0]),
			new Face(vertexIndexes[1], normalIndexes[1]),
			new Face(vertexIndexes[2], normalIndexes[2]),
			new Face(vertexIndexes[3], normalIndexes[3]),
			new Face(vertexIndexes[4], normalIndexes[4]),
		};
		
		this.initVertices(vertices);
		this.initNormals(normals);
		this.initFaces(faces);
	}

}
