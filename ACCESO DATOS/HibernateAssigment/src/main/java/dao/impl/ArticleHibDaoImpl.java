package dao.impl;

import dao.ArticleHibDao;
import dao.JPAUtil;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import model.Article;

import java.util.Collections;
import java.util.List;

public class ArticleHibDaoImpl implements ArticleHibDao {
    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public ArticleHibDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public Either<String, List<Article>> getAll() {
        em = jpaUtil.getEntityManager();

        Either<String, List<Article>> response;

        try {
            response = Either.right(em.createNamedQuery("HQL_GET_ALL_ARTICLE", Article.class).getResultList());
        } catch (PersistenceException e) {
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Either<String, List<Article>> delete(int newspaperId) {
        em = jpaUtil.getEntityManager();
        Either<String, List<Article>> response;
        em.getTransaction().begin();
        try {

            em.createNamedQuery("HQL_DELETE")
                    .setParameter("id", newspaperId)
                    .executeUpdate();


            em.getTransaction().commit();
            response = Either.right(Collections.emptyList());
        } catch (Exception e){
            response = Either.left(e.getMessage());
        } finally {
            if (em != null)  em.close();
        }

        return response;
    }
}
