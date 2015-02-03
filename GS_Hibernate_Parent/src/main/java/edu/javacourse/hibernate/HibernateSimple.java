package edu.javacourse.hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Простой пример для демонстрации иерархии
 * 
 * @author ASaburov
 */
public class HibernateSimple {

    public static void main(String[] args) {
        HibernateSimple hs = new HibernateSimple();

        {   // Рекурсивное удаление
            Session s = hs.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            
//            List<Catalog> catalogList = s.createQuery("from Catalog where parent=1").list();
//            // Вытащить список каталогов (чтобы SQL не мозолил глаза)
//            for (Catalog catalog : catalogList) {
//                hs.fetchCatalog(catalog);
//            }
//
//            // Просмотреть список каталогов (без SQL симпатичнее)
//            System.out.println();
//            System.out.println("Catalog List");
//            for (Catalog catalog : catalogList) {
//                hs.printCatalog(catalog, 1);
//            }
//            // Удаляем
//            if (catalogList.size() > 1) {
//                System.out.println();
//                System.out.println("DELETE 'second' -->");
//                s.delete(catalogList.get(1));
//            }
//
//            s.getTransaction().commit();
//        }
//        {   // Убеждаемся, что удален верхний "second"
//            Session s = hs.getSessionFactory().getCurrentSession();
//            s.beginTransaction();
//
//            System.out.println();
//            List<Catalog> catalogList = s.createQuery("from Catalog where parent=1").list();
//            // Вытащить список каталогов (чтобы SQL не мозолил глаза)
//            for (Catalog catalog : catalogList) {
//                hs.fetchCatalog(catalog);
//            }
//
//            // Просмотреть список каталогов (без SQL симпатичнее)
//            System.out.println();
//            System.out.println("Catalog List");
//            for (Catalog catalog : catalogList) {
//                hs.printCatalog(catalog, 1);
//            }


            Catalog catalog = (Catalog) s.get(Catalog.class, 1l);
            System.out.println("catalog = " + catalog);
            List<Catalog> childs = catalog.getCatalogList();
            if (childs != null && !childs.isEmpty()){
                printCatalog(catalog, 1);
            }


            System.out.println("===================================================");

            List<Catalog> catalogs = s.createQuery("from Catalog where catalogList.size > 1").list();
            for (Catalog catalog1 : catalogs) {
                System.out.println("catalog = " + catalog1);
            }




            s.getTransaction().commit();
        }

    }

    private SessionFactory getSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    private static void fetchCatalog(Catalog catalog) {
        if (catalog.getCatalogList().size() > 0) {
            for (Catalog c : catalog.getCatalogList()) {
                fetchCatalog(c);
            }
        }
    }
    
    private static void printCatalog(Catalog catalog, int shift) {
        for (int i = 0; i < shift; i++) {
            System.out.print(" ");
        }        
        System.out.println("Catalog:" + catalog);
        if (catalog.getCatalogList().size() > 0) {
            for (Catalog c : catalog.getCatalogList()) {
                printCatalog(c, shift + 1);
            }
        }
    }
}
