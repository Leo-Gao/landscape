package model;

/*
 * landscape as 2d array with given resolution
 * class holds array of water and land squares with
 * halo of water
 **/

public class Landscape {
	private int height;
	private int width;
	private int height_with_halo;
	private int width_with_halo;
	private boolean[][] grid; //using boolean rather than 1 and 0
						//for land and water to eliminate invalid values
	
	
	public Landscape(int i, int j){
		this.height = i;
		this.width = j;
		this.height_with_halo=height+2;
		this.width_with_halo=width+2;
		this.grid=new boolean[height_with_halo][width_with_halo];
	}
	
	public Landscape(int[][] landScape) {
		this.height = landScape.length;
		this.width = landScape[0].length;
		this.height_with_halo=height+2;
		this.width_with_halo=width+2;
		this.grid=new boolean[height_with_halo][width_with_halo];
		setLandscape(landScape);
	}
	
	public void setLandscape(int[][] grid_from_file){
		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				setSquare(i, j, grid_from_file[i][j]);
			}
		}
	}
	
	//value given as un-haloed grid coordinate, 0 or 1
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

	//i, j un haloed coordinates
	public int countLand(int i, int j) {
		int land_number = 0;
		land_number = land_number + getSquare(i-1,j)+ getSquare(i+1,j)+ getSquare(i,j-1)+ getSquare(i,j+1);
		return land_number;
	}

	//gets 0 or 1 value
	//i j - unhaloed coordinates
	//looks up corresponding coordinate in haloed square
	//will return value of halo (0) for i,j = -1, N
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

	public int getLandWidth() {
		return width;
	}


	public int getLandHeight() {
		return height;
	}


	//i j refers to unhaloed grid
	public boolean isLand(int i, int j) {
		return this.grid[i+1][j+1];
	}
	
	
}
