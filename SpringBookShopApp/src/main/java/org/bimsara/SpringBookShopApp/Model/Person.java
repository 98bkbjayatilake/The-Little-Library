package org.bimsara.SpringBookShopApp.Model;

import jakarta.persistence.MappedSuperclass;
import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
public abstract class Person extends BaseEntity<String>{
    protected String NIC;
    protected  String firstName;
    protected  String lastName;
    protected String phoneNumber;
    protected String email;

    public Person(){

    }
    public Person(String NIC, String firstName,String lastName, String phoneNumber,String email){
        this.NIC=NIC;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.email=email;
    }

    //Static map to track counts of duplicate first and last names
    private static final Map<String,Integer> nameCounterPerson=new HashMap<>();

    /*
     *This HashMap is static field, shared across all the Author and Customer instances
     * Key-A combination of firstname and lastName(e.g-Kevin_Peterson)
     * Value-An integer counter that tracks how many authors/customers share the same name and lack an NIC*/

    protected void generateIdForPerson(String prefix){
        //Generate ID only if not already set
        if(this.id==null || this.id.isEmpty()){
            if(this.NIC!=null && !this.NIC.isEmpty()) {
                //if NIC exists ,use it as part o the ID
                setId(prefix+"_"+firstName+"_"+NIC);
            }else{
                // For foreign authors/customers or those without NIC
                String nameKey=firstName+"_"+lastName;
                /*
                 * Combines firstName and lastName into a String to use as the map key
                 */
                int count=nameCounterPerson.getOrDefault(nameKey,0)+1;
                /*
                 * What Happens:
                 * If nameKey exists in the map(e.g-Kevin_Peterson),gets its value and increment by 1
                 * If doesn't exist ,return 0(default value), then increment to 1
                 */
                nameCounterPerson.put(nameKey,count);//Update the map
                setId(prefix+"_"+firstName+"_"+lastName+"_"+count);
            }
        }
    }

}
