package namesandaddresses;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // tämä ratkaisee N+1-kyselyn ongelman, addressit haetaan heti eikä laiskasti
    // Spring Jpa Eager/Lazy loading
    @EntityGraph(attributePaths = {"address"})
    List<Person> findAll();

}
