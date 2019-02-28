package com.levelp.example.web;

import com.levelp.example.UserDAO;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;
import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestWebConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import(TestDataConfiguration.class)
public class IndexControllerTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private Filter securityFilter;

    @Before
    public void configure(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(securityFilter)
                .apply(springSecurity())      // конфигурируем са security
                .build();
    }

    // тест для незалогированного user:
    @Test
    public void indexPageTest() throws Exception {

        userDAO.inviteUser("engineer", "user1");

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bean"))
                // IndexPageBeanBaseMatcher("") - для пустого незалогированного пользователя:
                .andExpect(model().attribute("bean", new IndexPageBeanBaseMatcher("")))
                .andReturn();
    }

    // для залогированного user:
    @Test
    public void indexPageTestWithUser() throws Exception {
        userDAO.inviteUser("engineer", "user1");

        mockMvc.perform(MockMvcRequestBuilders.get("/").
                with(user("user1").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bean"))
                .andExpect(model().attribute("bean", new IndexPageBeanBaseMatcher("user1")))
                .andReturn();
    }

    private static class IndexPageBeanBaseMatcher extends BaseMatcher<IndexPageBean> {

        private final String expectedUserName;

        private IndexPageBeanBaseMatcher(String expectedUserName) {
            this.expectedUserName = expectedUserName;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("index page bean");
        }

        @Override
        public boolean matches(Object o) {

            // проверяем Bean:
            if (!(o instanceof IndexPageBean)) return false;
            IndexPageBean bean = (IndexPageBean) o;

            if (bean.getCurrentDate() == null) return false;

            // в частности, пользователей, которые там были заполнены:
            if (bean.getUsers() == null) return false;
            if (bean.getUsers().stream().noneMatch(user ->
                    Objects.equals(user.getLogin(), "user1")
            )) {
                return false;
            }

            // проверим, что userName тот, который мы ожидаем получить:
            if (!expectedUserName.equals(bean.getCurrentUserName())){
                return false;
        }
            return true;
        }
    }
}