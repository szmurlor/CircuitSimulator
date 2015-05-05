# CircuitSimulator

**THIS IS A PROJECT FOR TEACHING GUI DESIGN AND IMPLEMENTATION! DO NOT TRY TO USE IT FOR PROFESSIONAL NGSPICE SIMULATIONS! IT SIMPLY WON"T WORK.**

Simple project developed on the course of Graphical User Interface Design on Electrical Engineering bachelor 
studies at Faculty of Electrical Engineering, Warsaw University of Technology, Poland.

1. The goal of the project is to provide a graphical user interface for NGSpice project (for reduced number of 
electrical components).
2. The project is written in Java Swing.

The main scenario of the programme is:

1. User edits the electrical circuit visually layouting its component and connections.
2. User presses Start simulation.
3. The programme generates NGSpice script.
4. The programme executes the script in NGSpice saving results in a text file.
5. The programme presents the results in a graphical plot.

Opis kolejnych etapów:

1. Zajęcia 1
    * Stworzone okno graficzne w graicznym programie do edycji IntelliJ IDEA - UI Designer, projekt banalnego layoutu
    * Implementacja podstawowych akcji w oknie graficznym
    * Implementacja prostego JPanelu z własną obsługą rysowania (Narysowanie kilku ręcznie zaimplementowanych prostokątów)
    * Implementacja obiektów ElectricalComponent i rysowanie ich w pętli. 
    * Dodanie możliwości przesuwania obiektów na ekranie
    * Dodanie możliwości zaznaczania wielu z klawiszem SHIFT
    * Przesuwanie wielu z SHIFT
    * Dodanie rysowania połączeń między komponentami (jeszcze bez możliwości własnego definiowania połączeń)
2. Zajęcia 2
    * Organizacja w pakiety: gui, domain.cicuitcomponents, view
    * Udoskonalenie wizualizaji komponentów obwodu elektrycznego (poprawne rysowanie symboli, dodanie węzłów wejściowych i wyjściowych do kompoentów)
    * Dodanie możliwości definiowania połączeń między węzłami komponentów
    * Reakcja na podwójne kliknięcie na komponencie w celu wyświetlenia okna dialogowego z włąściwościami komponentu
    * Implementacja okna dialogowego z właściwościami komponentu (nazwa, zmiana orientacji)
    * Utworzenie panelu z dostępnymi komponentami i umożliwienie dodawania ich do schematu (na razie wciśnięcie przycisku automatycznie dodaje komponent w lewym-górnym rogu panelu)
3. Zajęcia 3 
    * Integracja pozostałych komponentów stworzonych przez studentów
    * Dodanie listy Terminal (klasa terminal z isInside)
    * Dodanie onMouseMove z podświetlaniem terminali
    * Implementacja connectora.
    * Zaznaczanie connectora
    * Usuwanie komponentów.
    * Dokończenie toolbara - wszystkie komponenty (kondensator, cewka, źródło prądowe, źródło napięciowe)
    * Podpięcie obrazków pod komponenty toolbara
    * Poprawienie okna dialogowego i dorobienie w nim wartości elektrycznej.
    * Przerobienie CircuitConnection aby nie łączył Componentów tylko Terminale
    * Dodawanie połączenia - jeżeli podczas onHover na Terminalu wciśnięty zostanie przycisk OnPress to zostaje dodany tymczasowy obiekt połączenia, który ma podpięty oryginalny jeden Terminal i docelowy terminal tymczasowy utworzony w pustym komponencie (pusty komponent potrzebny ponieważ Terminal określa swoje współrzędne gemoteryczne względem komponentu rodzica.
    * Utworzenie połączenie po mouseRelease jeżeli jakiś inny terminal był Hovered.
    * Zaznaczanie połączeń (równanie prostej i odległość punktu od prostej).
    * Rysowanie zakończeń połączenia w postaci kwadratów.
    * Dodanie trybów interakcji myszki. (Stany interakcji)
    * Dodanie usuwania.
4. Zajęcia 4
    * Praca nad oknem dialogowym: dodanie wartości elektrycznej, etykiety, jednostki oraz poprawienie tytułu okna dialogowego (wymaga implementacji metod abstracyjnych getElectricalValue, getEletricalVulueLabel, getDoc, getEletricalValueUnit)
    * Reorganizacja pakietów (dodanie pakietu components)
    * Definicja struktury pliku do, której będziemy zapisywać.
    * Zapisywanie pliku.
    * Wczytywanie pliku.
5. Zajęcia 5
    * Poprawienie połączeń (zaznaczanie tylko gdy blisko prostej oraz gdy pomiędzy x0,y0 i x1,y2
    * Dokończone wczytywanie CircuitConnection
    * Zapisywanie skryptu NgSpice
    * Wprowadzenie różnych rodzajów symulacji
    * Uruchamianie NgSpice i pobieranie wyników (raw file)
    * Dodanie okna dialogowego konsoli NgSpice (podglądu komunikatów zwracanych przez NgSpice)
    * Dodanie okna dialogowego preferencji
