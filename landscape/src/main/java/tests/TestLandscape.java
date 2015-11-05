package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Landscape;

public class TestLandscape {

	@Test
	public void testConstructorInt() {
		int width = 2;
		int height = 5;
		Landscape testlandscape = new Landscape(height, width);
		assertEquals(testlandscape.getSquare(0,0), 0);
		assertEquals(testlandscape.getLandWidth(), 2);
		assertEquals(testlandscape.getLandHeight(), 5);
	}

	
	@Test
	public void testConstructorArray() {
		int[][] test_array_in = new int[][] {{0,1,1},{1,1,1},{0,0,1},{0,1,1}};
		Landscape testlandscape = new Landscape(test_array_in);
		assertEquals(testlandscape.getSquare(0,0), 0);
		assertEquals(testlandscape.getSquare(2,1), test_array_in[2][1]);
		assertEquals(testlandscape.getSquare(2,1), 0);
		}
	
	
	@Test
	public void testSetLandscapeSize() {
		int width = 4;
		int height = 3;
		Landscape testlandscape = new Landscape(width, height);
		int[][] test_array_in = new int[][] {{1,1,1},{0,1,1},{0,1,1},{0,0,1}};
		testlandscape.setLandscape(test_array_in);
		assertEquals(testlandscape.getSquare(0,0), 1);
		assertEquals(testlandscape.getSquare(1,0), 0);
		assertEquals(testlandscape.getLandWidth(), 3);
		assertEquals(testlandscape.getLandHeight(), 4);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testSetLandscapeSizeException() {
		int width = 2;
		int height = 5;
		Landscape testlandscape = new Landscape(width, height);
		int[][] test_array_in = new int[][] {{0,1,1},{1,1,1},{0,0,1}};
		testlandscape.setLandscape(test_array_in);
	}
	
	
	@Test
	public void testCountLand() {
		int[][] test_array_in = new int[][] {{0,1,1},{0,1,1},{0,0,0},{0,0,0}};
		Landscape testlandscape = new Landscape(test_array_in);
		assertEquals(testlandscape.countLand(0,0),1);
		assertEquals(testlandscape.countLand(1,1),2);
		assertEquals(testlandscape.countLand(3,1),0);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testCountLandIndexException() {
		int[][] test_array_in = new int[][] {{0,1,1},{1,1,1},{0,0,1},{0,0,0}};
		Landscape testlandscape = new Landscape(test_array_in);
		int landcount = testlandscape.countLand(0,21);
		landcount = testlandscape.countLand(-1,2);
	}
	
	
	@Test
	public void testGetLandHeight() {
		Landscape testlandscape= new Landscape(15, 130);
		assertEquals(testlandscape.getLandHeight(),15);
		int[][] test_array_in = new int[][] {{0,1,1},{0,1,1}};
		Landscape testlandscape2 = new Landscape(test_array_in);
		assertEquals(testlandscape2.getLandHeight(),2);
	}
	
	@Test
	public void testGetLandWidth() {
		//width = number of subarrays = j 
		Landscape testlandscape= new Landscape(15, 130);
		assertEquals(testlandscape.getLandWidth(),130);
		int[][] test_array_in = new int[][] {{0,1,1},{0,1,1}};
		Landscape testlandscape2 = new Landscape(test_array_in);
		assertEquals(testlandscape2.getLandWidth(),3);
	}
	
	@Test 
	public void testSetGetSquare() {
		int[][] test_array_in = new int[][] {{0,1,1},{0,1,1},{0,0,1}};
		Landscape testlandscape = new Landscape(test_array_in);
		assertEquals(testlandscape.getSquare(1, 1),1);
		assertEquals(testlandscape.getSquare(0, 1),test_array_in[0][1]);
		assertEquals(testlandscape.getSquare(0, 1),1);
		assertEquals(testlandscape.getSquare(1, 0),0);
		testlandscape.setSquare(1, 1, 0);
		assertEquals(testlandscape.getSquare(1, 1),0);
	}
	
	public void testSetSquareValueException() {
		int[][] test_array_in = new int[][] {{0,1,1},{0,1,1},{0,0,1}};
		Landscape testlandscape = new Landscape(test_array_in);
		testlandscape.setSquare(1, 1,15);
		fail("Not yet implemented");
	}
	
	public void testSetSquareIndexException() {
		int[][] test_array_in = new int[][] {{0,1,1},{0,1,1},{0,0,1}};
		Landscape testlandscape = new Landscape(test_array_in);
		testlandscape.setSquare(1, 30,1);
		fail("Not yet implemented");
	}
	
	@Test
	public void testIsLand() {
		int[][] test_array_in = new int[][] {{0,1,1},{0,1,1},{0,0,1}};
		Landscape testlandscape = new Landscape(test_array_in);
		assertEquals(testlandscape.isLand(0,0), false);
		assertEquals(testlandscape.isLand(1,1), true);
		assertEquals(testlandscape.isLand(0,-1), false);
	}
	
	
}
