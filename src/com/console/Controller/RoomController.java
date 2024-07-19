package com.console.Controller;

import com.console.DAO.RoomDAO;
import com.console.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomController {
    private RoomDAO roomDAO = new RoomDAO();

    public void addRoom(Room room) throws SQLException {
        roomDAO.addRoom(room);
    }

    public void getRoom(int roomNumber) throws SQLException {
        Room room = roomDAO.getRoom(roomNumber);
        if (room != null) {
            System.out.println(room);
        } else {
            System.out.println("Room not found.");
        }
    }

    public void updateRoom(Room room) throws SQLException {
        roomDAO.updateRoom(room);
    }

    public void removeRoom(int roomNumber) throws SQLException {
        roomDAO.removeRoom(roomNumber);
    }

    public void listAllRooms() throws SQLException {
        List<Room> rooms = roomDAO.getAllRooms();
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void listAvailableRooms() {
        List<Room> rooms = roomDAO.getAvailableRooms();
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void getTotalNumberOfRooms() throws SQLException {
        int totalRooms = roomDAO.getTotalNumberOfRooms();
        System.out.println("Total number of rooms: " + totalRooms);
    }
}
