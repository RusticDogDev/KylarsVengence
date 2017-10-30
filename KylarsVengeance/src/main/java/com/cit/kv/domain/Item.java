package com.cit.kv.domain;

public class Item {
	public enum ItemType
	{
		CloseRange,
		Shield,
		Armour,
		LongRange
	}
		
	private Long itemId;
	private String itemType;
	private int attack;
	private int defence;
	private int itemLevel;
	private String itemName;
	private Long itemValue;
	
	public Item(Long itemId, int attack, int defence, int itemlevel, String itemType, String itemName, Long itemValue) {
		super();
		this.itemId = itemId;
		this.itemType = itemType;
		this.attack = attack;
		this.defence = defence;
		this.itemLevel = itemlevel;
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

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
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
				", attack=" + attack +  
				", defence=" + defence + 
				", itemLevel=" + itemLevel + 
				", itemType=" + itemType + 
				", itemName=" + itemName + 
				", itemValue=" + itemValue + "]";
	}	
}
