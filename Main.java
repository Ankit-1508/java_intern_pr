import java.util.*;
class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}
class MarketData {
    Map<String, Stock> stocks = new HashMap<>();
    public MarketData() {
        stocks.put("AAPL", new Stock("AAPL", 150.00));
        stocks.put("GOOGL", new Stock("GOOGL", 2800.00));
        stocks.put("AMZN", new Stock("AMZN", 3400.00));
    }
    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }
    public void updatePrice(String symbol, double newPrice) {
        if (stocks.containsKey(symbol)) {
            stocks.get(symbol).price = newPrice;
        }
    }
    public void displayMarket() {
        System.out.println("Market Data:");
        for (Stock stock : stocks.values()) {
            System.out.printf("%s: $%.2f\n", stock.symbol, stock.price);
        }
    }
}
class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double cash;

    public Portfolio(double initialCash) {
        this.cash = initialCash;
    }
    public void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (cash >= cost) {
            holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + quantity);
            cash -= cost;
            System.out.println("Bought " + quantity + " shares of " + stock.symbol);
        } else {
            System.out.println("Insufficient funds to buy " + stock.symbol);
        }
    }
    public void sellStock(Stock stock, int quantity) {
        int owned = holdings.getOrDefault(stock.symbol, 0);
        if (owned >= quantity) {
            holdings.put(stock.symbol, owned - quantity);
            cash += stock.price * quantity;
            System.out.println("Sold " + quantity + " shares of " + stock.symbol);
        } else {
            System.out.println("Not enough shares to sell " + stock.symbol);
        }
    }
    public void displayPortfolio(MarketData marketData) {
        System.out.println("Portfolio:");
        double totalValue = cash;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            double price = marketData.getStock(symbol).price;
            double value = price * quantity;
            totalValue += value;
            System.out.printf("%s: %d shares @ $%.2f = $%.2f\n", symbol, quantity, price, value);
        }
        System.out.printf("Cash: $%.2f\n", cash);
        System.out.printf("Total Portfolio Value: $%.2f\n", totalValue);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MarketData market = new MarketData();
        Portfolio portfolio = new Portfolio(10000.00);
        while (true) {
            System.out.println("\n1. View Market Data\n2. Buy Stock\n3. Sell Stock\n4. View Portfolio\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.nextLine();
                    System.out.print("Enter quantity to buy: ");
                    int buyQty = scanner.nextInt();
                    portfolio.buyStock(market.getStock(buySymbol), buyQty);
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.nextLine();
                    System.out.print("Enter quantity to sell: ");
                    int sellQty = scanner.nextInt();
                    portfolio.sellStock(market.getStock(sellSymbol), sellQty);
                    break;
                case 4:
                    portfolio.displayPortfolio(market);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
