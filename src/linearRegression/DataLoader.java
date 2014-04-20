package linearRegression;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataLoader {
	List<Double> x;
	List<Double> y;

	public void loadData(String fileName) {
		x = new ArrayList<Double>();
		y = new ArrayList<Double>();
		try {
			Files.lines(Paths.get(fileName)).map(s -> s.split("\t"))
					.forEach(this::addDataEntry);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addDataEntry(String[] data) {
		x.add(Double.parseDouble(data[0]));
		y.add(Double.parseDouble(data[1]));
	}

	public List<Double> getX() {
		return x;
	}

	public List<Double> getY() {
		return y;
	}
}
