package com.learning.modifutsmysql.model;

public class ProductModel {

    String product_code, product_name, product_prce, product_desc;

    public ProductModel() {
    }

    public ProductModel(String product_code, String product_name, String product_prce, String product_desc) {
        this.product_code = product_code;
        this.product_name = product_name;
        this.product_prce = product_prce;
        this.product_desc = product_desc;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_prce() {
        return product_prce;
    }

    public void setProduct_prce(String product_prce) {
        this.product_prce = product_prce;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }
}
