package hiber.dao;

import hiber.model.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private EntityManagerFactory managerFactory;

   @Override
   public void add(User user) {managerFactory.createEntityManager().persist(user);}


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {return managerFactory.createEntityManager().createQuery("SELECT u FROM User u").getResultList();}


   @Override
   public List<User> getUsersForCar(String model, int series) {
      return managerFactory.createEntityManager()
              .createQuery("SELECT u FROM User u where u.car.model = :m and u.car.series = :s")
              .setParameter("m", model).setParameter("s", series)
              .getResultList();
   }

}
