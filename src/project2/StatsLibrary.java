package project2;

import java.lang.Math;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A library of various functions used primarily in statistics
 * @author Ta'Quawn Watts
 */

public class StatsLibrary {

	/**
	 * 
	 * @param inputNumbers inputNumbers is the list of double type numbers
	 * @return The mean of inputNumbers
	 */
	public double findMean(ArrayList<Double> inputNumbers) {
		double sum = 0;
		for (double singleElement : inputNumbers) { //
			sum = sum + singleElement;
		}
		double result = sum / inputNumbers.size();
		return result;
	}

	/**
	 * 
	 * @param inputNumbers inputNumbers is the list of double type numbers
	 * @return the median of inputNumbers
	 */

	public double findMedian(ArrayList<Double> inputNumbers) {
		Collections.sort(inputNumbers);
		double result;
		int middle;
		// If the size of list is even
		if (inputNumbers.size() % 2 == 0) {
			// Adds the two middle numbers
			result = inputNumbers.get((inputNumbers.size() / 2) - 1) + inputNumbers.get((inputNumbers.size() / 2));
			result = result / 2;
			return result;
		} else {
			middle = inputNumbers.size() / 2;
			return inputNumbers.get(middle);
		}
	}

	/**
	 * 
	 * @param inputNumbers inputNumbers is the list of double type numbers
	 * @return The mode of the inputNumbers if there is one.
	 */

	public Double findMode(ArrayList<Double> inputNumbers) {
		Collections.sort((inputNumbers));
		int count = 1;
		int maxCount = 1;
		// Mode is Double type so it may be null if need be
		Double mode = (double) inputNumbers.get(0);

		/*
		 * Checks for any duplicates. If a duplicate is found, count is added. To keep
		 * track of an element with the most duplicates, findMode checks if count is
		 * higher than our maxCount. If so, it sets that element as the new mode and
		 * sets a new maxCount. After, it reset count back to 1.
		 */

		for (int i = 1; i < inputNumbers.size(); i++) {
			if (inputNumbers.get(i).equals(inputNumbers.get(i - 1))) {
				count++;
			} else {
				if (count > maxCount) {
					maxCount = count;
					mode = (double) inputNumbers.get(i - 1);
				}
				count = 1;
			}
		}

		if (count > maxCount) {
			mode = (double) inputNumbers.get(inputNumbers.size() - 1);
		}

		// Returns null if there are multiple modes or if there is no modes
		if (count == maxCount) {
			mode = null;
		}

		return mode;
	}

	/**
	 * 
	 * @param inputNumbers inputNumbers is the list
	 * @return the standard deviation of the list Uses
	 *         {@link #findMean(ArrayList<Double>) findMean} to calculate the mean
	 * @see <a href="https://www.baeldung.com/java-copy-list-to-another">Copying a
	 *      list source</a>
	 */

	public double findStdDev(ArrayList<Double> inputNumbers) {
		double mean = findMean(inputNumbers);
		double sum = 0;
		double stdDev = 0;
		double result = 0;

		/*
		 * To avoid changing inputNumbers list, findStdDev creates the copy stdDevCopy
		 * to make changes to.
		 */
		ArrayList<Double> stdDevCopy = new ArrayList<>(inputNumbers);

		for (int i = 1; i < stdDevCopy.size(); i++) {
			double subtractMean = stdDevCopy.get(i) - mean;
			stdDevCopy.set(i, subtractMean);
		}

		for (int i = 1; i < stdDevCopy.size(); i++) { /* Squares each element and sets it as new element */
			double square = Math.pow(stdDevCopy.get(i), 2);
			stdDevCopy.set(i, square);
		}

		// adds each element up and saves it as the variable sum
		for (double singleElement : stdDevCopy) {
			sum = sum + singleElement;
		}

		stdDev = sum / (stdDevCopy.size() - 1);
		result = Math.sqrt(stdDev);
		return result;
	}

	/**
	 * 
	 * @param listA listA is the subset A containing integer elements
	 * @param listB listB is the subset B containing integer elements
	 * @return The union of subset A and subset B
	 */
	public ArrayList<Integer> findUnion(ArrayList<Integer> listA, ArrayList<Integer> listB) {
		ArrayList<Integer> unionAB = new ArrayList<>();
		unionAB.addAll(listA);
		unionAB.addAll(listB);
		Collections.sort(unionAB);

		/*
		 * After sorting unionAB, findUnion checks for any duplicates. Any duplicates
		 * are removed.
		 */

		for (int i = 1; i < unionAB.size(); i++) {
			if (unionAB.get(i) == unionAB.get(i - 1)) {
				unionAB.remove(i);
			}
		}

		return unionAB;
	}

