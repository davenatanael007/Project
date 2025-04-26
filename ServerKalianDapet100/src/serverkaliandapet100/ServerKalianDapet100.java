/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serverkaliandapet100;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import kaliandapet100.model.Ticket;
import kaliandapet100.model.User;

/**
 *
 * @author Rog
 */
public class ServerKalianDapet100 {

    private static ArrayList<Ticket> tickets = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();

    static {
        // Sample ticket entries
        tickets.add(new Ticket("A111", "Bruno Mars Live", "Live in Jakarta", 1000000, 200, "Sung Jin Woo", LocalDate.now(), LocalDate.now()));
        tickets.add(new Ticket("B222", "Taeyeon: The TENSE", "Live at GBK", 2850000, 150, "Kim Suho", LocalDate.now(), LocalDate.now()));
        tickets.add(new Ticket("C333", "IVE Live", "Live in Surabaya", 3000000, 300, "Kim Dokja", LocalDate.now(), LocalDate.now()));
        tickets.add(new Ticket("D444", "HIVI Live", "Live at Yogyakarta", 850000, 250, "Jin Mu Won", LocalDate.now(), LocalDate.now()));
        tickets.add(new Ticket("E555", "James Arthur Live", "Live in Melbourne", 2000000, 200, "Seo Mu Sang", LocalDate.now(), LocalDate.now()));
    }

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(5000);
            System.out.println("Server running, port : 5000");

            while (true) {
                Socket incoming = s.accept();
                System.out.println("Client connected : " + incoming.getInetAddress());

                new ClientHandler(incoming).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {

        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        private boolean isUserExist(String username, String password) {
            return users.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
        }

        private boolean isUsernameTaken(String username) {
            return users.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
        }

        @Override
        public void run() {
            try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream()); ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

                String requestType = (String) input.readObject();

                switch (requestType) {
                    case "LOGIN": {
                        String username = (String) input.readObject();
                        String password = (String) input.readObject();
                        if (isUserExist(username, password)) {
                            output.writeObject("SUCCESS: Login successful! Welcome, " + username);
                        } else {
                            output.writeObject("ERROR: Invalid username or password.");
                        }
                        break;
                    }

                    case "REGISTER": {
                        User newUser = (User) input.readObject();
                        if (isUsernameTaken(newUser.getUsername())) {
                            output.writeObject("ERROR: The user is already registered in the system.");
                        } else {
                            newUser.setMember_since(LocalDate.now());
                            users.add(newUser);
                            output.writeObject("SUCCESS: Registration successful.");
                        }
                        break;
                    }

                    case "ADD_TICKET": {
                        Ticket newTicket = (Ticket) input.readObject();
                        tickets.add(newTicket);
                        output.writeObject("SUCCESS: Ticket saved.");
                        break;
                    }

                    case "GET_TICKETS": {
                        output.writeObject(tickets);
                        break;
                    }

                    case "SEARCH_TICKETS": {
                        String keyword = ((String) input.readObject()).toLowerCase();
                        ArrayList<Ticket> filteredTickets = new ArrayList<>();

                        for (Ticket ticket : tickets) {
                            if (ticket.getTitle().toLowerCase().contains(keyword)
                                    || ticket.getDesc().toLowerCase().contains(keyword)) {
                                filteredTickets.add(ticket);
                            }
                        }

                        output.writeObject(filteredTickets);
                        break;
                    }

                    case "COUNT_SUBTOTAL": {
                        String ticketID = (String) input.readObject();
                        int quantity = (int) input.readObject();

                        boolean found = false;
                        for (Ticket ticket : tickets) {
                            if (ticket.getId().equals(ticketID)) {
                                found = true;
                                int subtotal = ticket.getPrice() * quantity;
                                if (quantity % 3 == 0) {
                                    subtotal = (int) (subtotal * 0.95);
                                }
                                output.writeObject(subtotal); // Kirim subtotal ke client
                                break;
                            }
                        }
                        if (!found) {
                            output.writeObject(-1); // Kirim error code
                        }
                        break;
                    }

                    case "CHECKOUT_TICKET": {
                        String ticketID = (String) input.readObject();
                        int quantity = (int) input.readObject();

                        boolean found = false;
                        for (Ticket ticket : tickets) {
                            if (ticket.getId().equals(ticketID)) {
                                found = true;
                                if (ticket.getStock() >= quantity) {
                                    ticket.setStock(ticket.getStock() - quantity);
                                    output.writeObject(ticket.getStock()); // Kirim stock terbaru
                                } else {
                                    output.writeObject(-1); // Kalau stok tidak cukup
                                }
                                break;
                            }
                        }
                        if (!found) {
                            output.writeObject(-2); // Kalau tiket tidak ditemukan
                        }
                        break;
                    }

                    default: {
                        output.writeObject("ERROR: Invalid request type.");
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}
