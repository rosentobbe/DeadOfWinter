import java.awt.List;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.opencsv.CSVReader;
public class CSVParser {

	private static final String SAMPLE_CSV_FILE_PATH = "C:\\Users\\Rosen\\eclipse-workspace\\CrossroadCards.csv";
	public List ParseCSV() {
		ArrayList<String[]> all_Cards = new ArrayList<String[]>();
		try 
		(
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			CSVReader csvReader = new CSVReader(reader);
		) {
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null) {
				all_Cards.add(nextRecord);
				System.out.println("Card Number: " + nextRecord[0]);
				System.out.println("Title: " + nextRecord[1]);
				System.out.println("Condition: " + nextRecord[2]);
				System.out.println("Setup: " + nextRecord[3]);
				System.out.println("Option1: " + nextRecord[4]);
				System.out.println("Option1 Short: " + nextRecord[5]);
				System.out.println("Option2: " + nextRecord[6]);
				System.out.println("Option2 Short: " + nextRecord[7]);
				System.out.println("Option3: " + nextRecord[8]);
				System.out.println("==============================");
			}
			System.out.println(all_Cards.get(1));
			return null;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}

