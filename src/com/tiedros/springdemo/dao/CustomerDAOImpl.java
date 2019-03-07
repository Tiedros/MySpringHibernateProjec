package com.tiedros.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tiedros.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	//@Transactional
	public List<Customer> getCustomers() {
		
		// get the current hibernate session 
		Session currentSession=sessionFactory.getCurrentSession();
		
		// create query
		Query<Customer> theQuery=currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers=theQuery.getResultList();
		
		// return result
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// get current session 
		Session session=sessionFactory.getCurrentSession();
		
		// get customer from DB
		return session.get(Customer.class, theId);
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get current session 
		Session session=sessionFactory.getCurrentSession();
		
		// delete customer 
		Query theQuery=session.createQuery("delete from Customer where id=:theCustomerId");
		theQuery.setParameter("theCustomerId", theId);
		
		theQuery.executeUpdate();
		
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		 // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	}

}
