package vending.machine.client;
import vending.machine.entities.*;
import vending.machine.exceptions.*;
public class operations {
    public static void main(String[] args) {
        VendingMachineBuilder vmb = new VendingMachineBuilder();
        VendingMachine vm = vmb.buildVendingMachine();
        vm.welcome();
        //if you are interested in vm.welcome(),you can try it.It is better than vm.welcome2().
        //In vm.welcome(),you can understand instruction according to my remainder.
        //Don't be afraid to make mistakes,my procedure can handle it and give you remainder.
        //Maybe you think my code is too long,because i complish two functions.
        vm.welcome2();
    }
}
