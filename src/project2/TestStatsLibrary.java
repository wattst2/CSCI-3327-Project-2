package project2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tests the StatsLibrary class and its methods.
 * @author Ta'Quawn Watts
 * @see <a href=
 *      "https://howtodoinjava.com/java/collections/arraylist/add-multiple-elements-arraylist/">Arrays.asList
 *      source</a>
 * @see <a href=
 *      "https://stackoverflow.com/questions/6219692/very-small-numbers">BigDecimal
 *      idea source</a>
 * @see <a href=
 *      "https://www.geeksforgeeks.org/bigdecimal-class-java/">BigDecimal
 *      source</a>
 * @see <a href=
 *      "https://stackoverflow.com/questions/62438100/string-decimal-to-percentage-in-java">Decimal
 *      to percentage source</a>
 */
public class TestStatsLibrary {
	public static void main(String[] args) {
		StatsLibrary test = new StatsLibrary();

		ArrayList<Double> someNumbers = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));

		double mean = test.findMean(someNumbers);
		double median = test.findMedian(someNumbers);
		Double mode = test.findMode(someNumbers);
		double stdDev = test.findStdDev(someNumbers);

//		/*
//		 * Expected mean (1+2+3+4+5) = 15/5 = 3.0 Expected median = 3.0 Expected mode =
//		 * none Expected standard deviation = 1.322
//		 */
		System.out.println("Numbers: " + someNumbers + "\nThe mean of the numbers is " + mean + "."
				+ "\nThe median of the numbers is " + median + ".");

		if (mode == null) {
			System.out.println("There is no mode in the numbers.");
		} else {
			System.out.println("The mode of the number is " + mode + ".");
		}

		System.out.println("The standard deviation of the numbers is " + stdDev + ".");

//		/*
//		 * Expected union = [1, 2, 4, 5, 6, 7, 9] Expected intersection = [2] Expected
//		 * complement = [1, 3, 5, 7, 8, 9]
//		 */
		ArrayList<Integer> set = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		ArrayList<Integer> listA = new ArrayList<>(Arrays.asList(2, 4, 6));
		ArrayList<Integer> listB = new ArrayList<>(Arrays.asList(1, 2, 5, 7, 9));
		ArrayList<Integer> unionAB = test.findUnion(listA, listB);
		ArrayList<Integer> intersectionAB = test.findIntersection(listA, listB);
		ArrayList<Integer> complementA = test.findComplement(listA, set);

		System.out.println("\nSet S = " + set + "\nSubset A = " + listA + "\nSubset B = " + listB + "\nA U B = "
				+ unionAB + "\nA ⋂ B = " + intersectionAB + "\nA' = " + complementA);

//		/*
//		 * Expected combination = 210 Expected permutation = 5040
//		 */
		BigInteger combo = test.solveCombo(10, 4);
		BigInteger perm = test.solvePerm(10, 4);

		System.out.println(
				"\nThe combination of 10 and 4 is " + combo + "." + "\nThe permutation of 10 and 4 is " + perm + ".");

//		/*
//		 * Expected answer = 0.2262
//		 */
		BigDecimal bino = test.solveBinoDistrib(5, 0, 0.05);
		BigDecimal answer = BigDecimal.valueOf(1).subtract(bino);
		String formattedAnswer = String.format("%.2f%%", (BigDecimal.valueOf(100).multiply(answer)));

		System.out.println("\nSuppose that a lot of 5000 electrical fuses contains 5% defectives. "
				+ "\nIf a sample of 5 fuses is tested, the probability of observing at least one defective "
				+ "\n(calculated using Binomial Distribution) is " + answer + " or " + formattedAnswer);

		/*
		 * Expected answer = 0.0312 or 3.12%
		 */
		BigDecimal geo = test.solveGeoDistrib(5, 0.5);
		String formattedGeo = String.format("%.2f%%", (BigDecimal.valueOf(100).multiply(geo)));
		System.out.println("\nA fair coin is tossed. The probability of getting a tail at the 5th toss"
				+ "\n(calculated using Geometric Distribution) is " + geo + " or " + formattedGeo);

		/*
		 * Expected answer = 0.0135 or 1.35%
		 */
		BigDecimal hyperGeo = test.solveHyperGeo(20, 5, 6, 4);
		String formattedHyper = String.format("%.2f%%", (BigDecimal.valueOf(100).multiply(hyperGeo)));

		System.out.println("\nA deck of cards contains 20 cards: 6 red cards and 14 black cards. "
				+ "\n5 cards are drawn randomly without replacement. "
				+ "\nThe probability that exactly four red cards are drawn (calculated"
				+ "\nusing Hypergeometric Distribution) is " + hyperGeo + " or " + formattedHyper);

		BigDecimal poisson = test.solvePossDistrib(5, 6);
		String formattedPoisson = String.format("%.2f%%", (BigDecimal.valueOf(100).multiply(poisson)));

		System.out.println("\nA person receives on average 3 emails per hour. "
				+ "\nThe probability that he will receive 5 emails over a period two hours "
				+ "\n(calculated using Poisson Distribution) is " + poisson + " or " + formattedPoisson);

		String tchebTheor = test.solveTchebTheor(16, 24, 20, 2);
		System.out.println("\nUsing Chebyshev's theorem, the percentage of values that fall"
				+ "\nbetween 123 and 179 for a dataset with mean of 151 and standard" + "\ndeviation of 14 is: "
				+ tchebTheor);
	}
}
