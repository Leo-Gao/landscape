package pojo;

import model.Landscape;

/**
 * entity of Hare
 * @author Gaolu
 * @author jklebes
 */

public class HarePopulation implements Population {
	//birth rate of hares
	private double r = 0.08d;
	
	//predation rate at which pumas eat hares
	private double a = 0.04d;
	
	//diffusion rates for hares
	private double k = 0.2d;
	
	//densities
	private double[][] densities; 
	private double[][] initDensities;
	
	Landscape grid;
	int gridwith; //include water halo
	int gridheight; //, = dimensions of densities array +2
	
	//constructor 
	public HarePopulation(Landscape grid){
		this.grid=grid;
	}
	
	@Override
	public void initiatePopulation(Landscape landscape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void timeStepSquare(int i, int j, double pumadensity) {
		//update population in square, referencing initDensities array
		//for densities of neighboring squares
		
		// equation goes here 
		
		diffuseSquare(i,j);
	}

	@Override
	public void timeStepAll(Population pumas) {
		//save state at beginning of timestep
		double[][] initDensities = densities;
		
		//evolve each square
		for (int i=1; i < gridwidth-1; i++){
			for (int j=1; j < gridheight-1; j++){
				timeStepSquare(i,j, pumas.getDensity(i, j));
			}
		}
	}

	@Override
	public void diffuseSquare(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBirthRate(double r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDeathRate(double a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDiffusionRate(double k) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public double getDensity(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[][] getDensities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDensities(double[][] densities) {
		// TODO Auto-generated method stub
		
	}
	
}
