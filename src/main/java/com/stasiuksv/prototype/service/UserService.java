package com.stasiuksv.prototype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stasiuksv.prototype.LoggingUnit;
import com.stasiuksv.prototype.controllers.Role;
import com.stasiuksv.prototype.controllers.User;
import com.stasiuksv.prototype.dao.UserDAO;
import com.stasiuksv.prototype.model.RoleEntity;
import com.stasiuksv.prototype.model.UserEntity;

@Service
@Transactional
public class UserService implements DataService<User,UserEntity> 
{	
	
		@Autowired
		private UserDAO userDAO;
			
		@Override
		public HttpStatus create(User user) 
		{
			userDAO.create(new UserEntity(user));
			return HttpStatus.OK;
		}

		@Override
		
		public HttpStatus update(Long id, User user)
		{	
			UserEntity savedUser = userDAO.getById(id);
			if (savedUser==null)
				return HttpStatus.NOT_FOUND;
			savedUser.setUserName(user.getUserName());
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassWord());
			savedUser.setPassWord(encodedPassword);
			savedUser.getRoles().clear();
			for (Role role:user.getRoles())
				savedUser.getRoles().add(new RoleEntity(role));
			userDAO.update(savedUser);
			return HttpStatus.OK;
		}

		@Override
		public HttpStatus deleteByID(Long id) 
		{
			UserEntity savedUser = userDAO.getById(id);
			if(savedUser!=null)
			{
				userDAO.delete(savedUser);
				LoggingUnit.log.info("Record deleted");
				return HttpStatus.OK;
			}
			else 
				return HttpStatus.NOT_FOUND;
		}

		@Override
		public UserEntity getByID(Long id) 
		{
			return userDAO.getById(id);
		}

		@Override
		public List<UserEntity> listAll() 
		{
			return userDAO.getAll();
		}

		@Override
		public UserEntity getByName(String name)
		{
			return userDAO.getByName(name);
		}

		
}
