package saper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class SaperModel extends JPanel {
    private final int rozmiarKomorki = 15;
    private final int zakrytaKomorka = 10;
    private final int oflagowanaKomorka = 10;
    private final int pustaKomorka = 0;
    private final int minaKomorka = 9;
    private final int zakrytaMinaKomorka = minaKomorka + zakrytaKomorka;
    private final int oflagowanaMinaKomorka = zakrytaMinaKomorka + oflagowanaKomorka;
    private final int liczbaWierszy = 9;
    private final int liczbaKolumn = 9;
    private int[] pole;
    private boolean czyGra;
    private int pozostaloMin;
    private Image[] ikona;
    private int wszystkieKomorki;
    private final JLabel komunikat;



    public SaperModel(JLabel komunikat) {
        this.komunikat = komunikat;
        inicjujPlansze();
    }

    public void inicjujPlansze() {
        int szerokosc = liczbaKolumn * rozmiarKomorki + 5;
        int wysokosc = liczbaWierszy * rozmiarKomorki + 5;
        setPreferredSize(new Dimension(szerokosc, wysokosc));
        int numerIkony = 13;
        ikona = new Image[numerIkony];
        for (int i = 0; i < numerIkony; i++) {
            ikona[i] = new ImageIcon("src/ikony/" + i + ".png").getImage();
        }
        addMouseListener(new SaperKontroler());
        nowaGra();
    }

    public void nowaGra() {
        int komorka;
        var losuj = new Random();
        czyGra = true;
        int liczbaMin = 10;
        pozostaloMin = liczbaMin;
        wszystkieKomorki = liczbaKolumn * liczbaWierszy;
        pole = new int[wszystkieKomorki];
        for (int i = 0; i < wszystkieKomorki; i++) {
            pole[i] = zakrytaKomorka;
        }
        komunikat.setText(Integer.toString(pozostaloMin));
        int i = 0;
        while (i < liczbaMin) { //Funkcja losowo rozmieszcza miny i ustala wartosci na polach dookoła
            int pozycja = (int) (losuj.nextDouble() * wszystkieKomorki); //Losuje pozycje miny
            if ((pozycja < wszystkieKomorki) && (pole[pozycja] != zakrytaMinaKomorka)) {
                int aktualnaKolumna = pozycja % liczbaKolumn; //Pobiera kolumnę z tablicy jednowymiarowej
                pole[pozycja] = zakrytaMinaKomorka; //Ustawienie miny
                i++;

                if (aktualnaKolumna > 0) {//ddawane sa wartosci na polach dookola miny
                    komorka = pozycja - 1 - liczbaKolumn;
                    if (komorka >= 0) {
                        if (pole[komorka] != zakrytaMinaKomorka) {
                            pole[komorka]++;
                        }
                    }
                    komorka = pozycja - 1;
                    if (komorka >= 0) {
                        if (pole[komorka] != zakrytaMinaKomorka) {
                            pole[komorka]++;
                        }
                    }
                    komorka = pozycja + liczbaKolumn - 1;
                    if (komorka < wszystkieKomorki) {
                        if (pole[komorka] != zakrytaMinaKomorka) {
                            pole[komorka]++;
                        }
                    }
                }
                komorka = pozycja - liczbaKolumn;
                if (komorka >= 0) {
                    if (pole[komorka] != zakrytaMinaKomorka) {
                        pole[komorka]++;
                    }
                }
                komorka = pozycja + liczbaKolumn;
                if (komorka < wszystkieKomorki) {
                    if (pole[komorka] != zakrytaMinaKomorka) {
                        pole[komorka]++;
                    }
                }
                if (aktualnaKolumna < liczbaKolumn - 1) {
                    komorka = pozycja + 1 + liczbaKolumn;
                    if (komorka < wszystkieKomorki) {
                        if (pole[komorka] != zakrytaMinaKomorka) {
                            pole[komorka]++;
                        }
                    }
                    komorka = pozycja + 1;
                    if (komorka < wszystkieKomorki) {
                        if (pole[komorka] != zakrytaMinaKomorka) {
                            pole[komorka]++;
                        }
                    }
                    komorka = pozycja - liczbaKolumn + 1;
                    if (komorka >= 0) {
                        if (pole[komorka] != zakrytaMinaKomorka) {
                            pole[komorka]++;
                        }
                    }
                }
            }
        }
    }

    public void znajdzPusteKomorki(int pozycja) { //znajduje puste komorki dookola pozycji
        int aktualnaKolumna = pozycja % liczbaKolumn; //tablica jednowymiarowa - kolumna jest pobierana z modulo
        int komorka;
        if (aktualnaKolumna > 0) {
            komorka = pozycja - liczbaKolumn - 1;
            if (komorka >= 0) {
                if (pole[komorka] > minaKomorka) {
                    pole[komorka] -= zakrytaKomorka;
                    if (pole[komorka] == pustaKomorka) {
                        znajdzPusteKomorki(komorka);
                    }
                }
            }
            komorka = pozycja - 1;
            if (komorka >= 0) {
                if (pole[komorka] > minaKomorka) {
                    pole[komorka] -= zakrytaKomorka;
                    if (pole[komorka] == pustaKomorka) {
                        znajdzPusteKomorki(komorka);
                    }
                }
            }
            komorka = pozycja + liczbaKolumn - 1;
            if (komorka < wszystkieKomorki) {
                if (pole[komorka] > minaKomorka) {
                    pole[komorka] -= zakrytaKomorka;
                    if (pole[komorka] == pustaKomorka) {
                        znajdzPusteKomorki(komorka);
                    }
                }
            }
        }
        komorka = pozycja - liczbaKolumn;
        if (komorka >= 0) {
            if (pole[komorka] > minaKomorka) {
                pole[komorka] -= zakrytaKomorka;
                if (pole[komorka] == pustaKomorka) {
                    znajdzPusteKomorki(komorka);
                }
            }
        }
        komorka = pozycja + liczbaKolumn;
        if (komorka < wszystkieKomorki) {
            if (pole[komorka] > minaKomorka) {
                pole[komorka] -= zakrytaKomorka;
                if (pole[komorka] == pustaKomorka) {
                    znajdzPusteKomorki(komorka);
                }
            }
        }
        if (aktualnaKolumna < liczbaKolumn - 1) {
            komorka = pozycja + 1 - liczbaKolumn;
            if (komorka >= 0) {
                if (pole[komorka] > minaKomorka) {
                    pole[komorka] -= zakrytaKomorka;
                    if (pole[komorka] == pustaKomorka) {
                        znajdzPusteKomorki(komorka);
                    }
                }
            }
            komorka = pozycja + 1 + liczbaKolumn;
            if (komorka < wszystkieKomorki) {
                if (pole[komorka] > minaKomorka) {
                    pole[komorka] -= zakrytaKomorka;
                    if (pole[komorka] == pustaKomorka) {
                        znajdzPusteKomorki(komorka);
                    }
                }
            }
            komorka = pozycja + 1;
            if (komorka < wszystkieKomorki) {
                if (pole[komorka] > minaKomorka) {
                    pole[komorka] -= zakrytaKomorka;
                    if (pole[komorka] == pustaKomorka) {
                        znajdzPusteKomorki(komorka);
                    }
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        int odkryta = 0;
        for (int i = 0; i<liczbaWierszy; i++) {
            for (int j = 0; j < liczbaKolumn; j++) {
                int komorka = pole[(i*liczbaKolumn)+j];
                if (czyGra && komorka == minaKomorka) {
                    czyGra = false;
                }

                int zakryta = 10;
                int oflagowana = 11;
                if (!czyGra) {
                    if (komorka == zakrytaMinaKomorka) {
                        int mina = 9;
                        komorka = mina;
                    } else if (komorka == oflagowanaMinaKomorka) {
                        komorka = oflagowana;
                    } else if (komorka > zakrytaMinaKomorka) {
                        int zleOflagowana = 12;
                        komorka = zleOflagowana;
                    } else if (komorka > minaKomorka) {
                        komorka = zakryta;
                    }
                } else {
                    if (komorka > zakrytaMinaKomorka) {
                        komorka = oflagowana;
                    } else if (komorka > minaKomorka) {
                        komorka = zakryta;
                        odkryta++;
                    }
                }
                g.drawImage(ikona[komorka], j*rozmiarKomorki, i*rozmiarKomorki, this);
            }
        }
        if (odkryta == 0 && czyGra) {
            czyGra = false;
            komunikat.setText("Wygrałeś!");
        } else if (!czyGra) {
            komunikat.setText("Przegrałeś!");
        }
    }

    public class SaperKontroler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent evt) {
            int x = evt.getX();
            int y = evt.getY();
            int kolumna = x / rozmiarKomorki;
            int wiersz = y / rozmiarKomorki;
            boolean czyRysowac = false;
            if (!czyGra) {
                nowaGra();
                repaint();
            }
            if ((x<liczbaKolumn*rozmiarKomorki) && (y<liczbaWierszy*rozmiarKomorki)) {
                if (evt.getButton() == MouseEvent.BUTTON3) {
                    if (pole[(wiersz*liczbaKolumn)+kolumna] > minaKomorka) {
                        czyRysowac = true;
                        if (pole[(wiersz*liczbaKolumn)+kolumna] <= zakrytaMinaKomorka) {
                            if (pozostaloMin > 0) {
                                pole[(wiersz*liczbaKolumn)+kolumna] += oflagowanaKomorka;
                                pozostaloMin--;
                                komunikat.setText("Pozostało min: " + pozostaloMin);
                            } else {
                                komunikat.setText("Nie ma wiecej flag!");
                            }
                       } else {
                            pole[(wiersz * liczbaKolumn) + kolumna] -= oflagowanaKomorka;
                            pozostaloMin++;
                            komunikat.setText("Pozostało min: " + pozostaloMin);
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
                            znajdzPusteKomorki((wiersz*liczbaKolumn)+kolumna);
                        }
                    }
                }
                if(czyRysowac) {
                    repaint();
                }
            }
        }
    }
}
