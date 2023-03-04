package dao.impl;

import dao.JPAUtil;
import dao.SubscriptionsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import model.NewspaperHib;
import model.ReaderHib;
import model.Subscription;

import java.util.List;

public class SubscriptionsDaoImpl implements SubscriptionsDao {
    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public SubscriptionsDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public Either<String, List<Subscription>> get(int idReader){
        em = jpaUtil.getEntityManager();

        Either<String, List<Subscription>> response;

        try {
            response = Either.right(em.createNamedQuery("HQL_GET_SUBSCRIBE_FROM_A_READER", Subscription.class)
                    .setParameter("idReader", idReader).getResultList());
        } catch (PersistenceException e) {
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }
}
