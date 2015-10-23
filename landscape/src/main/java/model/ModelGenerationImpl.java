package model;

import java.util.HashMap;

import pojo.Hare;
import pojo.Puma;

public class ModelGenerationImpl implements ModelGeneration {
	
	HashMap<Integer, Puma> pumas = new HashMap<Integer, Puma>();
	HashMap<Integer, Hare> hares = new HashMap<Integer, Hare>();
	
	@Override
	public void generateDensities(Puma puma, Hare hare, int T) {
		// TODO Auto-generated method stub
//		pumas = 
//		hares = 
	}

	@Override
	public HashMap<Integer, Puma> getPumas() {
		// TODO Auto-generated method stub
		return this.pumas;
	}

	@Override
	public HashMap<Integer, Hare> getHares() {
		// TODO Auto-generated method stub
		return this.hares;
	}

}
