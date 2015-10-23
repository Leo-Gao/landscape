package pojo;

import model.Landscape;

/*
 * interface for characteristics and methods of a population
 */

public interface Population {

	
    //generate first densities of population on landscape
    public void initiatePopulation(Landscape landscape);
    
    public void timeStepSquare(int i, int j, double otherpopulationdensity);
    
    public void timeStepAll( Population otherpopulation);
    
    public void diffuseSquare(int i, int j);
    
    public double getDensity(int i, int j);
    
	public double[][] getDensities();
	
	public void setDensities(double[][] densities);
	
	public void setBirthRate(double r);
	
	public void setDeathRate(double a);
	
	public void setDiffusionRate(double k);
	
	
}
