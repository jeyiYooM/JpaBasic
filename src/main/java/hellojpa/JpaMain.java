package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static  void  main(String[] args){

        EntityManagerFactory emf =  Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {
            Member member = new Member();
            member.setName("memberA");
            em.persist(member);

            // jpql
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .getResultList();

            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

       emf.close();

    }

}
