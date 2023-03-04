package dao.impl;

import dao.JPAUtil;
import dao.NewspaperHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Tuple;
import model.NewspaperHib;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        } catch (PersistenceException e) {
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Either<String, NewspaperHib> get(NewspaperHib newspaperHib) {
        em = jpaUtil.getEntityManager();

        Either<String, NewspaperHib> response;

        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            response = Either.right(em.find(NewspaperHib.class, newspaperHib.getId()));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Either<String, NewspaperHib> add(NewspaperHib newspaper) {
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
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public int delete(NewspaperHib newspaper) {
        int response;

        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.remove(em.merge(newspaper));
            tx.commit();
            response = 0;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            response = e.hashCode();
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Map<String, Integer> getNbrArticles(int newspaperId) {
        em= jpaUtil.getEntityManager();
        return em.createQuery("select a.type.description as name, count(a.type) as total from Article a WHERE a.newspaperId = :id group by a.type"
                        , Tuple.class)
                .setParameter("id", newspaperId)
                .getResultList()
                .stream()
                .collect(
                        Collectors.toMap(
                                tuple -> (tuple.get("name")).toString(),
                                tuple -> (Integer.parseInt(tuple.get("total").toString())
                                )
                        ));
    }
}
