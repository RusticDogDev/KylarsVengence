package com.cit.kv.domain;

public class Item {
	public enum ItemType
	{
		CloseRange,
		LongRange,
		Shield,
		Armour
	}
		
	private Long itemId;
	private String itemType;
	private Long attack;
	private Long defence;
	private int level;
	private String itemName;
	private Long itemValue;
	
	public Item(Long itemId, String itemType, Long attack, Long defence, int level, String itemName, Long itemValue) {
		super();
		this.itemId = itemId;
		this.itemType = itemType;
		this.attack = attack;
		this.defence = defence;
		this.level = level;
		this.itemName = itemName;
		this.itemValue = itemValue;	
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}	
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getAttack() {
		return attack;
	}

	public void setAttack(Long attack) {
		this.attack = attack;
	}

	public Long getDefence() {
		return defence;
	}

	public void setDefence(Long defence) {
		this.defence = defence;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}	

	public Long getItemValue() {
		return itemValue;
	}

	public void setItemValue(Long itemValue) {
		this.itemValue = itemValue;
	}	
	
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + 
				", itemType=" + itemType + 
				", attack=" + attack +  
				", defence=" + defence + 
				", level=" + level + 
				", itemName=" + itemName + 
				", itemValue=" + itemValue + "]";
	}	
}
