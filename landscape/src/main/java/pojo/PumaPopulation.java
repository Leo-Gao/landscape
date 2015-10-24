package pojo;

import model.Landscape;

/**
 * entity of puma
 * @author Gaolu
 *
 */
public class PumaPopulation implements Population {
	//birth rate of pumas
	private double birthrate = 0.2d;
	
	//mortality rate
	private double deathrate = 0.06d;
	
	//diffusion rates
	private double diffusionrate = 0.2d;
	
	//densities
	private double[][] densities;
	
	//landScape
	public Landscape grid;

	private double[][] initDensities;
	
	//constructor
    public PumaPopulation(Landscape grid){
    	this.grid=grid;
    }
    
    
    public PumaPopulation(double b, double m, double l) {
		super();
		this.birthrate = b;
		this.deathrate = m;
		this.diffusionrate = l;
	}
	
    @Override
	public double[][] getDensities() {
		return densities;
	}

    @Override
	public void setDensities(double[][] densities) {
		this.densities = densities;
	}


	@Override
	public void timeStepSquare(int i, int j, double dt, double haredensity) {
		double old_density = initDensities[i][j];
		
		//puma equation
		double new_density= old_density + dt* birthrate * haredensity*old_density - dt * deathrate *old_density;
		new_density = diffuseSquare( i,j, new_density, dt);
		
		densities[i][j]=new_density;
	}


	@Override
	public void timeStepAll(double dt, Population hares) {
		//save state at beginning of step
		this.initDensities = densities;	
		//evolve each square
		for (int i=1; i < grid.getLandWidth(); i++){
			for (int j=1; j < grid.getLandHeight(); j++){
				if (grid.isLand(i,j)){
					timeStepSquare(i,j, dt,hares.getDensity(i, j));
				}
			}
		}
	}


	@Override
	public double diffuseSquare(int i, int j, double init_density, double dt) {
		//old_denstiy - density in square at beginning of timestep
		//init_density - density after birthrate and deathrate but before diffusion
		//new_density - density at end of timestep
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
	public double getDensity(int i, int j) {
		if (grid.isLand(i,j)){
			return densities[i][j];
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
	
	
	
}
