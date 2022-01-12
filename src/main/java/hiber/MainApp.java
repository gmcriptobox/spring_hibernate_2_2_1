package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("ivan", "ivanov", "ivanov@mail.ru");
      user1.setCar(new Car("audi", 333));

      userService.add(user1);
      User user2 = new User("Petr", "Petrov", "Petrov@mail.ru");;
      user2.setCar(new Car("bmw", 444));
      userService.add(user2);

      List<User> list = userService.getUsersForCar("audi", 333);

      context.close();
   }
}
