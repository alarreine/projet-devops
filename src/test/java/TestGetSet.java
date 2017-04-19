import bean.reponse.ReponseBasic;
import bean.requete.RequeteSetInformation;
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
@SpringBootTest(classes = DemanderControleur.class)
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
        RequeteSetInformation info = new RequeteSetInformation();
        info.setKey(cle);
        info.setInfo(valeur);
		this.mockMvc.perform(put("/info/{id}",info))
                .andExpect(status().isAccepted());
    }

	@Test
	public void testGetCleExistant() throws Exception{

        String cle = "cle";
        String valeur = "contenu de 'cle'";
        RequeteSetInformation info = new RequeteSetInformation();
        info.setKey(cle);
        info.setInfo(valeur);
        this.mockMvc.perform(put("/info/{info}",info))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(get("/key/{id}",cle))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.key", is(1)));
        verify(valeur);
	}

	@Test
	public void testSetCleExistante(){
		String cle = "cle";
		String valeur1 = "contenu de 'cle'";
		String valeur2 = "nouveau contenu de 'cle'";
        RequeteSetInformation info1 = new RequeteSetInformation();
        RequeteSetInformation info2 = new RequeteSetInformation();
        info1.setKey(cle);
        info2.setKey(cle);
        info1.setInfo(valeur1);
        info2.setInfo(valeur2);
        this.mockMvc.perform(put("/info/{id}",info1))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(put("/info/{id}",info2))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(get("/key/{id}",cle))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.key", is(1)));
        verify(valeur2);
	}

	@Test
	public void testSetCaractereSpeciaux(){
		String cle = "clé 100% spécial !§*$£^ù c'est comme ça ;) \"voilà voilà\"";
		String valeur = "Le contenu est pas mal non plus \\bonjour\\/\\";
        RequeteSetInformation info = new RequeteSetInformation();
        info.setKey(cle);
        info.setInfo(valeur);
        this.mockMvc.perform(put("/info/{id}",info))
                .andExpect(status().isAccepted());
        this.mockMvc.perform(get("/key/{id}",cle))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.key", is(1)));
        verify(valeur);
	}

}