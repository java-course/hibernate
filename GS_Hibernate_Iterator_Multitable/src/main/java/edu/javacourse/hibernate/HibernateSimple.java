package edu.javacourse.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Простой пример работы с итератором
 * 
 * @author ASaburov
 */
public class HibernateSimple {

    public static void main(String[] args) {
        HibernateSimple hs = new HibernateSimple();
        SessionFactory sessionFactory = hs.getSessionFactory();

//        If instances are already in the session (primary-level cache) or second-level cache iterate() will give better performance.
//        If they are not already cached, iterate() will be slower than list() and might require many database hits for a simple query.

       //testIterator(sessionFactory);
        testList(sessionFactory);

        //insertSecondaryTable(sessionFactory);



        

    }

    public static void insertSecondaryTable(SessionFactory sessionFactory) {
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();

        List<Region> list = s.createQuery("from Region").list();
        for (Region region : list) {
            System.out.println("region = " + region);
        }

        //insert
        Region pskov = new Region("Pskov");
        pskov.setRegionLeader("Turchak");
        s.save(pskov);

        s.getTransaction().commit();
    }

    public static void testList(SessionFactory sessionFactory) {
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();

        // read list
        List<City> list = s.createQuery("from City").list();
        for (City city : list) {
            System.out.println("city = " + city);
        }

        System.out.println("====================================");
        // make query again
         list = s.createQuery("from City").list();
        for (City city : list) {
            System.out.println("city = " + city);
        }
        s.getTransaction().commit();
    }


    public static void testIterator(SessionFactory sessionFactory) {
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();

        // read iterator, makes multiple requests
        Iterator<City> regionIt = s.createQuery("from City").iterate();
        while(regionIt.hasNext()) {
            City city = regionIt.next(); // get by id
            System.out.println("City iterator:" + city);
        }
        System.out.println("====================================");

        // takes from cache
        regionIt = s.createQuery("from City").iterate();
        while(regionIt.hasNext()) {
            City city = regionIt.next(); // get by id
            System.out.println("City iterator:" + city);
        }


        s.getTransaction().commit();
    }

    private SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
