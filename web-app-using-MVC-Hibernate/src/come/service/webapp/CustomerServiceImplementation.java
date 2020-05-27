package come.service.webapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dao.webappDAO.CustomerDAO;

import cim.webapp.entity.Customer;

@Service  // added to the service implementation
public class CustomerServiceImplementation implements CustomerService {

	//need to inject the CustomerDAO, because the service depend on DAO
	@Autowired
	private CustomerDAO customerDAO;  // reference of CustomerDAO
	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
			
		return customerDAO.getCustomers(); // delage / hand over the call to DAO and return what ever the DAO has
	}


	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
		
	}


	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return customerDAO.getCustomer(theId);
	}


	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		customerDAO.deleteCustomer(theId);
		
	}


	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		
		return customerDAO.searchCustomer(theSearchName);
	}

}
