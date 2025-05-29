import java.util.*;
// Class representing a Room
class Rm {
    int no;
    String cat; 
    double prc;
    boolean avail;
    public Rm(int no, String cat, double prc) {
        this.no = no;
        this.cat = cat;
        this.prc = prc;
        this.avail = true;
    }
}
// Class representing a Booking
class Bk {
    int id;
    String name;
    Rm rm;
    Date in;
    Date out;
    boolean paid;
    public Bk(int id, String name, Rm rm, Date in, Date out) {
        this.id = id;
        this.name = name;
        this.rm = rm;
        this.in = in;
        this.out = out;
        this.paid = false;
    }
}
// Main Hotel Reservation System class
public class HRS {
    List<Rm> rms = new ArrayList<>();
    List<Bk> bks = new ArrayList<>();
    int ctr = 1;
    Scanner sc = new Scanner(System.in);
    // Constructor initializes sample rooms
    public HRS() {
        rms.add(new Rm(101, "Single", 100));
        rms.add(new Rm(102, "Double", 150));
        rms.add(new Rm(103, "Suite", 250));
    }
    // Display available rooms by category
    public void search(String cat) {
        System.out.println("Available " + cat + " rooms:");
        for (Rm r : rms) {
            if (r.cat.equalsIgnoreCase(cat) && r.avail) {
                System.out.printf("Room %d - $%.2f\n", r.no, r.prc);
            }
        }
    }
    // Create a new booking
    public void reserve() {
        System.out.print("Enter guest name: ");
        String name = sc.nextLine();
        System.out.print("Enter room category (Single/Double/Suite): ");
        String cat = sc.nextLine();

        Rm sel = null;
        for (Rm r : rms) {
            if (r.cat.equalsIgnoreCase(cat) && r.avail) {
                sel = r;
                break;
            }
        }
        if (sel == null) {
            System.out.println("No rooms available in this category.");
            return;
        }

        Date in = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(in);
        c.add(Calendar.DATE, 1);
        Date out = c.getTime();

        Bk b = new Bk(ctr++, name, sel, in, out);
        bks.add(b);
        sel.avail = false;

        System.out.println("Reservation successful. Booking ID: " + b.id);
    }
    // Display all bookings
    public void view() {
        if (bks.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Bk b : bks) {
            System.out.printf("Booking ID: %d\nGuest: %s\nRoom: %d (%s)\nCheck-In: %s\nCheck-Out: %s\nPaid: %b\n\n",
                    b.id,
                    b.name,
                    b.rm.no,
                    b.rm.cat,
                    b.in,
                    b.out,
                    b.paid);
        }
    }
    // Simulate payment processing
    public void pay() {
        System.out.print("Enter booking ID for payment: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Bk b : bks) {
            if (b.id == id) {
                if (b.paid) {
                    System.out.println("Booking already paid.");
                } else {
                    b.paid = true;
                    System.out.println("Payment successful for Booking ID: " + id);
                }
                return;
            }
        }
        System.out.println("Booking ID not found.");
    }
    // Menu to interact with system
    public void run() {
        while (true) {
            System.out.println("\n1. Search Rooms\n2. Make Reservation\n3. View Bookings\n4. Process Payment\n5. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter room category to search: ");
                    String cat = sc.nextLine();
                    search(cat);
                    break;
                case 2:
                    reserve();
                    break;
                case 3:
                    view();
                    break;
                case 4:
                    pay();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    public static void main(String[] args) 
    {
        HRS s = new HRS();
        s.run();
    }
}