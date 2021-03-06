package com.hitdavid.service.session;

import com.hitdavid.service.dao.model.Role;
import com.hitdavid.service.security.SecurityUser;
import com.hitdavid.service.security.SecurityUtil;
import com.hitdavid.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by David on 2017/5/25.
 */
@ComponentScan
@Service
public class SessionHelper {

    private static final Logger log = LoggerFactory.getLogger(SessionHelper.class);

    @Autowired
    private UserService userService;

    public SessionHelper() {
    }

    public ArrayList<Role> getRoleFormSession() {

        Long uid = getUserIdFromSession();
        if(uid == null) {
            return null;
        }
        else {
            ArrayList<Role> roles = new ArrayList<Role>();
            roles = (ArrayList<Role>) userService.getAllRolesById(uid);
            return roles;
        }
    }

    public Long getUserIdFromSession() {

        Long uid = SecurityUtil.getUid();
        return uid;
    }

    public SecurityUser getUserInfoFromSession(HttpSession session) {

        return SecurityUtil.getUser();
    }
}
