package pojo;
/**
 * entity of Hare
 * @author Gaolu
 *
 */
public class Hare {
	//birth rate of hares
	private double r = 0.08d;
	
	//predation rate at which pumas eat hares
	private double a = 0.04d;
	
	//diffusion rates for hares
	private double k = 0.2d;
	
	//densities
	private double[][] densities; 
    
	//landScape
    public int[][] landScpage;
    
    //generate fisrt densities for hare
    public double[][] generateIntDentities(){
    	//TODO
    	return null;
    }
	
	/**
	 * birth rate of hares
	 * @return
	 */
	public double getR() {
		return r;
	}
	/**
	 * predation rate at which pumas eat hares
	 * @return
	 */
	public double getA() {
		return a;
	}
	/**
	 * diffusion rates for hares
	 * @return
	 */
	public double getK() {
		return k;
	}
	
	
	
	/**
	 * densities
	 * @return
	 */
	public double[][] getDensities() {
		return densities;
	}


	public void setDensities(double[][] densities) {
		this.densities = densities;
	}
	
	
	public void setR(double r) {
		this.r = r;
	}
	public void setA(double a) {
		this.a = a;
	}
	public void setK(double k) {
		this.k = k;
	}
	public Hare() {
		super();
	}
	public Hare(double r, double a, double k) {
		super();
		this.r = r;
		this.a = a;
		this.k = k;
	}
	public Hare(double[][] densities) {
		super();
		this.densities = densities;
	}
	
}
