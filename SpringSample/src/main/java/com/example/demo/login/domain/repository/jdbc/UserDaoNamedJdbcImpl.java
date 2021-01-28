package com.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository("UserDaoNamedJdbcImpl")
public class UserDaoNamedJdbcImpl implements UserDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public int count() throws DataAccessException {

		String sql = "SELECT COUNT(*) FROM m_user";

		SqlParameterSource params = new MapSqlParameterSource();

		return jdbc.queryForObject(sql, params, Integer.class);
	}

	@Override
	public int insertOne(User user) throws DataAccessException {

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage())
				.addValue("role", user.getRole());

		return jdbc.update("INSERT INTO m_user(user_id,"
				+ " password,"
				+ " user_name,"
				+ " birthday,"
				+ " age,"
				+ " marriage,"
				+ " role)"
				+ " VALUES(:userId,:password,:userName,:birthday,:age,:marriage,:role)"
				, params
				);
	}

	@Override
	public User selectOne(String userId) throws DataAccessException {

		String sql = "SELECT * FROM m_user WHERE user_id = :userId";

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", userId);

		UserRowMapper rowMapper = new UserRowMapper();

		return jdbc.queryForObject(sql, params, rowMapper);

	}

	@Override
	public List<User> selectMany() throws DataAccessException {

		String sql = "SELECT * FROM m_user";

		SqlParameterSource params = new MapSqlParameterSource();

		UserRowMapper rowMapper = new UserRowMapper();

		return jdbc.query(sql, params, rowMapper);

	}

	@Override
	public int updateOne(User user) throws DataAccessException {

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("userId", user.getUserId())
				.addValue("password", user.getPassword())
				.addValue("userName", user.getUserName())
				.addValue("birthday", user.getBirthday())
				.addValue("age", user.getAge())
				.addValue("marriage", user.isMarriage());

		int rowNumber = jdbc.update("UPDATE m_user SET"
				+ " password=:password,"
				+ " user_name=:userName,"
				+ " birthday=:birthday,"
				+ " age=:age,"
				+ " marriage=:marriage"
				+ " WHERE user_id=:userId"
				, params);

		return rowNumber;
	}

	@Override
	public int deleteOne(String userId) throws DataAccessException {

		SqlParameterSource params = new MapSqlParameterSource().addValue("userId", userId);

		int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id=:userId", params);

		return rowNumber;
	}

	@Override
	public void userCsvOut() throws DataAccessException {
		String sql = "SELECT * FROM m_user";

		UserRowCallbackHandler handler = new UserRowCallbackHandler();

		jdbc.query(sql, handler);
	}


}
