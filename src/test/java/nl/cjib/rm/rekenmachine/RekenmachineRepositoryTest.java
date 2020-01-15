package nl.cjib.rm.rekenmachine;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RekenmachineRepositoryTest {


    @Test
    void addGetHistorieSomTest() {
        RekenmachineRepository rekenmachineRepository = new RekenmachineRepository();

        String som = "5+5";
        double uitkomst = 10.0;

        rekenmachineRepository.addHistorieSom(som, uitkomst);
        rekenmachineRepository.addHistorieSom(som, uitkomst);

        List<String> historieList = rekenmachineRepository.getHistorie();


        assertThat(historieList.get(0)).isEqualTo("5+5 = 10.0");
        assertThat(historieList.get(1)).isEqualTo("5+5 = 10.0");

    }

    @Test
    void getLaatsteUitkomst() {
        RekenmachineRepository rekenmachineRepository = new RekenmachineRepository();
        String som = "5+5";
        double uitkomst = 10.0;

        for (int i = 0; i <= 50; i++) {
            rekenmachineRepository.addHistorieSom(som, uitkomst);
            assertThat(rekenmachineRepository.getLaatsteUitkomst()).isEqualTo(10.0);
        }


    }


}