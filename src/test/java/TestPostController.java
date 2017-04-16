import bean.requete.RenameKey;
import controller.PostController;
import controller.PutController;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alarreine on 13/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PutController.class)
@WebAppConfiguration
public class TestPostController extends TestCase{


    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testConflicKey() throws Exception{
        RenameKey requete = new RenameKey();
        requete.setKey("12");
        requete.setNewKey("2");

        mockMvc.perform(
                post("/rename")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requete.toString()))
                .andExpect(status().isOk());


    }


}


