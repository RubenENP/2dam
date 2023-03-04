package dao.impl;

import dao.ArticleTypeHibDao;
import dao.JPAUtil;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import model.ArticleTypeHib;

import java.util.List;

public class ArticleTypeHibDaoImpl implements ArticleTypeHibDao {
    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public ArticleTypeHibDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public Either<String, List<ArticleTypeHib>> getAll() {
        em = jpaUtil.getEntityManager();

        Either<String, List<ArticleTypeHib>> response;

        try {
            response = Either.right(em.createNamedQuery("HQL_GET_ALL_ARTICLETYPE", ArticleTypeHib.class).getResultList());
        } catch (PersistenceException e){
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }
}
