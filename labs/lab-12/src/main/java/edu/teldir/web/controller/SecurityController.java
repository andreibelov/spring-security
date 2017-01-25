package edu.teldir.web.controller;

import edu.teldir.domain.entity.Contact;
import edu.teldir.domain.service.ContactService;
import edu.teldir.security.objects.PermissionObject;
import edu.teldir.security.objects.SecurityIdentType;
import edu.teldir.security.service.SecurityService;
import edu.teldir.web.bean.PermissionObjectBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.CumulativePermission;
import org.springframework.security.acls.model.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 14.04.12
 */
@Controller
@RequestMapping("/acl/*")
public class SecurityController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ContactService contactService;

    private static final Map<String, Permission> permissionMap = new HashMap<String, Permission>();
    static {
        permissionMap.put("Create", BasePermission.CREATE);
        permissionMap.put("Read", BasePermission.READ);
        permissionMap.put("Update", BasePermission.WRITE);
        permissionMap.put("Delete", BasePermission.DELETE);
        permissionMap.put("Administration", BasePermission.ADMINISTRATION);
    }

    @RequestMapping("/list.do")
    public ModelAndView list(@RequestParam(value = "id", required = true) long id) {
        Contact contact = contactService.getContact(id);
        Collection<PermissionObject> permissions = securityService.getPermissions(contact);

        ModelAndView result = new ModelAndView("acl/list");
        result.addObject("permissions", permissions);
        result.addObject("domainEntityId", id);
        return result;
    }

    @RequestMapping("/edit.do")
    public ModelAndView edit(@RequestParam(value = "id", required = true) long id) {
        PermissionObjectBean bean = new PermissionObjectBean();
        bean.setDomainEntityId(id);

        ModelAndView result = new ModelAndView("acl/edit");
        result.addObject("acl", bean);
        result.addObject("roles", securityService.getRoles());
        result.addObject("usernames", securityService.getUsernames());
        result.addObject("securityIdentTypes", SecurityIdentType.values());
        result.addObject("permissions", permissionMap.keySet());
        return result;
    }

    @RequestMapping("/save.do")
    public ModelAndView save(PermissionObjectBean permission) {
        Contact contact = contactService.getContact(permission.getDomainEntityId());

        PermissionObject po = new PermissionObject();
        po.setPermission(buildPermission(permission.getPermissions()));
        po.setDomainEntityId(permission.getDomainEntityId());
        if (permission.getSecurityIdentType() == SecurityIdentType.Authority) {
            po.setSecurityIdentType(SecurityIdentType.Authority);
            po.setSecurityIdentName(permission.getRole());
        } else if (permission.getSecurityIdentType() == SecurityIdentType.Principal) {
            po.setSecurityIdentType(SecurityIdentType.Principal);
            po.setSecurityIdentName(permission.getUsername());
        } else {
            throw new RuntimeException("Unsupported security identity type.");
        }

        securityService.grantPermissions(contact, po);

        return new ModelAndView("redirect:/acl/list.do?id=" + permission.getDomainEntityId());
    }

    private static Permission buildPermission(Collection<String> permissions) {
        CumulativePermission result = new CumulativePermission();
        for (String permission : permissions) {
            result.set(permissionMap.get(permission));
        }
        return result;
    }

    @RequestMapping("/clear.do")
    public ModelAndView reset(@RequestParam(value = "id", required = true) long id) {
        Contact contact = contactService.getContact(id);
        securityService.initDefaultACL(contact);
        return new ModelAndView("redirect:/acl/list.do?id=" + id);
    }
}
