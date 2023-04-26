package project2;

import javax.swing.JFrame;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.ChartPanel;

/**
 * A program that implements Apache Commons Math Library version 3.61 and
 * JFreeCharts version 1.0.19 to graph a function, salt the graph with a range
 * of randomized numbers, and smooth the graph using a moving average.
 * 
 * The program produces three graphs, the original's function graph, the salted
 * graph, and the smoothed graph.
 * 
 * @author Ta'Quawn Watts
 * @see <a href=
 *      "https://www.edureka.co/community/4028/how-to-import-a-jar-file-in-eclipse">Importing
 *      jars into Eclipse source</a>
 */

public class JFreeChartsGrapher {

	/**
	 * Solves the function x^2 + 2x + 1
	 * 
	 * @param x x is the int variable
	 * @return the output of x^2 + 2x + 1
	 */
	public static double functionSolver(int x) {
		return (Math.pow(x, 2) + (x * 2) + 1);
	}

	/**
	 * Uses JFreeChart's XYSeries class to store the x and y values of
	 * {@link #functionSolver(int)} in a dataset.
	 * 
	 * @param xMin xMin is the minimum value for x
	 * @param xMax xMax is the maximum value for x
	 * @return the XYSeries dataset of the function x^2 + 2x + 1
	 * 
	 * @see <a href="https://www.geeksforgeeks.org/arrays-in-java/#">Arrays
	 *      source</a>
	 * @see <a href=
	 *      "https://www.tutorialspoint.com/jfreechart/index.htm">JFreeCharts
	 *      tutorial source</a>
	 * @see <a href=
	 *      "https://www.jfree.org/jfreechart/javadoc/org/jfree/data/xy/XYSeries.html/">XYSeries
	 *      documentation</a>
	 */
	public XYSeries functionXY(int xMin, int xMax) {
		// Creates two arrays based on the amount of x points
		int size = xMax - xMin;
		double[] xValues = new double[size];
		double[] yValues = new double[size];

		// Stores each x and y value in the array
		for (int i = 0; i < size; i++) {
			double x = xMin + i;
			double y = functionSolver(xMin + i);
			xValues[i] = x;
			yValues[i] = y;
		}

		// Creates dataset using XYSeries to hold x and y values
		XYSeries functionXY = new XYSeries("Function Datapoints");

		for (int i = 0; i < size; i++) {
			functionXY.add(xValues[i], yValues[i]);
		}

		return functionXY;
	}

	/**
	 * Adds or subtracts a randomized number from a specified range to each y value
	 * of dataset using JFreeChart's XYSeries class, "salting" the data.
	 * 
	 * @param functionXY functionXY is the XYSeries containing the y values that is
	 *                   getting salted
	 * @param saltMin    saltMin is the minimum value that the randomized number can
	 *                   be
	 * @param saltMax    saltMax is the minimum value that the randomized number can
	 *                   be
	 * @return the XYSeries dataset of salted values
	 * 
	 * @see <a href="https://www.geeksforgeeks.org/arrays-in-java/#">Arrays
	 *      source</a>
	 * @see <a href=
	 *      "https://www.tutorialspoint.com/jfreechart/index.htm">JFreeCharts
	 *      tutorial source</a>
	 * @see <a href=
	 *      "https://www.jfree.org/jfreechart/javadoc/org/jfree/data/xy/XYSeries.html/">XYSeries
	 *      documentation</a>
	 * @see <a href=
	 *      "https://www.baeldung.com/java-generating-random-numbers">RandomDataGenerator
	 *      source</a>
	 */
	public XYSeries saltXY(XYSeries functionXY, int saltMin, int saltMax) {

		// Creates an array to hold the salted y values
		int size = functionXY.getItemCount();
		double[] saltValues = new double[size];

		// Adds the y values from the input functionSeries to the array
		for (int i = 0; i < size; i++) {
			saltValues[i] = (double) functionXY.getY(i);
		}

		// Creates dataset using XYSeries to hold x and salted y values
		XYSeries saltedSeries = new XYSeries("Salted Datapoints");

		/*
		 * Creates a randomized number within the saltMin and saltMax range. Adds or
		 * subtracts that number from the y value and replaces it, salting the data
		 */
		int negRand = 1;

		for (int i = 0; i < size; i++) {
			if (Math.random() <= 0.5) {
				negRand = -1;
			}

			RandomDataGenerator rand = new RandomDataGenerator();
			int randNum = rand.nextInt(saltMin, saltMax) * negRand;
			saltValues[i] = saltValues[i] + randNum;
			saltedSeries.add(functionXY.getX(i), saltValues[i]);
		}

		return saltedSeries;
	}

