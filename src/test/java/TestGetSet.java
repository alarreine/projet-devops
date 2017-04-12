import junit.framework.TestCase;
import controller.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import junit.framework.*;

/**
 * Created by alarreine on 12/04/2017.
 */

public class TestGetSet extends TestCase {

	private DemanderControleur demander;
	private StockerControleur stocker;
	
	@Before
	public void initialiser(){
		this.demander = new DemanderControleur();
		this.stocker = new StockerControleur();
	}

	@Test
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

}