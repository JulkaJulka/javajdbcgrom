package lesson1and2.hw1;

import java.sql.SQLException;

/**
 * Created by user on 01.03.2018.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        JDBCFirstStep jdbcFirstStep = new JDBCFirstStep();
        System.out.println(jdbcFirstStep.jdbcStart().toString());
    }
}
