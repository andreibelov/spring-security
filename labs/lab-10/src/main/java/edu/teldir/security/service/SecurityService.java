package edu.teldir.security.service;

import edu.teldir.domain.entity.DomainEntity;
import edu.teldir.security.objects.PermissionObject;

import java.util.Collection;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 14.04.12
 */
public interface SecurityService {
    void grantPermissions(DomainEntity entity, PermissionObject po);
    void removeACL(DomainEntity entity);
    Collection<PermissionObject> getPermissions(DomainEntity entity);
    Collection<String> getRoles();
    Collection<String> getUsernames();
    void initDefaultACL(DomainEntity entity);
}
