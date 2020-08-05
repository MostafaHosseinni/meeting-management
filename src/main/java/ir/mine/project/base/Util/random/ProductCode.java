package ir.mine.project.base.Util.random;

public class ProductCode {

    private String padding = "";
    private String paddedNumberAsString = "";
    private Integer paddingLength;
    private Integer value;

    public ProductCode(Integer length, Integer value) {
        this.paddingLength = length;
        this.value = value;
    }

    public String getCode() {
        generatePadding(paddingLength);
        generatePaddedNumberAsString(value);
        return paddedNumberAsString;
    }

    private void generatePaddedNumberAsString(int value) {
        String numberAsString = String.valueOf(value);
        paddedNumberAsString = padding.substring(numberAsString.length()) + numberAsString;
    }

    private void generatePadding(Integer length){
        for(int i=1; i<= length.intValue(); i++) {
            padding += "0";
        }
    }
}
