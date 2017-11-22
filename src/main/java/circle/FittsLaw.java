package circle;

/*
 * Copyright (C) 2017 Unaiza Faiz
 *
 * Fitts Law Apparatus:
 * Captures movement time data for different targetSize and distances
 * Data is then analysed using R
 *
 */

import javax.swing.JFrame;


public class FittsLaw {


    public FittsLaw() {
        JFrame frame = new JFrame();
        frame.setTitle("Fitts Law");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        initComponents(frame);

        frame.pack();
        frame.setVisible(true);
    }


    private void initComponents(JFrame frame) {
            DrawCircles shape = new DrawCircles(100,25);
            frame.add(shape);
    }
}

