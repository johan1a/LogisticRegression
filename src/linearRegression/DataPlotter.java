package linearRegression;

import java.util.List;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

public class DataPlotter {

	public static void plotData(List<Double> x, List<Double> y) {

		double[] xArray = new double[x.size()];
		double[] yArray = new double[y.size()];

		for (int i = 0; i < x.size(); i++) {
			xArray[i] = x.get(i);
			yArray[i] = y.get(i);
		}

		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();

		// add a line plot to the PlotPanel
		plot.addLinePlot("my plot", xArray, yArray);

		// put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setContentPane(plot);
		frame.setVisible(true);
	}
}
