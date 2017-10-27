package com.cit.kv.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cit.kv.domain.ItemsOwned;

public class ItemsOwnedRowMapper implements RowMapper<ItemsOwned> {

	@Override
	public ItemsOwned mapRow(ResultSet rs, int i) throws SQLException {
		return new ItemsOwned(rs.getLong("id"), rs.getLong("itemId"));		
	}
}