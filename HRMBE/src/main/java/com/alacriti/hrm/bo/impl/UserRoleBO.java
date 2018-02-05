package com.alacriti.hrm.bo.impl;

import java.sql.Connection;

import com.alacriti.hrm.dao.ISampleDAO;
import com.alacriti.hrm.dao.impl.DAOException;
import com.alacriti.hrm.dao.impl.SampleDAO;
import com.alacriti.hrm.dao.impl.UserDAO;
import com.alacriti.hrm.log.impl.AppLogger;
import com.alacriti.hrm.model.vo.UserRoleVO;
import com.alacriti.hrm.util.LogUtil;

public class UserRoleBO extends BaseBO {
	
	public UserRoleBO(Connection connection) {
		super(connection);
	}
	
	
	private static final AppLogger log = LogUtil.getLogger(UserRoleBO.class);
	
	public void getUserRole(UserRoleVO userVO) throws DAOException, BOException{
		log.debugPrintCurrentMethodName();
		
		try {
			UserDAO userDAO =   new UserDAO(getConnection());
			userDAO.getUserRole(userVO);
			
		} catch (Exception e) {
			log.logError("Exception in retrieveMessage " + e.getMessage(), e);
			throw new BOException();
		}
	}
	
	
	public void createUserRole(UserRoleVO userVO) throws DAOException, BOException{
		log.debugPrintCurrentMethodName();
		
		try {
			UserDAO userDAO =   new UserDAO(getConnection());
			userDAO.createUserRole(userVO);
			
		} catch (Exception e) {
			log.logError("Exception in retrieveMessage " + e.getMessage(), e);
			throw new BOException();
		}
	}

}
