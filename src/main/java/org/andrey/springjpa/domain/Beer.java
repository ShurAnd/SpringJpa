package org.andrey.springjpa.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Beer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Type type;
	private BigDecimal price;
	
	public static enum Type{
		LIGHT, DARK, BROWN, AMBER
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public boolean equals(Object that) {
		if (this == that) return true;
		if (this == null || !(that instanceof Beer)) return false;
		Beer beer2 = (Beer)that;
		if (!this.name.equals(beer2.name)) return false;
		if (!this.type.equals(beer2.type)) return false;
		if (!this.price.equals(beer2.price)) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		result = 7 * (name.hashCode() + type.hashCode() + price.hashCode());
		
		return result;
	}
}
