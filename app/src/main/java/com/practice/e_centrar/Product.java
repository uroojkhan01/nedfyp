package com.practice.e_centrar;

public class Product {

    private int Id;
    private String ProductName;
    private String ProductImage;
    private String Sku;
    private String Variants;
    private String OnHand;
    private String Instock;
    private String UnitPrice;
    private String ProductTypeIdFk;
    private String ProductCategoryIdFk;
    private String CreatedBy;
    private String CreatedDate;
    private String IsActive;
    private String UpdatedDate;
    private String UpdatedBy;
    private String Fullfilled;


    public Product(int id, String productName, String productImage, String sku, String variants, String onHand, String instock, String unitPrice, String productTypeIdFk, String productCategoryIdFk, String createdBy, String createdDate, String isActive, String updatedDate, String updatedBy, String fullfilled) {
        Id = id;
        ProductName = productName;
        ProductImage = productImage;
        Sku = sku;
        Variants = variants;
        OnHand = onHand;
        Instock = instock;
        UnitPrice = unitPrice;
        ProductTypeIdFk = productTypeIdFk;
        ProductCategoryIdFk = productCategoryIdFk;
        CreatedBy = createdBy;
        CreatedDate = createdDate;
        IsActive = isActive;
        UpdatedDate = updatedDate;
        UpdatedBy = updatedBy;
        Fullfilled = fullfilled;
    }

    public Product(String productName, String unitPrice, String productImage){
        this.ProductName=productName;
        this.UnitPrice=unitPrice;
        this.ProductImage=productImage;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getSku() {
        return Sku;
    }

    public void setSku(String sku) {
        Sku = sku;
    }

    public String getVariants() {
        return Variants;
    }

    public void setVariants(String variants) {
        Variants = variants;
    }

    public String getOnHand() {
        return OnHand;
    }

    public void setOnHand(String onHand) {
        OnHand = onHand;
    }

    public String getInstock() {
        return Instock;
    }

    public void setInstock(String instock) {
        Instock = instock;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getProductTypeIdFk() {
        return ProductTypeIdFk;
    }

    public void setProductTypeIdFk(String productTypeIdFk) {
        ProductTypeIdFk = productTypeIdFk;
    }

    public String getProductCategoryIdFk() {
        return ProductCategoryIdFk;
    }

    public void setProductCategoryIdFk(String productCategoryIdFk) {
        ProductCategoryIdFk = productCategoryIdFk;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public String getFullfilled() {
        return Fullfilled;
    }

    public void setFullfilled(String fullfilled) {
        Fullfilled = fullfilled;
    }
}
