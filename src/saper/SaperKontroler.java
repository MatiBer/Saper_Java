package saper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import static saper.SaperModel.*;

public class SaperKontroler extends MouseAdapter {

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
        if ((x<liczbaKolumn*rozmiarKomorki) && (y<liczbaWierszy*rozmiarKomorki)) {
            if (evt.getButton() == MouseEvent.BUTTON3) {
                if (pole[(wiersz*liczbaKolumn)+kolumna] > minaKomorka) {
                    czyRysowac = true;
                    if (pole[(wiersz*liczbaKolumn)+kolumna] <= zakrytaMinaKomorka) {
                        if (pozostaloMin > 0) {
                            pole[(wiersz*liczbaKolumn)+kolumna] += oflagowanaKomorka;
                            pozostaloMin--;
                            model.setKomunikat("Pozostało min: " + pozostaloMin);
                        } else {
                            model.setKomunikat("Nie ma wiecej flag!");
                        }
                    } else {
                        pole[(wiersz * liczbaKolumn) + kolumna] -= oflagowanaKomorka;
                        pozostaloMin++;
                        model.setKomunikat("Pozostało min: " + pozostaloMin);
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