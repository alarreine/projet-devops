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
@SpringBootTest(classes = {GetController.class, PutController.class,PostController.class, Application.class})
@WebAppConfiguration
public class TestUpDate extends TestCase {


    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext wac;

    private static Auth log;
    private static Gson gson;

    private SetInformation infoInt;
    private SetInformation infoString;

    private String cleInfoInt;
    private String cleInfoString;
    private String valueInt;
    private String valueString;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        log = new Auth("didesj", "123456");

        cleInfoInt = "int";
        cleInfoString = "string";
        valueInt = "4";
        valueString = "bonjour";
        infoInt = new SetInformation(cleInfoInt,valueInt);
        infoString = new SetInformation(cleInfoString,valueString);
        gson = new Gson();

        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(log)))
                .andExpect(status().isAccepted());

        mockMvc.perform(post("/{username}/set/",log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(infoInt)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status", is("OK")));

        mockMvc.perform(post("/{username}/set/",log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(infoString)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.status", is("OK")));
    }
    @After
    public void afterEnviroment() throws Exception {
        mockMvc =null;
    }


    @Test
    public void testRenameKey() throws Exception{
        RenameKey rename1 = new RenameKey(cleInfoString, cleInfoString.concat("Rename"));
        RenameKey rename2 = new RenameKey(cleInfoInt, cleInfoInt.concat("Rename"));

        mockMvc.perform(put("/{username}/rename/",log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(rename1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")));

        mockMvc.perform(get("/{username}/key/{cle}",log.getUser(),rename1.getNewKey()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusReponse.OK.toString())))
                .andExpect(jsonPath("$.info", hasSize(1)))
                .andExpect(jsonPath("$.info.[0]", is(valueString)));

        mockMvc.perform(put("/{username}/rename/",log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(rename2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")));

        mockMvc.perform(get("/{username}/key/{cle}",log.getUser(),rename2.getNewKey()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusReponse.OK.toString())))
                .andExpect(jsonPath("$.info", hasSize(1)))
                .andExpect(jsonPath("$.info.[0]", is(valueInt)));
    }

    @Test
    public void testIncreaseOnInt() throws Exception{
        // "4"+6 = "10"
        Increase increase = new Increase(cleInfoInt,6);
        mockMvc.perform(put("/{username}/increase/",log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(increase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")));

        mockMvc.perform(get("/{username}/key/{cle}",log.getUser(),cleInfoInt))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusReponse.OK.toString())))
                .andExpect(jsonPath("$.info", hasSize(1)))
                .andExpect(jsonPath("$.info.[0]", is("10")));
    }

    @Test
    public void testIncreaseOnString() throws Exception{
        // "bonjour" + 6 -> exception
        Increase increase = new Increase(cleInfoString,6);
        mockMvc.perform(put("/{username}/increase/",log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(increase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusReponse.VALUE_NOT_INT.toString())));

    }

    @Test
    public void testAddToList() throws Exception{
        String nextValueInt = "5";
        String nextValueString = "au-revoir";
        SetInformation infoIntNext = new SetInformation(cleInfoInt,nextValueInt);
        SetInformation infoStringNext = new SetInformation(cleInfoString,nextValueString);

        mockMvc.perform(put("/{username}/addlist/",log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(infoIntNext)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")));

        mockMvc.perform(put("/{username}/addlist/",log.getUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(infoStringNext)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")));

        mockMvc.perform(get("/{username}/key/{cle}",log.getUser(),cleInfoInt))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusReponse.OK.toString())))
                .andExpect(jsonPath("$.info", hasSize(2)))
                .andExpect(jsonPath("$.info.[0]", is(valueInt)))
                .andExpect(jsonPath("$.info.[1]", is(nextValueInt)));

        mockMvc.perform(get("/{username}/key/{cle}",log.getUser(),cleInfoString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(StatusReponse.OK.toString())))
                .andExpect(jsonPath("$.info", hasSize(2)))
                .andExpect(jsonPath("$.info.[0]", is(valueString)))
                .andExpect(jsonPath("$.info.[1]", is(nextValueString)));



    }
}
