package nl.cjib.rm.service;

import nl.cjib.rm.repository.RekenmachineRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public
class RekenmachineService {


    private RekenmachineRepository rekenmachineRepository;

    RekenmachineService(RekenmachineRepository rekenmachineRepository) {
        this.rekenmachineRepository = rekenmachineRepository;
    }


    public double som(List<String> somList) {
        while (somList.contains("(")) {
            System.out.println(somList);

            List<String> somTussenHaakjes = new ArrayList<>();
            int startpunt = somList.lastIndexOf("(");
            int eindpunt = somList.indexOf(")");

            for (int i = startpunt + 1; i < eindpunt; i++) {
                somTussenHaakjes.add(somList.get(i));
            }
            for (int i = eindpunt; i > startpunt; i--) {
                somList.remove(i);
                System.out.println(somList);

            }


            somList.set(startpunt, "" + som(somTussenHaakjes));
        }


        while (somList.contains("^")) {
            int indexOfOperator = somList.indexOf("^");
            somList.set(indexOfOperator - 1, "" + Math.pow(Double.parseDouble(somList.get(indexOfOperator - 1)), Double.parseDouble(somList.get(indexOfOperator + 1))));

            somList.remove(indexOfOperator + 1);
            somList.remove(indexOfOperator);
        }

        while (somList.contains("*") || somList.contains("/")) {
            int indexOfOperator;
            if (somList.contains("*") && !somList.contains("/") || somList.contains("*") && somList.indexOf("*") < somList.indexOf("/")) {
                indexOfOperator = somList.indexOf("*");
                somList.set(indexOfOperator - 1, "" + Double.parseDouble(somList.get(indexOfOperator - 1)) * Double.parseDouble(somList.get(indexOfOperator + 1)));
            } else {
                indexOfOperator = somList.indexOf("/");
                somList.set(indexOfOperator - 1, "" + Double.parseDouble(somList.get(indexOfOperator - 1)) / Double.parseDouble(somList.get(indexOfOperator + 1)));

            }
            somList.remove(indexOfOperator + 1);
            somList.remove(indexOfOperator);
        }
        System.out.println(somList);
        while (somList.contains("+") || somList.contains("-")) {
            int indexOfOperator;
            if (somList.contains("+") && !somList.contains("-") || somList.contains("+") && somList.indexOf("+") < somList.indexOf("-")) {
                indexOfOperator = somList.indexOf("+");
                somList.set(indexOfOperator - 1, "" + (Double.parseDouble(somList.get(indexOfOperator - 1)) + Double.parseDouble(somList.get(indexOfOperator + 1))));
            } else {
                indexOfOperator = somList.indexOf("-");
                somList.set(indexOfOperator - 1, "" + (Double.parseDouble(somList.get(indexOfOperator - 1)) - Double.parseDouble(somList.get(indexOfOperator + 1))));

            }

            somList.remove(indexOfOperator + 1);
            somList.remove(indexOfOperator);
        }

        return Double.parseDouble(somList.get(0));
    }

    public String wiskunde(String getal, char berekening) {
        double invoer = Double.parseDouble(getal);

        switch (berekening) {
            case 'w':
                return Double.toString(Math.sqrt(invoer));
            case 's':
                return Double.toString(Math.sin(invoer));
            case 'c':
                return Double.toString(Math.cos(invoer));
            case 't':
                return Double.toString(Math.tan(invoer));
            default:
                return "0.0";
        }
    }

    public List<String> getHistorie() {
        return rekenmachineRepository.getHistorie();
    }

    public void addHistorieSom(String som) {
        rekenmachineRepository.addHistorieSom(som);
    }

    public void addHistorieUitkomst(double uitkomst) {
        rekenmachineRepository.addHistorieUitkomst(uitkomst);
    }

    public double getLaatsteUitkomst() {
        return rekenmachineRepository.getLaatsteUitkomst();
    }

    public List<String> getHelp() {
        return rekenmachineRepository.getHelp();
    }

}


