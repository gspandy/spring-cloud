package com.xmair.eureka;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

        private MockMvc mockMvc;
        @Before
        public void setUp() {
            mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
            System.out.println("Before");
        }
        @Test
        public void getStudentList() throws Exception {


            mockMvc.perform(MockMvcRequestBuilders.get("/user/hello111?name=xxx"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            //.andExpect(MockMvcResultMatchers.content().string("365"));  //测试接口返回内容
        }
}