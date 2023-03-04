package dao.impl;

import dao.JPAUtil;
import dao.ReadersHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import model.ReaderHib;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ReadersHibDaoImpl implements ReadersHibDao {
    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public ReadersHibDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public Either<String, List<ReaderHib>> getAll() {
        em = jpaUtil.getEntityManager();

        Either<String, List<ReaderHib>> response;

        try {
            response = Either.right(em.createNamedQuery("HQL_GET_ALL_READERS", ReaderHib.class).getResultList());
        } catch (PersistenceException e) {
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Either<String, ReaderHib> get(int id) {
        em = jpaUtil.getEntityManager();

        Either<String, ReaderHib> response;

        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            response = Either.right(em.find(ReaderHib.class, id));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Either<String, List<ReaderHib>> getAll(String description) {
        em = jpaUtil.getEntityManager();

        Either<String, List<ReaderHib>> response;

        try {
            response = Either.right(em.createNamedQuery("HQL_GET_ALL_READERS_OF_TYPE", ReaderHib.class)
                    .setParameter("descripcion", description).getResultList());
        } catch (PersistenceException e) {
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Either<String, List<ReaderHib>> getAllByNewspaper(String newspaperName){
        em = jpaUtil.getEntityManager();

        Either<String, List<ReaderHib>> response;

        try {
            response = Either.right(em.createNamedQuery("HQL_GET_ALL_READERS_OF_NEWSPAPER", ReaderHib.class)
                    .setParameter("name", newspaperName)
                    .setParameter("date", Date.valueOf(LocalDate.now()))
                    .getResultList());
        } catch (PersistenceException e) {
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Either<String, ReaderHib> add(ReaderHib reader) {
        Either<String, ReaderHib> response;

        em = jpaUtil.getEntityManager();

        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(reader);
            tx.commit();
            response = Either.right(reader);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Either<String, ReaderHib> delete(ReaderHib reader) {
        Either<String, ReaderHib> response;

        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.remove(em.merge(reader));
            tx.commit();
            response = Either.right(reader);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Either<String, ReaderHib> update(ReaderHib reader) {
        Either<String, ReaderHib> response;

        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(reader);
            tx.commit();
            response = Either.right(reader);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }
}
