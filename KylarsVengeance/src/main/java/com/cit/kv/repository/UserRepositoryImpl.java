package com.cit.kv.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cit.kv.domain.User;
import com.cit.kv.rowmapper.UserRowMapper;
@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private JdbcTemplate jdbc;
		
	private static final String SQL_INSERT = "insert into User (id, userName, password, userType, level, balance) values (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "update User set userName=?, password=?, userType=?, level=?, balance =? where id=?";
	private static final String SQL_FIND_ONE = "select * from User where id = ?";
	private static final String SQL_FIND_ALL = "select * from User order by userType";	
	private static final String SQL_DELETE_ONE = "delete from User where id = ?";
	
	@Override
	public List<User> findAll() {
		return jdbc.query(SQL_FIND_ALL, new UserRowMapper());			
	}

	@Override
	public User findOne(Long id) {
		return jdbc.queryForObject(SQL_FIND_ONE, new UserRowMapper(), id);	
	}

	@Override
	public User save(User ur) {
		KeyHolder holder =  new GeneratedKeyHolder();
		int rows = jdbc.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"id"});
				ps.setLong(1, ur.getId());
				ps.setString(2, ur.getUserName());
				ps.setString(3, ur.getPassword());
				ps.setString(4, ur.getUserType());
				ps.setInt(5, ur.getLevel());
				ps.setLong(6, ur.getBalance());				
				return ps;
			}				
		}, holder);
		if (rows == 1)
		{
			ur.setId((Long)holder.getKey());
			return ur;
		}		
		return null;		
	}

	@Override
	public int update(User ur) {
		return jdbc.update(SQL_UPDATE, ur.getId(), ur.getUserName(), ur.getPassword(), ur.getUserType(), ur.getLevel(), ur.getBalance());
	}

	@Override
	public int deleteOne(Long id) {
		return jdbc.update(SQL_DELETE_ONE, id);	
	}
}
