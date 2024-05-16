package hiber.dao;

import com.mysql.cj.Query;
import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCar(Car Car) {
        sessionFactory.getCurrentSession().save(Car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getListCars() {
        List<Car> listCars = sessionFactory.getCurrentSession().createQuery("from Car").getResultList();
        return listCars;
    }
}
