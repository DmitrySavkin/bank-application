package org.bank.bank;

import org.bank.bank.controller.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BankApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CustomerController controller;

	@Test
	public void customerPageTest() throws  Exception{
		this.mockMvc.perform(get("/customers/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Customer")))
				.andExpect(content().string(containsString("Firstname")))
				.andExpect(content().string(containsString("Lastname")))
				.andExpect(content().string(containsString("Email")))
				.andExpect(content().string(containsString("Passnumber")));

		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Customer")))
				.andExpect(content().string(containsString("Firstname")))
				.andExpect(content().string(containsString("Lastname")))
				.andExpect(content().string(containsString("Email")))
				.andExpect(content().string(containsString("Passnumber")));
	}

	@Test
	public void productPageTest() throws  Exception{
		this.mockMvc.perform(get("/products/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Title")))
				.andExpect(content().string(containsString("Description")));


	}

}
