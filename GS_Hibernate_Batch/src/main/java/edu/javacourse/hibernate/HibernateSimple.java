package edu.javacourse.hibernate;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Простой пример для interceptor
 * 
 * @author ASaburov
 */
public class HibernateSimple {

    private static int loads = 0;

    public static void main(String[] args) {
        HibernateSimple hs = new HibernateSimple();

        Session s = hs.getSessionFactory().openSession();
        s.beginTransaction();


        for (int i = 0; i < 5000; i++) {

            Region r = new Region();
            r.setRegionName("region " + i);
            s.save(r);


            if (i % 20 == 0) {
                s.flush();
                s.clear();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        s.getTransaction().commit();



        // batch update
//        s = hs.getSessionFactory().getCurrentSession();
//        s.beginTransaction();
//
//        ScrollableResults regionList = s.createQuery("from Region r where r.regionId > 38")
//                .setCacheMode(CacheMode.IGNORE)
//                .scroll(ScrollMode.FORWARD_ONLY);
//        int count = 0;
//        while (regionList.next()) {
//            Region region = (Region) regionList.get(0);
//            region.setRegionName(region.getRegionName() + System.currentTimeMillis());
//            if (++count % 20 == 0) {
//                s.flush();
//                s.clear();
//            }
//        }
//        s.getTransaction().commit();
    }

    private SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
