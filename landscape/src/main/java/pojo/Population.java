package pojo;

import model.Landscape;

/*
 * interface for characteristics and methods of a population
 */

public interface Population {

	
    //generate first densities of population on landscape
    public void initiatePopulation(Landscape landscape);
    
    public void timeStepSquare(int i, int j, double dt, double otherpopulationdensity);
    
    public void timeStepAll(double dt, Population otherpopulation);
    
    public double diffuseSquare(int i, int j, double init_density, double dt);
    
    public double getDensity(int i, int j);
    
	public double[][] getDensities();
	
	public void setDensities(double[][] densities);
	
	public void setBirthRate(double r);
	
	public void setDeathRate(double a);
	
	public void setDiffusionRate(double k);
	
	
}
