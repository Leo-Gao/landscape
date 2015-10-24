package model;

/*
 * landscape as 2d array with given resolution
 * class holds array of water and land squares with
 * halo of water
 **/

public class Landscape {
	private int width;
	private int height;
	private int width_with_halo;
	private int height_with_halo;
	private boolean[][] grid; //using boolean rather than 1 and 0
						//for land and water to eliminate invalid values
	
	
	public Landscape(int i, int j){
		this.setWidth(i);
		this.setHeight(j);
		this.width_with_halo=getWidth()+2;
		this.height_with_halo=getHeight()+2;
		this.grid=new boolean[width_with_halo][height_with_halo];
	}
	
	public Landscape(int[][] landScape) {
		this.setWidth(landScape.length);
		this.setHeight(landScape[0].length);
		this.width_with_halo=getWidth()+2;
		this.height_with_halo=getHeight()+2;
		this.grid=new boolean[width_with_halo][height_with_halo];
		setLandscape(landScape);
	}
	
	public void setLandscape(int[][] grid_from_file){
		for (int i=1; i<=width_with_halo-1; i++){
			for (int j=1; j<=height_with_halo-1; j++){
				setSquare(i-1, j-1, grid_from_file[i-1][j-1]);
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
		return 0;
	}

	//gets 0 or 1 value
	//i j - unhaloed coordinates
	//looks up corresponding coordinate in haloed square
	//will return value of halo (0) for i,j = -1, N
	private int getSquare(int i, int j) {
		int value;
		if (this.grid[i+1][j+1]== true){
			value=1;
		}
		else { 
			value=0;
			}
		return value;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	//i j refers to unhaloed grid
	public boolean isLand(int i, int j) {
		return this.grid[i+1][j+1];
	}
	
	
}
