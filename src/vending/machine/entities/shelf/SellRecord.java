package vending.machine.entities.shelf;

public class SellRecord {
    private Integer payType;
    private int productId;
    private String productName;
    private Integer money;
    private int amount;
    public SellRecord(Integer payType, String productName,
                      int amount,int money,int productId) {
        this.payType=payType;
        this.productName=productName;
        this.money=money;
        this.amount=amount;
        this.productId=productId;
    }
    public Integer getPayingType() {
        return payType;
    }
    public int getMoney(){
        return money;
    }
    public String productName() { return productName; }
    public int getAmount() { return amount; }
    public int getProductId(){return productId;}
    public void show()
    {
        System.out.printf("productId: %d || productName: %s || " +
                          "sellingAmount: %d || earningMoney: %d || payType: %d\n",
                this.productId,this.productName,this.amount,this.money,this.payType);
    }
}
