package namesandaddresses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CountService {

    @Autowired
    private HttpSession session;

    public int incrementAndCount() {
        int count = 0;
        if (session.getAttribute("count") != null) {
            count = (int) session.getAttribute("count");
        }

        count++;
        session.setAttribute("count", count);
        return count;
    }
}