package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null || name.equals("") || price == null || tax == null || tax.compareTo(new BigDecimal(0)) < 0
                || price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException();
        }
        //name== porownanie porownuje referencje
        //new Integer(2)==new Integer==(2) - wyjdzie false, bo porownujemy czy ten obiekt jest ten sam a nie czy ich wartosc jest taka sama
        //Integer.valueOf(100) == Integer.valueOf(100)  wyswietli true dlatego ze w poprzenich wersajch javy stwierdzono ze obiekty na liczbach od 1-128 sa tak czesto uzywane wiec nie bierzmy nowego tylko zsczytuje ten co jz gdziues jest tam uzywany
        //Integer.valueOf(200)==Integer.valueOf(200) tutaj da juz false

        //zeby cena nie byla null
        //zeby cena nie byla ujemna
        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax() {

        return this.price.multiply(this.getTaxPercent()).add(this.getPrice());
    }
}