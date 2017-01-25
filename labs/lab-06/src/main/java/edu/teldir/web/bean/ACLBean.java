package edu.teldir.web.bean;

import java.util.Collection;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 14.04.12
 */
public class ACLBean {
    private SecurityIdentityType securityIdentityType;
    private String username;
    private String role;
    private Collection<String> permissions;

    public SecurityIdentityType getSecurityIdentityType() {
        return securityIdentityType;
    }

    public void setSecurityIdentityType(SecurityIdentityType securityIdentityType) {
        this.securityIdentityType = securityIdentityType;
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

    public Collection<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<String> permissions) {
        this.permissions = permissions;
    }
}
