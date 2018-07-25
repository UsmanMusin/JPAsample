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
    @Transactional
    public List<Organization> getAllOrganizations(){
        // left join fetch o.departmentSet deps
        List<Organization> res = em.createQuery("select o from Organization o",Organization.class).getResultList();
        int s=0;
        for (Organization o : res) {
            s+=o.getDepartmentSet().size();
        }
        return res;
    }

    @Override
    public List<Assignment> getAllAssignments(){
        List<Assignment> res = em.createQuery("select a from Assignment a",Assignment.class).getResultList();
        return res;
    }

    @Override
    public List<Assignment> getMyAssignments(long id){
        List<Assignment> res = em.createQuery(
                "select a from Assignment a where author.id = ?1", Assignment.class)
                .setParameter(1, id)
                .getResultList();
        return res;
    }

    @Override
    public List<Assignment> getForMeAssignments(long id){
        List<Assignment> res = em.createQuery(
                "select a from Assignment a where executor.id = ?1 and execution = false", Assignment.class)
                .setParameter(1, id)
                .getResultList();
        return res;
    }

    @Override
    public List<Assignment> getForMeDoneAssignments(long id){
        List<Assignment> res = em.createQuery(
                "select a from Assignment a where executor.id = ?1 and execution = true", Assignment.class)
                .setParameter(1, id)
                .getResultList();
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
            Set<Department> depset = new HashSet<Department>();
            Set<Employee> empset = new HashSet<>();
            depset.add(d1);
            depset.add(d2);
            empset.add(e1);
            empset.add(e3);
            o1.setDepartmentSet(depset);
            d1.setEmployeeSet(empset);
        }
    }


    @Override
    public long userCheck(String user, String pass){
        List<Employee> res = em.createQuery("select  e from Employee e",Employee.class).getResultList();
        long b = 0;
        for (Employee e: res) {
            if((e.getName().equals(user)) && (e.getPassword().equals(pass))){
                b = e.getId();
                break;
            }
        }
        return b;
    }


    @Override
    @Transactional
    public void removeEmp(Employee employee){
        em.remove(employee);
    }

    @Override
    public Employee getEmpById(long id) {
        Employee emp;
        emp = em.find(Employee.class, id);
        return emp;
    }

    @Transactional
    @Override
    public void updateEmp(Employee employee){
        em.merge(employee);
    }

    @Transactional
    @Override
    public void addEmp(Employee employee){
        em.persist(employee);
    }

    @Transactional
    @Override
    public void addAssign(Assignment assignment){
        em.persist(assignment);
    }

    @Override
    public Assignment getAssignById(long id) {
        Assignment assignment;
        assignment = em.find(Assignment.class, id);
        return assignment;
    }

    @Transactional
    @Override
    public void executeAssignById(long id){
        Assignment assignment = getAssignById(id);
        assignment.setExecution(true);
        em.persist(assignment);
    }











  
}