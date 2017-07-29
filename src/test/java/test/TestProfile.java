package test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dao.ProfileDAO;
import dao.filter.ProfileFilter;
import entity.Profile;

public class TestProfile {

	
	@Test
	public void crudTest(){
		
		//Junit não garante a ordem de execução dos métodos, então juntei todos em um método só
		testCreate();
		testUpdate();
		testDelete();
	}
	
	
	public void testCreate(){
		try {

			Profile profile = new Profile();
			profile.setName("The Tester");
			profile.setLogin("test_user");
			profile.setEmail("test@email");
			profile.addPassword("pass");
			ProfileDAO dao = new ProfileDAO();
			dao.insert(profile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public void testUpdate(){
		try {

			ProfileDAO dao = new ProfileDAO();
			ProfileFilter pf = new ProfileFilter();
			pf.byEmail("test@email");
			Profile profile = dao.search(pf).get(0);
			assertNotNull(profile);
			profile.addPassword("pass2");
			dao.update(profile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public void testDelete(){
		try {

			ProfileDAO dao = new ProfileDAO();
			ProfileFilter pf = new ProfileFilter();
			pf.byEmail("test@email");
			Profile profile = dao.search(pf).get(0);
			assertNotNull(profile);
			dao.delete(profile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
