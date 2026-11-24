
import java.util.*;
import java.time.*;

class Expense {
    int id;
    double amount;
    String date, category;

    Expense(int id, double amount, String date, String category) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String toString() {
        return id + " " + date + " Rs." + amount + " " + category;
    }
}

public class DailyExpenseAnalyzer {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Expense> list = new ArrayList<>();
    static int id = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Add Expense  2.View Expenses");
            System.out.println("3.Search  4.Sort  5.Category Report");
            System.out.println("6.Total Expense  7.Budget  0.Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Amount: ");
                    double a = sc.nextDouble();
                    sc.nextLine();

                    if (a <= 0) {
                        System.out.println("Invalid Amount!");
                        break;
                    }

                    System.out.print("Date (yyyy-mm-dd): ");
                    String d = sc.nextLine();

                    try {
                        LocalDate.parse(d);
                    } catch (Exception e) {
                        System.out.println("Invalid Date!");
                        break;
                    }

                    System.out.print("Category: ");
                    String c = sc.nextLine().trim();

                    if (c.isEmpty()) {
                        System.out.println("Invalid Category!");
                        break;
                    }

                    list.add(new Expense(id++, a, d, c));
                    System.out.println("Expense Added!");
                    break;

                case 2:
                    for (Expense e : list)
                        System.out.println(e);
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int s = sc.nextInt();
                    boolean found = false;

                    for (Expense e : list)
                        if (e.id == s) {
                            System.out.println(e);
                            found = true;
                        }

                    if (!found) System.out.println("Not Found!");
                    break;

                case 4:
                    list.sort(Comparator.comparingDouble(e -> e.amount));
                    for (Expense e : list)
                        System.out.println(e);
                    break;

                case 5:
                    HashMap<String, Double> map = new HashMap<>();

                    for (Expense e : list)
                        map.put(e.category,
                            map.getOrDefault(e.category, 0.0) + e.amount);

                    System.out.println(map);
                    break;

                case 6:
                    double total = 0;
                    for (Expense e : list) total += e.amount;
                    System.out.println("Total: Rs." + total);
                    break;

                case 7:
                    System.out.print("Enter Budget: ");
                    double budget = sc.nextDouble();
                    double sum = 0;

                    for (Expense e : list) sum += e.amount;

                    System.out.println("Remaining: Rs." + (budget - sum));

                    if (sum > budget)
                        System.out.println("Budget Exceeded!");
                    break;

                case 0:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
