package com.grofers.GroferShop.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderRequestDto {
    private Long userId;
    private List<Long> productIds;
    private Date deliveryDate;
    
    
    
	public OrderRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderRequestDto(Long userId, List<Long> productIds, Date deliveryDate) {
		super();
		this.userId = userId;
		this.productIds = productIds;
		this.deliveryDate = deliveryDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<Long> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@Override
	public String toString() {
		return "OrderRequestDto [userId=" + userId + ", productIds=" + productIds + ", deliveryDate=" + deliveryDate
				+ "]";
	}
    
    
}