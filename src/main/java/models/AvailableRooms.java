package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AvailableRooms {

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
        HashMap<Integer, Room> roomMap = parseRoomList(rooms);
        HashMap<Integer, ArrayList<Booking>> bookingMap = parseBookingList(bookings);
        findAvailableTimes(roomMap, bookingMap);
    }

    private void findAvailableTimes(HashMap<Integer, Room> roomMap, HashMap<Integer, ArrayList<Booking>> bookingMap) {
        addAllPublics(roomMap);
        addBookings(bookingMap);

//        for (Integer roomId : roomMap.keySet()) {
//            if (!availableRooms.containsKey(roomId)) availableRooms.put(roomId, new ArrayList<>());
//            ArrayList<Booking> bookingsByRoomId = bookingMap.get(roomId);
//            Room room = roomMap.get(roomId);
//
//            List<PublicDate> datesByRoom = room.getPublicDates();
//            for (PublicDate publicDate : datesByRoom) {
//                Timeslot t = new Timeslot(publicDate.getPublicStartDate(), publicDate.getPublicEndDate());
//                if (bookingsByRoomId == null && room != null)  {
//                    availableRooms.get(roomId).add(t);
//                    continue;
//                } if (room.getPublicDates() == null) continue;
//                for (Booking booking : bookingsByRoomId) {
//                    LocalDateTime s = booking.getStartDate();
//                    if (t.isWithinTimeslot(s)) {
//                        t.setEndDate(s.minusMinutes(1));
//                        availableRooms.get(roomId).add(t);
//                        t.setStartDate(booking.getEndDate().plusMinutes(1));
//                        t.setEndDate(publicDate.getPublicEndDate());
//                    }
//                }
//                availableRooms.get(roomId).add(t);
//            }
//        }
    }

    private void addBookings(HashMap<Integer, ArrayList<Booking>> bookingMap) {
        for (Integer roomId: bookingMap.keySet()) {
            List<Booking> bookingsByRoomRoomId = bookingMap.get(roomId);
            List<Timeslot> availableTimes = availableRooms.get(roomId);
            List<Timeslot> ats = new ArrayList<>();

            for (Timeslot availableTime : availableTimes) {
                for (Booking booking : bookingsByRoomRoomId) {
                    if (availableTime.isWithinTimeslot(booking.getStartDate())) {
                        ats.add(new Timeslot(availableTime.getStartDate(), booking.getStartDate().minusMinutes(1)));
                        availableTime.setStartDate(booking.getEndDate().plusMinutes(1));
                    }
                }
                ats.add(availableTime);
            }

            availableRooms.put(roomId, ats);
        }
    }

    private void addAllPublics(HashMap<Integer, Room> roomMap) {
        for (Integer roomId : roomMap.keySet()) {
            if (!availableRooms.containsKey(roomId)) availableRooms.put(roomId, new ArrayList<>());
            List<PublicDate> pds = roomMap.get(roomId).getPublicDates();
            for (PublicDate pd : pds) {
                Timeslot ts = new Timeslot(pd.getPublicStartDate(), pd.getPublicEndDate());
                availableRooms.get(roomId).add(ts);
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

    private static HashMap<Integer, Room> parseRoomList(List<Room> rooms) {
        HashMap<Integer, Room> map = new HashMap<>();
        for (Room room : rooms) {
            int id = room.getId();
            if (!map.containsKey(id)) map.put(id, room);
        }
        return map;
    }


}
