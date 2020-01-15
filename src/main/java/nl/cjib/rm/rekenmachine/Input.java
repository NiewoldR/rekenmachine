package nl.cjib.rm.rekenmachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Input {


    @Value("${somInvoerTekst}")
    private String somInvoerTekst;
    private RekenmachineService rekenmachineService;
    private Scanner scanner;
    private PrintStream printStream;


    @Autowired
    public Input(RekenmachineService rekenmachineService, Scanner scanner, PrintStream printStream) {
        this.rekenmachineService = rekenmachineService;
        this.scanner = scanner;
        this.printStream = printStream;
    }


    String rekenmachine() {

        String somInvoer = invoerOmzettenInSom();

        while (!somInvoer.equals("stop")) {
            if (somInvoer.equals("historie")) {
                printStream.println(String.join("\n", rekenmachineService.getHistorie()));
            } else if (somInvoer.equals("help")) {
                printStream.println(String.join("\n", getHelp()));


            } else {
                try {
                    vallideInvoer(somInvoer);

                    double uitkomst = rekenmachineService.som(somInvoerVerwerken(somInvoer));
                    printStream.println(uitkomst);
                    rekenmachineService.addHistorieSom(somInvoer, uitkomst);


                } catch (NumberFormatException | IndexOutOfBoundsException illegaalGetal) {
                    printStream.println(illegaalGetal.getMessage() + " Check uw invoer: " + somInvoer + " en voer uw som opnieuw in:");
                } catch (IllegalArgumentException illegaleInvoer) {
                    printStream.println(illegaleInvoer.getMessage() + " " + somInvoer + " Voer uw som opnieuw in:");
                }


            }
            somInvoer = invoerOmzettenInSom();
        }
        return "Bedankt voor het gebruiken van de Rekenmachine";
    }

    private String invoerOmzettenInSom() {

        printStream.print(somInvoerTekst);

        return scanner.nextLine()
                .toLowerCase()
                .replaceAll("\\s+", "")
                .replaceAll("pi", "" + Math.PI)
                .replaceAll("euler", "" + Math.E)
                .replaceAll("memory", "" + rekenmachineService
                        .getLaatsteUitkomst());
    }


    private List<String> somInvoerVerwerken(String som) {
        List<String> somList = new ArrayList<>();
        StringBuilder tempGetal = new StringBuilder();

        for (int i = 0; som.length() > i; i++) {

            char tempChar = som.charAt(i);

            if (Character.isDigit(tempChar) || tempChar == '.') {
                tempGetal.append(tempChar);

            } else if (tempChar == '-') {
                if (i == 0) {
                    tempGetal.append(tempChar);
                } else if (Character.isDigit(som.charAt(i - 1)) || som.charAt(i - 1) == ')' && Character.isDigit(som.charAt(i + 1)) || som.charAt(i + 1) == '-') {
                    if (tempGetal.length() > 0) {
                        somList.add(tempGetal.toString());
                    }
                    somList.add(som.substring(i, i + 1));
                    tempGetal = new StringBuilder();

                } else {
                    tempGetal.append(tempChar);
                }
            } else if (tempChar == 'w' || tempChar == 's' || tempChar == 'c' || tempChar == 't') {
                try {
                    String wiskundeUitkomst = rekenmachineService.wiskunde(tempGetal.toString(), tempChar);
                    tempGetal = new StringBuilder();
                    tempGetal.append(wiskundeUitkomst);
                } catch (IllegalArgumentException foutInWiskunde) {
                    printStream.println("Operator mist een getal, zorg dat er een getal voor deze operator staat");
                }
            } else if (tempChar == '(') {
                somList.add(som.substring(i, i + 1));
            } else {
                if (tempGetal.length() > 0) {
                    somList.add(tempGetal.toString());
                }
                somList.add(som.substring(i, i + 1));
                tempGetal = new StringBuilder();
            }

        }
        if (tempGetal.length() > 0) {
            somList.add(tempGetal.toString());
        }


        return somList;

    }

    private void vallideInvoer(String somInvoer) {

        String illegaleInvoer = somInvoer.replaceAll("[0-9wsct^*/+()\\-.]", "");

        if (!illegaleInvoer.isEmpty()) {
            throw new IllegalArgumentException("Illegale invoer: " + illegaleInvoer);

        }

    }

    public List<String> getHelp() {
        return List.of("Operators:",
                "+: optellen(getal 1 + getal 2)",
                "-: aftrekken(getal 1 - getal 2)",
                "*: vermenigvuldigen(getal 1 * getal 2)",
                "/: delen(getal 1 / getal 2)",
                "^: machten(getal 1 ^ getal 2)",
                "w: wortel(Getal 1 w)",
                "s: sinus(Getal 1 s)",
                "t: tangus(Getal 1 t)",
                "c: cosinus(Getal 1 c)\n",
                "Speciale getallen:",
                "memory: laatste getal berekend",
                "pi: pi",
                "euler: getal van Euler\n",
                "Speciale invoer:",
                "historie: bekijk alle eerder ingevoerde sommen en resultaten",
                "stop: sluit de applicatie af"
        );
    }
}








