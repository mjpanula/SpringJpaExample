package namesandaddresses;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class PersonService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findAllPersons() {
        String jpql = "SELECT p FROM Person p JOIN FETCH p.address";
        TypedQuery<Person> query = entityManager.createQuery(jpql, Person.class);
        return query.getResultList();
    }
}