package com.cit.kv.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.cit.kv.domain.ItemsOwned;
import com.cit.kv.rowmapper.ItemsOwnedRowMapper;

@Repository
public class ItemsOwnedRepositoryImpl implements ItemsOwnedRepository{
	@Autowired
	private JdbcTemplate jdbc;
		
	private static final String SQL_INSERT = "insert into ItemsOwned (id, itemId) values (?, ?)";	
	private static final String SQL_FIND_ALL_By_USER_ID = "select * from ItemsOwned where id =? order by id";
	private static final String SQL_FIND_ALL_By_ITEM_ID = "select * from ItemsOwned where id =? order by itemId";
	private static final String SQL_DELETE_ONE = "delete from ItemsOwned where itemId = ? And id =?";
	
	@Override
	public List<ItemsOwned> findAllByUser(Long id) {
		return jdbc.query(SQL_FIND_ALL_By_USER_ID, new ItemsOwnedRowMapper(), id);		
	}

	@Override
	public List<ItemsOwned> findAllByItem(Long itemId) {
		return jdbc.query(SQL_FIND_ALL_By_ITEM_ID, new ItemsOwnedRowMapper(), itemId);		
	}

	@Override
	public ItemsOwned save(ItemsOwned io) {		
		int rows = jdbc.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"id"});
				ps.setLong(1, io.getId());
				ps.setLong(2, io.getItemId());			
				return ps;
			}				
		});
		if (rows == 1)
		{
			return io;
		}		
		return null;					
	}

	@Override
	public int deleteOne(ItemsOwned io) {
		return jdbc.update(SQL_DELETE_ONE, io.getId(), io.getItemId());	
	}}
