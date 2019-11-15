package nl.cjib.rm.service;

import nl.cjib.rm.repository.RekenmachineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RekenmachineServiceTest {

    private RekenmachineService rekenmachineService;
    private List<String> somList;

    @BeforeEach
    void somTest() {
        RekenmachineRepository rekenmachineRepository = new RekenmachineRepository();
        rekenmachineService = new RekenmachineService(rekenmachineRepository);

        somList = new ArrayList<>();

        somList.add("6");
        somList.add("2");
    }

    @Test
    void addNumbersTest() {
        somList.add(1, "+");

        double optel = rekenmachineService.som(somList);

        assertThat(optel).isEqualTo(8.0);
    }

    @Test
    void subtractNumbersTest() {
        somList.add(1, "-");

        double aftrek = rekenmachineService.som(somList);

        assertThat(aftrek).isEqualTo(4.0);
    }

    @Test
    void multiplyNumbersTest() {
        somList.add(1, "*");

        double vermenigvuldig = rekenmachineService.som(somList);

        assertThat(vermenigvuldig).isEqualTo(12.0);
    }

    @Test
    void devideNumbersTest() {
        somList.add(1, "/");

        double deling = rekenmachineService.som(somList);

        assertThat(deling).isEqualTo(3.0);
    }

    @Test
    void powerNumbersTest() {
        somList.add(1, "^");

        double macht = rekenmachineService.som(somList);

        assertThat(macht).isEqualTo(36.0);
    }


    @Test
    void noOperator() {

        double getalInDatabase = rekenmachineService.som(somList);

        assertThat(getalInDatabase).isEqualTo(6.0);
    }


    @Test
    void wortelTest() {
        String getal = "9";
        char berekening = 'w';

        String uitkomst = rekenmachineService.wiskunde(getal, berekening);
        String controleGetal = "" + Math.sqrt(9);

        assertThat(uitkomst).isEqualTo(controleGetal);
    }

    @Test
    void cosTest() {
        String getal = "9";
        char berekening = 'c';

        String uitkomst = rekenmachineService.wiskunde(getal, berekening);
        String controleGetal = "" + Math.cos(9);

        assertThat(uitkomst).isEqualTo(controleGetal);
    }

    @Test
    void sinTest() {
        String getal = "9";
        char berekening = 's';

        String uitkomst = rekenmachineService.wiskunde(getal, berekening);
        String controleGetal = "" + Math.sin(9);

        assertThat(uitkomst).isEqualTo(controleGetal);
    }

    @Test
    void tanTest() {
        String getal = "9";
        char berekening = 't';

        String uitkomst = rekenmachineService.wiskunde(getal, berekening);
        String controleGetal = "" + Math.tan(9);

        assertThat(uitkomst).isEqualTo(controleGetal);
    }

    @Test
    void defaultWiskundeTest() {
        String getal = "9";
        char berekening = 'r';

        String uitkomst = rekenmachineService.wiskunde(getal, berekening);

        assertThat(uitkomst).isEqualTo("0.0");
    }


}
