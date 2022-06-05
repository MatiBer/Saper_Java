package saper;

import javax.swing.*;

public class Zegar implements Runnable {

    private Thread t;
    private SaperModel model;
    private SaperWidok widok;
    private JLabel czas;

    Zegar(SaperWidok widok) {
        this.widok = widok;
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
        String time = this.czas.getText();
        int time0 = Integer.parseInt(time);
        ++time0;
        this.czas.setText(Integer.toString(time0));
    }

    public void start() {
        if (t==null) {
            t = new Thread(this);
            t.start();
        }
    }

    public String getCzas() {
        String time = czas.getText();
        return time;
    }
}

