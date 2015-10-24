package pojo;

import model.Landscape;

/*
 * interface for characteristics and methods of a population
 */

public class Population {
	
	//default values
	//birth rate of hares
	protected double birthrate;
	
	//predation rate at which pumas eat hares
	protected double deathrate;
	
	//diffusion rates for hares
	protected double diffusionrate;
	
	//densities
	protected double[][] densities; 
	protected double[][] initDensities;
	
	Landscape grid;
	int gridwith; //include water halo
	int gridheight; //, = dimensions of densities array +2
	
	//constructor 
	public Population(Landscape grid){
		this.grid=grid;
		initiateArrays(grid.getLandWidth(),grid.getLandHeight());
		setUniformDensity(0);
	}
	

	public Population(Landscape grid, double density) {
		this.grid=grid;
		initiateArrays(grid.getLandWidth(),grid.getLandHeight());
		setUniformDensity(density);
	}

	

	public Population(Landscape grid, double[][] densityarray) {
		this.grid=grid;
		initiateArrays(grid.getLandWidth(),grid.getLandHeight());
		setDensities(densityarray);
	}

	private void initiateArrays(int width, int height) {
		densities = new double[width][height];
		initDensities = new double[width][height];
	}

	
	public void setUniformDensity(double density) {
		for (int i=0; i < grid.getLandWidth(); i++){
			for (int j=0; j < grid.getLandHeight(); j++){
				setDensity(i,j,density);
			}
		}
	}

	public void setDensity(int i, int j, double density) {
		if (grid.isLand(i,j)){
			densities[i][j] = density;
		}
		else{
			if (density !=0 ){
				System.out.println("could not set density at ("+i+ ", "+ j+ ") to " + density+" , is water");;
			}
		}
	}


	public void timeStepSquare(int i, int j, double dt, double pumadensity) {
		//override with specific equation
	}

	public void timeStepAll(double dt, Population pumas) {
		//save state at beginning of timestep
		this.initDensities = densities;	
		//evolve each square
		for (int i=0; i < grid.getLandWidth(); i++){
			for (int j=0; j < grid.getLandHeight(); j++){
				 if (grid.isLand(i,j)){
				timeStepSquare(i,j, dt,pumas.getDensity(i, j));
				 }
				}
		}
	}

	public double diffuseSquare(int i, int j,double init_density,double dt) {
		double old_density= initDensities[i][j];
		double new_density = init_density + dt* diffusionrate*(density_sum(i,j)- (double)grid.countLand(i,j)*old_density);
		if (new_density<0){new_density=0;}
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

	public void setBirthRate(double r) {
		this.birthrate=r;
	}
	
	public double getBirthRate() {
		return this.birthrate;
	}
	
	

	public void setDeathRate(double a) {
		this.deathrate=a;
	}

	public double getDeathRate() {
		return this.deathrate;
	}
	
	public void setDiffusionRate(double k) {
		this.diffusionrate=k;
	}
	
	public double getDiffusionRate() {
		return this.diffusionrate;
	}

	
	public double getDensity(int i, int j) {
		if (grid.isLand(i,j)){
			return densities[i][j];
		}
		else{
		return 0;
		}
	}

	public double[][] getDensities() {
		return this.densities;
	}

	public void setDensities(double[][] densities) {
		//setting each element separately to check if each is on water square
		for (int i=0; i < grid.getLandWidth(); i++){
			for (int j=0; j < grid.getLandHeight(); j++){
				setDensity(i,j,densities[i][j]);
			}
		}
	}

	//for testing
	public void setInitDensitis(double[][] densities) {
		initDensities=densities;
	}
	
}
