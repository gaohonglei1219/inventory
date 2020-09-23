package com.admin.service.user;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.springframework.stereotype.Service;

import sun.management.resources.agent;

import com.admin.dao.DaoSupport;
import com.admin.util.PageData;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * Services related with user management
 * 
 * @author stormlin
 */
@Service("newUserService")
public class NewUserService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /**
     * Validate user identity by selecting it from database
     * 
     * @param pd
     * @return
     * @throws Exception
     */
    public boolean validateUser(PageData pageData) throws Exception {

        return (Integer) dao.findForObject("NewUserMapper.validateUser", pageData) == 1;

    }

    /**
     * Confirm the existence of given user
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public Integer isUserExists(PageData pageData) throws Exception {

        return (Integer) dao.findForObject("NewUserMapper.isUserExists", pageData);

    }

    /**
     * Confirm the availability of target EMAIL
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public Integer isEmailExists(PageData pageData) throws Exception {
        
        return (Integer) dao.findForObject("NewUserMapper.isEmailExists", pageData);
        
    }
    
    /**
     * Confirm the availability of target phone number
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public Integer isPhoneExists(PageData pageData) throws Exception {
        
        return (Integer) dao.findForObject("NewUserMapper.isPhoneExists", pageData);
        
    }

    /**
     * Confirm the validity of user STATUS
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public boolean isUserStatusActive(PageData pageData) throws Exception {
        
        return (boolean) dao.findForObject("NewUserMapper.isUserStatusActive", pageData);
        
    }

    /**
     * Get user by ID
     * 
     * @param pd
     * @return If target record does not exist, returns null; otherwise, returns the record
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, Object> getUserByUserName(PageData pd) throws Exception {
        
        Object object = dao.findForObject("NewUserMapper.selectUserByUserName", pd);
        
        if (object != null) {
            return (HashMap<String, Object>) object;
        }
        
        return null;
        
    }
    
    /**
     * Get Name by ManBuyerID in DSR_DROPSHIPPER
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
	public HashMap<String, String> getNameByManBuyerID(PageData pageData) throws Exception {

        Object object = dao.findForObject("NewUserMapper.selectNameByManBuyerID", pageData);
        
        if (object != null) {
            return (HashMap<String, String>) object;
        }
        
        return null;
    	
	}

    /**
     * Update LAST_LOGIN and IP
     * 
     * @throws Exception
     */
    public boolean updateLoginInfo(PageData pd) throws Exception {
        
        return ((Integer) dao.save("NewUserMapper.updateLastLoginInfo", pd)) == 1;
        
    }

    /**
     * Insert new user into database
     * 
     * @param pd
     * @throws Exception
     */
    public boolean insertNewUser(PageData pd) throws Exception {
        
        return ((Integer) dao.save("NewUserMapper.insertNewUser", pd)) == 1;
    
    }

    /**
     * Insert new login flow record into "ULL_USER_LOGIN_LOGOUT_LOG"
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public boolean insertNewLoginRecord(PageData pageData) throws Exception {
        
        return ((Integer) dao.save("NewUserMapper.insertNewLoginRecord", pageData)) == 1;
    
    }

    /**
     * Get user by Email
     * 
     * @param pd
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, String> selectUserByEmail(PageData pd) throws Exception {
        
        Object object = dao.findForObject("NewUserMapper.selectUserByEmail", pd);
        
        if (object == null) {
            return null;
        } else {
            return (HashMap<String, String>) object;
        }
        
    }

    /**
     * Get password retrieval email sent time stamp
     * 
     * @param pd
     * @return
     * @throws Exception
     */
    public PageData selectTimeStampByID(PageData pd) throws Exception {
        /* TODO: Null XML */
        return (PageData) dao.findForObject("NewUserMapper.getTimeStampByID", pd);
    }
    
    /**
     * Select ROLE_ID by USERNAME
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, String> selectRoleNameByRoleID(PageData pageData) throws Exception {
        
        Object object = dao.findForObject("NewUserMapper.selectRoleNameByRoleID", pageData);
        
        if (object == null) {
            return null;
        } else {
            return (HashMap<String, String>) object;
        }
        
    }

    /**
     * Update user information by user ID
     * 
     * @param pd
     * @throws Exception
     */
    public boolean updateUserInfoByUserName(PageData pd) throws Exception {
        
        return ((Integer) dao.save("NewUserMapper.updateUserInfo", pd)) == 1;
        
    }

    /**
     * Update MAN_BUYER_ID by UserName
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public boolean updateManByerIDByUserName(PageData pageData) throws Exception {
        
        return (Integer) dao.save("NewUserMapper.updateManByerID", pageData) == 1;
        
    }
    
    /**
     * Update UserName in database by for given USER_ID
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public boolean updateNickNameByUserName(PageData pageData) throws Exception {
        
        return (Integer) dao.save("NewUserMapper.updateUserName", pageData) == 1;
    
    }

    /**
     * Update user skin by USERNAME
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public boolean updateUserSkin(PageData pageData) throws Exception {
        
        return ((Integer) dao.save("NewUserMapper.updateUserSkin", pageData)) == 1;
    
    }

    /**
     * Update user EMAIL by USERNAME
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public boolean updateUserEmail(PageData pageData) throws Exception {
        
        return ((Integer) dao.save("NewUserMapper.updateUserEmail", pageData)) == 1;
    
    }

    /**
     * Update user NUMBER by USERNAME
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public boolean updateUserNumber(PageData pageData) throws Exception {
        
        return ((Integer) dao.save("NewUserMapper.updateUserNumber", pageData)) == 1;
    
    }

    /**
     * Update user PHONE by USERNAME
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public boolean updateUserPhone(PageData pageData) throws Exception {
        
        return ((Integer) dao.save("NewUserMapper.updateUserPhone", pageData)) == 1;
    
    }

    /**
     * Update use PASSWORD by USERNAME
     * 
     * @param pageData
     * @return
     * @throws Exception
     */
    public boolean updateUserPassword(PageData pageData) throws Exception {
        
        return ((Integer) dao.save("NewUserMapper.updateUserPassword", pageData)) == 1;
    
    }

}
