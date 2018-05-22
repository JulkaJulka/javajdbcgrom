package hibernate.lesson4;

/**
 * Created by user on 12.12.2017.
 */
public class Utils {

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

