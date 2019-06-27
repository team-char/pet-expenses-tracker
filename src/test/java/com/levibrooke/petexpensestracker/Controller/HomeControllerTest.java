package com.levibrooke.petexpensestracker.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")

public class HomeControllerTest {


    @Autowired
    HomeController homeController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    public static RequestPostProcessor testUser(){
        return user("Confucius").password("password").roles("ADMIN");
    }

    @Test
    public void testIntegrationAbout() throws Exception{
        mockMvc.perform(get("/about-us")).andExpect(content().string(containsString("About Us")));
    }

    @WithMockUser
    @Test
    public void testIntegrationDashboard() throws Exception{
    mockMvc.perform(get("/dashboard").with(testUser())).andExpect(content().string(containsString("Dashboard")));

    }
    @WithMockUser
    @Test
    public void testIntegrationAllExpenses() throws Exception{
        mockMvc.perform(get("/all-expenses").with(testUser())).andExpect(content().string(containsString("All Expenses")));

    }
    @WithMockUser
    @Test
    public void testIntegrationEditExpense() throws Exception{
        mockMvc.perform(get("/edit-expense/2").with(testUser())).andExpect(content().string(containsString("Edit Expense")));

    }

}