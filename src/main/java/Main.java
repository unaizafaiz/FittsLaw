import circle.FittsLaw;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {

        //create frame and components on EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FittsLaw();
            }
        });
    }
    
}
