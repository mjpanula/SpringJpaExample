package namesandaddresses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Ei päästetä käyttäjää mihinkään sovelluksen resurssiin ilman
        // kirjautumista. Tarjotaan kuitenkin lomake kirjautumiseen, mihin
        // pääsee vapaasti. Tämän lisäksi uloskirjautumiseen tarjotaan
        // mahdollisuus kaikille.
        http.authorizeRequests()
                .anyRequest().authenticated().and()// käyttäjä tulee tunnistaa jokaisen pyynnön yhteydessä
                .formLogin().permitAll().and() // kirjautumiseen käytettyyn lomakkeeseen on kaikilla pääsy
                .logout().permitAll(); // uloskirjautumistoiminnallisuus on kaikille sallittu
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // withdefaultpasswordencoder on deprekoitu mutta toimii yhä
        UserDetails user = User.withDefaultPasswordEncoder()
                               .username("hei")
                               .password("maailma")
                               .authorities("USER")
                               .build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user);
        return manager;
    }
}