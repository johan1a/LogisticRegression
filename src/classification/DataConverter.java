package classification;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class DataConverter {
	private static final String dataPath = "data/";
	private String dataString = "";
	private int currentIndex = 0;

	public static void main(String[] args) {
		DataConverter converter = new DataConverter();
		converter.convertData();
	}

	public void convertData() {
		String label1 = "english", label2 = "french";

		List<String> labels = Arrays.asList("english", "french");

		labels.forEach(this::collectData);
		writeData(dataPath + "classificationData");
	}

	private void writeData(String string) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(string));
			writer.write(dataString);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Using the filename as label */
	private void collectData(String file) {
		final String label = file;
		try {
			Files.lines(Paths.get(dataPath + file)).map(s -> s.split("\t"))
					.forEach(s -> addDataEntry(s, label));
		} catch (IOException e) {
			e.printStackTrace();
		}
		currentIndex++;
	}

	/* Currently assumes that there are only two variables */
	private void addDataEntry(String[] entry, String label) {
		dataString += currentIndex + " 1:" + entry[0] + " 2:" + entry[1] + "\n";
	}
}
