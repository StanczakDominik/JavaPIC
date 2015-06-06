package pl.edu.fizyka.pojava.pic;

import javax.swing.*;
import java.awt.*;

/**
 * Autor: Mateusz Kaczorek, Dominik Stańczak
 *
 * Klasa tworząca wszystkie okienka tekstowe na podstawie treści zamieszczonych jako stringi z braku lepszego pomysłu.
 * Zrealizowana switchem o liczbowym argumencie, również z braku lepszego pomysłu. Rozwiązanie ma niewiele zalet, ale
 * pomiędzy nimi jest ta, że działa.
 *
 */
class TextFrameConstructor extends JFrame {

    private String plasmaPl = "Fizyka plazmy - z czym to się je?\n" +
            "\n" +
            "Plazma to tak zwany czwarty stan materii. Jest to - najprościej rzecz ujmując - zjonizowany gaz, pojawiający się przy wysokich energiach, w wysokich temperaturach lub pod wpływem silnych pól elektromagnetycznych. Przykłady - 99% widzialnej materii we Wszechświecie (gwiazdy, składają się ze zjonizowanego wodoru, helu i cięższych pierwiastków), błyskawice (jonizujące się powietrze pod wpływem przepływu wysokoenergetycznych jonów).\n" +
            "\n" +
            "Jak to się różni od zwykłego gazu? Diametralnie. Tym, co odróżnia plazmę, są interakcje dalekiego zasięgu. Cząsteczki zwykłego gazu oddziałują ze sobą zazwyczaj jedynie przez kolizje między poszczególnymi atomami. Cząstki zjonizowane, mające niezerowy elektryczny ładunek, oddziałują między sobą przez oddziaływania kulombowskie i pola magnetyczne. Pojawiają się oddziaływania kolektywne.\n" +
            "\n" +
            "To sprawia, że fizyka plazmy od strony teoretycznej przechodzi przez elektrodynamikę, zahacza o układy złożone, kradnie połowę pytań z kolokwium z mechaniki płynów wywołując przerażenie u studentów MEiLu, przebiera się za teorię chaosu, a ostatecznie wywraca energetykę do góry nogami - ale to historia na inny wieczór.";
    private String plasmaEng = "Plasma physics - what's that all about?\n" +
            "\n" +
            "Plasma is the so called fourth state of matter. Simply put, it's a ionized gas which appears at high energies - at high temperatures or under intense electromagnetic fields. Some examples include 99% of visible matter in the Universe (the Sun is a miasma of incandescent plasma composed of ionized hydrogen and helium), lightning (the air gets ionized by an initially small number of highly energetic ions).\n" +
            "\n" +
            "How's that different from a simple gas? As it turns out, the difference is enormous. What characterises plasmas are long-range interactions. In an everyday gas, most interactions are binary - they occur in collisions between individual atoms. In a plasma, ionized particles with a non-zero electric charge interact simultaneously with each other through Coulomb forces and magnetic fields. These are called \"collective interaction\".\n" +
            "\n" +
            "As a result, plasma physics on the theoretical side starts with electrodynamics, quickly turns into fluid dynamics, gets nonlinear, turns complex, dresses up in chaos theory and shakes energetics to the core - but that's a story for another day.";
    private String phasePl = "Ten wykres to tzw. wykres fazowy. Na poziomej osi zaznaczone jest położenie cząstek, zaś na pionowej - ich prędkości. Możemy go wyświetlić głównie dlatego, że mamy uproszczoną, jednowymiarową sytuację - wykres fazowy przestrzeni dwuwymiarowej wymagałby czterech prostopadłych osi.\n" +
            "\n" +
            "Jak widać, układ zaczyna z dokładnie określonymi prędkościami i jednorodnym (z dokładnością do niewielkiego zaburzenia) rozkładem położeń - stąd cienka pozioma linia przez całą długość układu. Jest to niestabilna równowaga - jak piłeczka pingpongowa ustawiona na czubku piłki nożnej, każde wystarczająco silne drganie powoduje wypchnięcie jej z tego położenia.\n" +
            "\n" +
            "Początkowa faza działania symulacji to okres liniowych oscylacji - w tym czasie działanie układu można opisać poprzez rozpisanie drgań na szereg Taylora, co pozostawiamy jako ćwiczenie Czytelnikowi.\n" +
            "\n" +
            "Po pewnym czasie można zaobserwować wybrzuszenie się wykresu w jednym rejonie, a ściśnięcie go w drugiej - jest to punkt przełomu, gdzie liniowe przybliżenie przestaje działać. Układ wchodzi w rejon niestabilności.\n" +
            "\n" +
            "Z ważnych cech, na które warto zwrócić szczególną uwagę - rozkład prędkości jest w przybliżeniu gaussowski wokół średniej ważonej prędkości. Niestabilność wystrzeliwuje pewną część cząstek na ogromne prędkości (tzw. ogon dystrybucji), nawet pięciokrotnie większe od początkowej prędkości. Często pojawia się \"dziura\" w przestrzeni fazowej, stabilna struktura wolna od przepływu cząstek.";
    private String phaseEng = "The upper plot is called a phase plot. On the horizontal axis, we have marked the position of each particle, and on the vertical axis, its velocity. Our simulation is one dimensional and thus we can show the complete phase plot - go 2D and you're going to need four perpendicular axes.\n" +
            "\n" +
            "As you can see, the system starts out with precisely known velocities and a homogeneous (up to a small perturbation) spread of positions - thus the thin horizontal line going through the whole system. It's a position of unstable equilibrium - like a ping pong ball on top of a football, any sufficiently strong deviation from that position gets amplified and results in the system landing somewhere completely different.\n" +
            "\n" +
            "The initial phase of our simulation is a period of linear oscillations - the system can be neatly described by writing out the first two terms of a Taylor series, which we leave as a trivial exercise for the Reader.\n" +
            "\n" +
            "After a time, you can observe that the plot gets squeezed at one point and a kink grows at another - it's a break point between the regimes of unstable and stable equilibria. The assumption of small deviations no longer holds and the instability begins.\n" +
            "\n" +
            "Some important facts to notice - the velocity distribution after the instability begins is approximately gaussian about the mean value. The instability accelerates some particles to great speeds, even five times larger than the initial value (this is the so called tail of the distribution). A frequent phenomenon is a \"hole\" in phase space, a stable structure devoid of particles.";
    private String fieldPl = "Symulacje złożonych układów, takich jak właśnie plazma, są wyjątkowo wymagające obliczeniowo. Prosty układ taki jak ten dla domyślnych wartości wymaga symulacji siedmiu i pół tysiąca różnych cząstek. Symulacja sił w takiej sytuacji jest bardzo skomplikowana - dla prostego oddziałowywania kulombowskiego, licząc wkłady do sił od każdego z ciał, należałoby w jednej iteracji przeliczyć 59 milionów interakcji.\n" +
            "\n" +
            "Oczywiście, jest to raczej niewykonalne. Dlatego też symulacje PIC stosują cwane uproszczenie - dyskretną siatkę. Obszar symulacji - w naszym przypadku, jednowymiarowa linia o długości 2 pi - zostaje podzielony na dyskretne \"komórki\". W każdej iteracji podliczany jest ładunek zawarty w każdej z nich. To daje nam gęstość ładunku w każdej z - domyślnie - 64 komórek. Na tej podstawie zaś, mając odpowiednie warunki brzegowe, można rozwiązać dowolnie wybranym algorytmem równanie Poissona - i otrzymujemy wartości potencjału i pola elektrycznego w każdej z komórek! Stąd nazwa - Particle in Cell, cząstka w komórce.\n" +
            "\n" +
            "Oczywiście, pola na krawędziach komórek nie są dokładnie polami w położeniach cząstek. Ale jako że rozmiar komórek jest niewielki w porównaniu z obszarem symulacji, całkiem niezłym przybliżeniem jest obliczanie pól w położeniach częstek poprzez liniową interpolację pól z sąsiadujących granic komórek.";
    private String fieldEng = "Simulating complex systems such as plasmas is very demanding computationally. Even a simple system such as this one requires - for default values - modelling the behavior of seven and a half thousand different particles. If you were to calculate Coulomb forces between each and every particle, it would take 59 million interactions. In every iteration.\n" +
            "\n" +
            "That's rather impractical. Thus, PIC type simulations use a clever simplification - a discrete grid. The simulation region - in our case, a one dimensional line of length 2 pi - is separated in discrete \"cells\". In each iteration, after movement, the charge in every one of these cells is summed up. This gives us charge density in each of the - by default - 64 cells. Given that and the appropriate boundary conditions (in this case, periodic), Poisson's equation can be solved with any algorithm you like (personally we enjoy Gauss-Seidel) - and you get the values of the electric potential and (by differentiation) electric field in each cell! Thus the name, Particle in Cell.\n" +
            "\n" +
            "Of course, fields are calculated on the edges of cells, not quite at the locations of particles. But since the size of each cell is small compared to the size of the entire simulation region, linearly interpolating from the fields at cell edges to fields at particles is a very good approximation.";
    private String instructionPl = "Prawy górny panel pozwala na sterowanie parametrami symulacji (w znormalizowanych, bezwymiarowych jednostkach).\n" +
            "\n" +
            "Masa 1, Masa 2: ustawia masę cząstek w każdym z dwóch strumieni. Masa odpowiada za inercję - jak ciężko poruszyć dany rodzaj cząstki.\n" +
            "\n" +
            "Lad. 1, Lad. 2: ustawia ładunek obu rodzajów cząstek. Ładunek kontroluje szybkość i czas interakcji.\n" +
            "\n" +
            "Krok T: ustawia krok czasowy symulacji. Mniejszy krok zmniejsza błędy wynikające z rozwiązywania równań ruchu i pól metodami przybliżonymi.\n" +
            "\n" +
            "N/dx: gęstość cząstek, ich liczba (w jednym strumieniu!) na komórkę w siatce. Większe N/dx zwolni symulację z powodu większej liczby obliczeń do przeprowadzenia w każdej iteracji.\n" +
            "\n" +
            "Vinit - początkowa prędkość. Należy zwrócić uwagę, że wykres fazowy skaluje oś prędkości względem prędkości początkowej. Ustawienie dużej prędkości początkowej wydłuży czas trwania liniowych oscylacji.\n" +
            "\n" +
            "N siatki - liczba komórek na siatce, na której liczone są pola. Większa liczba zwiększa dokładność obliczania pola elektrycznego. Nie należy z nią przesadzać!\n" +
            "\n" +
            "DeltaE - dopuszczalny błąd potencjału w każdej iteracji. Mniejsza wartość zwolni wykonywanie symulacji.\n" +
            "\n" +
            "Scatter - amplituda początkowego zaburzenia jednorodnego rozkładu cząstek. Większa wartość przyśpieszy rozpoczęcie fazy niestabilnej. Może znacznie wpływać na liczbę tworzących się w układzie wirów.\n" +
            "\n" +
            "\n" +
            "\n" +
            "Zmień parametry - uruchamia symulację od początku, z parametrami ustawionymi powyżej. \n" +
            "\n" +
            "|| lub > - pauzuje lub kontynuuje odtwarzanie symulacji.\n" +
            "\n" +
            "Restart - uruchamia ponownie symulację z takimi samymi parametrami, jak uprzednio.\n" +
            "\n" +
            "PL\\EN - zmiana języka.\n" +
            "\n" +
            "Zdjęcie - zapisuje w miejscu umieszczenia pliku .jar trzy grafiki poszczególnych wykresów. Nazwy zdjęć są generowane automatycznie. ";
    private String instructionEng = "The upper right panel lets you pick the paramers of the simulation (in non-dimensional, normalized units).\n" +
            "\n" +
            "Mass 1, Mass 2: control the masses of particles in each of the beams. The masses control the particles' inertia.\n" +
            "\n" +
            "Charge 1, Charge 2: control the charges of particles in each of the beams. The charges are responsible for the speed of interactions.\n" +
            "\n" +
            "Timestep: controls the timestep of the simulation. A smaller timestep decreases the errors resulting from solving motion and field equations with their finite difference approximations, but slows down the simulation.\n" +
            "\n" +
            "N/dx: particle density, the number of particles (in one beam!) per cell on the grid. Increasing N/dx will slow down the simulation as more calculations need to be done in each iterations.\n" +
            "\n" +
            "Vinit - the initial velocity. Note that the phase plot scales with the initial velocity, so you won't see this changing right away - setting a huge initial velocity will make the duration of linear oscillations really long, though.\n" +
            "\n" +
            "Grid N - the number of cells on the grid for calculating fields. A bigger N means increased precision of field calculations, but don't push it!\n" +
            "\n" +
            "DeltaE - allowed tolerance of electric potential error. A smaller value will slow down the simulation as more iterations over the grid have to be made.\n" +
            "\n" +
            "Scatter - the amplitude of the initial perturbation of particle distribution. A bigger value will accelerate the onset of the unstable phase. This may affect the number of vortices in the system.\n" +
            "\n" +
            "\n" +
            "\n" +
            "Change parameters - runs the simulation with parameters set up above\n" +
            "\n" +
            "|| or > - play\\pause.\n" +
            "\n" +
            "Restart - restarts the simulation without affecting its paremeters.\n" +
            "\n" +
            "PL\\EN - changes the language.\n" +
            "\n" +
            "PrintScreen - saves the current state of the three plots at the localization of the .jar file. The names are generated automatically and should not get overwritten in each run.";
    private String authorsPl = "TWO STREAM INSTABILITY\n" +
            "\n" +
            "Jednowymiarowa elektrostatyczna symulacja Particle in Cell\n" +
            "\n" +
            "Napisali: Dominik Stańczak & Mateusz Kaczorek\n" +
            "na potrzeby zajęć projektowych Programowania Obiektowego Politechniki Warszawskiej\n" +
            "\n" +
            "w ramach ponoszenia konsekwencji swoich błędów\njak na przykład myślenia, że byłoby fajnie napisać PICa w javie\n" +
            "\n" +
            "Copylefty: na licencji GNU GPL v2, którą można wygooglać\n" +
            "\n" +
            "Kod projektu dostępny na https://github.com/Twostreaminstabilitypolitechnika/JavaPIC\n"
            + "po wsze czasy, dopóki serwery githuba nie eksplodują";
    private String authorsEng = "TWO STREAM INSTABILITY\n" +
            "\n" +
            "A one dimensional electrostatic Particle in Cell simulation\n" +
            "\n" +
            "a tragedy in four acts and 13 classes\n" +
            "\n" +
            "Written by Dominik Stańczak & Mateusz Kaczorek\n" +
            "for an Object-Oriented Programming project at the Warsaw University of Technology\n" +
            "\n" +
            "as a consequence of their mistake of thinking, hey, it'd be neat to write a PIC in java\n" +
            "\n" +
            "Copylefts: on the GNU GPL v2 license, which can be readily googled \n" +
            "\n" +
            "Code available at https://github.com/Twostreaminstabilitypolitechnika/JavaPIC\n"
            + "until the very Sun burns down to a cold dead cinder\nor ITER gets finished, whichever comes first!";

