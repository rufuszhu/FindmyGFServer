package com.rufus.rest.resource;

import javax.jdo.*;
import javax.jdo.Query;
import javax.jdo.annotations.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.List;

import com.rufus.rest.model.*;

public class myPersistenceManager {
	
    /** Factory for creating persistence managers. */	
	private static final PersistenceManagerFactory pmf =
		      JDOHelper.getPersistenceManagerFactory("transactions-optional");


/**
 * method to persist a new person into the data store
 * if there is no previous post of this user, add new mood
 * if there is previous post, replace it
 * @param aMood
 * @return 1 if succeed, 0 if fail
 */
@SuppressWarnings("unchecked")
public int persistPerson(Person person){
	PersistenceManager pm=pmf.getPersistenceManager();
	
	try{

		System.out.println("persisting person");		
		Key k = KeyFactory.createKey(Person.class.getSimpleName(), person.getEmail());
		Query q = pm.newQuery(Person.class,"email == aemail");
		
		q.declareParameters("String aemail");
		List<Person> list = (List<Person>)q.execute(person.getEmail());
		
		//if this user is not in the database
		if(list.isEmpty()){
			System.out.println("new person "+person.getName());
	        // Persist it to the datastore
			person.setKey(k);
	        pm.makePersistent(person);
	        
		}
		else{
			Person p = list.get(0);
			System.out.println("update person "+person.getName());
			p.setCity(person.getCity());
			p.setCountry(person.getCountry());
			p.setEmail(person.getEmail());
			p.setGender(person.getGender());
			p.setName(person.getName());
			p.setPartner_email(person.getPartner_email());
			 
		}
		

		//posting succeeded, return 1
		return 1;
	}finally{

		pm.close();
		
	}
	   
	
	
}
/**
 * helper method to update a person's location
 * @param a person with his new location
 * @return 1 if succeed, 0 if fail
 */
@SuppressWarnings("unchecked")
private int updatePersonLocation(Person person){
	PersistenceManager pm=pmf.getPersistenceManager();
	
	try{	
		//get person by id
		Key k = KeyFactory.createKey(Person.class.getSimpleName(), person.getName());
		Person p = pm.getObjectById(Person.class, k);
	
		//update
		p.setLatitude(person.getLatitude());
		p.setLongitude(person.getLongitude());	
    }finally{
    	pm.close();
    }
    
	return 1;
	
}




/**
 * method to retrieve all moodCards in the datebase
 * method calls getTimeDiff() 
 * @return all moodCards in data store
 * @throws ParseException,NoMoodCardException
 */
//@SuppressWarnings("unchecked")
//public ArrayList<Person> getAll()throws NoMoodCardException, ParseException{
//	PersistenceManager pm = pmf.getPersistenceManager();
//    ArrayList<Person> allMoods =new ArrayList<Person>();
//    try{
//    Query q = pm.newQuery(Person.class);
//    q.setOrdering("id asc");
//    List<Person> result = (List<Person>)q.execute();
//    
//    if(result.isEmpty())
//    	throw new NoMoodCardException();
//    
//    for(Person p:result){
//    	m.setTimeDiff(getTimeDiff(m));
//        allMoods.add(m);
//     }
//    }
//    finally{
//    	pm.close();
//    }
//    return allMoods;
//}
/**
 * 	method to delete all items in database
 */
//public void removeAll(){
//		PersistenceManager pm = pmf.getPersistenceManager();
//	    try{
//		    Query q = pm.newQuery(Mood.class);
//		    //q.setOrdering("id asc");
//		    q.deletePersistentAll();
//		    
//	    }finally{
//	    	pm.close();
//	    }
//	}
	/**
	 * method to calculate time difference between current time and mood card time
	 * 
	 * @throws ParseException
	 * @return a string of the difference between current time and mood card publish time 
	 */
//private String getTimeDiff(Mood mood) throws ParseException{
//	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
//	
//	Date moodDate =(Date)sdf.parse(mood.getDateTime());
//	Date now = new Date();
//	String time="";
//	int diffInMins = (int)(now.getTime() - moodDate.getTime() )  / (1000 * 60);
//	
//	
//	//case 0 min
//	if(diffInMins == 0){
//		time = "0 minute ago";
//	}
//	//case: less than 1 hr
//	else if(diffInMins < 60 && diffInMins > 0){
//		time=Integer.toString(diffInMins);
//		time+= " minutes ago";
//	}
//		
//	//case: less than 1 day, more than 1hr;
//	else if(diffInMins >= 60 && diffInMins < 60*24 ){
//		int diffInHrs = diffInMins / 60 ;
//		time=Integer.toString(diffInHrs) + " hours ago";
//	}
//		
//	//case: over 1 day
//	else if(diffInMins >= 60*24){
//		int diffInDays = diffInMins/ (60 * 24);
//		time=Integer.toString(diffInDays) + " days ago";
//	}
//	else
//		time = Integer.toString(diffInMins)+" :this is an error message";
////	System.out.println("persisted moodcard datetime:"+mood.getDateTime());
////	System.out.println("current time:"+sdf.format(now));
////	System.out.println(time);
//	return time;
//	
//}

}
