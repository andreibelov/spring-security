package edu.teldir.domain.service.impl;

import edu.teldir.domain.dao.ContactDao;
import edu.teldir.domain.entity.Contact;
import edu.teldir.domain.service.ContactService;
import edu.teldir.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostFilter("hasPermission(filterObject, 'READ')")
    public Collection<Contact> getAllContacts() {
        return personDao.getAllContacts();
    }

    @Transactional
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public Contact getContact(long id) {
        return personDao.getContact(id);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_FIN_USER','ROLE_IT_USER')")
    public void create(Contact contact) {
        personDao.save(contact);
        securityService.initDefaultACL(contact);
    }

    @Transactional
    @PreAuthorize("hasPermission(#contact, 'WRITE')")
    public void save(Contact contact) {
        personDao.save(contact);
    }

    @Transactional
    @PreAuthorize("hasPermission(#contact, 'DELETE')")
    public void remove(Contact contact) {
        securityService.removeACL(contact);
        personDao.remove(contact);
    }
}
