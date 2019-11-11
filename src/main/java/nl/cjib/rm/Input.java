package nl.cjib.rm;

import nl.cjib.rm.model.Rekenmachine;
import nl.cjib.rm.service.RekenmachineService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Input {

    private RekenmachineService rekenmachineService;

    private Rekenmachine rekenmachine;

    public Input(RekenmachineService rekenmachineService) {
        this.rekenmachineService = rekenmachineService;
    }

    private Scanner scanner = new Scanner(System.in);


    public String rekenmachine() {

        String som = invoerOmzettenInSom();

        while (!som.equals("stop")) {
            if (som.equals("historie")) {
                System.out.println(String.join("\n", rekenmachineService.getHistorie()));
            } else if (som.equals("help")) {
                System.out.println(String.join("\n",rekenmachineService.getHelp()));


            } else {
                rekenmachine = somVerwerken(som);


                try {
                    double uitkomst = rekenmachineService.som(rekenmachine.getSomList());
                    System.out.println(uitkomst);
                    rekenmachineService.addHistorieUitkomst(uitkomst);


                } catch (Exception e) {
                    System.out.println(e.getMessage() + " voer uw som opnieuw in:");
                }

            }
            som = invoerOmzettenInSom();
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


    private Rekenmachine somVerwerken(String som) {
        List<String> somList = new ArrayList<>();
        StringBuilder tempGetal = new StringBuilder();

        for (int i = 0; som.length() > i; i++) {

            char tempChar = som.charAt(i);

            if (Character.isDigit(tempChar) || tempChar == '.') {
                tempGetal.append(tempChar);

            } else if (tempChar == '-') {
                if (i == 0) {
                    tempGetal.append(tempChar);
                } else if (Character.isDigit(som.charAt(i - 1)) && Character.isDigit(som.charAt(i + 1)) || som.charAt(i + 1) == '-') {
                    somList.add(tempGetal.toString());
                    somList.add(som.substring(i, i + 1));
                    tempGetal = new StringBuilder();

                } else {
                    tempGetal.append(tempChar);
                }
            } else if (tempChar == 'w' || tempChar == 's' || tempChar == 'c' || tempChar == 't') {
                String wiskundeUitkomst = rekenmachineService.wiskunde(tempGetal.toString(), tempChar);
                tempGetal = new StringBuilder();
                tempGetal.append(wiskundeUitkomst);

            } else if (tempChar == '(' || tempChar == ')') {
                somList.add(som.substring(i, i + 1));
            } else {
                somList.add(tempGetal.toString());
                somList.add(som.substring(i, i + 1));
                tempGetal = new StringBuilder();
            }

        }
        rekenmachineService.addHistorieSom(som);
        somList.add(tempGetal.toString());

        return new Rekenmachine.RekenmachineBuilder(somList, tempGetal).build();

    }


}



