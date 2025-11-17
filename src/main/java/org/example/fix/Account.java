package org.example.fix;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {

    private int customerNumber;
    private int pinNumber;
    private double checkingBalance = 0;
    private double savingBalance = 0;

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public void setCustomerNumber(int customerNumber){
        this.customerNumber = customerNumber;
    }

    public int getCustomerNumber(){
        return customerNumber;
    }

    public void setPinNumber(int pinNumber){
        this.pinNumber = pinNumber;
    }

    public int getPinNumber(){
        return pinNumber;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance(){
        return savingBalance;
    }

    public void calcCheckingWithdraw(double amount){
        checkingBalance -= amount;
    }

    public void calcSavingWithdraw(double amount){
        savingBalance -= amount;
    }

    public void calcCheckingDeposit(double amount){
        checkingBalance += amount;
    }

    public void calcSavingDeposit(double amount){
        savingBalance += amount;
    }

    public void getCheckingWithdrawInput(double amount) {
        System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Amount to withdraw from Checking Account: ");

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
        } else if (checkingBalance - amount >= 0){
            calcCheckingWithdraw(amount);
            System.out.println("New Checking Account Balance: " + moneyFormat.format(checkingBalance));
        } else {
            System.out.println("Not enough funds.");
        }
    }

    public void getSavingWithdrawInput(double amount) {
        System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
        System.out.print("Amount to withdraw from Saving Account: ");

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
        } else if (savingBalance - amount >= 0){
            calcSavingWithdraw(amount);
            System.out.println("New Saving Account Balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Not enough funds.");
        }
    }

    public void getCheckingDepositInput(double amount){
        System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
        } else {
            calcCheckingDeposit(amount);
            System.out.println("New Checking Account Balance: " + moneyFormat.format(checkingBalance));
        }
    }

    public void getSavingDepositInput(double amount){
        System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
        System.out.print("Amount to deposit to Saving Account: ");

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
        } else {
            calcSavingDeposit(amount);
            System.out.println("New Saving Account Balance: " + moneyFormat.format(savingBalance));
        }
    }
}
