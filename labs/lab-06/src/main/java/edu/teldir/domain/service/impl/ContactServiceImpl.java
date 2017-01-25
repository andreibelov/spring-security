package edu.teldir.domain.service.impl;

import edu.teldir.domain.dao.ContactDao;
import edu.teldir.domain.entity.Contact;
import edu.teldir.domain.service.ContactService;
import edu.teldir.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 10.04.12
 */
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactDao personDao;

    @Autowired
    private SecurityService securityService;


    @Transactional
    public Collection<Contact> getAllContacts() {
        return personDao.getAllContacts();
    }

    @Transactional
    public Contact getContact(long id) {
        return personDao.getContact(id);
    }

    @Transactional
    public void create(Contact contact) {
        personDao.save(contact);
        securityService.initDefaultACL(contact);
    }

    @Transactional
    public void save(Contact contact) {
        personDao.save(contact);
    }

    @Transactional
    public void remove(Contact contact) {
        securityService.removeACL(contact);
        personDao.remove(contact);
    }
}
