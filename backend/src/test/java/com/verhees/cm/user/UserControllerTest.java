package com.verhees.cm.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verhees.cm.config.SecurityConfig;
import com.verhees.cm.controller.UserController;
import com.verhees.cm.security.CustomUserDetailsService;
import com.verhees.cm.security.TokenProperties;
import com.verhees.cm.model.user.User;
import com.verhees.cm.model.user.UserCredentials;
import com.verhees.cm.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@Import({TokenProperties.class, BCryptPasswordEncoder.class, CustomUserDetailsService.class, SecurityConfig.class})
public class UserControllerTest {

    private JacksonTester<User> userJacksonTester;
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void register() throws Exception {
        UserCredentials userCredentials = new UserCredentials("user", "password", "ROLE_USER");
        User user = new User(userCredentials);
        given(userService.registerDefaultUser(user))
                .willReturn(user);

        mvc.perform(post("/api/users")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJacksonTester.write(user).getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userCredentials.username", is(user.getUserCredentials().getUsername())));
    }
}
