package edu.javacourse.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author ASaburov
 */
public class HibernateSimple {

    public static void main(String[] args) {
        HibernateSimple hs = new HibernateSimple();

        //testCompositeKey(hs);
        //testGetByCompositeKey(hs);

        testEmbeddable(hs);



    }

    private static void testEmbeddable(HibernateSimple hs) {
        Session s = hs.getSessionFactory().openSession();
        s.beginTransaction();

        User withAddress = new User();
        withAddress.setMiddleName("Ivanovich");
        withAddress.setUserId(new UserId("Igor", "Petrov"));

        Address address = new Address();
        address.setStreet("Lenina");
        address.setHouse(45);

        withAddress.setAddress(address);
        s.save(withAddress);


        List<User> userList = s.createQuery("from User").list();
        for (User user : userList) {
            System.out.println("Address:" + user.getAddress());
        }


    }

    private static void testCompositeKey(HibernateSimple hs) {
        Session s = hs.getSessionFactory().openSession();
        s.beginTransaction();

        List<User> userList = s.createQuery("from User").list();
        for (User user : userList) {
            System.out.println("User:" + user);
        }

        User newUser = new User();
        newUser.setMiddleName("Nikolaevich");
        newUser.setUserId(new UserId("Ivan", "Petrov"));
        s.save(newUser);


        System.out.println();
        userList = s.createQuery("from User").list();
        for (User user : userList) {
            System.out.println("User:" + user);
        }
        s.getTransaction().commit();
        s.close();
    }

    private static void testGetByCompositeKey(HibernateSimple hs) {
        Session s = hs.getSessionFactory().openSession();
        s.beginTransaction();

        User user = (User) s.get(User.class, new UserId("Ivan", "Petrov"));
        System.out.println("User:" + user);

        s.getTransaction().commit();
        s.close();
    }


    private SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
