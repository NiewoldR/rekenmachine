package nl.cjib.rm;

import nl.cjib.rm.service.RekenmachineService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Input {

    private RekenmachineService rekenmachineService;

    public Input(RekenmachineService rekenmachineService) {
        this.rekenmachineService = rekenmachineService;
    }

    private Scanner scanner = new Scanner(System.in);


    String rekenmachine() {

        String somInvoer = invoerOmzettenInSom();

        while (!somInvoer.equals("stop")) {
            if (somInvoer.equals("historie")) {
                System.out.println(String.join("\n", rekenmachineService.getHistorie()));
            } else if (somInvoer.equals("help")) {
                System.out.println(String.join("\n", rekenmachineService.getHelp()));


            } else {
                try {
                    vallideInvoer(somInvoer);

                    double uitkomst = rekenmachineService.som(somInvoerVerwerken(somInvoer));
                    System.out.println(uitkomst);
                    rekenmachineService.addHistorieUitkomst(uitkomst);


                } catch (NumberFormatException | IndexOutOfBoundsException illegaalGetal) {
                    System.out.println(illegaalGetal.getMessage() +" Check uw invoer: " + somInvoer + " en voer uw som opnieuw in:");
                } catch (IllegalArgumentException illegaleInvoer) {
                    System.out.println(illegaleInvoer.getMessage() + " " + somInvoer + " Voer uw som opnieuw in:");
                }


            }
            somInvoer = invoerOmzettenInSom();
        }
        return "Bedankt voor het gebruiken van de Rekenmachine";
    }

    private String invoerOmzettenInSom() {

        System.out.print("Voer uw som in: ");

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
                }
                catch (IllegalArgumentException foutInWiskunde){
                    System.out.println("Operator mist een getal, zorg dat er een getal voor deze operator staat");
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
        rekenmachineService.addHistorieSom(som);
        if (tempGetal.length() > 0) {
            somList.add(tempGetal.toString());
        }


        return somList;

    }

    private void vallideInvoer(String somInvoer) {

        String illegaleInvoer = somInvoer.replaceAll("[0-9wsct^*/+()\\-.]", "");

        if (!illegaleInvoer.isEmpty()) {
            throw new IllegalArgumentException("Illegale invoer: " + illegaleInvoer );

        }

    }

}






