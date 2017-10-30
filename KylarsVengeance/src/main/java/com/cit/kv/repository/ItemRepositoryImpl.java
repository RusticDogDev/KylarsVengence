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

import com.cit.kv.domain.Item;
import com.cit.kv.rowmapper.ItemRowMapper;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
	
	@Autowired
	private JdbcTemplate jdbc;
		
	private static final String SQL_INSERT = "insert into Items (itemId, attack, defence, itemLevel, itemType, itemName, itemValue) values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "update Items set attack=?, defence=?, itemLevel=?, itemType=?, itemName=?, itemValue where itemId=?";
	private static final String SQL_FIND_ONE = "select * from Items where itemId=?";
	private static final String SQL_FIND_ALL = "select * from Items order by itemType";	
	private static final String SQL_DELETE_ONE = "delete from Items where itemId=?";

	@Override
	public List<Item> findAll() {
		return jdbc.query(SQL_FIND_ALL, new ItemRowMapper());	
	}

	@Override
	public Item findOne(Long itemId) {
		return jdbc.queryForObject(SQL_FIND_ONE, new ItemRowMapper(), itemId);	
	}

	@Override
	public Item save(Item it) {
		KeyHolder holder =  new GeneratedKeyHolder();
		int rows = jdbc.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"itemId"});
				ps.setLong(1, it.getItemId());
				ps.setInt(2, it.getAttack());
				ps.setInt(3, it.getDefence());
				ps.setInt(4, it.getItemLevel());
				ps.setString(5, it.getItemType());
				ps.setString(6, it.getItemName());
				ps.setLong(7, it.getItemValue());
				return ps;
			}				
		}, holder);
		if (rows == 1)
		{
			it.setItemId((Long)holder.getKey());
			return it;
		}		
		return null;		
	}

	@Override
	public int update(Item it) {
		return jdbc.update(SQL_UPDATE, it.getItemId(), it.getAttack(), it.getDefence(), it.getItemLevel(), it.getItemName(), it.getItemValue());
	}	

	@Override
	public int deleteOne(Long itemId) {
		return jdbc.update(SQL_DELETE_ONE, itemId);			
	}
}
