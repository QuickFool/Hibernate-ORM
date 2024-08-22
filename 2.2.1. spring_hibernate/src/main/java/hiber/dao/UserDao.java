package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   static User getUserByCar(String model, int series) {
      return null;
   }

   void add(User user);
   void add(Car car);
   List<User> listUsers();
}
