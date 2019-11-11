//package nl.cjib.rm.service;
//
//import nl.cjib.rm.repository.RekenmachineRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//class RekenmachineServiceTest {
//
//    private RekenmachineService rekenmachineService;
//    private List<String> getalList;
//    private List<String> operatorList;
//
//    @BeforeEach
//    void somTest() {
//        RekenmachineRepository rekenmachineRepository = new RekenmachineRepository();
//        rekenmachineService = new RekenmachineService(rekenmachineRepository);
//
//        getalList = new ArrayList<>();
//
//        getalList.add("6");
//        getalList.add("2");
//    }
//
//    @Test
//    void addNumbersTest() {
//        operatorList.add("+");
//
//        double optel = rekenmachineService.som(getalList, operatorList);
//
//        assertThat(optel).isEqualTo(8.0);
//    }
//
//    @Test
//    void subtractNumbersTest() {
//        operatorList.add("-");
//
//        double aftrek = rekenmachineService.som(getalList, operatorList);
//
//        assertThat(aftrek).isEqualTo(4.0);
//    }
//
//    @Test
//    void multiplyNumbersTest() {
//        operatorList.add("*");
//
//        double vermenigvuldig = rekenmachineService.som(getalList, operatorList);
//
//        assertThat(vermenigvuldig).isEqualTo(12.0);
//    }
//
//    @Test
//    void devideNumbersTest() {
//        operatorList.add("/");
//
//        double deling = rekenmachineService.som(getalList, operatorList);
//
//        assertThat(deling).isEqualTo(3.0);
//    }
//
//    @Test
//    void defaultSomTest() {
//        operatorList.add("Q");
//
//        assertThatThrownBy(() -> rekenmachineService.som(getalList, operatorList))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("Foutive invoer");
//    }
//
//    @Test
//    void noOperator() {
//
//        double getalInDatabase = rekenmachineService.som(getalList,operatorList);
//
//        assertThat(getalInDatabase).isEqualTo(6.0);
//    }
//
//
//    @Test
//    void wortelTest() {
//        String getal = "9";
//        char berekening = 'w';
//
//        String uitkomst = rekenmachineService.wiskunde(getal, berekening);
//        String controleGetal = "" + Math.sqrt(9);
//
//        assertThat(uitkomst).isEqualTo(controleGetal);
//    }
//
//    @Test
//    void cosTest() {
//        String getal = "9";
//        char berekening = 'c';
//
//        String uitkomst = rekenmachineService.wiskunde(getal, berekening);
//        String controleGetal = "" + Math.cos(9);
//
//        assertThat(uitkomst).isEqualTo(controleGetal);
//    }
//
//    @Test
//    void sinTest() {
//        String getal = "9";
//        char berekening = 's';
//
//        String uitkomst = rekenmachineService.wiskunde(getal, berekening);
//        String controleGetal = "" + Math.sin(9);
//
//        assertThat(uitkomst).isEqualTo(controleGetal);
//    }
//
//    @Test
//    void tanTest() {
//        String getal = "9";
//        char berekening = 't';
//
//        String uitkomst = rekenmachineService.wiskunde(getal, berekening);
//        String controleGetal = "" + Math.tan(9);
//
//        assertThat(uitkomst).isEqualTo(controleGetal);
//    }
//
//    @Test
//    void defaultWiskundeTest() {
//        String getal = "9";
//        char berekening = 'r';
//
//        String uitkomst = rekenmachineService.wiskunde(getal, berekening);
//
//        assertThat(uitkomst).isEqualTo("0.0");
//    }
//
//
//}
