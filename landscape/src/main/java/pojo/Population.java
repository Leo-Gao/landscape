package pojo;

import java.util.Random;

import model.Landscape;


/**
 * Superclass holds characteristics and methods common to 
 * any evolving population of animals on a landscape.
 * Only method "timestep" needs to be overriden with 
 * specific equations for predator or prey.
 * 
 * @author jklebes
 *
 */
public class Population {
	
	/**
	 * birth rate
	 */
	protected double birthrate;
	
	/**
	 * death rate
	 */
	protected double deathrate;
	
	/**
	 * diffusion rate
	 */
	protected double diffusionrate;
	
	/**
	 * A of densities on each square of grid.
	 * Holds 0 for water squares
	 */
	protected double[][] densities;
	
	/**
	 * Array to hold saved copy of state at the beginning of each step.
	 */
	protected double[][] initDensities;
	
	/**
	 * Landscape class object giving array of land and water squares
	 * the population is on.
	 */
	Landscape grid;
	
	
	/**
	 * Constructor with no density arguments.
	 * Fills in default density of all 0s.
	 * @param grid Landscape grid the population is on
	 */
	public Population(Landscape grid){
		this.grid=grid;
		initiateArrays(grid.getLandHeight(),grid.getLandWidth());
		setUniformDensity(0);
	}
	
	/**
	 * Constructor with uniform density.
	 * Fills in density array with given double on land squares.
	 * @param grid Landscape object
	 * @param density double giving population's uniform density
	 */
	public Population(Landscape grid, double density) {
		this.grid=grid;
		initiateArrays(grid.getLandHeight(),grid.getLandWidth());
		setUniformDensity(density);
	}

	/**
	 * Constructor with density array.
	 * Copies given array of density values to land squares, water squares ignored.
	 * @param grid Landscape object
	 * @param density double giving population's density
	 */
	public Population(Landscape grid, double[][] densityarray) {
		this.grid=grid;
		initiateArrays(grid.getLandHeight(),grid.getLandWidth());
		setDensities(densityarray);
	}

	/**
	 * Constructor makes new population as copy of given population
	 * @param copy population to copy
	 */
	public Population(Population copy){
		this.grid=copy.grid;
		initiateArrays(grid.getLandHeight(),grid.getLandWidth());
		this.setDensities(copy.getDensities());
		this.setBirthRate(copy.getBirthRate());
		this.setDeathRate(copy.getDeathRate());
		this.setDiffusionRate(copy.getDiffusionRate());
	}
	
	/**
	 * Called by constructors,
	 * initiates arrays to correct sizes to match Landscape grid.
	 */
	private void initiateArrays(int height, int width) {
		densities = new double[height][width];
		initDensities = new double[height][width];
	}

	
	

	

	/**
	 * timesteps this population for the whole landscape for 1 step, interacting with 1 other population.
	 * @param dt length of step
	 * @param other_populations predator or prey population
	 */
	public void timeStepAll(double dt, Population other_population) {
		//save state at beginning of timestep
		this.initDensities = densities;	
		//evolve each square
		for (int i=0; i < grid.getLandHeight(); i++){
			for (int j=0; j < grid.getLandWidth(); j++){
				 if (grid.isLand(i,j)){
				timeStepSquare(i,j, dt,other_population.getDensity(i, j));
				 }
				}
		}
	
	}

	/**
	 * Evolves one population one square one timestep, interacting with 1 other population.
	 * Method to be overriden by subclass' specific equation.
	 * @param i index of square
	 * @param j index of square
	 * @param dt length of timestep
	 * @param other_density density of other predator or prey population at this location
	 */
	public void timeStepSquare(int i, int j, double dt, double other_density) {}
	
	/**
	 * Diffusion part of equation evolving the population on each square.
	 * Same for all species.
	 * @param i index of location
	 * @param j index of location
	 * @param init_density density after birth and death but before diffusion
	 * @param dt length of timestep
	 * @return density at end of step, after birth, death, and diffusion
	 */
	public double diffuseSquare(int i, int j,double init_density,double dt) {
		double old_density= initDensities[i][j];
		double new_density = init_density + dt* diffusionrate*(density_sum(i,j)- (double)grid.countLand(i,j)*old_density);
		if (new_density<0){new_density=0;}
		return new_density;
	}

	/**
	 * Sums density of population in surrounding squares (excluding diagonals).
	 * Used for diffusion.
	 * @param i index of location
	 * @param j index of location
	 * @return sum of population densities in surrounding squares
	 */
	private double density_sum(int i, int j) {
		double sum =0;
		sum = sum + getOldDensity(i-1,j)+ getOldDensity(i+1,j)+ getOldDensity(i,j-1)+ getOldDensity(i,j+1);
		return sum;
	}

	/**
	 * Looks up density from array saved at beginning of timestep.
	 * Reference these rather than partially evolved grid when difusing each square.
	 * @param i index of location
	 * @param j index of location
	 * @return density at index at beginning of this timestep
	 */
	private double getOldDensity(int i, int j) {
		if (grid.isLand(i,j)){
			return initDensities[i][j];
		}
		else{
			return 0;
		}
	}

	
	
