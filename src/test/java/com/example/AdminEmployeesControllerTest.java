package com.example;

import com.example.controller.AdminEmployeesController;
import com.example.model.entity.Employee;
import com.example.model.entityrepository.EmployeeRepository;
import com.example.security.SecurityConfig;
import com.example.service.EmployeeDetailsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.isNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminEmployeesControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private EmployeeRepository employeeRepository;
    private Gson gson = new GsonBuilder().create();
    private List<Employee> employeesList = new ArrayList<>();


    @Before
    public void setUpExpectedResult(){

        Employee employee = new Employee();
        employee.setName("Employee1");
        employee.setUsername("Employee1");
        employee.setSalary(1000);
        employee.setRole("EMPLOYEE");
        employee.setPassword(passwordEncoder.encode("sample"));
        employeesList.add(employee);

        employee = new Employee();
        employee.setName("Employee2");
        employee.setUsername("Employee2");
        employee.setSalary(2000);
        employee.setRole("EMPLOYEE");
        employee.setPassword(passwordEncoder.encode("sample"));
        employeesList.add(employee);


        employee = new Employee();
        employee.setName("Employee3");
        employee.setUsername("Employee3");
        employee.setSalary(2000);
        employee.setRole("EMPLOYEE");
        employee.setPassword(passwordEncoder.encode("sample"));
        employeesList.add(employee);

        when(employeeRepository.findAll()).thenReturn(employeesList);


    }

    @Test
    @WithMockUser(username ="boss", roles = {"MANAGER"})
    public void testManagerRoleAccess() throws Exception{

        //check for open path
        this.mvc.perform(get("/"))
                .andExpect(status().isOk());

        //check for authorized path
        this.mvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].salary").doesNotExist());

        //check for not authorized path
        this.mvc.perform(get("/admin/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].salary").exists());


    }

    @Test
    @WithMockUser
    public void testAdminRoleAccess() throws Exception{

        this.mvc.perform(get("/")
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());

        this.mvc.perform(get("/admin/employees")
        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].salary").exists());


        this.mvc.perform(get("/employees")
                .with(user("admin").roles("ADMIN")))
                .andExpect(status().is4xxClientError());

    }

    @Test
    @WithMockUser(username ="employee", roles = {"EMPLOYEE"})
    public void testEmployeeRoleAccess() throws Exception{

        //check for open path
        this.mvc.perform(get("/"))
                .andExpect(status().isOk());

        //check for authorized path
        this.mvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].salary").doesNotExist());

        //check for not authorized path
        this.mvc.perform(get("/admin/employees"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithAnonymousUser
    public void testAnonymousUserAccess() throws Exception{

        this.mvc.perform(get("/"))
                .andExpect(status().isOk());

        this.mvc.perform(get("/admin/employees"))
                .andExpect(status().is4xxClientError());

        this.mvc.perform(get("/employees"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void okResponseWithBasicAuthCredentialsForKnownUser() throws Exception {

        when(employeeRepository.findByUsername("Employee2")).thenReturn(employeesList.get(1));



        this.mvc
                .perform(get("/employees").header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("Employee2:sample".getBytes())))
                .andExpect(status().isOk());
    }


}
