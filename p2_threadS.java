
    
    class BankAccount {
        private double balance;
    
        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }
    
        // Synchronized method to ensure thread-safe deposits
        public synchronized void deposit(double amount) {
            double newBalance = balance + amount;
            System.out.println(Thread.currentThread().getName() + " depositing " + amount);
            balance = newBalance;
            System.out.println(Thread.currentThread().getName() + " new balance " + balance);
        }
    
        public double getBalance() {
            return balance;
        }
    }
    
    class DepositThread extends Thread {
        private BankAccount account;
        private double amount;
    
        public DepositThread(BankAccount account, double amount) {
            this.account = account;
            this.amount = amount;
        }
    
        @Override
        public void run() {
            account.deposit(amount);
        }
    }
    
    public class p2_threadS {
        public static void main(String[] args) {
            BankAccount account = new BankAccount(1000);
    
            // Create multiple threads trying to deposit money into the same account
            Thread t1 = new DepositThread(account, 200);
            Thread t2 = new DepositThread(account, 300);
            Thread t3 = new DepositThread(account, 400);
    
            // Start the threads
            t1.start();
            t2.start();
            t3.start();
    
            // Wait for all threads to finish
            try {
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            // Print the final balance
            System.out.println("Final balance: " + account.getBalance());
        }
    }
    
