package com.cit.kv.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cit.kv.domain.Item;
public class ItemRowMapper implements RowMapper<Item>{

	@Override
	public Item mapRow(ResultSet rs, int i) throws SQLException {
		return new Item(rs.getLong("itemId"), rs.getString("itemType"), rs.getInt("attack"), rs.getInt("defence"), rs.getInt("itemLevel"), rs.getString("itemName"), rs.getLong("itemValue"));
	}
}
