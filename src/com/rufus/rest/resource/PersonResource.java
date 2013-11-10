package com.rufus.rest.resource;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Request;
 
import com.rufus.rest.model.Person;
 
@Path("/person")
public class PersonResource {
 
    private final static String NAME = "name";
    private final static String LONGITUDE = "longitude";
    private final static String LATITUDE = "latitude";
    
	/**construct a persistence manager for data storage**/
	private myPersistenceManager mpm = new myPersistenceManager();
         
    //private Person person = new Person("Rufus", 1, 1);
     
    // The @Context annotation allows us to have certain contextual objects
    // injected into this class.
    // UriInfo object allows us to get URI information (no kidding).
    @Context
    UriInfo uriInfo;
 
    // Another "injected" object. This allows us to use the information that's
    // part of any incoming request.
    // We could, for example, get header information, or the requestor's address.
    @Context
    Request request;
     
    // Basic "is the service running" test
    @GET
    @Path("bbb")
    @Produces(MediaType.TEXT_PLAIN)
    public String respondAsReady() {
        return "Demo service is ready!";
    }
 
//    @GET
//    @Path("sample")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Person getSamplePerson() {
//         
//        System.out.println("Returning sample person: " + person.getName());
//         
//        return person;
//    }
         
    // Use data from the client source to create a new Person object, returned in JSON format.  
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String postPerson(
            MultivaluedMap<String, String> personParams
            ) {
       
        String name = personParams.getFirst(NAME);
        double latitude = Double.parseDouble(personParams.getFirst(LATITUDE));
        double longitude =Double.parseDouble(personParams.getFirst(LONGITUDE));
              
        //Person newPerson = new Person(name,latitude,longitude); 
        //System.out.println("Storing person: " + newPerson.getName() + " " + newPerson.getLongitude() + " " + newPerson.getLatitude());
        //mpm.persistPerson(newPerson);
         
        return "Person Posted";
                         
    }
    
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Person registerPerson(
            MultivaluedMap<String, String> personParams
            ) {
        String name = personParams.getFirst(NAME);
        String city = personParams.getFirst("city");
        String country = personParams.getFirst("country");
        String partner_email = personParams.getFirst("partner_email");
        String gender = personParams.getFirst("gender");
        String email = personParams.getFirst("email");
        
        Person aPerson = new Person(name, city, country, partner_email, email, gender); 
        System.out.println("Storing person: " + aPerson.getName() + ", " + aPerson.getCity() + ", " +aPerson.getCountry() +", " + aPerson.getEmail() +", " + aPerson.getPartner_email() + ", " +aPerson.getGender() );
        
        int result = mpm.persistPerson(aPerson);
        
        if(result == 1)
        return aPerson;
        else
        	return null;
         
    }
    
    @GET
    @Path("get_person_by_name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonName(@PathParam("name") final String name) {
        
        System.out.println("checking person: " + name );
        Person person = mpm.getPersonByName(name);
         if(person != null)
         {
        	 System.out.println("Returning Person" + person.getName());
        	 return person;
         }
        	 
         else
         {
        	 System.out.println("Not Found Person");
        	 return null;
         }
        	 
    }
    @GET
    @Path("get_person_by_email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByEmail(@PathParam("email") String email) {
        
        System.out.println("checking person: " + email );
        Person person = mpm.getPersonByEmail(email);
         if(person != null)
         {
        	 System.out.println("Returning Person" + person.getName());
        	 return person;
         }
        	 
         else
         {
        	 System.out.println("Not Found Person");
        	 return null;
         }
        	 
    }
//    @GET
//    @Path("check_person")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.TEXT_PLAIN)
//    public String checkExist(
//            MultivaluedMap<String, String> personParams
//            ) {
//        String email = personParams.getFirst("email");
//        
//        System.out.println("checking person with email: " + email );
//        int result = mpm.checkPerson(email);
//         if(result == 1)
//         {
//        	 System.out.println("Found Person");
//        	 return "Found Person";
//         }
//        	 
//         else
//         {
//        	 System.out.println("Not Found Person");
//        	 return "Not Found Person";
//         }
//        	 
//    }
    
    
}