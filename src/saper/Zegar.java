package saper;

import javax.swing.*;

public class Zegar implements Runnable {

    private Thread t;
    private SaperModel model;
    private SaperWidok widok;
    private JLabel czas;
    private int secondsElapsed;
    Zegar(JLabel czas) {
        this.czas = czas;
    }

    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
                licznik();
            }
            catch (InterruptedException e) {
                System.exit(0);
            }
        }
    }

    public void licznik() {
        secondsElapsed++;
        this.czas.setText(String.format("%03d", secondsElapsed));
    }

    public void start() {
        if (t==null) {
            t = new Thread(this);
            t.start();
        }
    }

    public void restart() {
        secondsElapsed = 0;
        this.czas.setText("000");

    }

}

