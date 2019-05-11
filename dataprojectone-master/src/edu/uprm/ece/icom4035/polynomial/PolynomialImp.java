package edu.uprm.ece.icom4035.polynomial;

import java.util.Iterator;
import java.util.StringTokenizer;

import edu.uprm.ece.icom4035.list.List;




public class PolynomialImp extends TermListFactory implements Polynomial{
	private List<Term> terms; 	

   
	public PolynomialImp() { 
		this.terms = new TermListFactory().newListFactory().newInstance();

	}

	public PolynomialImp(String input) {
		this.terms = new TermListFactory().newListFactory().newInstance();

		fromString(input);
	}
    /*This is a basic sum of the target Polynomial to the direct polynomial
     */
	@Override
	public Polynomial add(Polynomial P2) {
		List<Term> temp =  new TermListFactory().newListFactory().newInstance();
		Polynomial result = new PolynomialImp();


		for(Term e: P2)
			temp.add(e);


		for(int i = 0, j = 0; i<temp.size() && j<this.terms.size(); ){
			if(temp.get(i).getExponent() == this.terms.get(j).getExponent()){
				((PolynomialImp) result).addTerm((Term)new TermImp(temp.get(i).getCoefficient()+this.terms.get(j).getCoefficient(), temp.get(i).getExponent()));
				if(!(i==temp.size()))
					i++;
				if(!(j==this.terms.size()))
					j++;
			}
			else if(temp.get(i).getExponent()>this.terms.get(j).getExponent()){
				((PolynomialImp) result).addTerm(temp.get(i));
				if(!(i==temp.size()))
					i++;
			}
			else{
				((PolynomialImp) result).addTerm(terms.get(j));
				if(!(j==this.terms.size()))
					j++;
			}
		}
		return FixUp(result); 
	}
	
	/**
	 * @param P2 polinomial to be substracted
	 * @return new polynomial after substraction
	 */
	@Override
	public Polynomial subtract(Polynomial P2) {
		return add(P2.multiply(-1));
	}
	
	 
	 /**
	 * @param c value to be multiply and return
	 * @return new polynomial after scalar product
	 */
	@Override
	public Polynomial multiply(double c) {
		PolynomialImp temp = new PolynomialImp();
		
		for(Term e: terms) {
			temp.addTerm(new TermImp(e.getCoefficient()*c,e.getExponent()));
		}

		return FixUp(temp);
	}
	
	/**
	 * @param P2 Polynomial to be multiply
	 * @return resulting polynomial after multiplication using the distributive rule
	 */
	@Override
	public Polynomial multiply(Polynomial P2) {
		
		PolynomialImp temp = new PolynomialImp();
		
		for (Term term : this.terms) {
			
			for (Term term2 : P2) {
				
				temp.addTerm(new TermImp(term.getCoefficient()*term2.getCoefficient(), term.getExponent()+term2.getExponent()));
			
			}
			
		}
		
		return FixUp(temp);
	}

    /**
	 * @return new polynomial as the indefinite integral of current polynomial
	 */
	@Override
	public Polynomial indefiniteIntegral() {
		PolynomialImp temp = new PolynomialImp();

		Term tempTerm;
		
		for(int i = 0; i<terms.size();i++){
			
			tempTerm = new TermImp(terms.get(i).getCoefficient()/(1+terms.get(i).getExponent()), terms.get(i).getExponent()+1);
			
			terms.set(i, tempTerm);
			
			if(terms.get(i).getCoefficient()==0) {
				terms.remove(i);
			}
		}

		for (Term e : terms) {temp.addTerm(e);}
		
		temp.addTerm(new TermImp(1, 0));
		
		return temp;
	}

    /**
	 * @param x value to be evaluated on each term
	 * @return double value of the evaluating result
	 */
	@Override
	public double evaluate(double x) {
		
		double result = 0;
		
		for (Term e :this.terms) {
			result += e.getCoefficient()*Math.pow(x, e.getExponent());
		}
		return result;
	}
    
    /**
	 * @return new Polynomial with the derivative of the current polynomial
	 */
	@Override
	public Polynomial derivative() {
		
		PolynomialImp temp = new PolynomialImp();

		Term tempTerm;


		for(int i = 0; i<terms.size();i++){
			
			tempTerm = new TermImp(terms.get(i).getCoefficient()*terms.get(i).getExponent(), terms.get(i).getExponent()-1);
			
			terms.set(i, tempTerm);
			
			if(terms.get(i).getCoefficient()==0) {
				terms.remove(i);
			}
		}

		for (Term e : terms) {temp.addTerm(e);}

		return temp;
	}

