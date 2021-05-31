package springmvc.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springmvc.model.Member;

@Controller
public final class RosterController {

    private List<Member> members = new ArrayList<Member>();

    public RosterController() {
        // tymczasowo model jest na stałe wypełniany danymi, docelowo z DAO
        members.add(new Member("Jan", "Janowski"));
        members.add(new Member("Piotr", "Piotrowski"));
        members.add(new Member("Antoni", "Antowski"));
        members.add(new Member("Kamil", "Kamilski"));
    }
    @RequestMapping
    public void list(Model model) {
        model.addAttribute(members);

        //W JSP można będzie je pobrać za pomocą wyrażenia ${memberList}
    }

    @RequestMapping
    public void member(@RequestParam("id") Integer id, Model model) {
        model.addAttribute(members.get(id));
    }
}
