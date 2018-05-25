package hibernate.lesson4.controller;

import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;
import hibernate.lesson4.service.RoomService;

import java.util.List;

/**
 * Created by user on 25.05.2018.
 */
public class RoomController {
    private RoomService roomService = new RoomService();

    public RoomService getRoomService() {
        return roomService;
    }

    public List<Room> findRooms(Filter filter) throws Exception{
        return roomService.findRooms(filter);
    }
    public Object addRoom(Room room, User user)throws Exception{
        return roomService.addRoom(room, user);
    }
    public void deleteRoom(Long roomId,User user)throws Exception{
        roomService.deleteRoom(roomId, user);
    }
}
