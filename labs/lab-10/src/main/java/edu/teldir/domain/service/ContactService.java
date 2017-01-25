package edu.teldir.domain.service;

import edu.teldir.domain.dao.ContactDao;
import edu.teldir.domain.entity.Contact;

import java.util.Collection;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 10.04.12
 */
public interface ContactService {
    Collection<Contact> getAllContacts();
    Contact getContact(long id);
    void save(Contact contact);
    void remove(Contact contact);
    void create(Contact contact);
}
