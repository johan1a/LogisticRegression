import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinearRegressor {
	private double alpha = 0.05;
	private int q;
	private List<Double> x;
	private List<Double> y;

	public LinearRegressor(List<Double> x2, List<Double> y2) {
		double w0 = 0, w1 = 0;
		q = x2.size();
		this.x = x2;
		this.y = y2;

	}

	// for every a and b loop until converge
	// errors = 0
	// for i = 1 to data.length
	// fx = a * data[i] + b
	// errors += (fx - labelData[i]) * data[i]
	// end for
	// gradient = gradient - learningRate * 1/data.length * errors
	// end for

	public List<Double> linearRegression() {
		double w0 = 1, w1 = 1;

		int maxIter = 1000;

		for (int i = 0; i < maxIter; i++) {
			double tempW0 = w0;
			double tempW1 = w1;

			double g0 = gradient_dW0(tempW0, tempW1);
			double g1 = gradient_dW1(tempW0, tempW1);

			w0 = tempW0 + alpha / q * gradient_dW0(tempW0, tempW1);
			w1 = tempW1 + alpha / q * gradient_dW1(tempW0, tempW1);

			System.out.println(w0 + " " + w1 + " " + cost(w0, w1));
		}
		return Arrays.asList(w0, w1);
	}

	public double gradient_dW0(double w0, double w1) {
		double sum = 0;
		for (int i = 0; i < q; i++) {
			sum += y.get(i) - h(w0, w1, x.get(i));
		}
		return sum;
	}

	public double gradient_dW1(double w0, double w1) {
		double sum = 0;
		for (int i = 0; i < q; i++) {
			sum += x.get(i) * (y.get(i) - h(w0, w1, x.get(i)));
		}
		return sum;
	}

	public double cost(double w0, double w1) {
		double sum = 0;
		double term;
		for (int i = 0; i < q; i++) {
			term = y.get(i) - h(w0, w1, x.get(i));
			term = term * term;
			sum += term;
		}
		return sum;
	}

	public double h(double w0, double w1, Double x) {
		return w0 + w1 * x;
	}

	public double gradient(double w0, double w1) {

		double sum = 0;
		for (int i = 0; i < q; i++) {

			sum += (y.get(i) - h(w0, w1, x.get(i))) * x.get(i);
		}
		return sum;
	}
}
