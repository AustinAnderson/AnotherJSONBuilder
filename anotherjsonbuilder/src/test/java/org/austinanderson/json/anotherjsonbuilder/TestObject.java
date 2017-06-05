package org.austinanderson.json.anotherjsonbuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

public class TestObject{

	@Test
	public void emptyObject(){
		assertEquals("expected object to be empty: ", "{}",JSON.object().build());
	}
	@Test
	public void nullsAdded(){
		assertEquals("{\"\":\"null's Value\"}",
				JSON.object().add(null, "null's Value").build()
		);
	}
	
}
