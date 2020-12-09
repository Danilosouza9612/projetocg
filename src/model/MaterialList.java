package model;

import java.util.ArrayList;

public class MaterialList extends ArrayList<Material>{
	private static final long serialVersionUID = 1L;
	
	public MaterialList() {
		super();
	}
	
	public Material searchByName(String name) {
		for(Material item : this) {
			if(item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

}
