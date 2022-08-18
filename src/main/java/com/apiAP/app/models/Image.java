package com.apiAP.app.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Image {

		@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long idImg;
		@Basic
	  private String name;
	  private String type;
	  @Lob
	  private byte[] data;
	  public Image(){
	  }
	  public Image(String name, String type, byte[] data) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	  }
	  public Long getId() {
	    return idImg;
	  }
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	  public String getType() {
	    return type;
	  }
	  public void setType(String type) {
	    this.type = type;
	  }
	  public byte[] getData() {
	    return data;
	  }
	  public void setData(byte[] data) {
	    this.data = data;
	  }
}
