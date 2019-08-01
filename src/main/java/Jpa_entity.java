import javax.persistence.*;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Jpa_entity {
    public static void main(String[] args) {
        // Создаем фабрику и менеджер
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = emf.createEntityManager();

        // Открываем транзакцию
        //entityManager.getTransaction().begin();

//        W_dao w1 = new W_dao();

//        entityManager.persist(w1);

        // Транзакция
        //entityManager.getTransaction().commit();

        System.out.println("----\nQuerying using JPQL:");
//      String SQL = "SELECT [vl] FROM [flow3].[dbo].[Vls] WHERE [profileID] = 1851 AND [type] = 20 AND [channel] = 0 AND YEAR([dt])=" + i_year + " AND MONTH([dt])=" + i;
        Query query = entityManager.createQuery("select t.dt, t.vl from W_dao t where t.profileID = 1851 and t.type = 20 and t.vl >= 40");
//        List resultList = query.getResultList();

        List<Object[]> resultList = query.getResultList();
        for (Object[] o : resultList) {
            System.out.println(Arrays.toString(o));
        }
//        Timestamp wd = (Timestamp) resultList.get(0)[0];
//        System.out.println(wd.toLocalDateTime().toLocalDate());

        //Запрос criteria
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<W_dao> query2 = cb.createQuery(W_dao.class);
        Root<W_dao> w_dao_root = query2.from(W_dao.class);


        Predicate p1 = cb.equal(w_dao_root.get("vl"), 41);
        Predicate p2 = cb.equal(w_dao_root.get("profileID"), 1851);
        Predicate p3 = cb.equal(w_dao_root.get("type"), 20);

        Expression<Integer> year = cb.function("year", Integer.class, w_dao_root.get("dt"));
        Predicate p4 = cb.equal(year, 2008);

        Predicate p_f = cb.and(p1,p2,p3,p4);

        query2.select(w_dao_root).where(p_f);

        TypedQuery<W_dao> typedQuery = entityManager.createQuery(query2);
        System.out.println("----\nQuerying using Criteria:");
        typedQuery.getResultList().forEach(System.out::println);

        entityManager.close();
        emf.close();
    }
}
