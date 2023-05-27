package com.codebind;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class StopsInTripsGraph {
    private JPanel MainPanel;

    public StopsInTripsGraph(DefaultListModel<Trip> defaultTripList) {
        JFrame statisticsFrame = new JFrame("Statistics Window");
        statisticsFrame.setContentPane(this.MainPanel);
        statisticsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statisticsFrame.pack();
        statisticsFrame.setSize(800,600);
        statisticsFrame.setVisible(true);

        // create a dataset to store the number of stops for each trip
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i=0;i<defaultTripList.size();i++) {
            dataset.addValue(defaultTripList.get(i).stops.size(), "Stops", defaultTripList.get(i).getName());
        }

        // create a line chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Number of Stops in Each Trip", // chart title
                "Trip Name", // x-axis label
                "Number of Stops", // y-axis label
                dataset, // data
                PlotOrientation.VERTICAL, // plot orientation
                true, // include legend
                true, // tooltips
                false // urls
        );

        // add the chart to a panel and display it
        ChartPanel chartPanel = new ChartPanel(chart);
        statisticsFrame.setContentPane(chartPanel);
    }
}
