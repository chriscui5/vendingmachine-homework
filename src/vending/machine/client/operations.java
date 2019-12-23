package vending.machine.client;
import vending.machine.entities.*;
import vending.machine.exceptions.*;
public class operations {
    public static void main(String[] args) {
        VendingMachineBuilder vmb = new VendingMachineBuilder();
        VendingMachine vm = vmb.buildVendingMachine();
        vm.welcome();

    }
}
