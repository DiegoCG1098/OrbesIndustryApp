package com.orbes.articlesapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "suppliers")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "supplier_id")
	private long supplierId;

	@NotEmpty(message="Error de validación: se necesita un valor")
	@Column(name = "enterprise")
	private String enterprise;

	@Size(min=11, max=11, message="Debe llener los campos correctamente")
	@NotEmpty(message="Error de validación: se necesita un valor")
	@Column(name = "ruc")
	private String ruc;

	@NotEmpty(message="Error de validación: se necesita un valor")
	@Column(name = "product")
	private String product;

	@Size(min=9, max=9, message="Debe llener los campos correctamente")
	@NotEmpty(message="Error de validación: se necesita un valor")
	@Column(name = "phone")
	private String phone;
	
	/*@Lob
	@Type(type = "org.hibernate.type.TextType") 
	@NotEmpty(message="Error de validación: se necesita un valor")
	@Column(name = "description")
	private String description;*/
	
	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/*public String getDescription() {
	return description;
	}

	public void setDescription(String description) {
	this.description = description;
	}*/

}