    /**
	 * @return the degree of the polynomial
	 */
	@Override
	public int degree() {
		
		int temp = Integer.MIN_VALUE;
		
		for(int i = 0; i < this.terms.size(); i++) {
			
			if(this.terms.get(i).getExponent()>temp) {
				temp = this.terms.get(i).getExponent();
			}
		}
		return temp;
	}
	
    /**
	 * @param a = lower bound of the integral
	 * @parm b = upper bound of the integral
	 * @return the result of evaluating the integral
	 */
	@Override
	public double definiteIntegral(double a, double b) {
		PolynomialImp temp = new PolynomialImp();
		for (Term e : terms) {
			temp.addTerm(e);
		}
		temp = (PolynomialImp) temp.indefiniteIntegral();
		return temp.evaluate(b)-temp.evaluate(a);
	}

    /**
	 * Convert a string polynomial to a Term based polynomial
	 * @param str polynomial string form
	 */
	private void fromString(String str) {
		
		StringTokenizer strTok = new StringTokenizer(str, "+");
		
		String nextStr = null;	
		
		Term nextTerm = null;
		
		this.terms.clear();
		
		while (strTok.hasMoreElements()){
			
			nextStr = (String) strTok.nextElement();
			
			nextTerm = TermImp.fromString(nextStr);
			
			this.addTerm(nextTerm);
		}
	}

    /**
	 *Add new terms to the current polynomial
	 * @param nextTerm
	 */
	private void addTerm(Term nextTerm) {
		terms.add(nextTerm);
	}


	public Iterator<Term> iterator() {
		return terms.iterator();
	}
    
    /**
	 * Override the Object equals method to allow the comparison of to polynomial in its string form
	 */
	@Override
	public boolean equals(Polynomial P2){
		
		if (this.toString().equals(P2.toString())) {
			return true;
		}
		else {
			return false;
		}
	}

    /**
	 * Convert a Polynomial to a String Form
	 */

	@Override
	public String toString(){
		String result = "";

		if(terms.size()==0){
			return "0.00";
		}

		for(int i = 0; i < terms.size(); i++){
			
			if(i<terms.size()-1) {
				result += terms.get(i).toString()+"+";
			}
			else{
				result += terms.get(i).toString();}
		}
		return result;
	}

    /**
	 * Helper method that handle some fixing functions such as sorting, removing float formated zeros and adding equal degree terms
	 * @param P2 polynomial to be fixed
	 * @return fixes polynomial
	 */
	private Polynomial FixUp(Polynomial P2) {
		PolynomialImp temp = new PolynomialImp();
		List<Term> temporary = newListFactory().newInstance();


		for (Term term : P2) {temporary.add(term);}


		Term temporaryTerm = null;

		for(int a = 0; a<temporary.size(); a++){

			for(int b = 0; b<temporary.size(); b++){

				if(temporary.get(a).getExponent()>temporary.get(b).getExponent()){

					temporaryTerm = temporary.get(a);

					temporary.set(a, temporary.get(b));

					temporary.set(b, temporaryTerm);
				}
			}
		}

		temporary = sumDegree(temporary);

		for (int i = 0; i < temporary.size(); i++) {
			if(!(temporary.get(i).getCoefficient()==0))
				temp.addTerm(new TermImp(temporary.get(i).getCoefficient(), temporary.get(i).getExponent()));
		}
		return temp;
	}
    
    /**
	 * Helper method to add all terms that had the same exponent degree
	 * @param terms arraylist of terms to be cleaned
	 * @return arraylist of terms
	 */
	private List<Term> sumDegree(List<Term> terms){
		Term temporaryTerm = null;

		for(int i = 0; i < terms.size()-1; i++){

			if(terms.get(i).getExponent() == terms.get(i+1).getExponent()){
				temporaryTerm = terms.get(i);
				terms.set(i, new TermImp(terms.get(i).getCoefficient()+terms.get(i+1).getCoefficient(), temporaryTerm.getExponent()));
				terms.remove(i+1);
				sumDegree(terms);
			}	

		}
		return terms;
	}

}

