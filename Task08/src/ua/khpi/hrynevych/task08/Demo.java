package ua.khpi.hrynevych.task08;

import java.sql.SQLException;
import java.util.List;
import ua.khpi.hrynevych.task08.db.DBManager;
//import ua.khpi.hrynevych.task08.db.entity.Group;
import ua.khpi.hrynevych.task08.db.entity.User;

public class Demo {

    private static <T> void printList(List<T> list) {
        for (T element : list) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) throws SQLException {
        // users  ==> [ivanov]; groups ==> [teamA]

        DBManager dbManager = DBManager.getInstance();      

        System.out.println("===========================");

        
        // Part 1

        dbManager.insertUser(User.createUser("petrov"));
        // Warning! User.createUser just create new user instance with given login      

        dbManager.insertUser(User.createUser("obama"));       
        printList(dbManager.findAllUsers());
        // users  ==> [ivanov, petrov, obama]

    }
}

