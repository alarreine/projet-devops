import application.Application;
import bean.requete.Auth;
import bean.requete.SetInformation;
import com.google.gson.Gson;
import controller.GetController;
import controller.PostController;
import controller.PutController;
import enumerate.StatusReponse;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GetController.class, PutController.class, PostController.class, Application.class})
@WebAppConfiguration
public class TestPrueba {


    private Auth log;
    private Gson gson;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUpEnviroment() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        log = new Auth("didesj", "123456");
        gson = new Gson();

        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(log)))
                .andExpect(status().isAccepted());

    }
    @After
    public void afterEnviroment() throws Exception {
        mockMvc =null;
    }


    @Test
    public void testGetCleInexistante() throws Exception {
        String cle = "cle";
        this.mockMvc.perform(get( "/{username}/key/{cle}", log.getUser(),cle))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusReponse.KEY_NOT_FOUND.toString())))
        .andDo(print());
    }


    @Test
    public void testSetCreation() throws Exception {
        String cle = "cle";
        String valeur = "contenu de cle'";
        SetInformation info = new SetInformation(cle, valeur);
        String url = "/" + log.getUser() + "/set";
        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(info)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status", is("OK")))
                .andDo(print());
    }


    @Test
    public void testGetCleExistant() throws Exception{

        String cle = "cle";
        String valeur = "contenu de cle'";
        SetInformation info = new SetInformation(cle,valeur);
        this.mockMvc.perform(post("/{username}/set",log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(info)))
                .andExpect(status().isAccepted());

        this.mockMvc.perform(get("/{username}/key/{cle}",log.getUser(),cle))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusReponse.OK.toString())))
                .andExpect(jsonPath("$.info", hasSize(1)))
                .andExpect(jsonPath("$.info.[0]", is(valeur)));
    }

}