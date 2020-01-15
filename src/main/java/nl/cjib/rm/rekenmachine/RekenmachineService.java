package nl.cjib.rm.rekenmachine;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RekenmachineService {


    private Historie historie;

    RekenmachineService(Historie historie) {
        this.historie = historie;
    }


    public double som(List<String> somList) {

        while (somList.contains("(")) {

            List<String> somTussenHaakjes = new ArrayList<>();
            int startpunt = somList.lastIndexOf("(");
            int eindpunt = startpunt;

            while (!somList.get(eindpunt).equals(")")) {
                eindpunt += 1;
            }

            for (int i = startpunt + 1; i < eindpunt; i++) {
                somTussenHaakjes.add(somList.get(i));
            }
            somList.subList(startpunt + 1, eindpunt + 1).clear();


            somList.set(startpunt, "" + som(somTussenHaakjes));
        }

        if (somList.contains(")")) {
            throw new IndexOutOfBoundsException();
        }


        while (somList.contains("^")) {
            int indexOfOperator = somList.indexOf("^");
            try {
                somList.set(indexOfOperator - 1, "" + Math.pow(Double.parseDouble(somList.get(indexOfOperator - 1)), Double.parseDouble(somList.get(indexOfOperator + 1))));

                somList.remove(indexOfOperator + 1);
                somList.remove(indexOfOperator);
            } catch (IndexOutOfBoundsException ontbrekendeWaarde) {
                throw new IndexOutOfBoundsException("Uw som mist één  of meerdere operators en/of getallen.");
            }
        }

        while (somList.contains("*") || somList.contains("/")) {
            int indexOfOperator;
            if (somList.contains("*") && !somList.contains("/")
                    || somList.contains("*") && somList.indexOf("*") < somList.indexOf("/")) {
                indexOfOperator = somList.indexOf("*");
                try {


                    somList.set(indexOfOperator - 1, "" + Double.parseDouble(somList.get(indexOfOperator - 1)) *
                            Double.parseDouble(somList.get(indexOfOperator + 1)));
                } catch (IndexOutOfBoundsException ontbrekendeWaarde) {
                    throw new IndexOutOfBoundsException("Uw som mist één  of meerdere operators en/of getallen.");
                }
            } else {
                indexOfOperator = somList.indexOf("/");
                try {
                    if (somList.get(indexOfOperator + 1).equals("0")) {
                        throw new IllegalArgumentException("Delen door nul is niet mogelijk");
                    }

                    somList.set(indexOfOperator - 1, "" + Double.parseDouble(somList.get(indexOfOperator - 1)) /
                            Double.parseDouble(somList.get(indexOfOperator + 1)));
                } catch (IndexOutOfBoundsException ontbrekendeWaarde) {
                    throw new IndexOutOfBoundsException("Uw som mist één  of meerdere operators en/of getallen.");
                } catch (RuntimeException delenDoorNul) {
                    throw new IllegalArgumentException(delenDoorNul.getMessage());
                }

            }
            somList.remove(indexOfOperator + 1);
            somList.remove(indexOfOperator);

        }

        while (somList.contains("+") || somList.contains("-")) {
            int indexOfOperator;
            if (somList.contains("+") && !somList.contains("-") || somList.contains("+") && somList.indexOf("+") < somList.indexOf("-")) {
                indexOfOperator = somList.indexOf("+");
                try {
                    somList.set(indexOfOperator - 1, "" + (Double.parseDouble(somList.get(indexOfOperator - 1)) + Double.parseDouble(somList.get(indexOfOperator + 1))));
                } catch (IndexOutOfBoundsException ontbrekendeWaarde) {
                    throw new IndexOutOfBoundsException("Uw som mist één  of meerdere operators en/of getallen.");
                }
            } else {
                indexOfOperator = somList.indexOf("-");
                try {
                    somList.set(indexOfOperator - 1, "" + (Double.parseDouble(somList.get(indexOfOperator - 1)) -
                            Double.parseDouble(somList.get(indexOfOperator + 1))));
                } catch (IndexOutOfBoundsException ontbrekendeWaarde) {
                    throw new IndexOutOfBoundsException("Uw som mist één  of meerdere operators en/of getallen.");
                }

            }

            somList.remove(indexOfOperator + 1);
            somList.remove(indexOfOperator);
        }
        try {
            return Double.parseDouble(somList.get(0));

        } catch (NumberFormatException dubbelePunten) {
            throw new NumberFormatException("Een getal bevatte meerdere punten.");
        }
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

    List<String> getHistorie() {
        return historie.getHistorie();
    }

    void addHistorieSom(String som, double uitkomst) {
        historie.addHistorieSom(som, uitkomst);
    }


    double getLaatsteUitkomst() {
        return historie.getLaatsteUitkomst();
    }



}


