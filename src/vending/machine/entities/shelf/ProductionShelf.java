package vending.machine.entities.shelf;

import java.util.ArrayList;

public class ProductionShelf {
    private Integer productAmount;
    private Integer shelfCapacity;
    private Integer sellAmount;
    private ProductDescription pro;
    private ArrayList<SellRecord> sellLogs;

    public ProductionShelf(Integer productAmount, Integer shelfCapacity,
                           ProductDescription pro,Integer sellAmount) {
        this.productAmount = productAmount;
        this.shelfCapacity = shelfCapacity;
        this.pro = pro;
        this.sellAmount=sellAmount;
        this.sellLogs=new ArrayList<>(0);
    }
    public void addSellAmount(int sum)
    {
        this.sellAmount+=sum;
    }
    public Integer getSellAmount()
    {
        return sellAmount;
    }


    public ProductDescription getPro() {
        return pro;
    }

    public void addProduct(Integer num) {
        productAmount += num;
    }

    public void subProduct(Integer num) { productAmount -= num; }

    public Integer getProductAmount() {
        return productAmount;
    }


    public Integer getShelfCapacity() {
        return shelfCapacity;
    }

    public ArrayList<SellRecord> getSellLogs() {
        return sellLogs;
    }

    public void addSellLogs(SellRecord sellLog) {
        this.getSellLogs().add(sellLog);
    }

    public void showShelf() {
        System.out.printf("productId:%d || productName:%s || remaing: %d || productPrice: %d\n",
                this.getPro().getProductId(),
                this.pro.getProductName(),
                this.productAmount,
                this.pro.getPrice());
    }
    public void showEarning()
    {
        System.out.printf("productId:%d || productName:%s || sellAmount: %d || Earning: %d\n",
                this.getPro().getProductId(),
                this.getPro().getProductName(),
                this.sellAmount,
                this.getSellAmount()*this.getPro().getPrice());
    }
}
