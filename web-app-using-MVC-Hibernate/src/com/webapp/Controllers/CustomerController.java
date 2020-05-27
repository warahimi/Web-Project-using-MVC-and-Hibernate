package com.webapp.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Dao.webappDAO.CustomerDAO;

import cim.webapp.entity.Customer;
import come.service.webapp.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
//	
//	//injecting the DAO to this controller
//	@Autowired
//	private CustomerDAO customerDAO; // it will find the CustomerDAO implementation clss that has @Repository and inject it
//	Delete line 20 and 20 because now our Controller doesnt directly talk to DAO, it talks to service and service then talks to the  DAO
	
	//so we need to inject the Service impl
	@Autowired
	private CustomerService customerService;
	
	
	
	//@RequestMapping("/list")
	//@PostMapping("/list")
	@GetMapping("/list") // now the controller will handle only get requests
	public String listCustomet(Model theModel)
	{
		//using the DAO
		//Get the customers from DAO 
//		List<Customer> theCustomers = customerDAO.getCustomers();
		
		//deligating call to Service instead of DAO
		List<Customer> theCustomers = customerService.getCustomers(); // cant directly talk to DAO any more talk to Service Directly
		
		//add those customers to the SpringMVC model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customer";
	}
	
	
	//Controller Method to handle the Add Customer button request (/showFormForAdd)
	@GetMapping("/showFormForAdd")
	public String addCustomer(Model theModel)
	{
		//Create a model attribute to bind form data
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer", theCustomer); // we will use the customer in our html form to buidl the fields
		
		
		return "add-customer-form";
	}
	
	
	//code to handle the form comes from save / add cutomer form ("/saveCustomer")
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		//Save the customer using service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	
	//Method to handle the update request /showFormForUpdate
	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int theId,Model theModel)
	{
		
		//Get the customer from DB
		Customer theCustomer = customerService.getCustomer(theId);
		
		//set Customer as model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer); // setting theCustomer that we just retrieved to the model object
		
		//send it over to our form
		return "add-customer-form";
	}
	
	//Code to handle request from Delete /delete
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId)
	{
		//Delete customer
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}

}
