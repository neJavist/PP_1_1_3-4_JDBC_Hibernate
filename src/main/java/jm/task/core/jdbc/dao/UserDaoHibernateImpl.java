package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = new Util().getHibernateConnection();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (" +
                            "id int NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                            "name varchar(255)," +
                            "lastname varchar(255), " +
                            "age int)")
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createSQLQuery("DELETE FROM user WHERE id = " + id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createSQLQuery("DELETE FROM user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
