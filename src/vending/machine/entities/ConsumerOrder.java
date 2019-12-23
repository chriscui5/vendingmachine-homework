package vending.machine.entities;

import vending.machine.exceptions.InstructionException;

import javax.print.DocFlavor;
import java.util.Arrays;
public class ConsumerOrder {
    private int productId;
    private int buyingAmount;
    private int payingType;
    private int noteFaceValue;
    private int noteNumber;
    public ConsumerOrder(int productId,int buyingAmount,int payingType,
                         int noteFaceValue,int noteNumber)
    {
        this.productId=productId;
        this.buyingAmount=buyingAmount;
        this.payingType=payingType;
        this.noteFaceValue=noteFaceValue;
        this.noteNumber=noteNumber;
    }
    ////this function is used in welcome();you can ignore
    void parseInstruction(String instruction) throws InstructionException {
        String[] splitIns = instruction.split(",");
        int endsWithRun;
        try{
            productId = Integer.parseInt(splitIns[0]);
            buyingAmount = Integer.parseInt(splitIns[1]);
            payingType = Integer.parseInt(splitIns[2]);
            if(payingType == 3)
            {
                noteFaceValue = Integer.parseInt(splitIns[3]);
                noteNumber = Integer.parseInt(splitIns[4]);
                endsWithRun = splitIns[5].compareTo("run");
            }
            else{
                endsWithRun = splitIns[3].compareTo("run");
            }

        }
        catch (Exception e)
        {
            throw new InstructionException("Instructions must follow one of the two formats:\n" +
                    "productId,buyingAmount,payingType,run\n" +
                    "if you are paying in alipay(payingType==2) or wechat(payingType==1).\n"+
                    "productId,buyingAmount,payingType,noteFaceValue,noteNumber,run\n" +
                    "if you are paying in cash(payingType==3).\n" +
                    "check,full,display,stop");
        }
        if(endsWithRun!=0)
        {
            throw new InstructionException("Instructions must end with 'run' ");
        }
    }
    ////this function is used in welcome2();
    void parseInstruction2(String instruction) throws InstructionException
    {
        if(instruction.charAt(0) != '(' || instruction.charAt(instruction.length()-1) != ')'){
            throw new InstructionException("Instruction should start with '(' and end with ')'\n" +
                    "and check,full,display");
        }
        String instructionWithoutParentheses = instruction.substring(1,instruction.length()-1);
        String[] splitIns = instructionWithoutParentheses.split(",");

        try{
            productId = Integer.parseInt(splitIns[0]);
            buyingAmount = Integer.parseInt(splitIns[1]);
            payingType = Integer.parseInt(splitIns[2]);
            if(payingType == 3)
            {
                noteFaceValue = Integer.parseInt(splitIns[3]);
                noteNumber = Integer.parseInt(splitIns[4]);
            }

        }
        catch (Exception e)
        {
            throw new InstructionException("Instructions must follow one of the two formats:\n" +
                    "(productId,buyingAmount,payingType)\n" +
                    "if you are paying in alipay(payingType==1) or wechat(payingType==2).\n"+
                    "(productId,buyingAmount,payingType,noteFaceValue,noteNumber)\n" +
                    "if you are paying in cash(payingType==3).\n" +
                    "check,full,display");
        }
    }
    public int getProductId() { return productId; }

    public int getBuyingAmount() { return buyingAmount; }

    public int getPayingType() { return payingType; }

    public int getNoteFaceValue() { return noteFaceValue; }

    public int getNoteNumber() { return noteNumber; }
}