	/**
	 * set birth rate
	 * @param r birthrate
	 */
	public void setBirthRate(double r) {
		this.birthrate=r;
	}
	
	/**
	 * get birth rate
	 * @return birth rate
	 */
	public double getBirthRate() {
		return this.birthrate;
	}
	
	/**
	 * set death rate
	 * @param a death rate
	 */
	public void setDeathRate(double a) {
		this.deathrate=a;
	}

	/**
	 * get death rate 
	 * @return death rate
	 */
	public double getDeathRate() {
		return this.deathrate;
	}
	
	/**
	 * set diffusion rate
	 * @param k diffusion rate
	 */
	public void setDiffusionRate(double k) {
		this.diffusionrate=k;
		}
	
	/**
	 * get diffusion rate
	 * @return diffusion rate
	 */
	public double getDiffusionRate() {
		return this.diffusionrate;
	}
	
	/**
	 * Sets population density on all land squares to given value.
	 * @param density
	 */
	public void setUniformDensity(double density) {
		for (int i=0; i < grid.getLandHeight(); i++){
			for (int j=0; j < grid.getLandWidth(); j++){
				setDensity(i,j,density);
			}
		}
	}

	/**
	 * Sets population density at 1 square to given value.
	 * No effect if water.
	 * @param i coordinate of square to set
	 * @param j coordinate of square to set
	 * @param density
	 */
	public void setDensity(int i, int j, double density) {
		if (grid.isLand(i,j)){
			densities[i][j] = density;
		}
		else{
			if (density !=0 ){
//				System.out.println("could not set density at ("+i+ ", "+ j+ ") to " + density+" , is water");;
			}
		}
	}

	/**
	 * get density at square (i,j)
	 * @param i index of location
	 * @param j index of location
	 * @return population density at location
	 */
	public double getDensity(int i, int j) {
		if (grid.isLand(i,j)){
			return densities[i][j];
		}
		else{
		return 0;
		}
	}

	/**
	 * get array of population densities
	 * @return array of densities
	 */
	public double[][] getDensities() {
		return this.densities;
	}

	/**
	 * Set all densities from array.
	 * Will copy to class excluding water squares.
	 * @param densities array of densities
	 */
	public void setDensities(double[][] densities) {
		//setting each element separately to check if each is on water square
		for (int i=0; i < grid.getLandHeight(); i++){
			for (int j=0; j < grid.getLandWidth(); j++){
				setDensity(i,j,densities[i][j]);
			}
		}
	}
	
	/**
	 * average population densities on all squares including water
	 * @return average density
	 */
	public double getAvgDensityGrid(){
		double square_count = 0;
		double sum_density= 0;
		for (int i=0; i < grid.getLandHeight(); i++){
			for (int j=0; j < grid.getLandWidth(); j++){
				sum_density += getDensity(i,j);
				square_count += 1;
			}
		}
		double avgdensity = sum_density / square_count;
		return avgdensity;
	}

	/**
	 * average population density for land area
	 * @return average density
	 */
	public double getAvgDensityLand(){
		double square_count = 0;
		double sum_density= 0;
		for (int i=0; i < grid.getLandHeight(); i++){
			for (int j=0; j < grid.getLandWidth(); j++){
				if (grid.isLand(i,j)){
				sum_density += getDensity(i,j);
				square_count += 1;
				}
			}
		}
		double avgdensity = sum_density / square_count;
		return avgdensity;
	}
	
	/**
	 * sets squares to random densities evenly distributed between 0 and max
	 * @param max maximum density
	 * @param seed long seed
	 */
	public void setRandomDensities(double max, long seed){
		Random random = new Random(seed);
		for (int i=0; i < grid.getLandHeight(); i++){
			for (int j=0; j < grid.getLandWidth(); j++){
				setDensity(i,j,random.nextDouble()*max);
			}
		}
	}
	
	
	/**
	 * sets squares to random densities evenly distributed between min and max
	 * @param min minimum density
	 * @param max maximum density
	 * @param seed long seed
	 */
	public void setRandomDensities(double min, double max, long seed){
		Random random = new Random(seed);
		for (int i=0; i < grid.getLandHeight(); i++){
			for (int j=0; j < grid.getLandWidth(); j++){
				setDensity(i,j,min + random.nextDouble()*(max-min));
			}
		}
	}
		
	/**
	 * manually set array of densities at beginning of step.
	 * for testing only
	 * @param densities array of densities representing copy of past state
	 */
	public void setInitDensitis(double[][] densities) {
		initDensities=densities;
	}
	
	/**
	 * @return  max value of the desities accross gird
	 */
	public double getMaxValue(){
		
		Double max = 0d;
		for (int i=0; i < grid.getLandHeight(); i++){
			for (int j=0; j < grid.getLandWidth(); j++){
				if (densities[i][j] > max) {
					max = densities[i][j];
				}
			}
		}
		return max;
	}
	
}
