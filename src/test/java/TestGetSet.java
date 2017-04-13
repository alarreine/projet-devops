import bean.reponse.ReponseBasic;
import enumerate.StatusReponse;
import junit.framework.TestCase;
import controller.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

/**
 * Created by alarreine on 12/04/2017.
 */


	/*@Test
	public void testSetCreation(){

		assertTrue(this.stocker.stockerInformationParCle("cle", "contenu de 'cle'").getStatusCode().is2xxSuccessful());
    }

	@Test
	public void testGetCleExistant(){
		String cle = "cle";
		String valeur = "contenu de 'cle'";
		assertTrue(this.stocker.stockerInformationParCle(cle, valeur).getStatusCode().is2xxSuccessful());
		assertEquals(this.demander.obtenirInformationParCle(cle).getInformation(), valeur);
	}

	@Test
	public void testGetCleInexistante(){
		String cle = "cle";
		String valeur = "contenu de 'cle'";
		assertNotEquals(this.demander.obtenirInformationParCle(cle).getInformation(), valeur);
		assertFalse(this.demander.obtenirInformationParCle(cle).exist());
	}

	@Test
	public void testSetCleExistante(){
		String cle = "cle";
		String valeur1 = "contenu de 'cle'";
		String valeur2 = "nouveau contenu de 'cle'";
		assertTrue(this.stocker.stockerInformationParCle(cle, valeur1).getStatusCode().is2xxSuccessful());
		assertTrue(this.stocker.stockerInformationParCle(cle, valeur2).getStatusCode().is2xxSuccessful());
		assertEquals(this.demander.obtenirInformationParCle(cle).getInformation(), valeur2);
	}

	@Test
	public void testSetCaractereSpeciaux(){
		String cle = "clé 100% spécial !§*$£^ù c'est comme ça ;) \"voilà voilà\"";
		String valeur = "Le contenu est pas mal non plus \\bonjour\\/\\";
		assertTrue(this.stocker.stockerInformationParCle(cle, valeur).getStatusCode().is2xxSuccessful());
		assertEquals(this.demander.obtenirInformationParCle(cle).getInformation(), valeur);
	}
*/
//}