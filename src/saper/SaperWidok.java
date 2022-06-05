package saper;

import javax.swing.*;
import java.awt.*;

public class SaperWidok extends JFrame {
//    private JLabel czas;
        int time;
        JButton przycisk;


    public SaperWidok() {
//        Zegar zegar = new Zegar(this);
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

        JLabel czas = new JLabel("000");
//        czas.setText(zegar.getCzas());

        czas.setFont(new Font("Verdana", Font.BOLD, 32));
        czas.setForeground(Color.RED);
        czas.setBackground(Color.BLACK);
        czas.setOpaque(true);
        panel.add(czas);


        JPanel plansza = new JPanel();
        add(komunikat, BorderLayout.SOUTH); // wyświetla komunikat w dolnym miejscu
        add(panel, BorderLayout.NORTH); // wyświetla pane w górnym miejscu
        plansza.add(new SaperModel(komunikat, komunikat2)); // dodaje plansze do panelu
        add(plansza, BorderLayout.CENTER); // wyświetla planszę
//        setPreferredSize(new Dimension(250, 350));
        setBackground(Color.BLACK);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paintComponents(getGraphics());
    }

    public void setCzas(int time) {
       this.time = time;
       System.out.println(time);
    }



}
