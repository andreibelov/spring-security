package edu.teldir.domain.dao.impl;

import edu.teldir.domain.dao.ContactDao;
import edu.teldir.domain.entity.Contact;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 10.04.12
 */
@Repository
public class ContactDaoImpl extends HibernateDaoSupport implements ContactDao {

    public Collection<Contact> getAllContacts() {
        return getHibernateTemplate().loadAll(Contact.class);
    }

    public Contact getContact(long id) {
        return getHibernateTemplate().get(Contact.class, id);
    }

    public void save(Contact person) {
        getHibernateTemplate().saveOrUpdate(person);
    }

    public void remove(Contact person) {
        getHibernateTemplate().delete(person);
    }
}
