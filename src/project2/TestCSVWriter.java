package project2;
import java.io.IOException;
import java.util.Scanner;
public class TestCSVWriter {
	public static void main(String[] args) throws IOException {
		CSVWriter test = new CSVWriter();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the max X value: ");
		int runs = scan.nextInt();
		scan.close();
		test.writeToCSV(runs);
	}
}
