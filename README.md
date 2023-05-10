# PROO_Saper

![image](https://github.com/MatiBer/Saper_Java/assets/106385056/c6c34417-8b7a-49bd-bb5e-2124b9b4c08e)

## Opis

Przeznaczeniem projektu jest stworzenie gry – Saper.
Program wyświetla w graficznym interfejsie użytkownika planszę, liczbę min(lub flag) i czas. Na
planszy znajdują się zakryte pola, a po kliknięciu LPM wskazane pole się odkrywa i odkrywają się
wszystkie pola dookoła, które nie mają w sobie wartości min dookoła(puste pole). Odkryte pola
przedstawiają określone wartości w zależności od obecności miny w pobliżu. Klikając PPM na pole
oznacza się miny flagą, po oznaczeniu pola zmniejsza się ilość pozostałych min i kliknięcie LPM na to
pole nie odkrywa pola. Na planszy są losowo rozłożone miny. Każda mina zwiększa wartość o 1 na
polach dookoła pola na którym się znajduje. Odkrycie pola z miną powoduje przegraną i zakończenie
gry. Gracz wygrywa kiedy odkryje wszystkie pola na których nie ma miny. Liczenie czasu gry działa w
osobnym wątku. Czas ostatniej gry zapisuje się do pliku „wynik.txt”, po zamknięciu okna. Gdy skończą
się flagi to przy próbie dodania kolejnej flagi pojawi się wyjątek w terminalu informujący, że nie ma
więcej flag.

## Diagram klas

![image](https://github.com/MatiBer/Saper_Java/assets/106385056/4feb7cf0-7f15-4140-a008-9c34f5d16826)

## Opis Klas i metod

Saper – główna klasa projektu
main(String[] args) – tworzy objekt klasy SaperWidok
    
SaperModel – odpowiada za główne mechanizmy
nowaGra() – tworzy planszę o określonych wymiarach, ustawia losowo miny i wstawia w polach wartości min dookoła
znajdzPusteKomorki(int pozycja) – sprawdza, które komórki są puste dookoła klikniętego pola. Jeśli któreś pole dookoła jest puste to ponownie sprawdza czy komórki wokół są puste.
paintComponent(Graphics g) – odkrywa pola wstawiając ikony w miejsce pola
    
Plansza – podklasa klasy SaperModel
inicjujPlansze()
    
SaperWidok – buduje okno
SaperWidok() – konstruktor, który tworzy okno
    
SaperKontroler – zawiera instrukcje wykonywane po kliknięciu myszą
mousePressed(MouseEvent evt) – wykonuje instrukcje
  
Zegar – liczy czas
Run() – czeka 1 sekunde i wywołuje licznik
Licznik() – inkrementuje sekundy
Start() – tworzy nowy wątek

## Instrukcja do kompilacji i uruchomienia:

Z poziomu katalogu, w którym znajduje się folder „src”,kompilujemy poleceniem:
javac -d . ./src/saper/*.java
Następnie uruchamiamy poleceniem:
java saper.Saper

lub jeśli mamy Java SE Runtime Environment to wystarzy z jego pomocą uruhomić plik "Saper.jar"


