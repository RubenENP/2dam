package dao.impl;

import dao.ArticleTypeHibDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Article;

import java.util.List;

public class ArticleTypeHibDaoImpl implements ArticleTypeHibDao {
//    private JPAUtil jpaUtil;
//    private EntityManager em;

//    @Inject
//    public ArticleTypeHibDaoImpl() {
//
//    }
//
//    public Either<String, List<Article>> getAll() {
////        em = jpaUtil.getEntityManager();
////
//        Either<String, List<Article>> response;
////
////        try {
////            response = Either.right(em.createNamedQuery("HQL_GET_ALL_ARTICLETYPE", ArticleTypeHib.class).getResultList());
////        } catch (PersistenceException e) {
////            response = Either.left(e.getMessage());
////        } finally {
////            if (em != null) em.close();
////        }
////
//        return response;
//    }
//
//    public Either<String, Article> getReadestArticleType() {
////        em = jpaUtil.getEntityManager();
////
////        Either<String, Article> response;
////
////        try {
////            response = Either.right(em.createNamedQuery("HQL_GET_MOREREAD_TYPES", ArticleTypeHib.class).getResultList().stream().findFirst().orElse(null));
////        } catch (PersistenceException e) {
////            response = Either.left(e.getMessage());
////        } finally {
////            if (em != null) em.close();
////        }
////
////        return response;
//    }
}
