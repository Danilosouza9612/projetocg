package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Material {
	private String name;
	private float[] ambient;
	private float[] diffuse;
	private float[] specular;
	
	public static MaterialList ParserMaterialFile(String filePath) {
		FileReader fileReader=null;
		BufferedReader bReader=null;
		String line;
		Material material=null;
		MaterialList materials = new MaterialList();
		String[] numbersString;
		int length;
		try {
			fileReader = new FileReader(filePath);
			bReader = new BufferedReader(fileReader);
			
			while((line=bReader.readLine())!=null){
				length = line.length(); 
				if(length>=7 && line.substring(0, 7).equals("newmtl ")) {
					if(material!=null) {
						materials.add(material);
					}
					material = Material.getDefaultMaterial();
					material.name = line.substring(7, line.length());
				}else if(length>=3 &&line.substring(0, 3).equals("Ka ")) {
					material.ambient = new float[4];
					numbersString = line.substring(3, line.length()).split(" ");
					for(int i=0; i<3; i++) {
						material.ambient[i] = Float.parseFloat(numbersString[i]);
					}
					material.ambient[3]=0.8f;
				}else if(length>=3 &&line.substring(0, 3).equals("Kd ")) {
					material.diffuse = new float[4];
					numbersString = line.substring(3, line.length()).split(" ");
					for(int i=0; i<3; i++) {
						material.diffuse[i] = Float.parseFloat(numbersString[i]);
					}
					material.diffuse[3]=0.8f;
				}else if(length>=3 &&line.substring(0, 3).equals("Ks ")) {
					material.specular = new float[4];
					numbersString = line.substring(3, line.length()).split(" ");
					for(int i=0; i<3; i++) {
						material.specular[i] = Float.parseFloat(numbersString[i]);
					}
					material.specular[3]=1f;
				}
			}
			materials.add(material);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("O arquivo de definição de material(.mtl) relacionado não foi encontrado");
			return materials;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("O arquivo de definição de material(.mtl) está corrompidoS");
		}
		return materials;
	}
	
	public static Material getDefaultMaterial() {
		Material material = new Material();
		float[] ambient = {0.2f, 0.2f, 0.2f, 0.2f};
		float[] specular = {1f, 1f, 1f, 1f};
		float[] diffuse = {0.7f, 0.7f, 0.7f, 0.8f};
		material.ambient = ambient;
		material.specular = specular;
		material.diffuse = diffuse;
		return material;
	}
	
	public Material(String name) {
		this.name = name;
		this.ambient = new float[4];
		this.diffuse = new float[4];
		this.specular = new float[4];
	}
	
	Material() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float[] getAmbient() {
		return ambient;
	}
	
	public void setAmbient(float[] ambient) {
		this.ambient = ambient;
	}
	
	public float[] getDiffuse() {
		return diffuse;
	}
	
	public void setDiffuse(float[] diffuse) {
		this.diffuse = diffuse;
	}
	
	public float[] getSpecular() {
		return specular;
	}
	
	public void setSpecular(float[] specular) {
		this.specular = specular;
	}
}
