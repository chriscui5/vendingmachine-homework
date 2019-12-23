package vending.machine.exceptions;

public class InstructionException extends RuntimeException {
    private String instruction;
    public InstructionException(String msg){
        this.instruction=msg;
        System.out.println(msg);
    }
    public String getInstruction(){return instruction;}
}
