package model;

import java.util.HashMap;

import pojo.Hare;
import pojo.Puma;

public interface ModelGeneration {
	
	public void generateDensities(Puma puma,Hare hare,int T);
	
	public HashMap<Integer, Puma> getPumas();
	public HashMap<Integer, Hare> getHares();

}
