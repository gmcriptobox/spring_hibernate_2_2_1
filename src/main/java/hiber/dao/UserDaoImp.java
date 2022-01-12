package hiber.dao;

import hiber.model.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {sessionFactory.getCurrentSession().save(user);}


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {return sessionFactory.getCurrentSession().createQuery("from User").getResultList();}


   @Override
   public List<User> getUsersForCar(String model, int series) {
      return sessionFactory.getCurrentSession()
              .createQuery("from User u where u.car.model = :m and u.car.series = :s")
              .setParameter("m", model).setParameter("s", series)
              .getResultList();
//      List<User> list = null;
//      Transaction transaction = null;
//      try(Session session = sessionFactory.getCurrentSession()){
//         transaction = session.beginTransaction();
//         list = session.createQuery("from User u where u.car.model = :m and u.car.series = :s")
//               .setParameter("m", model).setParameter("s", series)
//               .getResultList();
//         transaction.commit();
//      }catch(HibernateException e){
//         if(transaction != null){
//            transaction.rollback();
//         }
//      }
//      return list;
   }

}
