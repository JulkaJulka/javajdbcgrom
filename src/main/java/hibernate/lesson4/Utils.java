package hibernate.lesson4;

/**
 * Created by user on 12.12.2017.
 */
public class Utils {

    public static String pathRoomDB = "D:/Ubuntu_backup/dev/RoomDB";
    public static String pathHotelDB = "D:/Ubuntu_backup/dev/HotelDB";
    public static String pathOrderDB = "D:/Ubuntu_backup/dev/OrderDB.txt";
    public static String pathUserDB = "D:/Ubuntu_backup/dev/UserDB";

    public static String getPathRoomDB() {
        return pathRoomDB;
    }

    public static String getPathHotelDB() {
        return pathHotelDB;
    }

    public static boolean checkWordOnLetters(String body) {
        char[] chars = body.toCharArray();
        for (char ch : chars) {
            if (!Character.isLetter(ch))
                return false;
        }

        return true;
    }

    public static boolean checkWordOnLetAndDigts(String body) {
        char[] chars = body.toCharArray();
        for (char ch : chars) {
            if (!Character.isLetterOrDigit(ch))
                return false;
        }

        return true;
    }

    public static boolean checkWordOnDigts(String body) {
        char[] chars = body.toCharArray();
        for (char ch : chars) {
            if (!Character.isDigit(ch))
                return false;
        }

        return true;
    }


}

