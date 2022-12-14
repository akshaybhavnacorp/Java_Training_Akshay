package com.bhavna.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReferralController {
	@Autowired
	ReferralDao dao;

	@RequestMapping("/referral.html")
	public String display(HttpServletRequest req, Model m) {
		System.out.println("in method");

		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		if (age > 18) {
			String msg = "Hello " + name + " thanks for referral";
			m.addAttribute("message", msg);
			return "successpage";

		} else {
			String msg = name + " IS NOT QUALIFIED AS REFERRAL.";
			m.addAttribute("message", msg);
			return "errorpage";
		}

	}

	@RequestMapping("/index.html")
	public String showform(Model m) {
		m.addAttribute("command", new ReferralBean());
		return "index";
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public String save(@ModelAttribute("ref") ReferralBean ref) {
		dao.save(ref);
		return "viewreferral";
	}

	@RequestMapping("/viewreferral.html")
	public String viewref(Model m) {
		List<ReferralBean> list = dao.getReferral();
		m.addAttribute("list", list);
		return "viewreferral";
	}

}