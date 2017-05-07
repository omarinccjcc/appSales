package pe.edu.upeu.appsales.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Product implements EntityGeneric {

	
	 @Id
    @GeneratedValue
    private Long id;
 
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
