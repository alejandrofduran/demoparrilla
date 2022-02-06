package com.demo.parrilla.dto;

public class ProductDataDTO {

	// private int productId;
	private boolean normalSizeOKToPublish;
	private boolean specialSizeOkToPublish;
	private boolean hasSpecialSizes;

	public ProductDataDTO(boolean normalSizeOKToPublish, boolean specialSizeOkToPublish, boolean hasSpecialSizes) {
		super();
		this.normalSizeOKToPublish = normalSizeOKToPublish;
		this.specialSizeOkToPublish = specialSizeOkToPublish;
		this.hasSpecialSizes = hasSpecialSizes;
	}

	public boolean isNormalSizeOKToPublish() {
		return normalSizeOKToPublish;
	}

	public void setNormalSizeOKToPublish(boolean normalSizeOKToPublish) {
		this.normalSizeOKToPublish = normalSizeOKToPublish;
	}

	public boolean isSpecialSizeOkToPublish() {
		return specialSizeOkToPublish;
	}

	public void setSpecialSizeOkToPublish(boolean specialSizeOkToPublish) {
		this.specialSizeOkToPublish = specialSizeOkToPublish;
	}

	public boolean isHasSpecialSizes() {
		return hasSpecialSizes;
	}

	public void setHasSpecialSizes(boolean hasSpecialSizes) {
		this.hasSpecialSizes = hasSpecialSizes;
	}

}