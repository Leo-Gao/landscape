package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Landscape;
import pojo.HarePopulation;

public class TestHarePopulation {

	@Test
	public void testDiffuseSquare() {
		fail("Not yet implemented");
	}
	@Test
	public void testGetDensities() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDensity() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitiatePopulation() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBirthRate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDeathRate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDensities() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDiffusionRate() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimeStepAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimeStepSquare() {
		Landscape testgrid=new Landscape(new int[][]{{1,1},{1,1}});
		HarePopulation hares = new HarePopulation (testgrid);
		hares.setDensities(new double[][]{{.5,.1},{.4,.2}});
		hares.setBirthRate(.2);
		hares.setDeathRate(.3);
		hares.setDiffusionRate(.25);
		hares.timeStepSquare(0, 0, .1 ,.3);
		double newdensity= .5 + .1*(.2*.5 - .3*.5*.3 + .25*(.1+.4 - 2*.5));
		assertEquals(hares.getDensity(0, 0), newdensity, .0001);
	}


}
