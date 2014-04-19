import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<Double> x = Arrays.asList(0.0, 1.0, 2.0, 3.0, 4.0);
		List<Double> y = Arrays.asList(0.0, 1.0, 2.0, 3.0, 4.0);

		LinearRegressor lr = new LinearRegressor(x, y);

		List<Double> w = lr.linearRegression();

		w.stream().forEach(System.out::println);

	}

}
