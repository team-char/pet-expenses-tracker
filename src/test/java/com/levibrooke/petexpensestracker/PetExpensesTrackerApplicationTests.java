package com.levibrooke.petexpensestracker;

import com.levibrooke.petexpensestracker.Controller.ExpenseController;

import com.levibrooke.petexpensestracker.Model.Expense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class PetExpensesTrackerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void getCreatedAt() {
		Expense expense = new Expense();
		expense.setCreatedAt(new Date());
		assertEquals(new Date(), expense.getCreatedAt());
	}

	@Test
	public void getExpenseDate() {
		Expense expense = new Expense("this is the description",4.9,"1864-12-12","Misc");
		assertEquals(expense.getExpenseDate().toString(),"Mon Dec 12 00:00:00 PST 1864");
	}


	@Test
	public void getUpdatedAt() {
		Expense expense = new Expense();
		expense.setUpdatedAt(new Date());
		assertEquals(new Date(), expense.getUpdatedAt());

	}

	@Test
	public void getDescription() {
		Expense expense = new Expense("this is the description",4.9,"1864-12-12","Misc");
		assertTrue(expense.getDescription() == "this is the description");
	}

	@Test
	public void getAmount() {
		Expense expense = new Expense("this is the description",4.9,"1864-12-12","Misc");
		assertTrue(expense.getAmount() == 4.9);

	}


	@Test
	public void setCreatedAt() {
		Expense expense = new Expense();
		expense.setCreatedAt(new Date());
		Date expected = new Date();
		Date actual = expense.getCreatedAt();
		assertEquals(expected, actual);


	}

	@Test
	public void setDescription() {

			Expense expense = new Expense();
			expense.setDescription("description");
			String result = expense.getDescription();

			assertEquals("description", result);
	}

	@Test
	public void setAmount() {
		Expense expense = new Expense();
		expense.setAmount(4.9);
		double result = expense.getAmount();

		assertEquals(4.9,result,0);
	}

	@Test
	public void getCategoryName() {
		Expense expense = new Expense("this is the description",4.9,"1864-12-12","Misc");
		assertTrue(expense.getCategoryName() == "Misc");
	}

	@Test
	public void setCategoryName() {
		Expense expense = new Expense();
		expense.setCategoryName("vet");
		String result = expense.getCategoryName();

		assertEquals("vet", result);

	}
	@Autowired
	ExpenseController expenseController;

	@Autowired
	MockMvc mockMvc;



	@Test
	public void testControllerIsAutoWired(){
		assertNotNull(expenseController);
	}

	@Test
	@WithMockUser
	public void testIntegrationExpense() throws Exception{
		mockMvc.perform(get("/create-expense")).andExpect(content().string(containsString("Add an expense!")));
	}

	@Test
	@WithMockUser
	public void testIntegrationSuccess() throws Exception{
		mockMvc.perform(get("/success")).andExpect(content().string(containsString("Success!")));
	}

}
