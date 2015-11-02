package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Landscape;
import pojo.HarePopulation;
import pojo.PumaPopulation;

public class TestHarePopulation {

	
	Landscape testlandscape = new Landscape(new int[][] {{1,1,1},{1,1,1},{1,1,1}});
	Landscape testlandscapewithwater = new Landscape(new int[][] {{1,1,1},{1,1,0},{1,0,0}});
	@Test
	public void testConstructor1() {
		HarePopulation hares = new HarePopulation(testlandscape);
		assertArrayEquals(hares.getDensities(), new double[][] {{0,0,0},{0,0,0},{0,0,0}});
	}
	
	@Test
	public void testConstructor2() {
		double density =2;
		HarePopulation hares = new HarePopulation(testlandscape, density);
		assertArrayEquals(hares.getDensities(), new double[][] {{2,2,2},{2,2,2},{2,2,2}});
	}
	
	@Test
	public void testConstructor3() {
		double[][] densityarray = new double[][] {{2,2,2},{2,2,2},{2,2,2}};
		HarePopulation hares = new HarePopulation(testlandscape, densityarray);
		assertArrayEquals(hares.getDensities(), new double[][] {{2,2,2},{2,2,2},{2,2,2}});
	}
	
	@Test
	public void testDiffuseSquareZeros() {
		HarePopulation hares = new HarePopulation(testlandscape, 0);
		assertEquals(hares.diffuseSquare(1, 1, 0, .1),0, .00001);
	}
	
	@Test
	public void testDiffuseIntoSquare() {
		HarePopulation hares = new HarePopulation(testlandscape, 5);
		hares.setDensity(1,1, 0);
		hares.setInitDensitis(hares.getDensities());
		hares.setDiffusionRate(.2);
		double new_density = hares.diffuseSquare(1, 1, 0, .1);
		double new_density_expected= 0 + .1*.2*(4*5 - 4*0);
		assertEquals(new_density_expected, new_density, .0001);
	}
	
	@Test
	public void testDiffuseOutOfSquare() {
		HarePopulation hares = new HarePopulation(testlandscape, 0);
		hares.setDensity(1,1, 5);
		hares.setInitDensitis(hares.getDensities());
		hares.setDiffusionRate(.2);
		double new_density = hares.diffuseSquare(1, 1, 5, .1);
		double new_density_expected= 5 + .1*.2*(4*0 - 4*5);
		assertEquals(new_density_expected, new_density, .0001);
	}
	
	@Test
	public void testDiffuseSquare() {
		double[][] densityarray = new double[][] {{2.1,13,10},{.4,3,17},{3.76,20,.1}};
		HarePopulation hares = new HarePopulation(testlandscape, densityarray);
		hares.setDiffusionRate(2);
		hares.setInitDensitis(hares.getDensities());
		double new_density = hares.diffuseSquare(1, 1, 3, .1);
		double new_density_expected= 3 + .1*2*(13 + .4 + 17 + 20 - 4*3);
		assertEquals(new_density_expected, new_density, .0001);
	}
	
	@Test
	public void testDiffuseSquareWithWater() {
		double[][] densityarray = new double[][] {{2.1,13,10},{.4,3,17},{3.76,20,.1}};
		HarePopulation hares = new HarePopulation(testlandscapewithwater, densityarray);
		hares.setDiffusionRate(2);
		hares.setInitDensitis(hares.getDensities());
		double new_density = hares.diffuseSquare(1, 1, 3, .1);
		double new_density_expected= 3+.1*2*(13 + .4 - 2*3);
		assertEquals(new_density_expected, new_density, .0001);
	}
	
	
	
	@Test
	public void testGetSetDensities() {
		HarePopulation hares = new HarePopulation(testlandscape);
		hares.setDensities(new double[][]{{.5,0,0},{14,2,1},{0,1,1}});
		assertEquals(hares.getDensity(0, 0), .5, .001);
		assertArrayEquals(hares.getDensities(), new double[][]{{.5,0,0},{14,2,1},{0,1,1}});
	}

	@Test
	public void testGetSetDensity() {
		HarePopulation hares = new HarePopulation(testlandscape);
		hares.setDensity(0, 1, 15.19);
		assertEquals(hares.getDensity(0, 1), 15.19, .0001);
		assertEquals(hares.getDensity(0, 0), 0, .0001);
	}

	@Test
	public void testSetGetBirthRate() {
		HarePopulation hares = new HarePopulation(testlandscape, 12);
		hares.setBirthRate(8.15);
		assertEquals(hares.getBirthRate(), 8.15, .0001);
	}

	@Test
	public void testSetBirthRateException() {
		HarePopulation hares = new HarePopulation(testlandscape, 12);
		hares.setBirthRate(-.2);
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetDeathRateException() {
		HarePopulation hares = new HarePopulation(testlandscape, 12);
		hares.setDeathRate(-3701);
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetDeathRate() {
		HarePopulation hares = new HarePopulation(testlandscape, 12);
		hares.setDeathRate(2.1);
		assertEquals(hares.getDeathRate(), 2.1, .0001);
	}

	@Test
	public void testSetAllDensities() {
		HarePopulation hares = new HarePopulation(testlandscape);
		hares.setUniformDensity(3);
		assertEquals(hares.getDensity(0, 2), 3, .001);
		assertArrayEquals(hares.getDensities(), new double[][]{{3,3,3},{3,3,3},{3,3,3}});
	}

	@Test
	public void testSetDiffusionRate() {
		HarePopulation hares = new HarePopulation(testlandscape, 0);
		hares.setDiffusionRate(3);
		assertEquals(hares.getDiffusionRate(), 3, .0001);
	}

	@Test
	public void testTimeStepAll() {
		Landscape testgrid=new Landscape(new int[][]{{1,1},{1,1}});
		HarePopulation hares = new HarePopulation (testgrid);
		PumaPopulation pumas = new PumaPopulation (testgrid,2);
		hares.setDensities(new double[][]{{.5,14},{4,.2}});
		hares.setBirthRate(.2);
		hares.setDeathRate(.3);
		hares.setDiffusionRate(.25);
		hares.timeStepAll(.1, pumas);
		double newdensity1= .5 + .1*(.2*.5 - .3*.5*2 + .25*(14 + 4 - 2*.5));
		assertEquals(hares.getDensity(0, 0), newdensity1, .05);
		double newdensity3= .2 + .1*(.2*.2 - .3*.2*2 + .25*(14 + 4 - 2*.2));
		assertEquals(hares.getDensity(1, 1), newdensity3, .05);
	}

	@Test
	public void testTimeStepSquare() {
		Landscape testgrid=new Landscape(new int[][]{{1,1},{1,1}});
		HarePopulation hares = new HarePopulation (testgrid);
		hares.setDensities(new double[][]{{.5,.4},{.4,.2}});
		hares.setInitDensitis(hares.getDensities());
		hares.setBirthRate(.2);
		hares.setDeathRate(.3);
		hares.setDiffusionRate(.25);
		hares.timeStepSquare(0, 0, .1 ,.3);
		double newdensity= .5 + .1*(.2*.5 - .3*.5*.3 + .25*(.4+.4 - 2*.5));
		assertEquals(hares.getDensity(0, 0), newdensity, .05);
	}
	
	@Test
	public void testGetMaxValue(){
		Landscape testgrid=new Landscape(new int[][]{{1,1},{1,1}});
		HarePopulation hares = new HarePopulation(testgrid);
		hares.setDensities(new double[][]{{.1,.55},{.3,.4}});
		double max = hares.getMaxValue();
		assertEquals(.55, max,.000001);
	}
}
