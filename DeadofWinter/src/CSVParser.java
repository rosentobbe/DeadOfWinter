import java.awt.List;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.opencsv.CSVReader;
public class CSVParser {
	private ArrayList<String[]> all_Cards = new ArrayList<String[]>();
	
	private static final String SAMPLE_CSV_FILE_PATH = "C:\\Users\\Rosen\\Documents\\GitHub\\DeadOfWinter\\CrossroadCards.csv";
	public ArrayList<String[]> ParseCSVFileWithCards() {
		try 
		(
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			CSVReader csvReader = new CSVReader(reader);
		) {
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null) {
				all_Cards.add(nextRecord);
//				System.out.println("New card");
//				System.out.println("Card Number: " + nextRecord[0]);
//				System.out.println("Title: " + nextRecord[1]);
//				System.out.println("Condition: " + nextRecord[2]);
//				System.out.println("Setup: " + nextRecord[3]);
//				System.out.println("Option1: " + nextRecord[4]);
//				System.out.println("Option1 Short: " + nextRecord[5]);
//				System.out.println("Option2: " + nextRecord[6]);
//				System.out.println("Option2 Short: " + nextRecord[7]);
//				System.out.println("Option3: " + nextRecord[8]);
//				System.out.println("==============================");
			}
//			System.out.println(all_Cards.get(1));
			return all_Cards;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public String getTitle(int index) {
		return all_Cards.get(index)[1];
	}
	public String getCondition(int index) {
		return all_Cards.get(index)[2];
	}
	public String getSetup(int index) {
		return all_Cards.get(index)[3];
	}
	public String getOptionOne(int index) {
		return all_Cards.get(index)[4];
	}
	public String getOptionOneShort(int index) {
		return all_Cards.get(index)[5];
	}
	public String getOptionTwo(int index) {
		return all_Cards.get(index)[6];
	}
	public String getOptionTwoShort(int index) {
		return all_Cards.get(index)[7];
	}
	public String getOptionThree(int index) {
		return all_Cards.get(index)[8];
	}
//	public String getOptionThreeShort(int index) {
//		return all_Cards.get(index)[9];
//	}
	public int getNumberOfCards() {
		return all_Cards.size();
	}
}

