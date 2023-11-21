package namesandaddresses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PersonService personService;

    @Autowired
    private CountService countService;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("persons", this.personRepository.findAll());
        model.addAttribute("visits", countService.incrementAndCount());
        return "index";
    }

    @GetMapping("/sql")
    public String listSql(Model model) {
        List<Person> persons = personService.findAllPersons();
        model.addAttribute("persons", persons);
        return "index";
    }

}
