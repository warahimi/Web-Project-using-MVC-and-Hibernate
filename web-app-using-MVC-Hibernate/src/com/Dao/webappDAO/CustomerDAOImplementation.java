package com.Dao.webappDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import cim.webapp.entity.Customer;

@Repository // always applied to DAO implementations ,Spring will scan and find theis and handle the exception translation for us
public class CustomerDAOImplementation implements CustomerDAO {

	//Injection the hibernate SessionFactory , so DAO can use to talk to DB
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	//@Transactional  // remove the @Transactional from here and move it at Service implementation method
	public List<Customer> getCustomers() {
		// get the current hibernate session 
		Session currentSession = sessionFactory.getCurrentSession(); //sessionFactory is injected above
		
		//create a query ... sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class); // making using of HQL
		
		//execute the query and get the result // the query will return a list of customers
		List<Customer> customers = theQuery.getResultList();
		
		//return the query
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//Get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//Save / update the customer to database .. finally..
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		//getting the current hibernate Session 
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read the customer from database using the primary key id
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get the current hibernate session 
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete the object with the primary key of id that is passed in 
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate(); // this executeUpdate() is a generic general purpose method whatever hql we have it will process it accordingly
		
	}

	@Override
	public List<Customer> searchCustomer(String theSearchName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
					Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results
		return customers;
	}

}
