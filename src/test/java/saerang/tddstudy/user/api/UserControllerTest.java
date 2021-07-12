package saerang.tddstudy.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import saerang.tddstudy.user.domain.User;
import saerang.tddstudy.user.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @Test
    void addUser() throws Exception {
        //given
        User user = new User("saerang", "tdd-master");
        String userJson = objectMapper.writeValueAsString(user);

        //when
        ResultActions resultActions = mockMvc.perform(post("/user").contentType(APPLICATION_JSON)
                .content(userJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(user.getName()));

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        User responseUser = objectMapper.readValue(contentAsString, User.class);

        em.flush();
        em.clear();
        User findUser = userRepository.findById(responseUser.getId()).get();

        //then
        assertThat(findUser.getId()).isEqualTo(responseUser.getId());
    }
}