
package edu.uprm.ece.icom4035.polynomial;

import java.util.StringTokenizer;

import edu.uprm.ece.icom4035.list.ArrayList;
import edu.uprm.ece.icom4035.list.List;

public class TermImp implements Term {
	private double coefficient;
	private int exponent;


	public TermImp(double coefficient, int exponent) {
		
		super();
		
		this.coefficient = coefficient;
		
		this.exponent = exponent;
		
	}
	
	
	
	public double getCoefficient() {
		
		return coefficient;
		
	}
	
	
	@Override
	public int getExponent() {
		
		return exponent;
		
	}
	
	
	public double evaluate(double x) {
		
		return coefficient * Math.pow(coefficient, exponent);
		
	}
	
	
	@Override
	
	public String toString(){
		
		if (this.exponent == 0){
			return String.format("%.2f", this.coefficient);

		}
		else if (this.exponent == 1){
			
			return String.format("%.2fx", this.coefficient);
		}
		else {
			
			return String.format("%.2fx^%d", this.coefficient, this.exponent);
		}
	}
	
	
	public static Term fromString(String str){
		
		String temp = new String(str);
		
		TermImp result = null;
		
		//Manages and handles the a*x^n component
		if (temp.contains("x^")){

			StringTokenizer strTok = new StringTokenizer(temp, "x^");
			
			List<String> list = new ArrayList<String>(2);
			
			while(strTok.hasMoreElements()){
				
				list.add((String) strTok.nextElement());
				
			}
			
			if (list.size() == 0){
				
				throw new IllegalArgumentException("Wrong Format");
				
			}
			
			else if (list.size() == 1){
				
				Integer expo = Integer.parseInt(list.get(0));
				
				result = new TermImp(1, expo);
				
			}
			
			else {
				
				Double coeff = Double.parseDouble(list.get(0));
				
				Integer expo = Integer.parseInt(list.get(1));
				
				result = new TermImp(coeff, expo);
				
			}	
			
		}
		
		else if (temp.contains("x")){
			
			StringTokenizer strTok = new StringTokenizer(temp, "x");
			
			List<String> list = new ArrayList<String>(2);
			
			while(strTok.hasMoreElements()){
				
				list.add((String) strTok.nextElement());
				
			}
			if (list.size() == 0){
				
				result = new TermImp(1.0, 1);
			}
			
			else {
				
				Double coeff = Double.parseDouble(list.get(0));
				result = new TermImp(coeff, 1);
			}
			
		}
		
		else {
			
			result = new TermImp(Double.parseDouble(temp), 0);
		}
		
		return result;
		
	}


}
