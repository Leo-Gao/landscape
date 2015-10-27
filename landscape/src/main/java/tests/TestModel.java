package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Landscape;
import model.Model;
import pojo.HarePopulation;
import pojo.PumaPopulation;

public class TestModel {

	@Test
	public void testConstructor() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEvolveZeroBirthRate1() {
		Landscape grid = new Landscape(new int[][] {{1,1,1},{1,1,1},{1,1,1}});
		HarePopulation hares=new HarePopulation(grid);
		hares.setDensities(new double[][] {{12,22,5.6},{1,2,1},{3.8,9,1}});
		hares.setBirthRate(0.0);
		hares.setDeathRate(0.2);
		hares.setDiffusionRate(0.2);
		assertEquals(hares.getBirthRate(), 0, .001);
		PumaPopulation pumas=new PumaPopulation(grid);
		pumas.setDensities(new double[][] {{1,.9,19},{5,2,5},{3,0,8.3}});
		pumas.setBirthRate(.2);
		pumas.setDeathRate(0.2);
		pumas.setDiffusionRate(0.2);
		assertEquals(pumas.getBirthRate(), 0.2, .001);
		Model model = new Model(hares, pumas);
		model.evolve(100, 1);
		assertEquals(hares.getDensity(0,0), 0, .001);
		assertEquals(hares.getDensity(0,1), 0, .001);
		assertEquals(hares.getDensity(1,2), 0, .001);
	}
	
	@Test
	public void testEvolveZeroBirthRate2() {
		Landscape grid = new Landscape(new int[][] {{1,1,1},{1,1,1},{1,1,1}});
		HarePopulation hares=new HarePopulation(grid);
		hares.setDensities(new double[][] {{12,22,5.6},{1,2,1},{3.8,9,1}});
		hares.setBirthRate(0.2);
		hares.setDeathRate(0.2);
		hares.setDiffusionRate(0.2);
		assertEquals(hares.getBirthRate(), 0.2, .001);
		PumaPopulation pumas=new PumaPopulation(grid);
		pumas.setDensities(new double[][] {{1,.9,19},{5,2,5},{3,0,8.3}});
		pumas.setBirthRate(0);
		pumas.setDeathRate(0.2);
		pumas.setDiffusionRate(0.2);
		assertEquals(pumas.getBirthRate(), 0.0, .001);
		Model model = new Model(hares, pumas);
		model.evolve(100, 1);
		assertEquals(pumas.getDensity(0,0), 0, .001);
		assertEquals(pumas.getDensity(0,1), 0, .001);
		assertEquals(pumas.getDensity(1,2), 0, .001);
	}



}
