package com.whittle.lsf;

import java.util.ArrayList;
import java.util.List;

import com.whittle.lsf.common.Coord;

public class TestLSF {

	private TestLSF() {
		
	}

	public static void main(String[] args) {

		List<Coord> coordinates = new ArrayList<Coord>();

		Coord c = new Coord(0, 26.23);
		coordinates.add(c);
		c = new Coord(1, 37.72);
		coordinates.add(c);
		c = new Coord(2, 48.32);
		coordinates.add(c);
		c = new Coord(3, 58.96);
		coordinates.add(c);
		c = new Coord(4, 69.40);
		coordinates.add(c);
		c = new Coord(5, 80.85);
		coordinates.add(c);
		c = new Coord(6, 91.68);
		coordinates.add(c);
		
		LeastSquaresFit leastSquaresFit = new LeastSquaresFit(coordinates);
		
		System.out.println("a: " + leastSquaresFit.getA());
		System.out.println("a ±: " + leastSquaresFit.getaError());
		System.out.println("b: " + leastSquaresFit.getB());
		System.out.println("b ±: " + leastSquaresFit.getbError());
		System.out.println("n: " + leastSquaresFit.getN());
		System.out.println("r: " + leastSquaresFit.getR());
		System.out.println(leastSquaresFit);
		
		

	}

}
