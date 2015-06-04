package pic;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * Author MATEUSZ
 */
public class TextFrameConstructor extends JFrame {

    String plazma = "Fizyka plazmy - z czym to się je?\n" +
            "\n" +
            "Plazma to tak zwany czwarty stan materii. Jest to - najprościej rzecz ujmując - zjonizowany gaz, pojawiający się przy wysokich energiach, w wysokich temperaturach lub pod wpływem silnych pól elektromagnetycznych. Przykłady - 99% widzialnej materii we Wszechświecie (gwiazdy, składają się ze zjonizowanego wodoru, helu i cięższych pierwiastków), błyskawice (jonizujące się powietrze pod wpływem przepływu wysokoenergetycznych jonów).\n" +
            "\n" +
            "Jak to się różni od zwykłego gazu? Diametralnie. Tym, co odróżnia plazmę, są interakcje dalekiego zasięgu. Cząsteczki zwykłego gazu oddziałują ze sobą zazwyczaj jedynie przez kolizje między poszczególnymi atomami. Cząstki zjonizowane, mające niezerowy elektryczny ładunek, oddziałują między sobą przez oddziaływania kulombowskie i pola magnetyczne. Pojawiają się oddziaływania kolektywne.\n" +
            "\n" +
            "To sprawia, że fizyka plazmy od strony teoretycznej przechodzi przez elektrodynamikę, zahacza o układy złożone, kradnie połowę pytań z kolokwium z mechaniki płynów wywołując przerażenie u studentów MEiLu, przebiera się za teorię chaosu, a ostatecznie wywraca energetykę do góry nogami - ale to historia na inny wieczór.";



    public TextFrameConstructor(String frameTitle) throws HeadlessException {
        setTitle(frameTitle);
        setSize(540, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea text = null;

        if(frameTitle.equals("Fizyka Plazmy")){
            text = new JTextArea(plazma);
        }
        add(text);


        //funkcjonalnoĹ›Ä‡ wyĹ›wietlania tekstu informacyjnego
        text.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        text.setLineWrap(true); //zawijanie czcionek
        text.setEditable(false); //wyĹ‚Ä…cza edycjÄ™ tekstu
        text.setVisible(true);
        text.setWrapStyleWord(true);
    }


    public static void main(String[] args) {
        TextFrameConstructor textFrame = new TextFrameConstructor("Fizyka Plazmy");
        textFrame.setVisible(true);
    }
}
