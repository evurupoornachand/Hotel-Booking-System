package com.console;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import com.console.Controller.BookingController;
import com.console.Controller.CustomerController;
import com.console.Controller.RoomController;

public class HotelBookingApp {
    private static RoomController roomController = new RoomController();
    private static CustomerController customerController = new CustomerController();
    private static BookingController bookingController = new BookingController();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        while (true) {
            System.out.println("Hotel Booking System");
            System.out.println("1. Add Room");
            System.out.println("2. Get Room");
            System.out.println("3. Update Room");
            System.out.println("4. Remove Room");
            System.out.println("5. List All Rooms");
            System.out.println("6. List Available Rooms");
            System.out.println("7. Total Number of Rooms");
            System.out.println("8. Add Customer");
            System.out.println("9. Get Customer");
            System.out.println("10. Update Customer");
            System.out.println("11. Remove Customer");
            System.out.println("12. List Customers");
            System.out.println("13. Book Room");
            System.out.println("14. Get Booking");
            System.out.println("15. Update Booking");
            System.out.println("16. Cancel Booking");
            System.out.println("17. List Bookings");
            System.out.println("18. List Bookings by Customer");
            System.out.println("19. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    getRoom();
                    break;
                case 3:
                    updateRoom();
                    break;
                case 4:
                    removeRoom();
                    break;
                case 5:
                    listAllRooms();
                    break;
                case 6:
                    listAvailableRooms();
                    break;
                case 7:
                    getTotalNumberOfRooms();
                    break;
                case 8:
                    addCustomer();
                    break;
                case 9:
                    getCustomer();
                    break;
                case 10:
                    updateCustomer();
                    break;
                case 11:
                    removeCustomer();
                    break;
                case 12:
                    listCustomers();
                    break;
                case 13:
                    bookRoom();
                    break;
                case 14:
                    getBooking();
                    break;
                case 15:
                    updateBooking();
                    break;
                case 16:
                    cancelBooking();
                    break;
                case 17:
                    listBookings();
                    break;
                case 18:
                    listBookingsByCustomer();
                    break;
                case 19:
                    System.out.println("Thankyou for Choosing");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addRoom() throws SQLException {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter room type: ");
        String roomType = scanner.nextLine();
        System.out.print("Enter price per night: ");
        BigDecimal pricePerNight = scanner.nextBigDecimal();
        Room room = new Room(roomNumber, roomType, pricePerNight);
        roomController.addRoom(room);
    }

    private static void getRoom() throws SQLException {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        roomController.getRoom(roomNumber);
    }

    private static void updateRoom() throws SQLException {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter room type: ");
        String roomType = scanner.nextLine();
        System.out.print("Enter price per night: ");
        BigDecimal pricePerNight = scanner.nextBigDecimal();
        Room room = new Room(roomNumber, roomType, pricePerNight);
        roomController.updateRoom(room);
    }

    private static void removeRoom() throws SQLException {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        roomController.removeRoom(roomNumber);
    }

    private static void listAllRooms() throws SQLException {
        roomController.listAllRooms();
    }

    private static void listAvailableRooms() {
        roomController.listAvailableRooms();
    }

    private static void getTotalNumberOfRooms() throws SQLException {
        roomController.getTotalNumberOfRooms();
    }

    private static void addCustomer() throws SQLException {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        Customer customer = new Customer(name, email);
        customerController.addCustomer(customer);
    }

    private static void getCustomer() throws SQLException {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        customerController.getCustomer(customerId);
    }

    private static void updateCustomer() throws SQLException {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        Customer customer = new Customer(customerId, name, email);
        customerController.updateCustomer(customer);
    }

    private static void removeCustomer() throws SQLException {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        customerController.removeCustomer(customerId);
    }

    private static void listCustomers() {
        customerController.listCustomers();
    }

    private static void bookRoom() {
    	System.out.println("Enter Booking ID :");
    	int bookingId = scanner.nextInt();
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        LocalDate checkInDate = LocalDate.parse(scanner.next());
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        LocalDate checkOutDate = LocalDate.parse(scanner.next());
        bookingController.bookRoom(customerId, roomNumber, checkInDate, checkOutDate);
    }

    private static void getBooking() throws SQLException {
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();
        bookingController.getBooking(bookingId);
    }

    private static void updateBooking() throws SQLException {
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        LocalDate checkInDate = LocalDate.parse(scanner.next());
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        LocalDate checkOutDate = LocalDate.parse(scanner.next());
        Booking booking = new Booking(bookingId, customerId, roomNumber, checkInDate, checkOutDate);
        bookingController.updateBooking(booking);
    }

    private static void cancelBooking() {
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();
        bookingController.cancelBooking(bookingId);
    }

    private static void listBookings() {
        bookingController.listBookings();
    }

    private static void listBookingsByCustomer() {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        bookingController.listBookingsByCustomer(customerId);
    }
}
