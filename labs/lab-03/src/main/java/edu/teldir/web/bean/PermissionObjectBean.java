package edu.teldir.web.bean;

import edu.teldir.security.objects.SecurityIdentType;

import java.util.Collection;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 15.04.12
 */
public class PermissionObjectBean {
    private long domainEntityId;
    private SecurityIdentType securityIdentType;
    private String username;
    private String role;
    private Collection<String> permissions;

    public long getDomainEntityId() {
        return domainEntityId;
    }

    public void setDomainEntityId(long domainEntityId) {
        this.domainEntityId = domainEntityId;
    }

    public SecurityIdentType getSecurityIdentType() {
        return securityIdentType;
    }

    public void setSecurityIdentType(SecurityIdentType securityIdentType) {
        this.securityIdentType = securityIdentType;
    }

    public Collection<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<String> permissions) {
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
