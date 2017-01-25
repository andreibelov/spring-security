package edu.teldir.domain.entity;

import javax.persistence.*;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 10.04.12
 */
@Entity
@Table(name = "TD_CONTACT")
@AttributeOverride(name = "id", column = @Column(name = "CONTACT_ID", nullable = false))
public class Contact extends DomainEntity {
    @Column(name = "CONTACT_NAME", length = 50)
    private String name;

    @Column(name = "CONTACT_TELNUMBER", length = 20)
    private String telephoneNumber;

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
}
