package dao.impl;

import dao.JPAUtil;
import dao.NewspaperHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import model.NewspaperHib;

import java.util.List;

public class NewspaperHibDaoImpl implements NewspaperHibDao {
    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public NewspaperHibDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public Either<String, List<NewspaperHib>> getAll() {
        em = jpaUtil.getEntityManager();

        Either<String, List<NewspaperHib>> response;

        try {
            response = Either.right(em.createNamedQuery("HQL_GET_ALL_NEWSPAPERS", NewspaperHib.class).getResultList());
        } catch (PersistenceException e){
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Either<String, NewspaperHib> get(NewspaperHib newspaperHib){
        em = jpaUtil.getEntityManager();

        Either<String, NewspaperHib> response;

        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            response = Either.right(em.find(NewspaperHib.class, newspaperHib.getId()));
            tx.commit();
        } catch (Exception e){
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        } finally {
            if (em != null)  em.close();
        }

        return response;
    }

    public Either<String, NewspaperHib> add(NewspaperHib newspaper){
        Either<String, NewspaperHib> response;

        em = jpaUtil.getEntityManager();

        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(newspaper);
            tx.commit();
            response = Either.right(newspaper);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        }
        finally {
            if (em != null)  em.close();
        }

        return response;
    }

    public Either<String, NewspaperHib> delete(NewspaperHib newspaper){
        Either<String, NewspaperHib> response;

        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.remove(em.merge(newspaper));
            tx.commit();
            response = Either.right(newspaper);
        } catch (Exception e){
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        } finally {
            if (em != null)  em.close();
        }

        return response;
    }
}
