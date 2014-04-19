package linearRegression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		DataLoader loader = new DataLoader();
		loader.loadData("french");

		List<Double> x = loader.getX();
		List<Double> y = loader.getY();

		x = scaleData(x);
		y = scaleData(y);

		x = Arrays.asList(0.0, 1.0, 2.0, 3.0, 4.0);
		y = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);

		LinearRegressor lr = new LinearRegressor(x, y);
		List<Double> w = lr.linearRegression();

		w.stream().forEach(a -> System.out.print(a + " "));
		System.out.println();
		
		DataPlotter.plotData(x, y);
	}

	/* Scales the data to fit in the range [0,1] */
	public static List<Double> scaleData(List<Double> list) {
		double max = list.stream().max(Double::compare).get();
		return list.stream().map(x -> x / max).collect(Collectors.toList());
	}
}
