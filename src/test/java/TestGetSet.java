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
public class TestGetSet extends TestCase{



    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetCleInexistante() throws Exception{
        String cle="cle";
        this.mockMvc.perform(get("/key/{id}", cle))
                .andExpect(status().isNotFound());
    }


	@Test
	public void testSetCreation() throws Exception{
        String cle = "cle";
		String valeur = "contenu de 'cle'";
        SetInformation info = new SetInformation(cle,valeur);
		this.mockMvc.perform(post("/set/{SetInformation}",info))
                .andExpect(status().isAccepted());
    }

	@Test
	public void testGetCleExistant() throws Exception{

        String cle = "cle";
        String valeur = "contenu de 'cle'";
        SetInformation info = new SetInformation(cle,valeur);
        this.mockMvc.perform(post("/set/{SetInformation}",info))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(get("/key/{String}",cle))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.key", is(1)));
        verify(valeur);
	}

	@Test
	public void testSetCleExistante() throws Exception{
		String cle = "cle";
		String valeur1 = "contenu de 'cle'";
		String valeur2 = "nouveau contenu de 'cle'";
        SetInformation info1 = new SetInformation(cle,valeur1);
        SetInformation info2 = new SetInformation(cle,valeur2);
        this.mockMvc.perform(post("/set/{SetInformation}",info1))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(post("/set/{SetInformation}",info2))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(get("/key/{String}",cle))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.key", is(1)));
        verify(valeur2);
	}

	@Test
	public void testSetCaractereSpeciaux() throws Exception {
		String cle = "clé 100% spécial !§*$£^ù c'est comme ça ;) \"voilà voilà\"";
		String valeur = "Le contenu est pas mal non plus \\bonjour\\/\\";
        SetInformation info = new SetInformation(cle,valeur);
        this.mockMvc.perform(put("/set/{SetInformation}",info))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(get("/key/{String}",cle))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.key", is(1)));
        verify(valeur);
	}

}