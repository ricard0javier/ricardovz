package com.ricardovz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ricardovz.api.controller.PropertiesController;
import com.ricardovz.api.controller.PropertiesControllerImpl;
import com.ricardovz.api.dto.PropertyDTO;
import com.ricardovz.api.repository.PropertiesRepository;
import com.ricardovz.api.service.PropertiesService;
import com.ricardovz.api.service.PropertiesServiceImpl;

public class PropertiesControllerTest {

    @Test
    @SuppressWarnings("unchecked")
    public void getProperties_givenVariableNames_returnsTextArray() {

	// given
	PropertiesRepository repository = Mockito.mock(PropertiesRepository.class);
	List<PropertyDTO> properties = getExampleProperties();
	Mockito.when(repository.findByNames(Mockito.anyList())).thenReturn(properties);

	PropertiesController propertiesController = buildController(repository);

	// then
	String[] variables = new String[] { "firstName", "middleName" };
	Map<String, String> actualProperties = propertiesController.getProperties(variables);

	// expect
	Map<String, String> expectedProperties = new HashMap<String, String>();
	expectedProperties.put("firstName", "Ricardo");
	expectedProperties.put("middleName", "Javier");
	Assert.assertEquals(expectedProperties, actualProperties);
    }

    private PropertiesController buildController(PropertiesRepository repository) {
	PropertiesService service = new PropertiesServiceImpl(repository);
	PropertiesController textController = new PropertiesControllerImpl(service);
	return textController;
    }

    private List<PropertyDTO> getExampleProperties() {
	List<PropertyDTO> properties = new ArrayList<PropertyDTO>();
	properties.add(new PropertyDTO("firstName", "Ricardo"));
	properties.add(new PropertyDTO("middleName", "Javier"));
	return properties;
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void setProperties_givenProperties_RepositorySaveIsCalled() {

	// given
	PropertiesRepository repository = Mockito.mock(PropertiesRepository.class);
	PropertiesController propertiesController = buildController(repository);

	// then
	List<PropertyDTO> properties = getExampleProperties();
	PropertyDTO[] propertiesArray = new PropertyDTO[properties.size()];
	properties.toArray(propertiesArray);
	
	propertiesController.setProperties(propertiesArray);

	// expect
	Mockito.verify(repository).save(Mockito.anyList());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void deleteProperties_givenProperties_RepositoryDeleteIsCalled() {
	
	// given
	PropertiesRepository repository = Mockito.mock(PropertiesRepository.class);
	PropertiesController propertiesController = buildController(repository);
	
	// then
	propertiesController.deleteProperties(new String[]{"propertyName"});
	
	// expect
	Mockito.verify(repository).deleteByNames(Mockito.anyList());
    }

}
