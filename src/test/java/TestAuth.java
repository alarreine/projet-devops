import application.Application;
import bean.requete.Auth;
import bean.requete.Increase;
import bean.requete.RenameKey;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by alarreine on 12/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GetController.class, PutController.class, PostController.class, Application.class})
@WebAppConfiguration
public class TestAuth extends TestCase {


    private static Auth log;
    private static Gson gson;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    private String cle="Cles";


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        log = new Auth("didesj", "123456");
        gson= new Gson();
    }

    @After
    public void afterEnviroment() throws Exception {
        mockMvc = null;
    }


    @Test
    public void testGetSansAuth() throws Exception {
        this.mockMvc.perform(get("/{username}/exist/{cle}", log.getUser(), cle))
                .andExpect(status().isUnauthorized());


    }
    @Test
    public void testSettSansAuth() throws Exception {
        String valeur = "contenu de cle'";
        SetInformation info = new SetInformation(cle, valeur);
        String url = "/" + log.getUser() + "/set";
        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(info)))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void testIncreaseSansAuth() throws Exception {
        Increase increase = new Increase(cle, 6);
        mockMvc.perform(put("/{username}/increase/", log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(increase)))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void testDeleteSansAuth() throws Exception {
        this.mockMvc.perform(delete("/{username}/delete/{cle}", log.getUser(), cle))
                .andExpect(status().isUnauthorized());


    }
    @Test
    public void testExistSansAuth() throws Exception {
        this.mockMvc.perform(get("/{username}/exist/{cle}", log.getUser(), cle))
                .andExpect(status().isUnauthorized());


    }

    @Test
    public void testRenameSansAuth() throws Exception {
        RenameKey rename1 = new RenameKey(cle, cle.concat("Rename"));


        mockMvc.perform(put("/{username}/rename/", log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(rename1)))
                .andExpect(status().isUnauthorized());

    }

}
