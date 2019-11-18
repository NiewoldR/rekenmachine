package nl.cjib.rm.repository;

import java.util.List;

public interface Historie {

    void addHistorieSom(String som, double uitkomst);

    List<String> getHistorie();

    double getLaatsteUitkomst();
}
