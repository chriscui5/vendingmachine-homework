package vending.machine.entities.pay;

import vending.machine.entities.shelf.SellRecord;

import java.util.ArrayList;

public interface Payment {
    public void setId(int id);
    public Integer getId();
    public void addSum(int money);
    public Integer getSum();


}
