package nl.cjib.rm.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("bestand")
public class HistorieRepository implements Historie {

    private List<String> historieSom = new ArrayList<>();
    private List<Double> historieUitkomst = new ArrayList<>();

@Override
    public void addHistorieSom(String som, double uitkomst) {
        historieSom.add(som);
        historieUitkomst.add(uitkomst);
        System.out.println("Historie toegevoegd!");
    }
@Override
    public List<String> getHistorie() {
        List<String> getHistorie = new ArrayList<>();
        System.out.println("Historie is: ");
        for (int i = 0; historieUitkomst.size() > i; i++) {
            getHistorie.add(historieSom.get(i) + " = " + historieUitkomst.get(i));
        }
        return getHistorie;
    }
    @Override
    public double getLaatsteUitkomst() {
        System.out.println("Laatste uitkomst ophalen...");
        if (historieUitkomst.size() == 0) {
            return 0;
        }
        return historieUitkomst.get((historieUitkomst.size() - 1));

    }
}


