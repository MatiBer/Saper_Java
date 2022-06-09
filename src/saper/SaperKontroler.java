package saper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;


import static saper.SaperModel.*;

public class SaperKontroler extends MouseAdapter {

    public static MouseListener getInstance(Zegar z) {
    return new SaperKontroler(z);
    }

    private final Zegar z;
    SaperKontroler(Zegar z) {
        this.z = z;
    }


    @Override
    public void mousePressed(MouseEvent evt) {


        SaperModel model = (SaperModel) evt.getSource();
        int x = evt.getX();
        int y = evt.getY();
        int kolumna = x / rozmiarKomorki;
        int wiersz = y / rozmiarKomorki;
        boolean czyRysowac = false;
        if (!czyGra) {
            model.nowaGra();
            model.Rysuj();
        }
        this.z.start();

        if ((x<liczbaKolumn*rozmiarKomorki) && (y<liczbaWierszy*rozmiarKomorki)) {
            if (evt.getButton() == MouseEvent.BUTTON3) {
                if (pole[(wiersz*liczbaKolumn)+kolumna] > minaKomorka) {
                    czyRysowac = true;
                    if (pole[(wiersz*liczbaKolumn)+kolumna] <= zakrytaMinaKomorka) {
//                        if (pozostaloMin > 0) {
//                            pole[(wiersz*liczbaKolumn)+kolumna] += oflagowanaKomorka;
//                            pozostaloMin--;
//                            String miny = String.format("%03d", pozostaloMin);
//                            model.setKomunikat2(miny);
//                        } else {
//                            model.setKomunikat("Nie ma wiecej flag!");
//                        }
                        try{
                            int wyjatekKontrolowany = kolumna * wiersz / pozostaloMin;
                            pole[(wiersz*liczbaKolumn)+kolumna] += oflagowanaKomorka;
                            pozostaloMin--;
                            String miny = String.format("%03d", pozostaloMin);
                            model.setKomunikat2(miny);
                        }
                        catch (ArithmeticException e){
                            //Wypisanie wyjatku tymczasowo na konsole
                            System.out.println("Wyjatek - brak flag");
                            model.setKomunikat("Nie ma wiecej flag!");
                        }
                    } else {
                        pole[(wiersz * liczbaKolumn) + kolumna] -= oflagowanaKomorka;
                        pozostaloMin++;
                        String miny = String.format("%03d", pozostaloMin);
                        model.setKomunikat2(miny);
                    }
                }
            } else {
                if (pole[(wiersz*liczbaKolumn)+kolumna] > zakrytaMinaKomorka) {
                    return;
                }
                if ((pole[(wiersz*liczbaKolumn)+kolumna] > minaKomorka) && (pole[(wiersz*liczbaKolumn)+kolumna]<oflagowanaMinaKomorka)) {
                    pole[(wiersz*liczbaKolumn)+kolumna] -= zakrytaKomorka;
                    czyRysowac = true;
                    if (pole[(wiersz*liczbaKolumn)+kolumna] == minaKomorka) {
                        czyGra = false;
                        try {
                            this.z.restart();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if (pole[(wiersz*liczbaKolumn)+kolumna] == pustaKomorka) {
                        model.znajdzPusteKomorki((wiersz*liczbaKolumn)+kolumna);
                    }
                }
            }
            if(czyRysowac) {
                model.Rysuj();

            }
        }
    }

}