	/**
	 * 
	 * @param listA listA is the subset A containing integer elements
	 * @param listB listB is the subset B containing integer elements
	 * @return The intersection of listA and listB
	 */
	public ArrayList<Integer> findIntersection(ArrayList<Integer> listA, ArrayList<Integer> listB) {
		ArrayList<Integer> intersectionAB = new ArrayList<>();
		Collections.sort(listA);
		Collections.sort(listB);

		/*
		 * After sorting listA and listB, findIntersection checks for any matches
		 * between the two. If there are any matches between listA and listB, the
		 * element is added to intersectionAB
		 */
		for (int i = 0; i < listA.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				if (listA.get(i) == listB.get(j)) {
					intersectionAB.add(listA.get(i));
					break;
				}
			}
		}
		return intersectionAB;
	}

	/**
	 * 
	 * @param inputList inputList is a list containing integer elements.
	 * @param inputSet  inputSet is a list containing integer elements.
	 * @return The complement of list.
	 */
	public ArrayList<Integer> findComplement(ArrayList<Integer> inputList, ArrayList<Integer> inputSet) {
		ArrayList<Integer> listComplement = new ArrayList<>(inputSet);
		Collections.sort(inputList);
		Collections.sort(inputSet);

		/*
		 * After sorting inputList and inputSet, findComplement checks for any matches
		 * between the two. If there are any matches, it removes the match from
		 * listComplement.
		 */
		for (int i = 0; i < inputList.size(); i++) {
			for (int j = 0; j < inputSet.size(); j++) {
				if (inputList.get(i) == inputSet.get(j)) {
					listComplement.remove(inputList.get(i));
					break;
				}
			}
		}
		return listComplement;
	}

	/**
	 * 
	 * @param inputNum inputNum is the integer type number being input.
	 * @return The factorial of inputNum.
	 * @see <a href="https://www.javatpoint.com/factorial-program-in-java">Factorial
	 *      program source</a>
	 * @see <a href=
	 *      "https://www.geeksforgeeks.org/biginteger-class-in-java/">BigInteger
	 *      source</a>
	 */
	public BigInteger factorial(int inputNum) {
		if (inputNum == 0) {
			return BigInteger.ONE;
		} else {
			BigInteger b = BigInteger.valueOf(inputNum);
			return b.multiply(factorial(inputNum - 1));
		}
	}

	/**
	 * 
	 * @param n n is the first integer type number being input.
	 * @param r r is the second integer type number being input.
	 * @return The combination of n and r. Uses {@link #factorial(int)} to calculate
	 *         the factorial
	 * @see <a href=
	 *      "https://www.geeksforgeeks.org/biginteger-class-in-java/">BigInteger
	 *      source</a>
	 */
	public BigInteger solveCombo(int n, int r) {
		if (r > n) {
			return BigInteger.ZERO;
		}

		BigInteger nFact = factorial(n);
		BigInteger denom = factorial(n - r).multiply(factorial(r));
		return nFact.divide(denom);
	}

	/**
	 * 
	 * @param n is the first integer type number being input
	 * @param r is the second integer type number being input
	 * @return the permutation of n and r Uses {@link #factorial(int)} to calculate
	 *         the factorial
	 * @see <a href=
	 *      "https://www.geeksforgeeks.org/biginteger-class-in-java/">BigInteger
	 *      source</a>
	 */
	public BigInteger solvePerm(int n, int r) {
		if (r > n) {
			return BigInteger.ZERO;
		}
		BigInteger nFact = factorial(n);
		BigInteger denom = factorial(n - r);
		return nFact.divide(denom);
	}

	/**
	 * 
	 * @param n n is the integer type number of trials
	 * @param y y is the integer type number of successes
	 * @param p p is the double type probability of a successful trial
	 * @return The Binomial Probability Distribution using n, y, p. Uses
	 *         {@link #solveCombo(int, int)} to calculate the factorial
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/6219692/very-small-numbers">BigDecimal
	 *      idea source</a>
	 * @see <a href=
	 *      "https://www.geeksforgeeks.org/bigdecimal-class-java/">BigDecimal
	 *      source</a>
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/3115413/how-to-convert-biginteger-to-bigdecimal">BigInteger
	 *      to BigDecimal source</a>
	 */
	public BigDecimal solveBinoDistrib(int n, int y, double p) {
		BigInteger combo = solveCombo(n, y);
		BigDecimal q = BigDecimal.valueOf(1.0).subtract(BigDecimal.valueOf(p));
		BigDecimal qExpo = BigDecimal.valueOf(n - y);
		BigDecimal pVar = BigDecimal.valueOf(p).pow(y);
		BigDecimal qVar = q.pow(qExpo.intValue());
		BigDecimal pqVar = pVar.multiply(qVar);
		BigDecimal result = new BigDecimal(combo).multiply(pqVar);
		return result;
	}

	/**
	 * 
	 * @param n n is the integer type number of trials.
	 * @param p p is the double type probability of a successful trial.
	 * @return The Geometric Probability Distribution using n and p.
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/6219692/very-small-numbers">BigDecimal
	 *      idea source</a>
	 * @see <a href=
	 *      "https://www.geeksforgeeks.org/bigdecimal-class-java/">BigDecimal
	 *      source</a>
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/3115413/how-to-convert-biginteger-to-bigdecimal">BigInteger
	 *      to BigDecimal source</a>
	 */

	public BigDecimal solveGeoDistrib(int n, double p) {
		BigDecimal q = BigDecimal.ONE.subtract(BigDecimal.valueOf(p));
		BigDecimal qExpo = new BigDecimal(n - 1);
		BigDecimal pVar = BigDecimal.valueOf(p);
		BigDecimal qVar = q.pow(qExpo.intValue());
		BigDecimal pqVar = pVar.multiply(qVar);

		return pqVar;
	}

	/**
	 * 
	 * @param N N is the integer representing N in the hypergeometric distribution
	 *          formula
	 * @param n n is the integer representing n in the hypergeometric distribution
	 *          formula
	 * @param r r is the integer representing r in the hypergeometric distribution
	 *          formula
	 * @param y y is the integer representing y in the hypergeometric distribution
	 *          formula
	 * @return the hypergeometric distribution probability using the formula
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/4591206/arithmeticexception-non-terminating-decimal-expansion-no-exact-representable">BigDecimal
	 *      rounding source</a>
	 */
	public BigDecimal solveHyperGeo(int N, int n, int r, int y) {
		BigInteger type1 = solveCombo(r, y);
		BigInteger type2 = solveCombo((N - r), (n - y));
		BigInteger top = type1.multiply(type2);
		BigInteger bottom = solveCombo(N, n);
		BigDecimal result = new BigDecimal(top).divide(new BigDecimal(bottom), 6, RoundingMode.HALF_UP);
		return result;
	}

	/**
	 * 
	 * @param x      x is the integer representing x in the poisson distribution
	 *               formula
	 * @param lambda lambda is the integer representing lambda in the poisson distribution
	 *               formula
	 * @return the Poisson distribution probability using the formula Uses
	 *         {@link #factorial(int)} to calculate the denominator of the formula.
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/4591206/arithmeticexception-non-terminating-decimal-expansion-no-exact-representable">BigDecimal
	 *      rounding source</a>
	 */

	public BigDecimal solvePossDistrib(int x, int lambda) {
		int negLambda = lambda * -1;
		BigInteger bottom = factorial(x);
		BigDecimal ePow = BigDecimal.valueOf(Math.pow(Math.E, negLambda));
		BigInteger lambdaPow = BigInteger.valueOf((long) Math.pow(lambda, x));
		BigDecimal top = new BigDecimal(lambdaPow).multiply(ePow);
		BigDecimal result = top.divide(new BigDecimal(bottom), 6, RoundingMode.HALF_UP);
		return result;
	}
	
	
	/**
	 * 
	 * @param min min is the minimum value needed for the Tchebysheff's Theorem 
	 * @param max max is the maximum value needed for the Tchebysheff's Theorem
	 * @param mean mean is the mean needed for the Tchebysheff's Theorem
	 * @param std std is the standard deviation needed for the Tchebysheff's Theorem
	 * @return the percentage of data between the minimum value and maximum value
	 */
	public String solveTchebTheor(int min, int max, int mean, int std) {
		int within = max - mean;
		int k = within / std;
		double result = 1 - (1 / (Math.pow(k, 2)));
		double percent = result * 100;
		return String.format("%.2f%%", percent);
		
	}

}
