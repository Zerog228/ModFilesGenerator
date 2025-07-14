package zerog.modfilesgenerator.enums;

public enum ProgramVersion {
    V1_19_2_GECKO_3("gecko3"),
    V1_19_3_GECKO_4("gecko4"),
    ;

    private final String gecko;

    ProgramVersion(String gecko){
        this.gecko = gecko;
    }

    public String getGecko() {
        return gecko;
    }
}
