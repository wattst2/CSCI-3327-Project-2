package project2;

/**
 * Tests CSVWriter and its methods.
 * @author Ta'Quawn Watts
 */
public class TestCSVWriter {
	public static void main(String[] args) {
		CSVWriter test = new CSVWriter();
		test.writeToCSV(-25, 25);
		test.saltCSV("/Users/tqwatts/eclipse-workspace/Prob Stat Project2/data.csv", 5, 100);
		test.smoothCSV("/Users/tqwatts/eclipse-workspace/Prob Stat Project2/saltedData.csv", 5);
	}
}