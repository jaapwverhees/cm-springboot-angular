package com.verhees.cm.cookie;

import com.verhees.cm.config.SecurityConfig;
import com.verhees.cm.security.CustomUserDetailsService;
import com.verhees.cm.security.TokenProperties;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@RunWith(SpringRunner.class)
//@WebMvcTest(value = CookieController.class)
@Import({TokenProperties.class, BCryptPasswordEncoder.class, CustomUserDetailsService.class, SecurityConfig.class})
public class CookieControllerTest {

//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    @WithMockUser(roles = "USER")
//    public void getsCookies() throws Exception {
//        mvc.perform(get("/api/cookies")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.[0].flavour", is("chocolate")))
//                .andExpect(jsonPath("$.[1].flavour", is("vanilla")))
//                .andExpect(status().isOk());
//    }

}
