package edu.javacourse.hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Простой пример для конфигурации в виде XML
 * 
 * @author ASaburov
 */
public class HibernateSimple {

    public static void main(String[] args) {
        HibernateSimple hs = new HibernateSimple();

        Session s = hs.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        
        List<Region> regionList = s.createQuery("from Region").list();
        for(Region r : regionList) {
            System.out.println(r);
        }

        System.out.println();
        System.out.println("Filter for RegionId applied:");
        s.enableFilter("filterRegionId").setParameter("minId", 7);

        regionList = s.createQuery("from Region").list();
        for(Region r : regionList) {
            System.out.println(r);
        }

        System.out.println();
        System.out.println("Formula demo:");
        s.disableFilter("filterRegionId");


        regionList = s.createQuery("from Region").list();
        for(Region r : regionList) {
            System.out.println(r);
            System.out.println("Cities count:" + r.getCitiesCount());
            System.out.println("Full Name :" + r.getFullName());
            System.out.println("Even - Odd :" + (r.isEven() ? "Even" : "Odd")) ;
            System.out.println("=====================") ;
        }
//
        s.getTransaction().commit();
    }

    private SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
