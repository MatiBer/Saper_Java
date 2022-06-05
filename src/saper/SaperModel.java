package saper;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class SaperModel extends JPanel {
    public static final int rozmiarKomorki = 24;
    public static final int zakrytaKomorka = 10;
    public static final int oflagowanaKomorka = 10;
    public static final int pustaKomorka = 0;
    public static final int minaKomorka = 9;
    public static final int zakrytaMinaKomorka = minaKomorka + zakrytaKomorka;
    public static final int oflagowanaMinaKomorka = zakrytaMinaKomorka + oflagowanaKomorka;
    public static final int liczbaWierszy = 10;
    public static final int liczbaKolumn = 10;
    public static int[] pole;
    public static boolean czyGra;
    public static int pozostaloMin;
    public static Image[] ikona;
    public static int wszystkieKomorki;
    private final JLabel komunikat;
    private final JLabel komunikat2;



    public SaperModel(JLabel komunikat, JLabel komunikat2) {
        Plansza plansza = new Plansza();
        this.komunikat = komunikat;
        this.komunikat2 = komunikat2;
//        this.czas = czas;
        synchronized (this) {
            plansza.inicjujPlansze();
            nowaGra();
        }
    }


    public class Plansza { // Plansza (psuje się jak jest w osobnym pliku)
        public void inicjujPlansze() {
            int szerokosc = liczbaKolumn * rozmiarKomorki;
            int wysokosc = liczbaWierszy * rozmiarKomorki;
            setPreferredSize(new Dimension(szerokosc, wysokosc));
            int numerIkony = 13;
            ikona = new Image[numerIkony];
            for (int i = 0; i < numerIkony; i++) {
                ikona[i] = new ImageIcon("src/ikony/" + i + ".png").getImage();
            }
            addMouseListener(SaperKontroler.getInstance());
        }

    }

    public void nowaGra() {
        int komorka;
        var losuj = new Random();
        czyGra = true;
        int liczbaMin = (liczbaWierszy * liczbaKolumn) / 10;
        pozostaloMin = liczbaMin;
        wszystkieKomorki = liczbaKolumn * liczbaWierszy;
        pole = new int[wszystkieKomorki];
        for (int i = 0; i < wszystkieKomorki; i++) {
            pole[i] = zakrytaKomorka;
        }
        komunikat.setText(" ");
//        komunikat2.setText(Integer.toString(pozostaloMin));
        String miny = String.format("%03d", pozostaloMin);
        komunikat2.setText(miny);
//        Zegar licznik = new Zegar(this);
//        licznik.start();
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
        int komorka; //komorka oznacza pozycje w tablicy jednowymiarowej
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
        super.paintComponent(g);
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
                        komorka = 9;
                    } else if (komorka == oflagowanaMinaKomorka) {
                        komorka = oflagowana;
                    } else if (komorka > zakrytaMinaKomorka) {
                        komorka = 12;
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

                g.setColor(Color.BLACK);
//                g.drawRect(j*rozmiarKomorki, i*rozmiarKomorki, rozmiarKomorki, rozmiarKomorki);//dodaje ramki
            }
        }
        if (odkryta == 0 && czyGra) {
            czyGra = false;
            komunikat.setText("Wygrałeś!");
        } else if (!czyGra) {
            komunikat.setText("Przegrałeś!");
        }
    }

    public JLabel setKomunikat(String komunikat) {
        this.komunikat.setText(komunikat);
        return this.komunikat;
    }

    public JLabel setKomunikat2(String komunikat) {
        this.komunikat2.setText(komunikat);
        return this.komunikat2;
    }

    public void Rysuj() {
        repaint();
    }

    public int getSzerokosc() {
        return liczbaKolumn * rozmiarKomorki;
    }
    public int getWysokosc() {
        return liczbaWierszy * rozmiarKomorki;
    }


}
