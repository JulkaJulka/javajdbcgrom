package hibernate.lesson4.repository;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static hibernate.lesson4.Utils.*;

/**
 * Created by user on 30.11.2017.
 */
public class OrderRepository extends GeneralRepository {

    public static final String FIND_OR_BY_ID_OR = "FROM Order WHERE ID = :ID ";
    public static final String DELETE_OR_BY_ID_OR = "DELETE FROM Order WHERE ID = :ID ";
}
