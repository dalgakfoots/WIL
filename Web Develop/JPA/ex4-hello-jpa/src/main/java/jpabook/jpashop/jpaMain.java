package jpabook.jpashop;

import jpabook.jpashop.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            Book book = new Book();
            book.setName("SuperTraining");
            book.setAuthor("BK");
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        em.close();
        emf.close();

    }

}
