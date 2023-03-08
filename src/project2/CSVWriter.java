package project2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Solves a function and writes its outputs to a CSV file
 * @author Ta'Quawn Watts
 *
 */
public class CSVWriter {
	/**
	 * 
	 * @param x is the double variable
	 * @return the output of the function of x^2 + 2x + 1
	 * 
	 */
	public int functionSolver(double x) {
			return (int) (Math.pow(x, 2) + (x * 2) + 1);
		}
	
	//
	/**
	 * Writes the outputs of functionSolver() to a CSV file 
	 * @throws IOException
	 * @see <a href="https://springhow.com/java-write-csv/">Writing a file to CSV source</a>
	 */
	public void writeToCSV(int num) throws IOException {
		int xValue = 0;
		File csv = new File("test.csv");
		FileWriter fileWriter = new FileWriter(csv);
		fileWriter.write("x,y");
		for(int i = 0; i < (num + 1); i++) {
			int value = functionSolver(xValue);
			fileWriter.write("\n" + xValue + "," + value);
			xValue++;
		}
		fileWriter.close();
		System.out.println("CSV file path: " + csv.getAbsolutePath());
		
	}
}