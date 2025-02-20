package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products= new HashMap<>();


    public void addProduct(Product product) {
        addProduct(product,1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity==null ||quantity <=0){
            throw new IllegalArgumentException("Produkt nie może być null i ilość musi być większa od zera.");
        }
        products.merge(product, quantity, Integer::sum);
    }

    public BigDecimal getNetPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            sum = sum.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return sum;
    }

    public BigDecimal getTax() {
        BigDecimal totalTax = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            BigDecimal taxAmount = entry.getKey().getPrice()
                    .multiply(entry.getKey().getTaxPercent())
                    .multiply(BigDecimal.valueOf(entry.getValue()));
            totalTax = totalTax.add(taxAmount);
        }
        return totalTax;
    }

    public BigDecimal getGrossPrice() {
        return getNetPrice().add(getTax());
    }
}