package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;

@Repository("UserDaoJdbcImpl2")
public class UserDaoJdbcImpl2 extends UserDaoJdbcImpl {

	@Autowired
	JdbcTemplate jdbc;

	@Override
	public User selectOne(String userId) throws DataAccessException {

		UserRowMapper rowMapper = new UserRowMapper();

		return jdbc.queryForObject("SELECT * FROM m_user WHERE user_id = ?", rowMapper, userId);

	}

	@Override
	public List<User> selectMany() throws DataAccessException {

		UserRowMapper rowMapper = new UserRowMapper();

		return jdbc.query("SELECT * FROM m_user", rowMapper);

	}

}
