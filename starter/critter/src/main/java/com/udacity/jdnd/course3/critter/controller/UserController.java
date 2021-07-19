package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.controller.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.controller.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.controller.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.controller.dto.PetDTO;
import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.entities.user.Customer;
import com.udacity.jdnd.course3.critter.repository.entities.user.Employee;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        Customer customer = convertCustomerDTOtoEntity(customerDTO);

        return convertEntityToCustomerDTO(customerService.save(customer));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

        return customerService.findAllCustomers().stream().map(customer -> convertEntityToCustomerDTO(customer)).collect(Collectors.toList());
    }

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees(){

        return employeeService.findAllEmployees().stream().map(employee -> convertEntityToEmployeeDTO(employee)).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    @SneakyThrows
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        return convertEntityToCustomerDTO(customerService.findOwnerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = convertEmployeeDTOtoEntity(employeeDTO);

        return convertEntityToEmployeeDTO(employeeService.save(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        return convertEntityToEmployeeDTO(employeeService.findById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    @SneakyThrows
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setsAvailableDays(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    public static CustomerDTO convertEntityToCustomerDTO(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();
        if(customer != null) {
            BeanUtils.copyProperties(customer, customerDTO);
            if (customer.getPets() != null)
                customerDTO.setPetIds(customer.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        }

        return customerDTO;
    }

    public static Customer convertCustomerDTOtoEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public static EmployeeDTO convertEntityToEmployeeDTO(Employee employee){

        EmployeeDTO employeeDTO = new EmployeeDTO();
        if(employee != null)
            BeanUtils.copyProperties(employee, employeeDTO);

        return employeeDTO;
    }

    public static Employee convertEmployeeDTOtoEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

}
