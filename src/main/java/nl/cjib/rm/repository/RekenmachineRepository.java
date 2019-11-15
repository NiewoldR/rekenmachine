package nl.cjib.rm.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RekenmachineRepository {

    private List<String> historieSom = new ArrayList<>();
    private List<Double> historieUitkomst = new ArrayList<>();
    private final List<String> helpOverzicht =
            List.of("Operators:",
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


    public void addHistorieSom(String som, double uitkomst) {
        historieSom.add(som);
        historieUitkomst.add(uitkomst);
    }

    public List<String> getHistorie() {
        List<String> getHistorie = new ArrayList<>();
        for (int i = 0; historieUitkomst.size() > i; i++) {
            getHistorie.add(historieSom.get(i) + " = " + historieUitkomst.get(i));
        }
        return getHistorie;
    }

    public double getLaatsteUitkomst() {
        if (historieUitkomst.size() == 0) {
            return 0;
        }
        return historieUitkomst.get((historieUitkomst.size() - 1));

    }

    public List<String> getHelp() {
        return helpOverzicht;
    }
}
