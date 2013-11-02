package com.rufus.rest.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.*;
import javax.xml.bind.annotation.XmlRootElement;


import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
@XmlRootElement
public class Person {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	@Persistent
	private String name,city,country,partner_email,gender,email;
	@Persistent
	private double latitude, longitude;	
	
	
	
    public Person() {
    	key= null;
        name = "";
        latitude = -1;
        longitude = -1;
    }
 
    public Person(String name,String city, String country, String partner_email, String email, String gender) {
//    	Key key = KeyFactory.createKey("Product",name);
//    	this.key= key;
    	this.key = null;
        this.name = name;
        this.country = country;
        this.partner_email = partner_email;
        this.email = email;
        this.city = city;
        this.gender = gender;
        this.longitude = 0;
        this.latitude = 0;
        
    }
    
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPartner_email() {
		return partner_email;
	}

	public void setPartner_email(String partner_email) {
		this.partner_email = partner_email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
     
    public Key getKey() {
        return key;
    }
    public void setKey(Key key)
    {
    	this.key = key;
    }


     
}