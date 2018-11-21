package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AvailableRooms<T> {

    private HashMap<Integer, List<Timeslot>> availableRooms;

    public AvailableRooms() {
    }

    public HashMap<Integer, List<Timeslot>> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(HashMap<Integer, List<Timeslot>> availableRooms) {
        this.availableRooms = availableRooms;
    }

    public void generateAllAvailableRooms(List<Room> rooms, List<Booking> bookings) {
        availableRooms = new HashMap<>();
        HashMap<Integer, ArrayList<Room>> roomMap = parseRoomList(rooms);
        HashMap<Integer, ArrayList<Booking>> bookingMap = parseBookingList(bookings);
        findAvailableTimes(roomMap, bookingMap);
    }

    private void findAvailableTimes(HashMap<Integer, ArrayList<Room>> roomMap, HashMap<Integer, ArrayList<Booking>> bookingMap) {
        for (Integer roomId : roomMap.keySet()) {
            if (!availableRooms.containsKey(roomId)) availableRooms.put(roomId, new ArrayList<>());
            ArrayList<Booking> bookingsByRoomId = bookingMap.get(roomId);
            ArrayList<Room> roomsByRoomId = roomMap.get(roomId);


            for (Room room : roomsByRoomId) {
                List<PublicDate> datesByRoom = room.getPublicDates();
                for (PublicDate publicDate : datesByRoom) {
                    Timeslot t = new Timeslot(publicDate.getPublicStartDate(), publicDate.getPublicEndDate());
                    if (bookingsByRoomId == null && roomsByRoomId != null)  {
                        availableRooms.get(roomId).add(t);
                        continue;
                    } else if (roomsByRoomId == null) {
                        continue;
                    }
                    boolean free = true;
                    for (Booking booking : bookingsByRoomId) {
                        LocalDateTime s = booking.getStartDate();
                        try {
                            if (t.isWithinTimeslot(s)) {
                                t.setEndDate(s.minusMinutes(1));
                                availableRooms.get(roomId).add(t);
                                t.setStartDate(booking.getEndDate().plusMinutes(1));
                                t.setEndDate(publicDate.getPublicEndDate());
                                free = false;
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                    if (free) availableRooms.get(roomId).add(t);
                }

            }
        }
    }

    private HashMap<Integer, ArrayList<Booking>> parseBookingList(List<Booking> bookings) {
        HashMap<Integer, ArrayList<Booking>> map = new HashMap<>();
        for (Booking booking : bookings) {
            int id = booking.getRoomId();
            if (!map.containsKey(id)) map.put(id, new ArrayList<>());

            map.get(id).add(booking);
        }

        return map;
    }

    private static HashMap<Integer, ArrayList<Room>> parseRoomList(List<Room> rooms) {
        HashMap<Integer, ArrayList<Room>> map = new HashMap<>();
        for (Room room : rooms) {
            int id = room.getId();
            if (!map.containsKey(id)) map.put(id, new ArrayList<>());

            map.get(id).add(room);
        }

        return map;
    }


}
