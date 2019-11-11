package nl.cjib.rm.model;

import java.util.List;

public class Rekenmachine {

    private final List<String> somList;
    private final StringBuilder tempGetal;

    public static class RekenmachineBuilder {
        private final List<String> somList;
        private final StringBuilder tempGetal;

        public RekenmachineBuilder(List<String> getalList, StringBuilder tempGetal) {
            this.somList = getalList;
            this.tempGetal = tempGetal;
        }

        public Rekenmachine build() {
            // call the private constructor in the outer class
            return new Rekenmachine(somList, tempGetal, this);
        }

    }

    private Rekenmachine(List<String> somList, StringBuilder tempGetal, RekenmachineBuilder builder) {
        this.somList = somList;
        this.tempGetal = tempGetal;
    }

    public List<String> getSomList() {
        return somList;
    }


    public StringBuilder getTempGetal() {
        return tempGetal;
    }


}
