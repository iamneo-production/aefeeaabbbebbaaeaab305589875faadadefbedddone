package com.example.springapp;


import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	@Autowired
	private  MockMvc mockMvc ;

	@Test
	void testPostTree() throws Exception{	

		String st = "{\"treeId\": 1000,\"treeName\": \"Demo\", \"usedfor\": \"coconut\",\"treeLifetime\": \"20yrs\"}";
		 mockMvc.perform(MockMvcRequestBuilders.post("/")
		 				.contentType(MediaType.APPLICATION_JSON)
		 				.content(st)
		 				.accept(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(jsonPath("$").value(true))
						.andReturn();
	}

	@Test
	void testGetOneTree() throws Exception{	

		 mockMvc.perform(get("/1000")
		 				.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.treeLifetime").value("20yrs"))
						.andReturn();
	}

	@Test
	void testUpdateTree() throws Exception{	

		String st = "{\"treeId\": 1000,\"treeName\": \"Demo\", \"usedfor\": \"coconut\",\"treeLifetime\": \"25yrs\"}";
				  mockMvc.perform(MockMvcRequestBuilders.put("/1000")
		 				.contentType(MediaType.APPLICATION_JSON)
		 				.content(st)
		 				.accept(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(jsonPath("$.treeLifetime").value("25yrs"))
						.andReturn();
	}

	@Test
	void testGetAllTrees() throws Exception{	

		 mockMvc.perform(get("/")
		 				.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").isArray())
						.andReturn();
	}

	@Test
	void testDeleteTree() throws Exception{	

		 mockMvc.perform(MockMvcRequestBuilders.delete("/1000")
		 				.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").value(true))
						.andReturn();
	}

	

	
	
	 @Test
    public void testTreeControllerClassExists() {
        checkClassExists("com.example.springapp.controller.TreeController");
    }

    @Test
    public void testTreeRepoClassExists() {
        checkClassExists("com.example.springapp.repository.TreeRepo");
    }

    @Test
    public void testApiServiceClassExists() {
        checkClassExists("com.example.springapp.service.ApiService");
    }

    @Test
    public void testTreeModelClassExists() {
        checkClassExists("com.example.springapp.model.Tree");
    }

    @Test
    public void testTreeControllerHasAutowiredField() {
        checkFieldExists("com.example.springapp.controller.TreeController", "apiService");
    }

    @Test
    public void testTreeModelHasIdField() {
        checkFieldExists("com.example.springapp.model.Tree", "treeId");
    }

    @Test
    public void testTreeModelHasTreeNameField() {
        checkFieldExists("com.example.springapp.model.Tree", "treeName");
    }

    @Test
    public void testTreeModelHasUsedForField() {
        checkFieldExists("com.example.springapp.model.Tree", "usedfor");
    }

    @Test
    public void testTreeModelHasTreeLifetimeField() {
        checkFieldExists("com.example.springapp.model.Tree", "treeLifetime");
    }

    @Test
    public void testTreeRepoExtendsJpaRepository() {
        checkClassImplementsInterface("com.example.springapp.repository.TreeRepo", "org.springframework.data.jpa.repository.JpaRepository");
    }

    @Test
    public void testApiServiceHasAutowiredField() {
        checkFieldExists("com.example.springapp.service.ApiService", "treeRepo");
    }

    @Test
    public void testTreeControllerHasGetMappingAnnotation() {
        checkAnnotationExists("com.example.springapp.controller.TreeController", "org.springframework.web.bind.annotation.GetMapping");
    }

    @Test
    public void testTreeControllerHasPostMappingAnnotation() {
        checkAnnotationExists("com.example.springapp.controller.TreeController", "org.springframework.web.bind.annotation.PostMapping");
    }

    @Test
    public void testTreeControllerHasPutMappingAnnotation() {
        checkAnnotationExists("com.example.springapp.controller.TreeController", "org.springframework.web.bind.annotation.PutMapping");
    }

    @Test
    public void testTreeControllerHasDeleteMappingAnnotation() {
        checkAnnotationExists("com.example.springapp.controller.TreeController", "org.springframework.web.bind.annotation.DeleteMapping");
    }

    @Test
    public void testTreeModelHasEntityAnnotation() {
        checkAnnotationExists("com.example.springapp.model.Tree", "javax.persistence.Entity");
    }

    @Test
    public void testTreeModelHasIdAnnotation() {
        checkAnnotationExists("com.example.springapp.model.Tree", "javax.persistence.Id");
    }

    @Test
    public void testTreeRepoHasRepositoryAnnotation() {
        checkAnnotationExists("com.example.springapp.repository.TreeRepo", "org.springframework.stereotype.Repository");
    }

    @Test
    public void testApiServiceHasServiceAnnotation() {
        checkAnnotationExists("com.example.springapp.service.ApiService", "org.springframework.stereotype.Service");
    }

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

    private void checkClassImplementsInterface(String className, String interfaceName) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?> interfaceClazz = Class.forName(interfaceName);
            assertTrue(interfaceClazz.isAssignableFrom(clazz));
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " or interface " + interfaceName + " does not exist.");
        }
    }
	private void checkAnnotationExists(String className, String annotationName) {
		try {
			Class<?> clazz = Class.forName(className);
			ClassLoader classLoader = clazz.getClassLoader();
			Class<?> annotationClass = Class.forName(annotationName, false, classLoader);
			assertNotNull(clazz.getAnnotation((Class) annotationClass)); // Use raw type
		} catch (ClassNotFoundException | NullPointerException e) {
			fail("Class " + className + " or annotation " + annotationName + " does not exist.");
		}
	}
	
}

	
	

