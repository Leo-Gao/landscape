package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Landscape;

public class TestLandscape {

	@Test
	public void testConstructorInt() {
		int width = 2;
		int height = 5;
		Landscape testlandscape = new Landscape(width, height);
		assertEquals(testlandscape.getSquare(0,0), 0);
		assertEquals(testlandscape.getLandWidth(), 2);
		assertEquals(testlandscape.getLandHeight(), 5);
		fail("Not yet implemented");
	}

	
	@Test
	public void testConstructorArray() {
		int[][] test_array_in = new int[][] {{0,1,1},{1,1,1},{0,0,1}};
		Landscape testlandscape = new Landscape(test_array_in);
		assertEquals(testlandscape.getSquare(0,0), 0);
		assertEquals(testlandscape.getSquare(1,2), 1);
		}
	
	@Test
	public void testConstructorArrayException() {
		}
	
	@Test
	public void testSetLandscape() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetLandscapeSquare() {
		fail("Not yet implemented");
	}
	
	
}
