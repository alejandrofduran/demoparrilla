package com.demo.parrilla.dto;

public class SizeDTO {

	private Integer id;
	private Integer productId;
	private boolean backSoon;
	private boolean special;

	public SizeDTO(Integer id, Integer productId, boolean backSoon, boolean special) {
		super();
		this.id = id;
		this.productId = productId;
		this.backSoon = backSoon;
		this.special = special;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public boolean isBackSoon() {
		return backSoon;
	}

	public void setBackSoon(boolean backSoon) {
		this.backSoon = backSoon;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SizeDTO other = (SizeDTO) obj;
		if (id != other.id)
			return false;
		if (productId != other.productId)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + productId;

		return result;
	}

	@Override
	public String toString() {
		return "SizeDTO [id=" + id + ", productId=" + productId + ", backSoon=" + backSoon + ", special=" + special
				+ "]";
	}

}
