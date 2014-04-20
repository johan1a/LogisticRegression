package util;

import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import classification.DataPoint;

public class DataPlotter {
	Plot2DPanel plot = new Plot2DPanel();

	public DataPlotter() {
		// put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setContentPane(plot);
		frame.setVisible(true);
	}

	public void plotData(List<Double> x, List<Double> y) {

		double[] xArray = new double[x.size()];
		double[] yArray = new double[y.size()];

		for (int i = 0; i < x.size(); i++) {
			xArray[i] = x.get(i);
			yArray[i] = y.get(i);
		}

		plot.addScatterPlot("my plot", xArray, yArray);
	}

	/* Plots the different classes with different colors. */
	public void plotData(List<DataPoint> data) {
		double[] x1 = new double[data.size()];
		double[] y1 = new double[data.size()];

		double[] x2 = new double[data.size()];
		double[] y2 = new double[data.size()];

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getClassification() == 0) {
				x1[i] = data.get(i).getVariable(1);
				y1[i] = data.get(i).getVariable(2);
			} else {
				x2[i] = data.get(i).getVariable(1);
				y2[i] = data.get(i).getVariable(2);
			}
		}
		plot.addScatterPlot("my plot", x1, y1);
		plot.addScatterPlot("my plot", x2, y2);
	}

	public void plotLine(HashMap<Integer, Double> weights) {
		double[] p1 = { 0, 0 };
		double[] p2 = { 1,
				(weights.get(0) + weights.get(1)) / (-weights.get(2)) };
		double[][] array = { p1, p2 };
		plot.addLinePlot("line plot", array);
	}

	public void plotLine(List<Double> weights) {
		double[] p1 = { 0, 0 };
		double[] p2 = { 1, weights.get(0) + weights.get(1) };
		
		double[][] array = { p1, p2 };
		plot.addLinePlot("line plot", array);
	}
}
