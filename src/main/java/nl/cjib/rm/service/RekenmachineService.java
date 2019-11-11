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
//        while (somList.contains("(")) {
//            List<String> somTussenHaakjes = new ArrayList<>();
//            int startpunt = somList.lastIndexOf("(");
//            somTussenHaakjes.add(somList.get(startpunt));
//            int eindpunt = somList.indexOf(")");
//
//            for (int i = startpunt + 1; i <= eindpunt; i++) {
//                somTussenHaakjes.add(somList.get(i));
//                somTussenHaakjes.add(somList.get(i));
//                System.out.println(somTussenHaakjes);
//                System.out.println(somTussenHaakjes);
//            }
//
//            somList.set(startpunt, "" + som(somTussenHaakjes));
//            if (eindpunt < 3){
//                eindpunt = 3;
//            }
//            for (int i = eindpunt;i > 0 ; i--){
//                somList.remove(startpunt);
//                System.out.println(somList);
//            }
//
//            for (int i = eindpunt - 2;i > 0 ; i--){
//                somList.remove(startpunt+1);
//                System.out.println(somList);
//            }
//        }


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


