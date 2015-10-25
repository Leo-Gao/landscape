package model;


/**
 * 
 * Landscape class holds grid of water or land values
 * Includes halo of 0s in internal grid but
 * all public methods can be used withou considering halo
 * 
 * @author jsk
 * 
 */
public class Landscape {
	
	/**
	 * height of input/output grid
	 * (number of subarrays)
	 */
	private int height;
	
	/**
	 * length of input/output grid
	 * (number of elements in each subarray)
	 */
	private int width;
	
	/**
	 * height of internal grid
	 */
	private int height_with_halo;
	
	/**
	 * length of internal grid
	 */
	private int width_with_halo;
	
	/**
	 * internal grid including halo of water
	 * uses booleans rather than 1 and 0
	 */
	private boolean[][] grid; 
	
	/**
	 * Constructor .
	 * Initiates grid size i x j 
	 * filled with default boolean false (water).
	 * @param i height of grid = number of rows = number of subarrays
	 * @param j width of grid = number of columns = length of subarrays
	 */
	public Landscape(int i, int j){
		this.height = i;
		this.width = j;
		this.height_with_halo=height+2;
		this.width_with_halo=width+2;
		this.grid=new boolean[height_with_halo][width_with_halo];
	}
	
	/**
	 * Constructor from array.
	 * Initiates grid by copying given grid of 1s and 0s into class.
	 * @param landScape array of 1s and 0s
	 */
	public Landscape(int[][] landScape) {
		this.height = landScape.length;
		this.width = landScape[0].length;
		this.height_with_halo=height+2;
		this.width_with_halo=width+2;
		this.grid=new boolean[height_with_halo][width_with_halo];
		setLandscape(landScape);
	}
	
	/**
	 * Copies given int array of 1s and 0s
	 * as internal grid of booleans.
	 * @param grid_from_file array of 1s and 0s
	 */
	public void setLandscape(int[][] grid_from_file){
		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				setSquare(i, j, grid_from_file[i][j]);
			}
		}
	}
	
	/**
	 * Sets single square to land or water.
	 * @param i index
	 * @param j index
	 * @param value as 1 or 0
	 */
	public void setSquare(int i, int j, int value){
		if (value==1){
			this.grid[i+1][j+1]=true;
		}
		else if (value==0) {
			this.grid[i+1][j+1]=false;
		}
		else { 
			// TODO exception
			System.out.println("bad value in landscape grid entered");		
		}
	}

	/**
	 * Counts number of land squares surrounding given square
	 * (excluding diagonals).
	 * @param i index of square
	 * @param j index of square
	 * @return number (0 to 4) of neighboring land squares
	 */
	public int countLand(int i, int j) {
		int land_number = 0;
		land_number = land_number + getSquare(i-1,j)+ getSquare(i+1,j)+ getSquare(i,j-1)+ getSquare(i,j+1);
		return land_number;
	}

	/**
	 * Looks up land or water value at square.
	 * Indices given as unhaloed grid coordinated. 
	 * Looks up corresponding haloed location, 
	 * will also accept -1 and N and return 0 (water).
	 * @param i index of square to look up
	 * @param j index of square to look up
	 * @return value of square as 0 or 1
	 */
	public int getSquare(int i, int j) {
		int value;
		if (this.grid[i+1][j+1]== true){
			value=1;
		}
		else { 
			value=0;
			}
		return value;
	}

	/**
	 * looks up width of grid (excluding halo)
	 * @return width
	 */
	public int getLandWidth() {
		return width;
	}

	/**
	 * looks up height of grid (excluding halo)
	 * @return height
	 */
	public int getLandHeight() {
		return height;
	}


	/**
	 * Checks if location is land.
	 * Indices refer to unhaloed coordinate system.
	 * @param i index to look up
	 * @param j index to look up
	 * @return true if land
	 */
	public boolean isLand(int i, int j) {
		return this.grid[i+1][j+1];
	}
	
	
}
