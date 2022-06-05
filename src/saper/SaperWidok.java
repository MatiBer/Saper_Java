package saper;

import javax.swing.*;
import java.awt.*;

public class SaperWidok extends JFrame {
        JButton przycisk;
        public JLabel czas;


    public SaperWidok() {
        setTitle("Saper");


        String[] poziomy = {"łatwy","średni","trudny"};
        JComboBox poziom = new JComboBox(poziomy);

        JPanel opcje = new JPanel();
        opcje.add(poziom);
        add(opcje);

        JLabel komunikat = new JLabel(" ");
        komunikat.setFont(new Font("Arial", Font.BOLD, 12));
        komunikat.setForeground(Color.BLACK);
        JPanel panel = new JPanel();
        panel.add(new Panel());
        JLabel komunikat2 = new JLabel("0");
        komunikat2.setBackground(Color.BLACK);
        komunikat2.setOpaque(true);
        panel.add(komunikat2);
        komunikat2.setFont(new Font("Verdana", Font.BOLD, 32));
        komunikat2.setForeground(Color.RED);

        Image buzka = new ImageIcon("src/ikony/buzka.png").getImage();
        przycisk = new JButton(new ImageIcon(buzka));
        przycisk.setPreferredSize(new Dimension(36,36));
        panel.add(przycisk);
        przycisk.addActionListener( //Operacja po kliknieciu w buzke
                evt -> {

                }

        );

        this.czas = new JLabel("000");

        this.czas.setFont(new Font("Verdana", Font.BOLD, 32));
        this.czas.setForeground(Color.RED);
        this.czas.setBackground(Color.BLACK);
        this.czas.setOpaque(true);

        panel.add(this.czas);

        JPanel plansza = new JPanel();
        add(komunikat, BorderLayout.SOUTH); // wyświetla komunikat w dolnym miejscu
        add(panel, BorderLayout.NORTH); // wyświetla pane w górnym miejscu
        plansza.add(new SaperModel(komunikat, komunikat2, new Zegar(this.czas))); // dodaje plansze do panelu
        add(plansza, BorderLayout.CENTER); // wyświetla planszę
        setBackground(Color.BLACK);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paintComponents(getGraphics());
    }




}
