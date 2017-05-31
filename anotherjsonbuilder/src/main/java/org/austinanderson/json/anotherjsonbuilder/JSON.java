package org.austinanderson.json.anotherjsonbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSON{
	private String escapeIt(String value){
		return value.replace("\"", "\\\"")
					.replace("\\", "\\\\")
					.replace("/", "\\/")
					.replace("\b", "\\b")
					.replace("\f", "\\f")
					.replace("\n", "\\n")
					.replace("\r", "\\r")
					.replace("\t", "\\t");
	}
	private String quoteAndEscapeIt(String value){
		return "\""+escapeIt(value)+"\"";
	}
	public class JSONObjectBuilder {
		Map<String,String> data=new HashMap<>();
        public JSONObjectBuilder add(String name, JSONArrayBuilder objectOrArray) {
        	data.put(quoteAndEscapeIt(name),objectOrArray.build());
            return this;
        }
        public JSONObjectBuilder add(String name, JSONObjectBuilder objectOrArray) {
        	data.put(quoteAndEscapeIt(name),objectOrArray.build());
            return this;
        }
        public JSONObjectBuilder add(String name, long value) {
        	data.put(quoteAndEscapeIt(name),""+value);
            return this;
        }
        public JSONObjectBuilder add(String name, String value) {
        	data.put(quoteAndEscapeIt(name),quoteAndEscapeIt(value));
            return this;
        }
        public JSONObjectBuilder add(String name, double value) {
        	data.put(quoteAndEscapeIt(name),""+value);
            return this;
        }
        public JSONObjectBuilder add(String name, char value) {
        	data.put(quoteAndEscapeIt(name),""+value);
            return this;
        }
		public JSONObjectBuilder add(String name, boolean value) {
        	data.put(quoteAndEscapeIt(name),""+value);
			return this;
		}
        public String build() {
        	return "{"+data.entrySet().stream().
        			map(entry->entry.getKey()+": "+entry.getValue()).
        			reduce((str1,str2)->str1+","+str2).orElse("")+"}";
        }
	}
	public class JSONArrayBuilder {
		List<String> data=new ArrayList<>();
        public JSONArrayBuilder add(JSONObjectBuilder... objectOrArray) {
        	for(JSONObjectBuilder b:objectOrArray){
        		data.add(b.build());
        	}
            return this;
        }
        public JSONArrayBuilder add(JSONArrayBuilder... objectOrArray) {
        	for(JSONArrayBuilder b:objectOrArray){
        		data.add(b.build());
        	}
            return this;
        }
        public JSONArrayBuilder add(long... value) {
        	for(long l:value){
        		data.add(""+l);
        	}
            return this;
        }
        public JSONArrayBuilder add(String... value) {
        	for(String s:value){
        		data.add(quoteAndEscapeIt(s));
        	}
            return this;
        }
        public JSONArrayBuilder add(double... value) {
        	for(double d:value){
        		data.add(""+d);
        	}
            return this;
        }
        public JSONArrayBuilder add(char... value) {
        	for(char c:value){
        		data.add(""+c);
        	}
            return this;
        }
		public JSONArrayBuilder add(boolean... value) {
			for(boolean b:value){
				data.add(""+b);
			}
			return this;
		}
        public String build() {
        	return "["+data.stream().reduce((str1,str2)->str1+","+str2).orElse("")+"]";
        }
	}
	public static JSONArrayBuilder array(){
		return new JSON().new JSONArrayBuilder();
	}
	public static JSONObjectBuilder object(){
		return new JSON().new JSONObjectBuilder();
	}
}
