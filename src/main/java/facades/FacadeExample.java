package facades;

import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}

    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getPersonCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long PersonCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return PersonCount;
        }finally{  
            em.close();
        }
    }

    public List<Person> getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> tq = em.createQuery("SELECT p FROM Person p", Person.class);
            List<Person> persons = tq.getResultList();
            return persons;
        } finally {
            em.close();
        }
    }

}
