package com.levelp.example.web;

import com.levelp.example.UserDAO;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IndexControllerTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void configure(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void indexPageTest() throws Exception {

        userDAO.inviteUser("engineer", "user1");

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bean"))
                .andExpect(model().attribute("bean", new BaseMatcher<IndexPageBean>() {
                    @Override
                    public void describeTo(Description description) {
                        description.appendText("index page bean");
                    }

                            @Override
                            public boolean matches(Object o) {
                                if (!(o instanceof IndexPageBean)) return false;
                                IndexPageBean bean = (IndexPageBean) o;

                                if (bean.getCurrentDate() == null) return false;

                                if (bean.getUsers() == null) return false;
                                if (bean.getUsers().stream().noneMatch(user ->
                                        Objects.equals(user.getLogin(), "user1")
                                )) {
                                    return false;
                                }

                                return true;
                            }
                        })).andReturn();
    }
}