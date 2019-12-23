package vending.machine.entities.pay;

import vending.machine.entities.shelf.SellRecord;

import java.util.ArrayList;

public class CashPay implements Payment {
    private int id;
    private int sum;
    private int amountOf20;
    private int amountOf10;
    private int amountOf5;
    private int amountOf1;
    private ArrayList<SellRecord> sellRecords;

    public CashPay(int id,int sum,
                   int amountOf20,int amountOf10,
                   int amountOf5,int amountOf1) {
        this.id=id;
        this.sum=sum;
        this.amountOf1=0;
        this.amountOf5=0;
        this.amountOf10=0;
        this.amountOf20=0;
        this.sellRecords=new ArrayList<SellRecord>();
    }

    public void addAmountOf20(int i)
    {
        amountOf20+=i;
        this.sum+=i*20;
    }
    public Integer getAmountOf20() { return amountOf20; }
    public void addAmountOf10(int i)
    {
        amountOf10+=i;
        this.sum+=i*10;
    }
    public Integer getAmountOf10()
    {
        return amountOf10;
    }
    public void addAmountOf5(int i)
    {
        amountOf5+=i;
        this.sum+=i*5;
    }
    public Integer getAmountOf5()
    {
        return amountOf5;
    }
    public void addAmountOf1(int i)
    {
        amountOf1+=i;
        this.sum+=i;
    }
    public Integer getAmountOf1()
    {
        return amountOf1;
    }

    @Override
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
