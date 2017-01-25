package edu.teldir.security.service.impl;

import edu.teldir.domain.entity.DomainEntity;
import edu.teldir.security.dao.SecurityObjectDao;
import edu.teldir.security.objects.PermissionObject;
import edu.teldir.security.objects.SecurityIdentType;
import edu.teldir.security.service.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 14.04.12
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger logger = Logger.getLogger(SecurityServiceImpl.class);

    private static final Permission[] OWNER_PERMISSIONS = {
            BasePermission.CREATE,
            BasePermission.READ,
            BasePermission.WRITE,
            BasePermission.DELETE
    };

    @Autowired
    private MutableAclService aclService;

    @Autowired
    private SecurityObjectDao securityObjectDao;

    @Transactional
    public void grantPermissions(DomainEntity entity, PermissionObject po) {
        MutableAcl acl = readAcl(entity);
        if (acl == null) {
            acl = aclService.createAcl(new ObjectIdentityImpl(entity.getClass(), entity.getId()));
            acl.setOwner(new PrincipalSid(SecurityContextHolder.getContext().getAuthentication()));
        }
        acl.insertAce(acl.getEntries().size(), po.getPermission(), createSid(po), true);
        aclService.updateAcl(acl);
    }

    @Transactional
    public void removeACL(DomainEntity entity) {
        aclService.deleteAcl(
                new ObjectIdentityImpl(entity.getClass(), entity.getId()),
                true);
    }

    @Transactional
    public Collection<PermissionObject> getPermissions(DomainEntity entity) {
        Collection<PermissionObject> result = new ArrayList<PermissionObject>();

        Acl acl = readAcl(entity);
        if (acl == null) {
            return Collections.emptyList();
        }

        for (AccessControlEntry entry : acl.getEntries()) {
            PermissionObject po = createPermissionObject(entry);
            po.setDomainEntityId(entity.getId());
            result.add(po);
        }

        return result;
    }

    @Transactional
    public Collection<String> getRoles() {
        return securityObjectDao.getRoles();
    }

    @Transactional
    public Collection<String> getUsernames() {
        return securityObjectDao.getUsernames();
    }

    @Transactional
    public void initDefaultACL(DomainEntity entity) {
        removeACL(entity);

        Sid owner = new PrincipalSid(
                SecurityContextHolder.getContext().getAuthentication());

        MutableAcl acl = aclService.createAcl(
                new ObjectIdentityImpl(entity.getClass(), entity.getId()));
        acl.setOwner(owner);
        for (Permission permission : OWNER_PERMISSIONS) {
            acl.insertAce(acl.getEntries().size(), permission, owner, true);
        }
        aclService.updateAcl(acl);
    }

    private static PermissionObject createPermissionObject(AccessControlEntry entry) {
        PermissionObject result = new PermissionObject();
        result.setPermission(entry.getPermission());
        if (entry.getSid() instanceof GrantedAuthoritySid) {
            GrantedAuthoritySid sid = (GrantedAuthoritySid) entry.getSid();
            result.setSecurityIdentName((sid).getGrantedAuthority());
            result.setSecurityIdentType(SecurityIdentType.Authority);
        } else if (entry.getSid() instanceof PrincipalSid) {
            PrincipalSid sid = (PrincipalSid) entry.getSid();
            result.setSecurityIdentName(sid.getPrincipal());
            result.setSecurityIdentType(SecurityIdentType.Principal);
        } else {
            throw new RuntimeException("Unsupported type of Sid.");
        }
        return result;
    }

    private static Sid createSid(PermissionObject po) {
        if (po.getSecurityIdentType() == SecurityIdentType.Authority) {
            return new GrantedAuthoritySid(po.getSecurityIdentName());
        } else if (po.getSecurityIdentType() == SecurityIdentType.Principal) {
            return  new PrincipalSid(po.getSecurityIdentName());
        } else {
            throw new RuntimeException("Unsupported security identity type.");
        }
    }

    private MutableAcl readAcl(DomainEntity entity) {
        try {
            return (MutableAcl) aclService.readAclById(
                    new ObjectIdentityImpl(entity.getClass(), entity.getId()));
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
}
