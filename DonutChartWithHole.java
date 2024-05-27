import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.Constants;



import javax.swing.*;
import java.awt.*;

public class DonutChartWithHole extends JFrame {

    public DonutChartWithHole() {
        setTitle("Donut Chart With Hole");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Java", 40);
        dataset.setValue("Python", 30);
        dataset.setValue("JavaScript", 15);
        dataset.setValue("C++", 10);
        dataset.setValue("PHP", 5);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Programming Languages",
                dataset,
                true, // whether legend is outside
                false, // whether tooltips are enabled
                false // whether URLs are enabled
        );

        // Access the PiePlot and set it to be transparent in the center
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setTransparent(true);
        piePlot.setBackgroundAlpha(0.0f); // Set the center to be fully transparent
        piePlot.setShadowVisible(false); // Optional: Remove shadow for cleaner look

        // Adjust the legend's position and padding
        RectangleInsets legendPadding = new RectangleInsets(10, 10, 10, 10);
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        chart.getLegend().setInsets(legendPadding);

        // Create Panel
        ChartPanel chartPanel = new ChartPanel(chart, ChartPanel.DEFAULT_SIZE, ChartPanel.DEFAULT_SIZE, true);
        chartPanel.setPreferredSize(new Dimension(500, 300)); // Adjust size as needed
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        DonutChartWithHole demo = new DonutChartWithHole();
        demo.setVisible(true);
    }
}
