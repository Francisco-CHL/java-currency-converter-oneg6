package com.fogalde.currency_converter.models;

public class Conversion {
    private final Double amount;
    private final String baseCode;
    private final String targetCode;
    private final Double conversionResult;

    public Conversion(ConversionApiExchange conversionApiExchange, Double amount){
        this.amount = amount;
        this.baseCode = conversionApiExchange.base_code();
        this.targetCode = conversionApiExchange.target_code();
        this.conversionResult = Double.valueOf(conversionApiExchange.conversion_result());
    }

    @Override
    public String toString() {
        return "\n\nEl valor " + this.amount + " ["+this.baseCode+"]" + " convertido da un total de =>>> " + this.conversionResult + " [" + this.targetCode + "]\n\n";
    }
}
