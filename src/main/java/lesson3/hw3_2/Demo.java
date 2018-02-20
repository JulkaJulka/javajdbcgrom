package lesson3.hw3_2;

import java.sql.SQLException;

/**
 *
 * Created by user on 29.01.2018.
 */
public class Demo {
    public static void main(String[] args) throws SQLException {
        Solution solution = new Solution();
       //System.out.println(solution.testSavePerformance());
      //  System.out.println(solution.randomString().toString());
      //System.out.println(solution.testSelectByIdPerformance2());
      //  System.out.println(solution.testSelectPerformance());
       // System.out.println(solution.testDeletePerformance());
        System.out.println(solution.testDeleteByIdPerformance());

    }

}
