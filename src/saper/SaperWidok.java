package saper;

import javax.swing.*;
import java.awt.*;

public class SaperWidok extends JFrame {
    public SaperWidok() {
        setTitle("Saper");
        JLabel komunikat = new JLabel("");
//        add(komunikat, BorderLayout.NORTH); // wyświetla komunikat w górnym miejscu, ale psuje plansze
        add(new SaperModel(komunikat));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
