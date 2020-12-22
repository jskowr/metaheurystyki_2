package zad_2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class Chart extends ApplicationFrame {
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;
	public Chart(final String title, int[] x_data, int[] y_data_worst, int[] y_data_avg, int[] y_data_best, int[] y_data_current) {
	    super(title);
	    final XYSeries series1 = new XYSeries("Worst");
	    final XYSeries series2 = new XYSeries("Average");
	    final XYSeries series3 = new XYSeries("Best");
	    final XYSeries series4 = new XYSeries("Current");
	    for(int i=0; i<x_data.length; i++) {
	    	series1.add(x_data[i], y_data_worst[i]);
	    	series2.add(x_data[i], y_data_avg[i]);
	    	series3.add(x_data[i], y_data_best[i]);
	    	series4.add(x_data[i], y_data_current[i]);
	    }
	    final XYSeriesCollection data = new XYSeriesCollection();
	    data.addSeries(series1);
	    data.addSeries(series2);
	    data.addSeries(series3);
	    data.addSeries(series4);
	    this.chart = ChartFactory.createXYLineChart(
	        title,
	        "Pokolenie", 
	        "Wartoœæ", 
	        data,
	        PlotOrientation.VERTICAL,
	        true,
	        true,
	        false
	    );
	    XYPlot plot = this.chart.getXYPlot();
	    plot.setBackgroundPaint(Color.WHITE);
	    
	    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
	    renderer.setSeriesShapesVisible(0, false);
	    renderer.setSeriesStroke(0, new BasicStroke(0.75f));
	    renderer.setSeriesStroke(1, new BasicStroke(0.75f));
	    renderer.setSeriesStroke(2, new BasicStroke(0.75f));
	    renderer.setSeriesStroke(3, new BasicStroke(0.75f));
	    
	    //renderer.setBaseStroke(new BasicStroke(1));
	    plot.setRenderer(renderer);
	}
	
	public void saveChart(String chartName) {
		try {
		    ChartUtilities.saveChartAsPNG(new File("charts/"+chartName+".png"), this.chart, 800, 500);
		} catch (IOException ex) {
		}
	}
	
	public void showChart() {
		
	}
}
