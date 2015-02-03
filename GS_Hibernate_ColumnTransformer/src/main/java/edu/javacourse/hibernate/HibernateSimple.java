package edu.javacourse.hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
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
            System.out.println(r.getRegionName());
        }


        Region region = new Region("Test column transformer");
        s.save(region);
        s.getTransaction().commit();
    }

    private SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
