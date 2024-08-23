package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private final UserDao userDao;
   private final SessionFactory sessionFactory;


   public UserServiceImp(UserDao userDao, SessionFactory sessionFactory) {
      this.userDao = userDao;
      this.sessionFactory = sessionFactory;
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public List<Car> listCar() {
      return userDao.listCar();
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series AND u.id = (SELECT MIN(u2.id) FROM User u2 WHERE u2.car.model = :model AND u2.car.series = :series)", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getSingleResult();
   }
}
