import bean.requete.Auth;
import bean.requete.Increase;
import bean.requete.RenameKey;
import bean.requete.SetInformation;
import enumerate.StatusReponse;
import junit.framework.TestCase;
import controller.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

/**
 * Created by alarreine on 12/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GetController.class, PutController.class})
@WebAppConfiguration
public class TestUpDate extends TestCase {


    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext wac;
    private String user;
    private Auth log;

    private SetInformation infoInt;
    private SetInformation infoString;

    private String cleInfoInt;
    private String cleInfoString;
    private String valueInt;
    private String valueString;

    @Before
    public void setUp() throws Exception {
        user = "didesj";
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        log = new Auth(user, "1234");

        cleInfoInt = "int";
        cleInfoString = "string";
        valueInt = "4";
        valueString = "bonjour";
        infoInt = new SetInformation(cleInfoInt,valueInt);
        infoString = new SetInformation(cleInfoString,valueString);
        this.mockMvc.perform(post (user+"/set/{SetInformation}",infoInt))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(post (user+"/set/{SetInformation}",infoString))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testRenameKey() throws Exception{
        RenameKey rename1 = new RenameKey(cleInfoString, cleInfoString + "Rename");
        RenameKey rename2 = new RenameKey(cleInfoInt, cleInfoInt + "Rename");
        this.mockMvc.perform(put(user+"/rename/{RenameKey}",rename1))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(get (user+"/key/{String}",cleInfoInt + "Rename"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.key", is(1)));
        verify(valueInt);
        this.mockMvc.perform(put(user+"/rename/{RenameKey}",rename2))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(get (user+"/key/{String}",cleInfoString + "Rename"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.key", is(1)));
        verify(valueString);
    }

    @Test
    public void testIncreaseOnInt() throws Exception{
        // "4"+6 = "10"
        Increase increase = new Increase(cleInfoInt,6);
        this.mockMvc.perform(put(user+"/increase/{Increase}", increase))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(get (user+"/key/{String}",cleInfoInt))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.key", is(1)));
        verify(10);
    }

    @Test
    public void testIncreaseOnString() throws Exception{
        // "bonjour" + 6 -> exception
        Increase increase = new Increase(cleInfoString,6);
        this.mockMvc.perform(put(user+"/increase/{Increase}", increase))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void testAddToList() throws Exception{
        String nextValueInt = "5";
        String nextValueString = "au-revoir";
        SetInformation infoIntNext = new SetInformation(cleInfoInt,nextValueInt);
        SetInformation infoStringNext = new SetInformation(cleInfoInt,nextValueString);
        this.mockMvc.perform(put(user+"/addlist/{SetInformation}",infoIntNext))
                .andExpect(status().isAccepted());
        // à finir
//        this.mockMvc.perform(put(user+"/addlist/{SetInformation}",infoStringNext))
//                .andExpect(status().isAccepted());
//        this.mockMvc.perform(get())
    }
}
