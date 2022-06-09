package saper;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Zegar implements Runnable {

    private Thread t;
    private final JLabel czas;
    private int sekundy;
    private boolean gameInProgress = false;
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
        if(gameInProgress) {
            sekundy++;
            this.czas.setText(String.format("%03d", sekundy));
        }
    }

    public void start() {
        if (t==null) {
            t = new Thread(this);
            t.start();
        }
        gameInProgress = true;
    }

    public void restart() throws FileNotFoundException {
        gameInProgress = false;
        PrintWriter zapis = new PrintWriter("wynik.txt");
        zapis.println("Czas gry: " + sekundy);
        zapis.close();
        this.czas.setText(String.format("%03d", sekundy));
        sekundy = 0;
    }

}

