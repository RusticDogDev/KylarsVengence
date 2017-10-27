package com.cit.kv.domain;

public class itemsOwned {
	private long itemId;
	private long id;	
	
	public itemsOwned(Long itemId, Long id){
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
		return "itemsOwned [itemId=" + itemId + ", id=" + id +"]";
	}
}
