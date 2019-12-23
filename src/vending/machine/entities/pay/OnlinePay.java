package vending.machine.entities.pay;

import vending.machine.entities.shelf.SellRecord;

import java.util.ArrayList;

public class OnlinePay implements Payment {
    private int id;
    private int sum;
    private ArrayList<SellRecord> sellRecords;
    public OnlinePay(int id,int sum) {
        this.id=id;
        this.sum=sum;
        this.sellRecords=new ArrayList<SellRecord>();
    }
    public void setId(int id) { this.id=id; }
    public Integer getId() { return id; }
    public void addSum(int money) { this.sum+=money; }
    public Integer getSum() { return sum; }
    public void addSellRecords(SellRecord e)
    {
        sellRecords.add(e);
    }
    public ArrayList<SellRecord> getSellRecords()
    {
        return sellRecords;
    }
}
