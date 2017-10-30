package com.cit.kv.domain;

public class ItemsOwned {
	private long itemId;
	private long id;	
	
	public ItemsOwned(Long id, Long itemId){
		super();
		this.setItemId(itemId);
		this.setId(id);
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "itemsOwned [id=" + id +", itemId=" + itemId + "]";
	}
}
