package edu.teldir.domain.dao;

import edu.teldir.domain.entity.Contact;

import java.util.Collection;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 10.04.12
 */
public interface ContactDao {
    Collection<Contact> getAllContacts();
    Contact getContact(long id);
    void save(Contact contact);
    void remove(Contact contact);
}
