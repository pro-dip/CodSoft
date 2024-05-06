import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// BankAccount class represents the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}

// ATM class represents the ATM machine
class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean withdraw(double amount) {
        return bankAccount.withdraw(amount);
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    public double checkBalance() {
        return bankAccount.getBalance();
    }
}

// ATM GUI class
public class ATM_GUI extends JFrame {
    private ATM atm;

    public ATM_GUI(ATM atm) {
        this.atm = atm;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("ATM Machine");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton balanceButton = new JButton("Check Balance");
        JButton exitButton = new JButton("Exit");

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Enter amount to withdraw:");
                if (amountString != null && !amountString.isEmpty()) {
                    double amount = Double.parseDouble(amountString);
                    if (atm.withdraw(amount)) {
                        JOptionPane.showMessageDialog(null, "Withdrawal successful.\nRemaining balance: $" + atm.checkBalance());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance.");
                    }
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = JOptionPane.showInputDialog("Enter amount to deposit:");
                if (amountString != null && !amountString.isEmpty()) {
                    double amount = Double.parseDouble(amountString);
                    atm.deposit(amount);
                    JOptionPane.showMessageDialog(null, "Deposit successful.\nNew balance: $" + atm.checkBalance());
                }
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Current balance: $" + atm.checkBalance());
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank you for using our ATM. Goodbye!");
                System.exit(0);
            }
        });

        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);
        panel.add(exitButton);

        add(panel);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000); // Assuming initial balance is $1000
        ATM atm = new ATM(userAccount);
        new ATM_GUI(atm);
    }
}
