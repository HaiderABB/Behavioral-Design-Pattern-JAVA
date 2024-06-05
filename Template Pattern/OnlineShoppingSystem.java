import java.util.List;

abstract class OrderTemplate {

  public final double calculateTotalPrice(List<Item> items) {
    double subtotal = calculateSubtotal(items);
    double discountedTotal = applyDiscount(subtotal);
    double totalPrice = addTaxes(discountedTotal);
    return totalPrice;
  }

  protected abstract double calculateSubtotal(List<Item> items);

  protected abstract double applyDiscount(double amount);

  protected abstract double addTaxes(double amount);
}

class OnlineOrder extends OrderTemplate {
  private double shippingCost;

  public OnlineOrder(double shippingCost) {
    this.shippingCost = shippingCost;
  }

  @Override
  protected double calculateSubtotal(List<Item> items) {
    double subtotal = 0.0;
    for (Item item : items) {
      subtotal += item.getPrice();
    }
    return subtotal + shippingCost;
  }

  @Override
  protected double applyDiscount(double amount) {
    double discountRate = 0.10;
    return amount * (1 - discountRate);
  }

  @Override
  protected double addTaxes(double amount) {
    double taxRate = 0.07;
    return amount * (1 + taxRate);
  }
}

class InStoreOrder extends OrderTemplate {

  @Override
  protected double calculateSubtotal(List<Item> items) {
    double subtotal = 0.0;
    for (Item item : items) {
      subtotal += item.getPrice();
    }
    return subtotal;
  }

  @Override
  protected double applyDiscount(double amount) {
    double discountRate = 0.05;
    return amount * (1 - discountRate);
  }

  @Override
  protected double addTaxes(double amount) {
    double taxRate = 0.07;
    return amount * (1 + taxRate);
  }
}

class Item {
  private String name;
  private double price;

  public Item(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public double getPrice() {
    return price;
  }
}

// Main class
public class OnlineShoppingSystem {

  public static void main(String[] args) {

    List<Item> onlineItems = List.of(new Item("Mobile", 10000.0), new Item("Watch", 3000.0));

    OnlineOrder onlineOrder = new OnlineOrder(100.0); // Shipping cost $20

    double onlineTotalPrice = onlineOrder.calculateTotalPrice(onlineItems);

    System.out.println("Total Price is for your online order: $" + onlineTotalPrice);

    // Creating in-store order with items
    List<Item> inStoreItems = List.of(new Item("Shorts", 1000.0), new Item("Bermudas", 600.0));
    InStoreOrder inStoreOrder = new InStoreOrder();
    double inStoreTotalPrice = inStoreOrder.calculateTotalPrice(inStoreItems);
    System.out.println("Total Price for in store is: $" + inStoreTotalPrice);
  }
}