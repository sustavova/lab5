package pars;

import java.math.BigInteger;
import java.math.BigDecimal;


public class BigFraction {
    // member variables
	
    private BigInteger numerator, denominator;  // stores the fraction data
   
    public BigFraction() {
        numerator = BigInteger.ZERO;
        denominator = BigInteger.ZERO;
    }

   
    public BigFraction(String str) {
        if (str.indexOf("/") != -1){
            String num = str.substring(0, str.indexOf("/"));
            String denom = str.substring(str.indexOf("/")+1, str.length());
   
    }

   
    public BigInteger getNumerator() {
        return numerator;
    }

   
    public void setNumerator(BigInteger num) {
        numerator = num;
    }

    
    public BigInteger getDenominator() {
        return denominator;
    }

   
    public void setDenominator(BigInteger den) {
        denominator = den;
    }

    
    public BigFraction add(BigFraction b) {
        // check preconditions
        if ((denominator.equals(BigInteger.ZERO)) || (b.denominator.equals(BigInteger.ZERO)))
            throw new IllegalArgumentException("invalid denominator");
        // find lowest common denominator
        BigInteger common = lcd(denominator, b.denominator);
        // convert fractions to lcd
        BigFraction commonA = new BigFraction();
        BigFraction commonB = new BigFraction();
        commonA = convert(common);
        commonB = b.convert(common);
        // create new fraction to return as sum
        BigFraction sum = new BigFraction();
        // calculate sum
        sum.numerator = commonA.numerator.add(commonB.numerator);
        sum.denominator = common;
        // reduce the resulting fraction
        sum = sum.reduce();
        return sum;
    }

    /**
     * Subtract fraction b from a, where a is the "this" object, and b is passed as the input parameter.
     * Both fractions a and b must contain valid values.
     * @param b the fraction to subtract from "this"
     * @return A Fraction representing the differenct of the two fractions a and b.
     */
    public BigFraction subtract(BigFraction b) {
        // check preconditions
        if ((denominator.equals(BigInteger.ZERO)) || (b.denominator.equals(BigInteger.ZERO)))
            throw new IllegalArgumentException("invalid denominator");
        // find lowest common denominator
        BigInteger common = lcd(denominator, b.denominator);
        // convert fractions to lcd
        BigFraction commonA = new BigFraction();
        BigFraction commonB = new BigFraction();
        commonA = convert(common);
        commonB = b.convert(common);
        BigFraction diff = new BigFraction();
        diff.numerator = commonA.numerator.subtract(commonB.numerator);
        diff.denominator = common;
        diff = diff.reduce();
        return diff;
    }

    
    public BigFraction multiply(BigFraction b) {
        if ((denominator.equals(BigInteger.ZERO)) || (b.denominator.equals(BigInteger.ZERO)))
            throw new IllegalArgumentException("invalid denominator");
        BigFraction product = new BigFraction();
        product.numerator = numerator.multiply(b.numerator);
        product.denominator = denominator.multiply(b.denominator);
        product = product.reduce();
        return product;
    }

    
    public BigFraction divide(BigFraction b) {
        if ((denominator.equals(BigInteger.ZERO)) || (b.denominator.equals(BigInteger.ZERO)) || (b.numerator.equals(BigInteger.ZERO)))
            throw new IllegalArgumentException("invalid denominator");
        BigFraction result = new BigFraction();
        result.numerator = numerator.multiply(b.denominator);
        result.denominator = denominator.multiply(b.numerator);
        result = result.reduce();
        return result;
    }

    
    public String toString() {
        String buffer;
        if (denominator.equals(BigInteger.ONE))
           buffer = numerator.toString();
        else
            buffer = numerator.toString() + "/" + denominator.toString();
        return buffer;
    }

    public int compareTo(BigFraction b) {
        BigFraction tmp = this.subtract(b);
        return tmp.numerator.compareTo(BigInteger.ZERO);
    }

    
    private BigInteger lcd(BigInteger denom1, BigInteger denom2) {
        BigInteger factor = denom1;
        while (!(denom1.mod(denom2)).equals(BigInteger.ZERO))
            denom1 = denom1.add(factor);
        return denom1;
    }

   
    private BigInteger gcd(BigInteger denom1, BigInteger denom2) {
        BigInteger factor = denom2;
        while (!denom2.equals(BigInteger.ZERO)) {
            factor = denom2;
            denom2 = denom1.mod(denom2);
            denom1 = factor;
        }
        return denom1;
    }

   
    private BigFraction convert(BigInteger common) {
        BigFraction result = new BigFraction();
        BigInteger factor = common.divide(denominator);
        result.numerator = numerator.multiply(factor);
        result.denominator = common;
        return result;
    }

   
    private BigFraction reduce() {
        BigFraction result = new BigFraction();
        BigInteger common = BigInteger.ZERO;
        BigInteger num = numerator.abs();
        BigInteger den = denominator.abs();
        if (num.compareTo(den) == 1)
            common = gcd(num, den);
        else if (num.compareTo(den) == -1)
            common = gcd(den, num);
        else  
            common = num;
        result.numerator = numerator.divide(common);
        result.denominator = denominator.divide(common);
        return result;
    }
}