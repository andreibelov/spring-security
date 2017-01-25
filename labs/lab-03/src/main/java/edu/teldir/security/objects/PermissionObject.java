package edu.teldir.security.objects;

import org.springframework.security.acls.model.Permission;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 15.04.12
 */
public class PermissionObject {
    private long domainEntityId;
    private SecurityIdentType securityIdentType;
    private String securityIdentName;
    private Permission permission;

    public SecurityIdentType getSecurityIdentType() {
        return securityIdentType;
    }

    public void setSecurityIdentType(SecurityIdentType securityIdentType) {
        this.securityIdentType = securityIdentType;
    }

    public String getSecurityIdentName() {
        return securityIdentName;
    }

    public void setSecurityIdentName(String securityIdentName) {
        this.securityIdentName = securityIdentName;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public long getDomainEntityId() {
        return domainEntityId;
    }

    public void setDomainEntityId(long domainEntityId) {
        this.domainEntityId = domainEntityId;
    }
}
