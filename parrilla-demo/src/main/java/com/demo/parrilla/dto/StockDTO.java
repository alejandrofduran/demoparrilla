package com.demo.parrilla.dto;

public class StockDTO {

	private Integer sizeId;
	private Integer quantity;

	public StockDTO(Integer sizeId, Integer quantity) {
		super();
		this.sizeId = sizeId;
		this.quantity = quantity;
	}

	public Integer getSizeId() {
		return sizeId;
	}

	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockDTO other = (StockDTO) obj;
		if (sizeId != other.sizeId)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sizeId;
		return result;
	}

	@Override
	public String toString() {
		return "StockDTO [sizeId=" + sizeId + ", quantity=" + quantity + "]";
	}

}
