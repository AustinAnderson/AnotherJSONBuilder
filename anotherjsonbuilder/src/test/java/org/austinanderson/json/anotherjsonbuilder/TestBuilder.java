package org.austinanderson.json.anotherjsonbuilder;

import org.junit.Test;

import com.google.gson.JsonParser;

import static org.junit.Assert.*;

public class TestBuilder {

	@Test
	public void testValidOutput(){
		
		String jsonOutput=JSON.object()
			.add("key", 3)
			.add("key2","value")
			.add("array",JSON.array()
				.add("test")
				.add("this")
				.add(JSON.object().add("nestKey",
						JSON.array().add(3,4,2,2,5,2,6,3,4,5,1)
					).add("me","")
				)
				.add("nested")
			).add("", "")
		.build();
		try{
			new JsonParser().parse(jsonOutput);
		}catch(Exception ex){
			fail(ex.getMessage());
		}
	}
}
