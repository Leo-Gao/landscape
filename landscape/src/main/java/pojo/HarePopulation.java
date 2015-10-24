package pojo;

import model.Landscape;

/**
 * entity of Hare
 * @author Gaolu
 * @author jklebes
 */

public class HarePopulation implements Population {
	//birth rate of hares
	private double birthrate = 0.08d;
	
	//predation rate at which pumas eat hares
	private double deathrate = 0.04d;
	
	//diffusion rates for hares
	private double diffusionrate = 0.2d;
	
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
	public void timeStepSquare(int i, int j, double dt, double pumadensity) {
		//update population in square, referencing initDensities array
		//for densities of neighboring squares
		double old_density = initDensities[i][j];
		double new_density= old_density + dt* birthrate * old_density - dt * deathrate *old_density * pumadensity;
		new_density = diffuseSquare( i,j, new_density, dt);
		densities[i][j]=new_density;
	}

	@Override
	public void timeStepAll(double dt, Population pumas) {
		//save state at beginning of timestep
		this.initDensities = densities;	
		//evolve each square
		for (int i=1; i < grid.getWidth(); i++){
			for (int j=1; j < grid.getHeight(); j++){
				 if (grid.isLand(i,j)){
				timeStepSquare(i,j, dt,pumas.getDensity(i, j));
				 }
				}
		}
	}

	@Override
	public double diffuseSquare(int i, int j,double init_density,double dt) {
		double old_density= initDensities[i][j];
		double new_density = init_density + diffusionrate*(density_sum(i,j)-(double)grid.countLand(i,j)*old_density);
		return new_density;
	}

	private double density_sum(int i, int j) {
		double sum =0;
		sum = sum + getOldDensity(i-1,j)+ getOldDensity(i+1,j)+ getOldDensity(i,j-1)+ getOldDensity(i,j+1);
		return sum;
	}

	private double getOldDensity(int i, int j) {
		if (grid.isLand(i,j)){
			return initDensities[i][j];
		}
		else{
			return 0;
		}
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
		if (grid.isLand(i,j)){
			return densities[i][j];
		}
		else{
		return 0;
		}
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