	/**
	 * Takes each y value of the input dataset using JFreeChart's XYSeries using and
	 * averages it with a specified range of y values around it using Apache Commons
	 * Library, "smoothing" the data.
	 * 
	 * @param saltedXY    saltedXY is the XYSeries containing the y values that are
	 *                    getting smoothed
	 * @param windowValue windowValue is the range of points being averaged
	 * @return the XYSeries dataset of smoothed values
	 * 
	 * @see <a href="https://www.geeksforgeeks.org/arrays-in-java/#">Arrays
	 *      source</a>
	 * @see <a href=
	 *      "https://www.tutorialspoint.com/jfreechart/index.htm">JFreeCharts
	 *      tutorial source</a>
	 * @see <a href=
	 *      "https://www.jfree.org/jfreechart/javadoc/org/jfree/data/xy/XYSeries.html/">XYSeries
	 *      documentation</a>
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/3793400/is-there-a-function-in-java-to-get-moving-average">DescriptiveStatistics
	 *      idea source</a>
	 * 
	 * @see <a href=
	 *      "https://commons.apache.org/proper/commons-math/userguide/stat.html">DescriptiveStatistics
	 *      examples source</a>
	 * @see <a href=
	 *      "https://medium.com/root-node/moving-average-from-data-stream-774aefb72a2c">Smoothing
	 *      inspiration source</a>
	 * 
	 */
	public XYSeries smoothXY(XYSeries saltedXY, int windowValue) {

		// Creates an array to hold our y values
		int size = saltedXY.getItemCount();
		double[] yValues = new double[size];

		// Adds y values from input saltedXY
		for (int i = 0; i < size; i++) {
			yValues[i] = (double) saltedXY.getY(i);
		}

		// Create a dataset for your smoothed data points
		XYSeries smoothedXY = new XYSeries("Smoothed Datapoints");

		/*
		 * Loops through the dataset and creates a new DescriptiveStatistics object to
		 * hold data points. We add values within the window size using Math.max and
		 * Math.min to the object. After all the values within the window size is added,
		 * we calculate the mean and add it to our smoothed dataset.
		 */
		for (int i = 0; i < size; i++) {
			DescriptiveStatistics stats = new DescriptiveStatistics();
			int start = Math.max(0, i - windowValue / 2);
			int end = Math.min(size - 1, i + windowValue / 2);
			for (int j = start; j <= end; j++) {
				stats.addValue(yValues[j]);
			}

			double smoothedValue = stats.getMean();
			smoothedXY.add(saltedXY.getX(i), smoothedValue);
		}

		return smoothedXY;
	}

	/**
	 * Plots and graph the original function datapoints, salted datapoints, and
	 * smoothed datapoints using {@link #functionXY(int, int)},
	 * {@link #saltXY(XYSeries, int, int)} and {@link #smoothXY(XYSeries, int)}.
	 * 
	 * @param xMin        xMin is the minimum x value
	 * @param xMax        xMax is the maximum x value
	 * @param saltMin     saltMin is the minimum randomized number can be to salt
	 * @param saltMax     saltMax is the maximum randomized number can be to salt
	 * @param windowValue is the range of datapoints being average
	 * 
	 * @see <a href=
	 *      "https://www.tutorialspoint.com/jfreechart/jfreechart_xy_chart.htm">JFreeCharts
	 *      XY Chart source</a>
	 * 
	 * @see <a href=
	 *      "https://www.jfree.org/jcommon/api/org/jfree/ui/RefineryUtilities.html">JFreeCharts
	 *      RefineryUtilities documentation</a>
	 * @see <a href=
	 *      "https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html">JFrame
	 *      documentation</a>
	 * 
	 */
	public void displayGraphs(int xMin, int xMax, int saltMin, int saltMax, int windowValue) {
		XYSeries functionXY = functionXY(xMin, xMax);
		XYSeries saltedXY = saltXY(functionXY, saltMin, saltMax);
		XYSeries smoothedXY = smoothXY(saltedXY, windowValue);

		// Graphing the original function
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(functionXY);

		JFreeChart chart = ChartFactory.createXYLineChart("Graph of x^2 + 2x + 1", "x", "y", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(chart);
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		plot.setRenderer(renderer);

		JFrame frame = new JFrame("Function Graph");
		frame.setContentPane(chartPanel);
		frame.pack();
		RefineryUtilities.positionFrameRandomly(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Graphing the function after salting
		XYSeriesCollection saltDataset = new XYSeriesCollection();
		saltDataset.addSeries(saltedXY);

		JFreeChart saltChart = ChartFactory.createXYLineChart("After Salting", "x", "y", saltDataset,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel saltChartPanel = new ChartPanel(saltChart);
		XYPlot saltPlot = saltChart.getXYPlot();
		XYLineAndShapeRenderer saltRenderer = new XYLineAndShapeRenderer();
		saltPlot.setRenderer(saltRenderer);

		JFrame saltFrame = new JFrame("Salted Graph");
		saltFrame.setContentPane(saltChartPanel);
		saltFrame.pack();
		RefineryUtilities.positionFrameRandomly(saltFrame);
		saltFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		saltFrame.setVisible(true);

		// Graphing the salted function after smoothing
		XYSeriesCollection smoothDataset = new XYSeriesCollection();
		smoothDataset.addSeries(smoothedXY);

		JFreeChart smoothChart = ChartFactory.createXYLineChart("After Smoothing", "x", "y", smoothDataset,
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel smoothChartPanel = new ChartPanel(smoothChart);
		XYPlot smoothPlot = smoothChart.getXYPlot();
		XYLineAndShapeRenderer smoothRenderer = new XYLineAndShapeRenderer();
		smoothPlot.setRenderer(smoothRenderer);

		JFrame smoothFrame = new JFrame("Smoothed Graph");
		smoothFrame.setContentPane(smoothChartPanel);
		smoothFrame.pack();
		RefineryUtilities.positionFrameRandomly(smoothFrame);
		smoothFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		smoothFrame.setVisible(true);
	}

}