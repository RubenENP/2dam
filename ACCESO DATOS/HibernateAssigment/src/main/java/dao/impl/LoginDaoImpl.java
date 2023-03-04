package dao.impl;

import dao.JPAUtil;
import dao.LoginDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Login;

public class LoginDaoImpl implements LoginDao {
    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public LoginDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }
    public Either<String, Login> get(String userName){
        em = jpaUtil.getEntityManager();

        Either<String, Login> response;

        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            response = Either.right(em.find(Login.class, userName));
            tx.commit();
        } catch (Exception e){
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        } finally {
            if (em != null)  em.close();
        }

        return response;
    }

    public Either<String, Login> add(Login login){
        Either<String, Login> response;

        em = jpaUtil.getEntityManager();

        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(login);
            tx.commit();
            response = Either.right(login);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        }
        finally {
            if (em != null)  em.close();
        }

        return response;
    }

    public Either<String, Login> delete(Login login){
        Either<String, Login> response;

        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.remove(em.merge(login));
            tx.commit();
            response = Either.right(login);
        } catch (Exception e){
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        } finally {
            if (em != null)  em.close();
        }

        return response;
    }
}