    public TextFrameConstructor(int switcher) throws HeadlessException {

        setSize(540, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea text = null;

        switch (switcher) {
            case 0:
                text = new JTextArea(plasmaPl);
                setTitle("Fizyka plazmy");
                break;
            case 1:
                text = new JTextArea(plasmaEng);
                setTitle("Plasma physics");
                break;
            case 2:
                text = new JTextArea(phasePl);
                setTitle("Wykres fazowy");
                break;
            case 3:
                text = new JTextArea(phaseEng);
                setTitle("Phase plot");
                break;
            case 4:
                text = new JTextArea(fieldPl);
                setTitle("Obliczanie pól");
                break;
            case 5:
                text = new JTextArea(fieldEng);
                setTitle("Field calculations");
                break;
            case 6:
                text = new JTextArea(instructionPl);
                setTitle("Instrukcja");
                break;
            case 7:
                text = new JTextArea(instructionEng);
                setTitle("Instructions");
                break;
            case 8:
                text = new JTextArea(authorsPl);
                setTitle("Autorzy");
                break;
            case 9:
                text = new JTextArea(authorsEng);
                setTitle("Authors");
        }

        //noinspection ConstantConditions
        text.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        text.setLineWrap(true);
        text.setEditable(false);
        text.setVisible(true);
        text.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(text);
        add(scrollPane);
    }


    public static void main(String[] args) {
        TextFrameConstructor textFrame = new TextFrameConstructor(9);
        textFrame.setVisible(true);
    }
}
