package vending.machine.client;

import vending.machine.entities.*;
import vending.machine.entities.pay.CashPay;
import vending.machine.entities.pay.CoinPurse;
import vending.machine.entities.pay.OnlinePay;
import vending.machine.entities.shelf.ProductDescription;
import vending.machine.entities.shelf.ProductionShelf;

import java.io.*;
import java.util.Scanner;

public class VendingMachineBuilder {
    public VendingMachine buildVendingMachine(){
        try {
            //set up paramenters
            int shelfCapacity = 10;
            int coinsCapacity= 100;
            int shelfEmptyingThreshold = 3;
            int coinsEmptyingThreshold = 20;
            int numberOfShelfs= 10;
            int totalEarning=0;
            int wechatId=1;
            int aliId=2;
            int cashId=3;
            int coinPurseId=4;
            ConsumerOrder ins = new ConsumerOrder(0,0,0,0,0);
            OnlinePay alipay=new OnlinePay(aliId,totalEarning);
            OnlinePay wetchat=new OnlinePay(wechatId,totalEarning);
            CoinPurse coinPurse=new CoinPurse(coinPurseId,coinsCapacity,coinsCapacity);
            CashPay cashPay=new CashPay(cashId,coinsCapacity,coinsCapacity,coinsCapacity,coinsCapacity,coinsCapacity);

            //set up goods and shelfs
            ProductDescription[] goods=new ProductDescription[shelfCapacity];
            int j = 0;
            Scanner scanner = new Scanner(new File("D:\\a_yixiaolan\\OOD\\vm1.1\\VM\\products.txt"));
            while(scanner.hasNext()){
                goods[j++] = new ProductDescription(scanner.nextInt(),scanner.next(),scanner.nextInt());
            }
            ProductionShelf[] shelfs=new ProductionShelf[numberOfShelfs];
            for(int i=0;i<numberOfShelfs;i++)
            {
                shelfs[i]=new ProductionShelf(shelfCapacity,shelfCapacity,goods[i],totalEarning);
            }

            /* build up the vending machine, finally */
            return new VendingMachine(shelfs,numberOfShelfs,totalEarning,
                                      coinPurse,wetchat,alipay,cashPay,
                                      shelfEmptyingThreshold,coinsEmptyingThreshold,ins);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
