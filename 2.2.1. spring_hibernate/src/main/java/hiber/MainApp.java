package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        Car car1 = new Car("model1", 1);
        Car car2 = new Car("model2", 2);
        Car car3 = new Car("model3", 3);
        Car car4 = new Car("model4", 4);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        carService.addCar(car1);
        carService.addCar(car2);
        carService.addCar(car3);
        carService.addCar(car4);

        List<Car> cars = carService.getListCars();
        cars.stream().forEach(car -> System.out.println(car));

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);

        List<User> users = userService.getListUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println(userService.getUserByCarModelAndSeries(car1.getModel(), car1.getSeries()));
        System.out.println(userService.getUserByCarModelAndSeries(car2.getModel(), car2.getSeries()));
        System.out.println(userService.getUserByCarModelAndSeries(car3.getModel(), car3.getSeries()));
        System.out.println(userService.getUserByCarModelAndSeries(car4.getModel(), car4.getSeries()));

        context.close();
    }
}
