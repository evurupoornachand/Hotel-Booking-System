package com.console.Controller;

import com.console.DAO.BookingDAO;
import com.console.DAO.CustomerDAO;
import com.console.DAO.RoomDAO;
import com.console.Booking;
import com.console.Customer;
import com.console.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookingController {
    private BookingDAO bookingDAO = new BookingDAO();

    public void bookRoom(int customerId, int roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        Booking booking = new Booking(customerId, roomNumber, checkInDate, checkOutDate);
        bookingDAO.addBooking(booking);
    }

    public void getBooking(int bookingId) throws SQLException {
        Booking booking = bookingDAO.getBooking(bookingId);
        if (booking != null) {
            System.out.println(booking);
        } else {
            System.out.println("Booking not found.");
        }
    }

    public void updateBooking(Booking booking) {
        // Check if customer exists
        Customer customer = CustomerDAO.getCustomer(booking.getCustomerId());
        if (customer == null) {
            System.out.println("Customer with ID " + booking.getCustomerId() + " does not exist. Booking cannot proceed.");
            return;
        }

        // Check if room exists
        Room room = RoomDAO.getRoom(booking.getRoomNumber());
        if (room == null) {
            System.out.println("Room with number " + booking.getRoomNumber() + " does not exist. Booking cannot proceed.");
            return;
        }

        // Proceed with booking update
        bookingDAO.updateBooking(booking);
        System.out.println("Booking updated successfully.");
    }

    // Other methods...

    public void cancelBooking(int bookingId) {
        bookingDAO.removeBooking(bookingId);
    }

    public void listBookings() {
        List<Booking> bookings = bookingDAO.getAllBookings();
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public void listBookingsByCustomer(int customerId) {
        List<Booking> bookings = bookingDAO.getBookingsByCustomer(customerId);
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}
