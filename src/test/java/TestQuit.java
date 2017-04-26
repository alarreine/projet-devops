import application.Application;
import bean.requete.Auth;
import bean.requete.SetInformation;
import com.google.gson.Gson;
import controller.GetController;
import controller.PostController;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by alarreine on 12/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GetController.class, PostController.class, Application.class})
@WebAppConfiguration
public class TestQuit extends TestCase {


    private static Auth log;
    private static Gson gson;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    /**
     * Il faut se connecter avant de faire un requête avec /auth
     *
     * @throws Exception
     */
    @Before
    public void setUpEnviroment() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        log = new Auth("didesj1", "123456");
        gson = new Gson();

        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(log)))
                .andExpect(status().isAccepted());

    }

    @Test
    public void testQuitDuService() throws Exception {
        String cle = "cle";
        this.mockMvc.perform(get("/{username}/quit/", log.getUser(), cle))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusReponse.OK.toString())));
    }

}