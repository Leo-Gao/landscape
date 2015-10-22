package pojo;
/**
 * entity of puma
 * @author Gaolu
 *
 */
public class Puma {
	//birth rate of pumas
	private double b = 0.2d;
	
	//mortality rate
	private double m = 0.06d;
	
	//diffusion rates
	private double l = 0.2d;
	
	//densities
	private double[][] densities;
	
	//landScape
	public int[][] landScape;
	
	//generate fisrt densities for puma
    public double[][] generateIntDentities(){
    	//TODO
    	//according to the component of landScape to create its first densities 
    	return null;
    }
    

	/**
	 *  birth rate of pumas
	 */
	public double getB() {
		return b;
	}

	/**
	 * mortality rate
	 */
	public double getM() {
		return m;
	}
	
	/**
	 * diffusion rates
	 */
	public double getL() {
		return l;
	}
	
	public void setB(double b) {
		this.b = b;
	}


	public void setM(double m) {
		this.m = m;
	}


	public void setL(double l) {
		this.l = l;
	}


	public Puma() {
		super();
	}
	
	public Puma(double b, double m, double l) {
		super();
		this.b = b;
		this.m = m;
		this.l = l;
	}

	public Puma(double[][] densities) {
		super();
		this.densities = densities;
	}

	public double[][] getDensities() {
		return densities;
	}

	public void setDensities(double[][] densities) {
		this.densities = densities;
	}
	
	
	
}
