package linearRegression;

import java.util.Arrays;
import java.util.List;

public class LinearRegressor {
	private double alpha = 0.05;
	private int q;
	private List<Double> x;
	private List<Double> y;
	private double tolerance = Math.pow(10, -18);

	public LinearRegressor(List<Double> x2, List<Double> y2) {
		q = x2.size();
		this.x = x2;
		this.y = y2;

	}

	/* Linear regression in two-dimensional space. */
	public List<Double> linearRegression() {
		double w0 = 0, w1 = 0;

		int maxIter = 1000000;
		int i = 0;
		while (cost(w0, w1) > tolerance && i < maxIter) {
			double tempW0 = w0;
			double tempW1 = w1;

			w0 = tempW0 + alpha / q * gradient_dW0(tempW0, tempW1);
			w1 = tempW1 + alpha / q * gradient_dW1(tempW0, tempW1);
			i++;
		}
		System.out.println("Iterations: " + i);
		return Arrays.asList(w0, w1);
	}

	private double gradient_dW0(double w0, double w1) {
		double sum = 0;
		for (int i = 0; i < q; i++) {
			sum += y.get(i) - h(w0, w1, x.get(i));
		}
		return sum;
	}

	private double gradient_dW1(double w0, double w1) {
		double sum = 0;
		for (int i = 0; i < q; i++) {
			sum += x.get(i) * (y.get(i) - h(w0, w1, x.get(i)));
		}
		return sum;
	}

	private double cost(double w0, double w1) {
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

}
