package al.cit.supermarket.service;

import al.cit.supermarket.model.Product;
import al.cit.supermarket.util.CSVImporter;

import java.util.List;

public class ProductCSVImporter extends CSVImporter<Product> {

    public ProductCSVImporter() {

        super();

        // Setting allowed columns
        setAllowedColumns(4);
    }

    @Override
    public Product mapStringToObject(String[] strings) {

        Product product = new Product();
        product.setName(strings[0]);
        product.setDescription(strings[1]);
        product.setPrice(Double.parseDouble(strings[2]));
        product.setQuantity(Integer.parseInt(strings[3]));

        return product;
    }

    @Override
    public boolean isDuplicate(List<Product> products, Product obj, StringBuilder stringBuilder, String message) {
        return false;
    }

    @Override
    public void responseConfiguration() {

    }
}
