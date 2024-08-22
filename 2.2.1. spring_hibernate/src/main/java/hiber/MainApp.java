package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
public static void main(String[] args) {

   AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);

   UserService userService = context.getBean(UserService.class);

   addUsersWOCars(userService);
   getUsersWOCars(userService);

   User user5 = new User("User5", "Lastname4", "user4@mail.ru");
    user5.setCar(new Car("BMW", 555));
    userService.add(user5);

   getUsersWCars(userService);
    context.close();
}

   private static void addUsersWOCars(UserService userService) {
      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
   }

   private static void getUsersWOCars(UserService userService) {
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }
   }

   private static void getUsersWCars(UserService userService) {
      List<Car> cars = userService.listCar();
      for (Car car : cars) {
         System.out.println("Id = " + car.getUser().getId());
         System.out.println("First Name = " + car.getUser().getFirstName());
         System.out.println("Last Name = " + car.getUser().getLastName());
         System.out.println("Email = " + car.getUser().getEmail());
         System.out.println("Car model = " + car.getModel());
         System.out.println("Car series = " + car.getSeries());
         System.out.println();
      }

   }
}
