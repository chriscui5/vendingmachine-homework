package vending.machine.entities;

import vending.machine.entities.pay.CashPay;
import vending.machine.entities.pay.CoinPurse;
import vending.machine.entities.pay.OnlinePay;
import vending.machine.entities.shelf.ProductionShelf;
import vending.machine.entities.shelf.SellRecord;
import vending.machine.exceptions.InstructionException;

import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {
    private static final int PAY_TYPE_OPTIONS = 3;
    public ProductionShelf[] shelfs;
    public Integer numberOfShelfs;
    public Integer totalEarning;
    private int shelfEmptyingThreshold;
    private int coinsEmptyingThreshold;
    public CoinPurse coinPurse;
    public OnlinePay wechat;
    public OnlinePay alipay;
    public CashPay cash;
    private ConsumerOrder instruction;
    public VendingMachine(ProductionShelf[] shelfs,
                          Integer numberOfShelfs,
                          Integer totalEarning,
                          CoinPurse coinPurse,
                          OnlinePay wechat,
                          OnlinePay alipay,
                          CashPay cash,
                          int shelfEmptyingThreshold,
                          int coinsEmptyingThreshold,
                          ConsumerOrder instruction) {
        this.numberOfShelfs=numberOfShelfs;
        this.shelfs=shelfs;
        this.totalEarning=totalEarning;
        this.coinPurse=coinPurse;
        this.wechat=wechat;
        this.alipay=alipay;
        this.cash=cash;
        this.shelfEmptyingThreshold=shelfEmptyingThreshold;
        this.coinsEmptyingThreshold=coinsEmptyingThreshold;
        this.instruction=instruction;
    }
    //this is my welcome() you can ingnore
    public void welcome(){
        display();
        Scanner scanner = new Scanner(System.in); // Get user input
        String input;
        label:
        while(scanner.hasNext()) {
            input=scanner.next();
            switch (input) {
                case "stop":
                    System.out.println("Bye!Bye!");
                    System.out.println("Turning off...");
                    break label;
                case "check":
                    stocktaking();
                    break;
                case "full":
                    fillShelfAndCoinsPurse();
                    break;
                case "display":
                    display();
                    break;
                default:
                    try {
                        runInstruction(input);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Purchase cancled, please try again!");
                    }
                    break;
            }
            display();
        }
    }
    //this is welcome2() according to homework requirements.
    public void welcome2(){
        display2();
        ArrayList<String> ins=new ArrayList<>();
        Scanner scanner = new Scanner(System.in); // Get user input
        String input;
        while (true)
        {
            input=scanner.next();
            ins.add(input);
            if(input.equals("run"))
            {
                break;
            }
        }
        for(String item:ins)
        {
            switch (item) {
                case "run":
                    display3();
                    System.out.println("Bye!Bye!");
                    System.out.println("Turning off...");
                    break;
                case "check":
                    stocktaking();
                    break;
                case "full":
                    fillShelfAndCoinsPurse();
                    break;
                case "display":
                    display2();
                    break;
                default:
                    try {
                        runInstruction2(item);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Purchase cancled, please try again!");
                    }
                    break;
            }
        }


    }
    //this function is used in welcome2();
    public void display3()
    {
        System.out.println("===========================================================");
        System.out.printf("The total earing: %d\n",this.totalEarning);
        System.out.printf("The remaing of the coins: %d\n",coinPurse.getSum());
        System.out.println("============================================================");
    }
    //this function is used in welcome2();
    public void display2()
    {
        System.out.println("=========================WELCOME TO VENDING MACHINE===================================");
        System.out.println("=========================OPERATIONS===================================");
        System.out.println("Input check to do a checking.");
        System.out.println("Input run to turn off the vending machine.");
        System.out.println("Input fill to fill up the shelf and the coins purse.");
        System.out.println("Input display to browse products.");
        System.out.println("=========================INSTRUCTION TO BUY===================================");
        System.out.println("Input an instruction in the following format.(productId=shelfNumber)\n" +
                "(productId,buyingAmount,payingType)\n" +
                "if you are paying in alipay(payingType==2) or wechat(payingType==1).\n"+
                "(productId,buyingAmount,payingType,noteFaceValue,noteNumber)\n" +
                "if you are paying in cash(payingType==3).");
        System.out.println("=========================LIST OF GOODS===================================");
        for(int i = 0;i<this.numberOfShelfs;i++)
        {
            System.out.printf("Number %d shelf || ",i+1);
            shelfs[i].showShelf();
            if(shelfs[i].getProductAmount()<= shelfEmptyingThreshold)
            {
                System.out.printf("Warning: There are few %s remaining in the number %d shelf" +
                                ",whose amount is %d.\n",
                        shelfs[i].getPro().getProductName(),i+1,shelfs[i].getProductAmount());
            }
        }
        System.out.printf("The remaing of the coins: %d\n",coinPurse.getSum());
        if(coinPurse.getSum() <= coinsEmptyingThreshold){
            System.out.printf("Warning: There are almost no coins in the coin purse," +
                    "whose amount is %d." +
                    "Please use alipay or wechat when paying.\n",coinPurse.getSum());
        }
    }

    //show shelfs,goods,coins.in welcome()you can ignore
    public void display(){
        System.out.println("=========================WELCOME TO VENDING MACHINE===================================");
        System.out.println("=========================LIST OF GOODS===================================");
        for(int i = 0;i<this.numberOfShelfs;i++)
        {
            System.out.printf("Number %d shelf || ",i+1);
            shelfs[i].showShelf();
            if(shelfs[i].getProductAmount()<= shelfEmptyingThreshold)
            {
                System.out.printf("Warning: There are few %s remaining in the number %d shelf" +
                                ",whose amount is %d.\n",
                        shelfs[i].getPro().getProductName(),i+1,shelfs[i].getProductAmount());
            }
        }
        System.out.println("=========================LIST OF GOODS===================================");
        if(coinPurse.getSum() <= coinsEmptyingThreshold){
            System.out.printf("Warning: There are almost no coins in the coin purse," +
                    "whose amount is %d." +
                    "Please use alipay or wechat when paying.\n",coinPurse.getSum());
        }
        System.out.println("=========================OPERATIONS===================================");
        System.out.println("Input check to do a checking.");
        System.out.println("Input stop to turn off the vending machine.");
        System.out.println("Input full to fill up the shelf and the coins purse.");
        System.out.println("Input display to fill up the shelf and the coins purse.");
        System.out.println("=========================INSTRUCTION TO BUY===================================");
        System.out.println("Input an instruction in the following format.(productId=shelfNumber)\n" +
                "productId,buyingAmount,payingType,run\n" +
                "if you are paying in alipay(payingType==2) or wechat(payingType==1).\n"+
                "productId,buyingAmount,payingType,noteFaceValue,noteNumber,run\n" +
                "if you are paying in cash(payingType==3).");
        System.out.println("=========================INSTRUCTION TO BUY===================================");

    }
    //this function is used in welcome2();
    //deal with instruction
    public void runInstruction2(String s) throws InstructionException{
        //this.instruction.parseInstruction(s);
        this.instruction.parseInstruction2(s);

        //1.judge whether instruction is illegal
        if(this.instruction.getProductId() <=0 || this.instruction.getProductId()> numberOfShelfs){
            throw new InstructionException("Illegal productID.");
        }
        int rank=(this.instruction.getProductId()-1);
        // Not enough wares or illegal input
        if(shelfs[rank].getProductAmount()< instruction.getBuyingAmount() || shelfs[rank].getProductAmount()<=0){
            throw new InstructionException("Not enough products");
        }
        else if(this.instruction.getPayingType()<=0||this.instruction.getPayingType()>3){
            throw new InstructionException("Invalid paying type, input must be " +
                                     "2 - alipay, 1 - wechat, 3 - cash.");
        }
        else if(this.instruction.getBuyingAmount()<=0){
            throw new InstructionException("buying amount cannot be negative or zero.");
        }


        //deal with payment
        if(this.instruction.getPayingType() == 3) {
            //cash payment
            if(this.instruction.getNoteFaceValue() != 1 &&
                    this.instruction.getNoteFaceValue() != 5 &&
                    this.instruction.getNoteFaceValue() != 10 &&
                    this.instruction.getNoteFaceValue()!= 20)
            {
                throw new InstructionException("Illegal note face value," +
                        " we only receive RMB notes less than 50.");
            }
            if(this.instruction.getNoteNumber() <= 0 ||
                    this.instruction.getNoteNumber()*this.instruction.getNoteFaceValue() <
                            shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount())
            {
                throw new InstructionException("Not enough cash.");
            }
            if((this.instruction.getNoteNumber()*this.instruction.getNoteFaceValue()-shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount())>coinPurse.getSum())
            {
                throw new InstructionException("Not enough coins.Please use less cash.");
            }
            //sub coins
            this.coinPurse.subSum(this.instruction.getNoteNumber()*this.instruction.getNoteFaceValue()-
                    shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount());
            System.out.printf("Please collect %d coins you got back.\n",this.instruction.getNoteNumber()*this.instruction.getNoteFaceValue()-
                    shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount());
            //add cash
            if(this.instruction.getNoteFaceValue() == 1)
            {
                this.cash.addAmountOf1( this.instruction.getNoteNumber());
            }
            else if(this.instruction.getNoteFaceValue() == 5)
            {
                this.cash.addAmountOf5( this.instruction.getNoteNumber());
            }
            else if(this.instruction.getNoteFaceValue() == 10)
            {
                this.cash.addAmountOf10( this.instruction.getNoteNumber());
            }
            else  if (this.instruction.getNoteFaceValue() == 20)
            {
                this.cash.addAmountOf20( this.instruction.getNoteNumber());
            }
            //this.cash.addSellRecords(log);

        }
        else{
            //wechat pay
            if(this.instruction.getPayingType() == 1)
            {
                System.out.println("Scan the wetchat QR code below to pay.");
                System.out.println("(Imagine there is a wetchat QR code.)");
                this.wechat.addSum(shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount());
                //this.wechat.addSellRecords(log);
            }
            //alipay
            else if(this.instruction.getPayingType() == 2)
            {
                System.out.println("Scan the alipay QR code.");
                System.out.println("(Imagine there is a alipay QR code.)");
                this.alipay.addSum(shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount());
                //this.alipay.addSellRecords(log);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Your payment will be dealing with in 5 secs...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("================PLEASE WAIT=================");
        try {
            Thread.sleep(200);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 3. sell the goods
        totalEarning += shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount();
        SellRecord log=new SellRecord(this.instruction.getPayingType(),
                shelfs[rank].getPro().getProductName(),
                instruction.getBuyingAmount(),
                shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount(),
                this.instruction.getProductId());
        if(this.instruction.getPayingType()==2) {
            this.alipay.addSellRecords(log);
        }
        else if(this.instruction.getPayingType()==1)
        {
            this.wechat.addSellRecords(log);
        }
        else if(this.instruction.getPayingType()==3)
        {
            this.cash.addSellRecords(log);
        }
        shelfs[rank].addSellLogs(log);
        shelfs[rank].subProduct(this.instruction.getBuyingAmount());
        shelfs[rank].addSellAmount(this.instruction.getBuyingAmount());
        System.out.println("Current total earning of this vending machine: "+totalEarning+" RMB");
        System.out.println("<<<<<<Pay for success,welcome to come again!>>>>>");
    }
    //fill the shelf and coinpurse
    public void fillShelfAndCoinsPurse()
    {
        for(int i=0;i<numberOfShelfs;i++)
        {
            int add=shelfs[i].getShelfCapacity()-shelfs[i].getProductAmount();
            shelfs[i].addProduct(add);
        }
        int addCoins=coinPurse.getCapacity()-coinPurse.getSum();
        coinPurse.addSum(addCoins);
        System.out.println("<<<<<<<<<<<<<<fill Shelf And CoinsPurse Successfully>>>>>>>>>>>>>>>>");
    }
    //to check
    public void stocktaking()
    {
        int sum=0;
        System.out.println("====================INCOMING OF EACH WARE=======================");
        for(int i=0;i<numberOfShelfs;i++)
        {
            System.out.printf("Number %d shelf || ",i+1);
            shelfs[i].showEarning();
            sum+=shelfs[i].getPro().getPrice()*shelfs[i].getSellAmount();
        }

        System.out.println("\n====================INCOMING BY PAYTYPE========================");
        for(int i = 1;i<=PAY_TYPE_OPTIONS;i++) {
            if(i==2) {
                System.out.println("\n========INCOMING BY ALIPAY========");
                System.out.println("------START of logs------");
                for(SellRecord log:alipay.getSellRecords())
                {
                    log.show();
                }
                System.out.println("------END of logs------");
                System.out.printf("AliPayRevenue: %d\n",alipay.getSum());
                System.out.println("========INCOMING BY ALIPAY=========\n");
            }
            else if(i==1) {
                System.out.println("========INCOMING BY WECHAT========");
                System.out.println("------START of logs------");
                for(SellRecord log:wechat.getSellRecords())
                {
                    log.show();
                }
                System.out.println("------END of logs------");
                System.out.printf("WechatRevenue: %d\n",wechat.getSum());
                System.out.println("========INCOMING BY WECHAT========\n");
            }
            else if(i==3) {
                int earning=0;
                System.out.println("========INCOMING BY CASH========");
                System.out.println("------START of logs------");
                for(SellRecord log:cash.getSellRecords())
                {
                    log.show();
                    earning+=log.getMoney();
                }
                System.out.println("------END of logs------");
                System.out.printf("CashRevenue: %d\n",earning);
                System.out.println("========INCOMING BY CASH========\n");
            }
        }
        System.out.println("\n====================CHECKING INCOMEING=======================");
        System.out.println("Current total earning of the vending machine:"+totalEarning);
        System.out.println("The sum earning of each good:"+sum);
        if(sum==totalEarning){
            System.out.println("=========CHECK FOR SUNCCESS==========");
        }else{
            System.out.println("========WARNING: CHECK FOR UNSUNCCESS========");
        }
        System.out.println("<<<<<<<<<<<<<<Checking Finished>>>>>>>>>>>>>>>>");
        System.out.printf("\n\n");
    }
    //this function is used in welcome();you can ignore
    //deal with instruction.
    public void runInstruction(String s) throws InstructionException {
        this.instruction.parseInstruction(s);

        //1.judge whether instruction is illegal
        if(this.instruction.getProductId() <=0 || this.instruction.getProductId()> numberOfShelfs){
            throw new InstructionException("Illegal productID.");
        }
        int rank=(this.instruction.getProductId()-1);
        // Not enough wares or illegal input
        if(shelfs[rank].getProductAmount()< instruction.getBuyingAmount() || shelfs[rank].getProductAmount()<=0){
            throw new InstructionException("Not enough products");
        }
        else if(this.instruction.getPayingType()<=0||this.instruction.getPayingType()>3){
            throw new InstructionException("Invalid paying type, input must be " +
                    "2 - alipay, 1 - wechat, 3 - cash.");
        }
        else if(this.instruction.getBuyingAmount()<=0){
            throw new InstructionException("buying amount cannot be negative or zero.");
        }


        //deal with payment
        if(this.instruction.getPayingType() == 3) {
            //cash payment
            if(this.instruction.getNoteFaceValue() != 1 &&
                    this.instruction.getNoteFaceValue() != 5 &&
                    this.instruction.getNoteFaceValue() != 10 &&
                    this.instruction.getNoteFaceValue()!= 20)
            {
                throw new InstructionException("Illegal note face value," +
                        " we only receive RMB notes less than 50.");
            }
            if(this.instruction.getNoteNumber() <= 0 ||
                    this.instruction.getNoteNumber()*this.instruction.getNoteFaceValue() <
                            shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount())
            {
                throw new InstructionException("Not enough cash.");
            }
            if((this.instruction.getNoteNumber()*this.instruction.getNoteFaceValue()-shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount())>coinPurse.getSum())
            {
                throw new InstructionException("Not enough coins.Please use less cash.");
            }
            //sub coins
            this.coinPurse.subSum(this.instruction.getNoteNumber()*this.instruction.getNoteFaceValue()-
                    shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount());
            System.out.printf("Please collect %d coins you got back.\n",this.instruction.getNoteNumber()*this.instruction.getNoteFaceValue()-
                    shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount());
            //add cash
            if(this.instruction.getNoteFaceValue() == 1)
            {
                this.cash.addAmountOf1( this.instruction.getNoteNumber());
            }
            else if(this.instruction.getNoteFaceValue() == 5)
            {
                this.cash.addAmountOf5( this.instruction.getNoteNumber());
            }
            else if(this.instruction.getNoteFaceValue() == 10)
            {
                this.cash.addAmountOf10( this.instruction.getNoteNumber());
            }
            else  if (this.instruction.getNoteFaceValue() == 20)
            {
                this.cash.addAmountOf20( this.instruction.getNoteNumber());
            }
            //this.cash.addSellRecords(log);

        }
        else{
            //wechat pay
            if(this.instruction.getPayingType() == 1)
            {
                System.out.println("Scan the wetchat QR code below to pay.");
                System.out.println("(Imagine there is a wetchat QR code.)");
                this.wechat.addSum(shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount());
                //this.wechat.addSellRecords(log);
            }
            //alipay
            else if(this.instruction.getPayingType() == 2)
            {
                System.out.println("Scan the alipay QR code.");
                System.out.println("(Imagine there is a alipay QR code.)");
                this.alipay.addSum(shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount());
                //this.alipay.addSellRecords(log);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Your payment will be dealing with in 5 secs...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("================PLEASE WAIT=================");
        try {
            Thread.sleep(200);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 3. sell the goods
        totalEarning += shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount();
        SellRecord log=new SellRecord(this.instruction.getPayingType(),
                shelfs[rank].getPro().getProductName(),
                instruction.getBuyingAmount(),
                shelfs[rank].getPro().getPrice()*this.instruction.getBuyingAmount(),
                this.instruction.getProductId());
        if(this.instruction.getPayingType()==2) {
            this.alipay.addSellRecords(log);
        }
        else if(this.instruction.getPayingType()==1)
        {
            this.wechat.addSellRecords(log);
        }
        else if(this.instruction.getPayingType()==3)
        {
            this.cash.addSellRecords(log);
        }
        shelfs[rank].addSellLogs(log);
        shelfs[rank].subProduct(this.instruction.getBuyingAmount());
        shelfs[rank].addSellAmount(this.instruction.getBuyingAmount());
        System.out.println("Current total earning of this vending machine: "+totalEarning+" RMB");
        System.out.println("<<<<<<Pay for success,welcome to come again!>>>>>");
        System.out.println("<<<<<<Pay for success,welcome to come again!>>>>>");
        System.out.println("<<<<<<Pay for success,welcome to come again!>>>>>");
        System.out.println("<<<<<<Pay for success,welcome to come again!>>>>>");
        System.out.printf("\n\n");
    }
}
