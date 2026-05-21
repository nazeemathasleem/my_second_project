package my_second_project;

import java.io.*;
import java.util.*;

// STOCK //
class Stock {

    String stockName;
    double price;

    Stock(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
    }

    void displayStock() {
        System.out.println(stockName + " - ₹" + price);
    }
}

// TRANSACTION //
class Transaction {

    String type;
    String stockName;
    int quantity;
    double totalAmount;

    Transaction(String type, String stockName,
                int quantity, double totalAmount) {

        this.type = type;
        this.stockName = stockName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    void displayTransaction() {

        System.out.println(type + " | "
                + stockName + " | Qty: "
                + quantity + " | ₹"
                + totalAmount);
    }
}

// PORTFOLIO //
class Portfolio {

    double balance = 100000;

    HashMap<String, Integer> holdings = new HashMap<>();

    ArrayList<Transaction> transactions = new ArrayList<>();

    void buyStock(Stock stock, int quantity) {

        double total = stock.price * quantity;

        if (total <= balance) {

            balance -= total;

            holdings.put(
                    stock.stockName,
                    holdings.getOrDefault(stock.stockName, 0) + quantity
            );

            transactions.add(
                    new Transaction("BUY",
                            stock.stockName,
                            quantity,
                            total)
            );

            System.out.println("\nStock Purchased Successfully!");
        }

        else {
            System.out.println("\nInsufficient Balance!");
        }
    }

    void displayPortfolio() {

        System.out.println("\n===== PORTFOLIO =====");

        System.out.println("Balance: ₹" + balance);

        for (String stock : holdings.keySet()) {

            System.out.println(stock + " : "
                    + holdings.get(stock) + " shares");
        }
    }
}

// MAIN //
public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Stock> market = new ArrayList<>();

        market.add(new Stock("TCS", 3500));
        market.add(new Stock("Infosys", 1500));

        Portfolio user = new Portfolio();

        int choice;

        do {

            System.out.println("\n1. Display Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. View Portfolio");
            System.out.println("4. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    for (int i = 0; i < market.size(); i++) {

                        System.out.println((i + 1)
                                + ". "
                                + market.get(i).stockName
                                + " - ₹"
                                + market.get(i).price);
                    }

                    break;

                case 2:

                    System.out.print("Enter Stock Number: ");
                    int stockChoice = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    user.buyStock(
                            market.get(stockChoice - 1),
                            qty
                    );

                    break;

                case 3:

                    user.displayPortfolio();

                    break;

                case 4:

                    System.out.println("Thank You!");
                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}