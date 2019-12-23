package vending.machine.entities.pay;

public class CoinPurse implements Payment{
    private int id;
    private int sum;
    private int capacity;
    public CoinPurse(int id,int sum,int capacity) {
        this.id=id;
        this.sum=sum;
        this.capacity=capacity;
    }
    public void setId(int id) {
        this.id=id;
    }
    public Integer getId() {
        return id;
    }
    public void addSum(int money) {
        this.sum+=money;
    }
    public void subSum(int money) {
        this.sum-=money;
    }
    public Integer getSum() { return sum; }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
