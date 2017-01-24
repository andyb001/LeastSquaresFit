package com.whittle.lsf;

import java.text.DecimalFormat;
import java.util.List;

import com.whittle.lsf.common.Coord;

/**
 * Least Squares Fit analysis.
 * @author Andy Whittle
 * @version 1.0.0
 */
public class LeastSquaresFit {
	
	private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###,###,###,##0.0000");
	private List<Coord> coordinates;
	private double x2Sum = 0;
	private double xSum = 0;
	private double xSum2 = 0;
	private double ySum = 0;
	private double y2Sum = 0;
	private double ySum2 = 0;
	private double xySum = 0;
	private double a = 0, b = 0, n = 0, r = 0, aError = 0, bError = 0;
	
	/**
	 * Constructor
	 * @param coordinates A list of coordinate pairs
	 * @see com.whittle.lsf.Coord
	 */
	public LeastSquaresFit(List<Coord> coordinates) {
		this.coordinates = coordinates;
		init();
	}

	private void init() {
		
		n = coordinates.size();
		
		for (Coord c : coordinates) {
			x2Sum += Math.pow(c.getX(), 2);
			xSum += c.getX();
			ySum += c.getY();
			xySum += c.getX() * c.getY();
			y2Sum += Math.pow(c.getY(), 2);
		}
		xSum2 = Math.pow(xSum, 2);
		ySum2 = Math.pow(ySum, 2);
		
		a = (n * xySum - xSum * ySum) / (n * x2Sum - xSum2);
		
		b = (x2Sum * ySum - xSum * xySum) / (n * x2Sum - xSum2);
		
		r = (n * xySum - xSum * ySum)/(Math.sqrt(n * x2Sum - xSum2) * Math.sqrt(n * y2Sum - ySum2));
		
		double n2Sigma2 = (n * y2Sum - ySum2 - (Math.pow((n * xySum - xSum * ySum), 2) / (n * x2Sum - xSum2))) / 1;
		
		aError = Math.sqrt(n2Sigma2) / Math.sqrt((n-2) * (n * x2Sum - xSum2));
		
		bError = Math.sqrt(x2Sum) / Math.sqrt(n*(n-2)*(n*x2Sum - xSum2));
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("LSF[");
		s.append("n = ");
		s.append(n);
		s.append(", a = ");
		s.append(DECIMAL_FORMAT.format(a));
		s.append(" ± ");
		s.append(DECIMAL_FORMAT.format(aError));
		s.append(", b = ");
		s.append(DECIMAL_FORMAT.format(b));
		s.append(" ± ");
		s.append(DECIMAL_FORMAT.format(bError));
		s.append(", r = ");
		s.append(DECIMAL_FORMAT.format(r));
		s.append("]");
		return s.toString();
	}

	/**
	 * 
	 * @return
	 */
	public double getA() {
		return a;
	}

	/**
	 * 
	 * @return
	 */
	public double getB() {
		return b;
	}

	/**
	 * 
	 * @return
	 */
	public double getN() {
		return n;
	}

	/**
	 * 
	 * @return
	 */
	public double getR() {
		return r;
	}

	/**
	 * 
	 * @return
	 */
	public double getaError() {
		return aError;
	}

	/**
	 * 
	 * @return
	 */
	public double getbError() {
		return bError;
	}
	
}
