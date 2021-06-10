package springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springmvc.model.Member;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RosterController {

	private List<Member> members = new ArrayList<Member>();

	public RosterController() {
		members.add(new Member("Jan", "Janowski"));
		members.add(new Member("Piotr", "Piotrowski"));
		members.add(new Member("Antoni", "Antowski"));
		members.add(new Member("Kamil", "Kamilski"));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring MVC");
		return "hello";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model) {

		model.addAttribute(members);
		return "list";
	}

	@RequestMapping
	public void member(@RequestParam("id") Integer id, Model model) {
		model.addAttribute(members.get(id));
	}

}