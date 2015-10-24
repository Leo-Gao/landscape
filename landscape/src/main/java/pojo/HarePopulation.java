package pojo;

import model.Landscape;

/**
 * entity of Hare
 * @author Gaolu
 * @author jklebes
 */

public class HarePopulation implements Population {
	
	//default values
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
		initiateArrays(grid.getLandWidth(),grid.getLandHeight());
		setUniformDensity(0);
	}
	

	public HarePopulation(Landscape grid, double density) {
		this.grid=grid;
		initiateArrays(grid.getLandWidth(),grid.getLandHeight());
		setUniformDensity(density);
	}

	

	public HarePopulation(Landscape grid, double[][] densityarray) {
		this.grid=grid;
		initiateArrays(grid.getLandWidth(),grid.getLandHeight());
		setDensities(densityarray);
	}

	private void initiateArrays(int width, int height) {
		densities = new double[width][height];
		initDensities = new double[width][height];
	}

	
	@Override
	public void setUniformDensity(double density) {
		for (int i=0; i < grid.getLandWidth(); i++){
			for (int j=0; j < grid.getLandHeight(); j++){
				setDensity(i,j,density);
			}
			}
	}

	@Override
	public void setDensity(int i, int j, double density) {
		if (grid.isLand(i,j)){
			densities[i][j] = density;
		}
		else{
			if (density !=0 ){
				System.out.println("could not set density at "+i+ ", "+ j+ " to " + density+" , is water");;
			}
		}
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
		for (int i=0; i < grid.getLandWidth(); i++){
			for (int j=0; j < grid.getLandHeight(); j++){
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
		this.birthrate=r;
	}

	@Override
	public void setDeathRate(double a) {
		this.deathrate=a;
	}

	@Override
	public void setDiffusionRate(double k) {
		this.diffusionrate=k;
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
		return this.densities;
	}

	@Override
	public void setDensities(double[][] densities) {
		//setting each element separately to check if each is on water square
		for (int i=0; i < grid.getLandWidth(); i++){
			for (int j=0; j < grid.getLandHeight(); j++){
				setDensity(i,j,densities[i][j]);
			}
		}
	}
	
}
