package nl.cjib.rm;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("cache")
public class RekenmachineRepository implements Historie {

    private List<String> historieSom = new ArrayList<>();
    private List<Double> historieUitkomst = new ArrayList<>();

    @Override
    public void addHistorieSom(String som, double uitkomst) {
        historieSom.add(som);
        historieUitkomst.add(uitkomst);
    }

    @Override
    public List<String> getHistorie() {
        List<String> getHistorie = new ArrayList<>();
        for (int i = 0; historieUitkomst.size() > i; i++) {
            getHistorie.add(historieSom.get(i) + " = " + historieUitkomst.get(i));
        }
        return getHistorie;
    }

    @Override
    public double getLaatsteUitkomst() {
        if (historieUitkomst.size() == 0) {
            return 0;
        }
        return historieUitkomst.get((historieUitkomst.size() - 1));

    }
}


