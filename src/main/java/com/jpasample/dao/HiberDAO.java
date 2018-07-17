package com.jpasample.dao;
import com.jpasample.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
public class HiberDAO implements DAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public HiberDAO() {
    }

    @Override
    public List<Employee> getAllEmployees(){
        List<Employee> res = em.createQuery("select  e from Employee e",Employee.class).getResultList();
        return res;
    }

    @Override
    public List<Department> getAllDepartments(){
        List<Department> res = em.createQuery("select  d from Department d",Department.class).getResultList();
        return res;
    }

    @Override
    public List<Organization> getAllOrganizations(){
        List<Organization> res = em.createQuery("select o from Organization o",Organization.class).getResultList();
        return res;
    }

    @Override
    @Transactional
    public void init(){
        List<Organization> res = em.createQuery("select o from Organization o",Organization.class).getResultList();
        if(res.isEmpty()) {
            Employee e1 = new Employee("Ivanov", "Ivan", "Ivanovich", "Tester", "123");
            em.persist(e1);
            Employee e2 = new Employee("Gavrilov", "Oleg", "Vladimirovich", "Analytic", "123");
            em.persist(e2);
            Employee e3 = new Employee("Utkin", "Vasiliy", "Alekseevich", "Developer", "123");
            em.persist(e3);
            Employee e4 = new Employee("Petuhov", "Alexander", "Sergeevich", "System Administrator", "123");
            em.persist(e4);

            Department d1 = new Department("Filenet", "601");
            em.persist(d1);
            Department d2 = new Department("SPO", "603");
            em.persist(d2);

            Organization o1 = new Organization("Datatech", "Ufa", "Ufa");
            em.persist(o1);
            o1.setManager(e1);
            Set<Department> depset = new HashSet<Department>();
            depset.add(d1);
            depset.add(d2);
            o1.setDepartmentSet(depset);
//         o1.getDepartmentSet().add(d1);
            //o1.getDepartmentSet().add(d2);
            d1.setManager(e1);
            //d1.addEmployee(e2);
            d2.setManager(e3);
            //d2.addEmployee(e4);
            e1.setDepartment(d1);
        }
    }


    @Override
    public boolean userCheck(String user, String pass){
        //List<Employee> res = em.createQuery("FROM Employee E WHERE E.name = " + user,Employee.class).getResultList();
        List<Employee> res = em.createQuery("select  e from Employee e",Employee.class).getResultList();
        boolean b = false;
        for (Employee e: res) {
            if((e.getName().equals(user)) && (e.getPassword().equals(pass))){
                b = true;
                break;
            }
        }
        return b;
    }

    @Override
    @Transactional
    public void deleteEmp(long id) {
        Employee emp;
        emp = em.find(Employee.class, id);
        if(emp != null){
            em.remove(emp);
        }
    }






  
}