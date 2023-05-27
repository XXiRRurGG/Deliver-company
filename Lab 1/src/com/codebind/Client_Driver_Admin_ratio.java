package com.codebind;

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;
import org.jfree.chart.axis.NumberAxis;


public class Client_Driver_Admin_ratio {
    private JPanel MainPanel;
    public Client_Driver_Admin_ratio(DefaultListModel<User> defaultListModel)
    {
        JFrame statisticsFrame = new JFrame("Statistics Window");
        statisticsFrame.setContentPane(this.MainPanel);
        statisticsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statisticsFrame.pack();
        statisticsFrame.setSize(800,600);
        statisticsFrame.setVisible(true);

        int admins = 0;
        int clients = 0;
        int drivers = 0;
        for(int i=0;i<defaultListModel.size();i++)
        {
            switch (defaultListModel.get(i).access)
            {
                case CLIENT:
                {
                    clients++;
                    break;
                }
                case ADMIN:
                {
                    admins++;
                    break;
                }
                case DRIVER:
                {
                    drivers++;
                    break;
                }
            }
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Clients: " + String.valueOf(clients), clients);
        dataset.setValue("Admins: "+ String.valueOf(admins), admins);
        dataset.setValue("Drivers: "+ String.valueOf(drivers), drivers);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Client-Admin-Driver Ratio",
                dataset,
                true,
                true,
                false
        );

        // Set color scheme
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Clients", java.awt.Color.BLUE);
        plot.setSectionPaint("Admins", java.awt.Color.RED);
        plot.setSectionPaint("Drivers", java.awt.Color.GREEN);

        ChartPanel chartPanel = new ChartPanel(chart);
        statisticsFrame.setContentPane(chartPanel);

    }
}
