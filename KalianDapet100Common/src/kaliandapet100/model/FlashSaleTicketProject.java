package kaliandapet100.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author daven
 */
public class FlashSaleTicketProject {

    /**
     * @param args the command line arguments
     */
    //Project by kelompok Kalian Dapet 100
    //1. Dave Natanael / 160423007
    //2. Elifele Immanuelly Pribadi / 160423089
    //3. Alexander Fabiano Joynard Lapod / 160423232
    //4. Celine Felica Widjanarko
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Ticket> listTicket = new ArrayList<>();
        List<Purchase> listPurchase = new ArrayList<>();

        //input data user
        System.out.println("Enter fullname: ");
        String fullname = scanner.nextLine();
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter date of birth (yyyy-mm-dd): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter member since (yyyy-mm-dd): ");
        LocalDate member_since = LocalDate.parse(scanner.nextLine());

        User user = new User(fullname, username, password, email, dob, member_since);
        System.out.println("\nUser Information:");
        System.out.println("Fullname: " + user.getFullname());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Date of Birth: " + user.getDob());
        System.out.println("Member Since: " + user.getMember_since());

        //pilih aksi add ticket atau purchase
        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Add Ticket");
            System.out.println("2. Purchase Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int action = Integer.parseInt(scanner.nextLine());

            if (action == 1) {
                //Add ticket
                System.out.println("\nEnter Ticket ID: ");
                String id = scanner.nextLine();
                System.out.println("Enter title: ");
                String title = scanner.nextLine();
                System.out.println("Enter description: ");
                String desc = scanner.nextLine();
                System.out.println("Enter price: ");
                int price = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter creator: ");
                String creator = scanner.nextLine();
                System.out.println("Enter stock: ");
                int stock = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter date created (yyyy-mm-dd): ");
                LocalDate createdDate = LocalDate.parse(scanner.nextLine());

                LocalDate updatedDate = LocalDate.now();

                Ticket ticket = new Ticket(id, title, desc, price, stock, creator, createdDate, updatedDate);
                listTicket.add(ticket);

                System.out.println("\nTicket added successfully:");
                System.out.println("ID: " + id + "\tTitle: " + title + "\tStock: " + stock + "\tPrice: " + price);
                System.out.println("Description: " + desc);
                System.out.println("Creator: " + creator + "\nCreated Date: " + createdDate + "\nUpdated Date: " + updatedDate);
            } 
            else if (action == 2) {
                // Purchase ticket
                if (listTicket.isEmpty()) {
                    System.out.println("\nNo tickets available! Please add tickets first.");
                    continue;
                }

                // Display available tickets
                System.out.println("\nAvailable Tickets:");
                System.out.println("ID\tTitle\tStock\tPrice");
                System.out.println("-------------------------------------------------");
                for (Ticket ticket : listTicket) {
                    System.out.println(ticket.getId()+"\t"+ticket.getTitle()+"\t"+ticket.getStock()+"\t"+ticket.getPrice());
                }
                System.out.println("-------------------------------------------------");

                System.out.print("\nEnter Ticket ID to purchase: ");
                String ticketID = scanner.nextLine();
                Ticket selectedTicket = null;

                //menyocokkan id tiket yang diinput oleh user dengan id tiket yang sudah terdaftar
                for (Ticket ticket : listTicket) {
                    if (ticket.getId().equals(ticketID)) {
                        selectedTicket = ticket;
                        break;
                    }
                }

                //cek jika id tiket yang dipilih ada atau tidak
                if (selectedTicket == null) {
                    System.out.println("Ticket not found!");
                    continue;
                }

                System.out.print("Enter quantity to purchase: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                //kalau qty purchase melebihi stock yg tersedia, purchase gagal
                if (quantity > selectedTicket.getStock()) {
                    System.out.println("Not enough stock available!");
                } else {
                    int subTotal = quantity * selectedTicket.getPrice();
                    LocalDate purchaseDate = LocalDate.now();

                    Purchase purchase = new Purchase(username, ticketID, quantity, subTotal, purchaseDate, purchaseDate);
                    listPurchase.add(purchase);

                    selectedTicket.setStock(selectedTicket.getStock() - quantity);
                    System.out.println("Purchase successful! Subtotal: $" + subTotal);
                    System.out.println("Detail: ");
                    System.out.println("Username: " + username + "\nTicket ID: " + ticketID + "\nQuantity: " + quantity + "\nSubtotal: " + subTotal + "\nCreated Date: " + purchaseDate + "\nUpdated Date: " + purchaseDate);
                }
            } 
            else if (action == 3) {
                // Exit the program
                System.out.println("\nExiting... Thank you!");
                break;
            } 
            else {
                System.out.println("Invalid choice! Please select again.");
            }
        }

        scanner.close();
    }
}
