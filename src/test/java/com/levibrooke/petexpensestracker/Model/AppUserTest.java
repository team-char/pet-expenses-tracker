package com.levibrooke.petexpensestracker.Model;

import com.levibrooke.petexpensestracker.Controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class AppUserTest {
    @Test
    public void testGetUserName(){
        AppUser appUser = new AppUser("userNameInfo","password","petName","desertCrawler");

        assertTrue(appUser.getUsername() == "userNameInfo");
    }
    @Test
    public void testGetPassword(){
        AppUser appUser = new AppUser("userNameInfo","password","petName","desertCrawler");

        assertTrue(appUser.getPassword() == "password");
    }
    @Test
    public void testGetPetName(){
        AppUser appUser = new AppUser("userNameInfo","password","petName","desertCrawler");

        assertTrue(appUser.getPetName() == "petName");
    }
    @Test
    public void testGetPettype(){
        AppUser appUser = new AppUser("userNameInfo","password","petName","desertCrawler");

        assertTrue(appUser.getPetType() == "desertCrawler");
    }

    @Autowired
    HomeController homeController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testControllerIsAutoWired(){
        assertNotNull(homeController);
    }

    @Test
    public void testIntegrationHome() throws Exception{
        mockMvc.perform(get("/")).andExpect(content().string(containsString("")));

    }


}