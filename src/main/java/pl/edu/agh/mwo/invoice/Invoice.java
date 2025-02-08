package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> productIndex= new HashMap<>();


    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        for(int i=0; i < quantity; i++) {
            addProduct(product);
        }
    }

    public BigDecimal getNetPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products) {
            sum = sum.add(product.getPrice());
        }
        return sum;
    }

    public BigDecimal getTax() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getGrossPrice() {
        return BigDecimal.ZERO;
    }
}
