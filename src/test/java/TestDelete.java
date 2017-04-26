import application.Application;
import bean.requete.Auth;
import bean.requete.SetInformation;
import com.google.gson.Gson;
import controller.GetController;
import controller.PostController;
import controller.PutController;
import enumerate.StatusReponse;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by DIDESJ on 26/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GetController.class, PutController.class, PostController.class, Application.class})
@WebAppConfiguration
public class TestDelete extends TestCase {


    private static Auth log;
    private static Gson gson;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    private SetInformation info;

    private String cle;
    private String value;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        log = new Auth("didesj", "123456");

        cle = "int";
        value = "4";
        info = new SetInformation(cle, value);
        gson = new Gson();

        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(log)))
                .andExpect(status().isAccepted());

        mockMvc.perform(post("/{username}/set/", log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(info)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status", is("OK")));
    }

    @After
    public void afterEnviroment() throws Exception {
        mockMvc = null;
    }

    @Test
    public void testDelete() throws Exception{
        this.mockMvc.perform(delete("/{username}/delete/{cle}", log.getUser(), cle))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/{username}/key/{cle}", log.getUser(), cle))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusReponse.KEY_NOT_FOUND.toString())));
    }

}
