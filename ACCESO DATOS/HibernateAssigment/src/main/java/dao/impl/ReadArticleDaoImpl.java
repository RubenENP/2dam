package dao.impl;

import dao.JPAUtil;
import dao.ReadArticleDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Tuple;
import model.NewspaperHib;
import model.ReadArticle;

import java.util.Map;
import java.util.stream.Collectors;

public class ReadArticleDaoImpl implements ReadArticleDao {
    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public ReadArticleDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    public Either<String, ReadArticle> add(ReadArticle readArticle) {
        Either<String, ReadArticle> response;

        em = jpaUtil.getEntityManager();

        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(readArticle);
            tx.commit();
            response = Either.right(readArticle);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            response = Either.left(e.getMessage());
        } finally {
            if (em != null) em.close();
        }

        return response;
    }

    public Map<Double, String> getAvgRating(int reader) {
        em = jpaUtil.getEntityManager();

        //Get the average rating of the articles read by a reader, group by newspaper
        return em.createQuery("select avg(rA.ranking) as avg, rA.article.idNewspapers.articleList as desc from ReadArticle rA where rA.reader.id = :id group by rA.article.idNewspapers"
                        , Tuple.class)
                .setParameter("id", reader)
                .getResultList()
                .stream()
                .collect(
                        Collectors.toMap(
                                tuple -> (Double.parseDouble(tuple.get("avg").toString())),
                                tuple -> (tuple.get("desc").toString())
                        )
                );
    }
}
