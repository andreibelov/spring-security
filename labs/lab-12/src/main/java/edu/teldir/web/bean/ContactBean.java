package edu.teldir.web.bean;

import edu.teldir.domain.entity.Contact;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 10.04.12
 */
public class ContactBean implements Comparable<ContactBean> {
    private long id;
    private String name;
    private String telephoneNumber;

    public ContactBean() {
    }

    public ContactBean(Contact contact) {
        id = contact.getId();
        name = contact.getName();
        telephoneNumber = contact.getTelephoneNumber();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int compareTo(ContactBean o) {
        int result = getName().compareTo(o.getName());
        if (result == 0) {
            result = getTelephoneNumber().compareTo(o.getTelephoneNumber());
        }
        return result;
    }
}
