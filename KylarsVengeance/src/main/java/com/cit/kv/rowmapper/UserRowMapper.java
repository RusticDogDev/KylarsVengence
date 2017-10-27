package com.cit.kv.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cit.kv.domain.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int i) throws SQLException {
		return new User(rs.getLong("id"), rs.getString("fName"), rs.getString("lName"), rs.getString("userType"), rs.getInt("level"), rs.getLong("balance") );	
	}
}
