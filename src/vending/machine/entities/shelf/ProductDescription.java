package vending.machine.entities.shelf;

public class ProductDescription {
    private String productName;
    private int price;
    private int productId;
    public ProductDescription(int productId,String productName,int price)
    {
        this.productName=productName;
        this.price=price;
        this.productId =productId;
    }
    public String getProductName()
    {
        return productName;
    }
    public int getProductId()
    {
        return productId;
    }
    public int getPrice()
    {
        return price;
    }
}
