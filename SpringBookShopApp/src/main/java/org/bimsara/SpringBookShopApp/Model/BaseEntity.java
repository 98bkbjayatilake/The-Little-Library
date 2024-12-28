package org.bimsara.SpringBookShopApp.Model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Objects;

/**
 *Abstract class to handle common hashcode and equals methods with generics
 *
 * @param <T>The type of the entity ID(e.g.,String,Long, UUID).
 */
@MappedSuperclass
public abstract class BaseEntity<T>{
    @Id
    protected T id;

    public abstract T getId();

    public void setId(T id){
        this.id=id;
    }
    @Override
    public int hashCode(){
        /*
        *If id is null ,calling id.hashCode() would throw a NullPointerException
        * Objects.hash() prevents this issue and gracefully returns 0 for null
         */
        return  Objects.hash(getId());
    }
    @Override
    public boolean equals(Object o){
        if(this==o)return true;
    //Strict type checking
        if(o==null || getClass()!=o.getClass())return false;
    /*
    *
    * The casting (BaseEntity<?>) explicitly tells the compiler:"Trust me,I know this object is a BaseEntity,even though it was passed as an object "
    *
    * Why <?> Instead of <T>
    *<?>  is called an unbounded wildcard in Java Generics.
    *It means:"I don't care about the specific type of the generic parameter here.
    *  Just treat it as a BaseEntity with some unknown type."
    * */
        BaseEntity<?> that=(BaseEntity<?>) o;
    //Now that both this and that are recognized as BaseEntity objects,we compare their IDs.
        return  Objects.equals(getId(),that.getId());
    }
}

