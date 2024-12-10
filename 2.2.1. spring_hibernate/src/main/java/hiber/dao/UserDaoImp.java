package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(Car car) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User u where u.car.model=:model and u.car.series = :series", User.class);
        query.setParameter("model", car.getModel());
        query.setParameter("series", car.getSeries());
        return query.getResultList().get(0);
    }

    public Car findCarByModelAndSeries(String model, int series) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(
                "from Car car where car.model = :model and car.series = :series", Car.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList().stream().findFirst().orElse(null);
    }

}
