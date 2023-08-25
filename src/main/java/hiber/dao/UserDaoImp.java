package hiber.dao;

import hiber.model.User;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.TypedQuery;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Deprecated
    @Override
    @SuppressWarnings("unchecked")
    public List<User> findUser(String model, int series) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("from User where car.model like :model and car.series = :series", User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList();
    }

}
