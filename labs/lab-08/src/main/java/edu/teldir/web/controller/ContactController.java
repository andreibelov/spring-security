package edu.teldir.web.controller;

import edu.teldir.domain.entity.Contact;
import edu.teldir.domain.service.ContactService;
import edu.teldir.web.bean.ContactBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 11.04.12
 */
@Controller
@RequestMapping("/contact/*")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @RequestMapping("/list.do")
    public ModelAndView list() {
        Collection<Contact> contacts = contactService.getAllContacts();

        List<ContactBean> beans = new ArrayList<ContactBean>(contacts.size());
        for (Contact contact : contacts) {
            beans.add(new ContactBean(contact));
        }

        Collections.sort(beans);

        ModelAndView result = new ModelAndView("contact/list");
        result.addObject("contacts", beans);
        return result;
    }


    @RequestMapping("/edit.do")
    public ModelAndView load(@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        Contact contact = new Contact();
        if (id > 0) {
            contact = contactService.getContact(id);
        }

        ModelAndView result = new ModelAndView("contact/edit");
        result.addObject("contact", new ContactBean(contact));
        return result;
    }


    @RequestMapping("/save.do")
    public ModelAndView save(ContactBean contactBean) {
        Contact contact;
        if (contactBean.getId() > 0) {
            contact = contactService.getContact(contactBean.getId());
        } else {
            contact = new Contact();
        }
        contact.setName(contactBean.getName());
        contact.setTelephoneNumber(contactBean.getTelephoneNumber());

        if (contactBean.getId() > 0) {
            contactService.save(contact);
        } else {
            contactService.create(contact);
        }

        return new ModelAndView("redirect:/contact/list.do");
    }


    @RequestMapping("/remove.do")
    public ModelAndView remove(@RequestParam("id") long id) {
        Contact contact = contactService.getContact(id);
        contactService.remove(contact);
        return new ModelAndView("redirect:/contact/list.do");
    }
}
