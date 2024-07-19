package com.console.DAO;

import com.console.DatabaseConnection;
import com.console.Room;
import com.console.Customer;
import com.console.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final RoomDAO roomDAO = new RoomDAO();

    public void addBooking(Booking booking) {
        Customer customer = CustomerDAO.getCustomer(booking.getCustomerId());
        if (customer == null) {
            System.out.println("Customer with ID " + booking.getCustomerId() + " does not exist. Booking cannot be added.");
            return;
        }

        Room room = RoomDAO.getRoom(booking.getRoomNumber());
        if (room == null) {
            System.out.println("Room with number " + booking.getRoomNumber() + " does not exist. Booking cannot be added.");
            return;
        }

        String query = "INSERT INTO bookings (customer_id, room_number, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, booking.getCustomerId());
            stmt.setInt(2, booking.getRoomNumber());
            stmt.setDate(3, Date.valueOf(booking.getCheckInDate()));
            stmt.setDate(4, Date.valueOf(booking.getCheckOutDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Booking getBooking(int bookingId) {
        String query = "SELECT * FROM bookings WHERE booking_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("customer_id");
                int roomNumber = rs.getInt("room_number");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                return new Booking(bookingId, customerId, roomNumber, checkInDate.toLocalDate(), checkOutDate.toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBooking(Booking booking) {
        Customer customer = customerDAO.getCustomer(booking.getCustomerId());
        if (customer == null) {
            System.out.println("Customer with ID " + booking.getCustomerId() + " does not exist. Booking update cannot proceed.");
            return;
        }
        Room room = roomDAO.getRoom(booking.getRoomNumber());
        if (room == null) {
            System.out.println("Room with number " + booking.getRoomNumber() + " does not exist. Booking update cannot proceed.");
            return;
        }

        String query = "UPDATE bookings SET customer_id = ?, room_number = ?, check_in_date = ?, check_out_date = ? WHERE booking_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, booking.getCustomerId());
            stmt.setInt(2, booking.getRoomNumber());
            stmt.setDate(3, Date.valueOf(booking.getCheckInDate()));
            stmt.setDate(4, Date.valueOf(booking.getCheckOutDate()));
            stmt.setInt(5, booking.getBookingId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeBooking(int bookingId) {
        String query = "DELETE FROM bookings WHERE booking_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int bookingId = rs.getInt("booking_id");
                int customerId = rs.getInt("customer_id");
                int roomNumber = rs.getInt("room_number");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                Booking booking = new Booking(bookingId, customerId, roomNumber, checkInDate.toLocalDate(), checkOutDate.toLocalDate());
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public List<Booking> getBookingsByCustomer(int customerId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE customer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int bookingId = rs.getInt("booking_id");
                int roomNumber = rs.getInt("room_number");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                Booking booking = new Booking(bookingId, customerId, roomNumber, checkInDate.toLocalDate(), checkOutDate.toLocalDate());
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
