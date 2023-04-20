package project2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * Solves a function, writes the x and y values to a CSV file. Salts inputed CSV
 * files of x and y values with randomized numbers. Smoothes inputed CSV files
 * of x and y values.
 * @author Ta'Quawn Watts
 */
public class CSVWriter {

	/**
	 * Solves the function x^2 + 2x + 1
	 * 
	 * @param x x is the int variable
	 * @return The output of the function of x^2 + 2x + 1
	 * 
	 */
	public int functionSolver(int x) {
		return (int) (Math.pow(x, 2) + (x * 2) + 1);
	}

	/**
	 * 
	 * Writes the outputs of x^2 + 2x + 1 using {@link #functionSolver(int)} and
	 * writes the inputs and outputs to a CSV file named "output.csv".
	 * 
	 * @param min min is the minimum value of x
	 * @param max max is the maximum value of x
	 * @see <a href="https://springhow.com/java-write-csv/">Writing a file to CSV
	 *      source</a>
	 * @see <a href=
	 *      "https://www.digitalocean.com/community/tutorials/java-open-file">Opening
	 *      a file source</a>
	 */

	public void writeToCSV(int min, int max) {
		int xValue = min;

		/*
		 * writeToCSV tries to use FileWriter to create and write to a CSV file named
		 * test.csv. This file will contain the inputs incrementing by 1 starting at the
		 * minimum x value input and ending at the maximum x value input by the user.
		 * Then it outputs of the x^2 + 2x + 1 function using functionSolver as a y
		 * value and prints the path of the file.
		 */
		try {
			File data = new File("data.csv");
			FileWriter fileWriter = new FileWriter(data);
			fileWriter.write("x,y");

			for (int i = min; i < (max + 1); i++) {
				int value = functionSolver(xValue);
				fileWriter.write("\n" + xValue + ", " + value);
				xValue++;
			}
			fileWriter.close();
			System.out.println("output.csv location: " + "\n" + data.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Error. Please try again.");
			e.printStackTrace();

		}
	}

	/**
	 * Creates a copy of the CSV file of x and y values provided named "salted.csv".
	 * Adds or subtracts a randomized number to each y value, "salting" the file.
	 * 
	 * @param filePath filePath is the path to the file that will be salted.
	 * @param min      min is the minimum value that the randomized number can be.
	 * @param max      max is the maximum value that the randomized number can be.
	 * @see <a href="https://springhow.com/java-write-csv/">Writing a file to CSV
	 *      source</a>
	 * @see <a href=
	 *      "https://www.digitalocean.com/community/tutorials/java-open-file">Opening
	 *      a file source</a>
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/64629900/how-to-replace-specific-string-in-a-text-file-by-java">
	 *      BufferedReader, BufferedWriter, and Files idea source</a>
	 * @see <a href=
	 *      "https://www.tabnine.com/code/java/methods/java.nio.file.Files/copy">Files
	 *      source</a>
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/6952363/replace-a-character-at-a-specific-index-in-a-string">
	 *      StringBuilder idea source</a>
	 * @see <a href=
	 *      "https://beginnersbook.com/2014/08/how-to-append-a-newline-to-stringbuilder/">Stringbuilder
	 *      append source</a>
	 * 
	 */
	public void saltCSV(String filePath, int min, int max) {
		/*
		 * After loading the inputed file and creating a copy of it named
		 * saltedData.csv, saltCSV loops through the saltedData.csv file and parses each
		 * y value as a double. saltCSV then chooses a random number in the interval
		 * specified and randomly decides whether to add or subtract that random number
		 * to the y value. After this, saltCSV replaces the original y value with the
		 * "salted" y value. After replacing all of the original y values with a salted
		 * one, it prints the path of saltedData.csv.
		 */

		try {
			File data = new File(filePath);
			File saltedData = new File("saltedData.csv");
			Files.copy(data.toPath(), saltedData.toPath(), StandardCopyOption.REPLACE_EXISTING);

			BufferedReader br = new BufferedReader(new FileReader(saltedData));
			StringBuilder sb = new StringBuilder();
			String line = "";
			sb.append(br.readLine()).append("\n");

			int negRand = 1;
			while ((line = br.readLine()) != null) {
				String[] t = line.split(", ");
				double yValue = Double.parseDouble(t[1].trim());

				int randNum = min + (int) (Math.random() * ((max - min) + 1));
				if (Math.random() <= 0.5) {
					negRand = -1;
				}

				String saltedY = String.valueOf(yValue + (randNum * negRand));
				line = line.replace(t[1], saltedY);
				sb.append(line).append("\n");
			}
			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter(saltedData));
			bw.write(sb.toString());
			bw.close();

			System.out.println("\nsaltedData.csv location: " + "\n" + saltedData.getAbsolutePath());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please try again.");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Please try again.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error. Please try again.");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Takes each element of a list and averages it with a specific range of points
	 * around it, also known as "smoothing".
	 * 
	 * @param data        data is the list of double numbers being smoothed.
	 * @param windowValue windowValue is the number of points being taken right and
	 *                    left of the index point.
	 * @return A list of smoothed numbers.
	 * 
	 */
	public ArrayList<Double> smoothData(ArrayList<Double> data, int windowValue) {
		ArrayList<Double> smoothedData = new ArrayList<>();

		/*
		 * First, the for loop goes through list of values. Using the range variable,
		 * smoothData adds the initial value to the sum variable and checks to see if
		 * there is an value to the left or right of it. If so, smoothData adds the
		 * value of the element to sum. To keep track of how many elements have been
		 * totaled, we add one to the count variable. Once the range has reached the
		 * windowValue inputed, smoothData uses count to average the added values. The
		 * average is then added to the smoothedData list. After this, it continues
		 * until it reaches the end of the list of values and returns the list of y
		 * values.
		 */
		for (int i = 0; i < data.size(); i++) {
			int count = 0;
			int range = 0;
			double point = 0;
			double sum = data.get(i);

			do {
				// Checking if there is a value left of the initial value
				if ((i - (range + 1)) >= 0) {
					sum += data.get(i - range - 1);
					count++;
				}
				// Checking if there is a value right of the initial value
				if ((i + (range + 1)) < data.size()) {
					sum += data.get(i + range + 1);
					count++;
				}

				range++;

			} while (range != windowValue);

			point = sum / (count + 1);
			smoothedData.add(point);
		}
		return smoothedData;
	}

	/**
	 * Creates a copy of the CSV file of x and y values provided named
	 * "smoothed.csv". Takes each y value and averages it with a specified range of
	 * y values around it, "smoothing" the data using
	 * {@link #smoothData(ArrayList, int)}.
	 * 
	 * 
	 * @param filePath    filePath is the path to the file that will be smoothed
	 * @param windowValue windowValue is the range of points being averaged
	 * @see <a href="https://springhow.com/java-write-csv/">Writing a file to CSV
	 *      source</a>
	 * @see <a href=
	 *      "https://www.digitalocean.com/community/tutorials/java-open-file">Opening
	 *      a file source</a>
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/64629900/how-to-replace-specific-string-in-a-text-file-by-java">
	 *      BufferedReader, BufferedWriter, and Files idea source</a>
	 * @see <a href=
	 *      "https://www.tabnine.com/code/java/methods/java.nio.file.Files/copy">Files
	 *      source</a>
	 * @see <a href=
	 *      "https://stackoverflow.com/questions/6952363/replace-a-character-at-a-specific-index-in-a-string">
	 *      StringBuilder idea source</a>
	 * @see <a href=
	 *      "https://beginnersbook.com/2014/08/how-to-append-a-newline-to-stringbuilder/">Stringbuilder
	 *      append source</a>
	 */
	public void smoothCSV(String filePath, int windowValue) {
		ArrayList<Double> dataPoints = new ArrayList<>();

		/*
		 * After loading the inputed file and creating a copy of it named
		 * smoothedData.csv, smoothCSV loops through the smoothedData.csv file and
		 * parses each y value as a double. It adds each y value to an list named
		 * dataPoints and smoothes the data using the smoothData method. After smoothing
		 * the points and saving it to the smoothedPoints list, smoothCSV loops through
		 * the smoothedData.csv again and replaces each original y value with the
		 * smoothed y values. smoothCSV then prints the path of smoothData.csv.
		 * 
		 */
		try {
			File data = new File(filePath);
			File smoothedData = new File("smoothedData.csv");
			Files.copy(data.toPath(), smoothedData.toPath(), StandardCopyOption.REPLACE_EXISTING);

			BufferedReader br = new BufferedReader(new FileReader(smoothedData));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while ((line = br.readLine()) != null) {
				String[] t = line.split(", ");
				double yPoint = Double.parseDouble(t[1].trim());
				dataPoints.add(yPoint);
			}

			br.close();

			ArrayList<Double> smoothedPoints = smoothData(dataPoints, windowValue);

			br = new BufferedReader(new FileReader(smoothedData));
			sb.append(br.readLine()).append("\n");

			int index = 0;

			while ((line = br.readLine()) != null) {
				String[] t = line.split(", ");
				String y = String.valueOf(smoothedPoints.get(index));
				line = line.replace(t[1], y);
				sb.append(line).append("\n");
				index++;
			}
			br.close();

			BufferedWriter bw = new BufferedWriter(new FileWriter(smoothedData));
			bw.write(sb.toString());
			bw.close();

			System.out.println("\nsmoothedData.csv location: " + "\n" + smoothedData.getAbsolutePath());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please try again.");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Please try again.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error. Please try again.");
			e.printStackTrace();
		}

	}

}