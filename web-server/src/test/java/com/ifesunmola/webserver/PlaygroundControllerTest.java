package com.ifesunmola.webserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaygroundController.class)
class PlaygroundControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void noCodeProvidedShouldFail() throws Exception {
		mockMvc.perform(post("/run"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("""
					{
						"isSuccess": false,
						"msg": "Nothing to compile!"
					}
				"""));
	}

	@Test
	void validCodeProvidedShouldSucceed() throws Exception {
		String code = """
			fn main (){
				_ = yapln("Hello, World!")
			}
			""";
		mockMvc.perform(post("/run").content(code))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json("""
					{
						"isSuccess": true,
						"msg": "Hello, World!"
					}
				"""));
	}

	@Test
	void invalidCodeProvidedShouldFail() throws Exception {
		String code = """
			fn main (){
				_ = yapln("Hello, World!"
			}
			""";
		mockMvc.perform(post("/run").content(code))
			.andExpect(status().isOk())
			.andExpect(content().string(stringContainsInOrder("isSuccess", "false")))
			.andExpect(content().string(containsString("on line 3")));

	}
}