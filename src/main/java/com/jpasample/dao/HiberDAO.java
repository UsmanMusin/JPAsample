package com.jpasample.dao;
import com.jpasample.model.Cat;
import com.jpasample.model.Department;
import com.jpasample.model.Employee;
import com.jpasample.model.Person;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;


@Repository
public class HiberDAO {
    
    //@Autowired
    //EntityManagerFactory emf;
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    
    private Random r = new Random();
    private String lastStatus;
    
    public HiberDAO() {
    }

    @Transactional
    public Cat addRandomCat() {
        //EntityManager em = emf.createEntityManager();
        
        Cat c = new Cat();
        //em.getTransaction().begin();
        c.setName("Cat"+r.nextInt(100));
        c.setWeight(1.0f+r.nextInt(40)/10.0f);
        em.persist(c);
        //em.getTransaction().commit();
        lastStatus = "Кошка добавлена!";
        return c;
    }

    //@Transactional
    public List<Cat> getAllCats() {
        //EntityManager em = emf.createEntityManager();
        List<Cat> res = em.createQuery("select c from Cat c",Cat.class).getResultList();
        
        return res;
    }

    //@Transactional
    public List<Person> getAllPersons() {
        //EntityManager em = emf.createEntityManager();
        List<Person> res = em.createQuery("select p from Person p",Person.class).getResultList();
        
        return res;
    }

    @Transactional
    public void init() {

        /*Employee e1 = new Employee("Ivanov", "Ivan", "Ivanovich", "Tester");
        em.persist(e1);
        Employee e2 = new Employee("Gavrilov", "Oleg", "Vladimirovich", "Analytic");
        em.persist(e2);
        Employee e3 = new Employee("Utkin", "Vasiliy", "Alekseevich", "Developer");
        em.persist(e3);

        Department d1 = new Department("Filenet", "601");
        em.persist(d1);
        Department d2 = new Department("SPO", "603");
        em.persist(d2);*/

        



        Person p1 = new Person("Ivan");
        em.persist(p1);
        Person p2 = new Person("Andrey");
        em.persist(p2);
        Person p3 = new Person("Olya");
        em.persist(p3);

        Cat c1 = new Cat("Barsik", 5.0f, p1);
        em.persist(c1);
        Cat c2 = new Cat("Muska", 2.0f, p2);
        em.persist(c2);
        Cat c3 = new Cat("Pushok", 0.5f, p3);
        em.persist(c3);

        Cat c4 = new Cat("Felix",1.0f,p1);
        em.persist(c4);
        c1.setOwner(p3);
        p2.getCats().add(c4);
        c4.setOwner(p2);

        lastStatus = "Кошки построены!";
        
        
    }

    /// проблема с многопоточным доступом!
    //@Transactional
    public String pullStatus() {
        return lastStatus;
    }

    //@Transactional
    public Cat getCatById(long id) {
        //EntityManager em = emf.createEntityManager();
        Cat c;
        c = em.find(Cat.class, id);
        return c;
    }

    @Transactional
    public void deleteCat(long id) {
        //EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();
        //em.clear();
        Cat c = em.find(Cat.class, id);
        if(c!=null) {
           /* if (c.getOwner()!=null) {
                c.getOwner().getCats().remove(c);
            }
            c.setOwner(null);*/
            em.remove(c);
        }
        //em.getTransaction().commit();
    }

    @Transactional
    public void deletePerson(long idPerson) {
        //EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();
        Person p;
        //em.createQuery("delete from Person p where p.id=?").setParameter(1,idPerson).executeUpdate();
        p = em.find(Person.class, idPerson);
        if(p != null){
            em.remove(p);
        }
        //em.getTransaction().commit();
    }

    @Transactional
    public void changeOwner(long cid, long pid) {
        //EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();
        Cat c = em.find(Cat.class, cid);
        Person newOwner = em.find(Person.class, pid);
        if(c!=null && newOwner!=null) {
            Person oldowner = c.getOwner();
            c.setOwner(newOwner);
            for (Cat cat: oldowner.getCats()) {
                if(cat.equals(c)){
                    newOwner.getCats().add(c);
                    oldowner.getCats().remove(cat);
                    break;
                }
            }
        }
        //em.getTransaction().commit();
    }
  
}