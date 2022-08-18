package Transactions;

public class Transactions {

    private int customerId;
    private double deposit;
    private double withdrawal;
    private double balance;

    public Transactions(){}
    public Transactions(int customerId, Double deposit, Double withdrawal, Double balance) {
        this.customerId = customerId;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
        this.balance = balance;
    }

   public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getDeposit(){
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getWithdrawal(){
        return withdrawal;
    }
    public void setWithdrawal(double withdrawal) {
        this.withdrawal = deposit;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
}
