package com.spring.mvc.chapter04_ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mvc.dto.ProductDTO;

@Controller
@RequestMapping("/ajax")
public class AjaxToController {
	
	/*
	    # AJAX데이터 전송
	    
	    - pom.xml파일에 의존성 추가
	    
		<!-- jackson-core -->
		<dependency>
		    <groupId>com.fasterxml.jackson.core</groupId>
		    <artifactId>jackson-databind</artifactId>
		    <version>2.9.2</version>
		</dependency>
	*/
	
	@GetMapping("/ajaxToController")
	public String ajaxToController() {
		return "chapter04_ajax/ajaxToController";
	}
	
	// 1. 단일 데이터 전송
	@GetMapping("/a2cEx01")
	public ResponseEntity<Object> a2cEx01(@RequestParam Map<String, Object> param) {
		System.out.println(param.get("productCd"));
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
	// 2. DTO 전송
	@GetMapping("/a2cEx02")
	public ResponseEntity<Object> a2cEx02(@ModelAttribute ProductDTO productDTO)  {
		System.out.println(productDTO);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	// 3. map 전송
	@GetMapping("/a2cEx03")
	public ResponseEntity<Object> a2cEx03(@RequestParam Map<String, Object> param) {
		System.out.println(param);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	// 4. List<DTO> 전송
	@GetMapping("/a2cEx04")
	public ResponseEntity<Object> a2cEx04(@RequestParam Map<String, Object> paramList) throws JsonParseException, JsonMappingException, IOException  {
		
		//1) paramList로 넘어온 데이터를 문자열로 변환한다. 
		String json = paramList.get("paramList").toString();
		ObjectMapper mapper = new ObjectMapper();
		  
		// 2) 변환된 데이터를 List타입으로 저장한다.
		//JSON 파일을 Java 객체로 deserialization 하기 위해서 ObjectMapper의 readValue() 메서드를 이용한다.
		List<ProductDTO> productList = mapper.readValue(json, new TypeReference<ArrayList<ProductDTO>>(){});
		
		for (ProductDTO productDTO : productList) {
			System.out.println(productDTO);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	// 5. List<Map> 전송
	@GetMapping("/a2cEx05")
	public ResponseEntity<Object> a2cEx05(@RequestParam Map<String, Object> paramList) throws JsonParseException, JsonMappingException, IOException {
		
		//1) paramList로 넘어온 데이터를 문자열로 변환한다. 
		String json = paramList.get("paramList").toString();
		ObjectMapper mapper = new ObjectMapper();
		  
		// 2) 변환된 데이터를 List타입으로 저장한다.
		//JSON 파일을 Java 객체로 deserialization 하기 위해서 ObjectMapper의 readValue() 메서드를 이용한다.
		List<Map<String, Object>> productList = mapper.readValue(json, new TypeReference<ArrayList<Map<String, Object>>>(){});
		for (Map<String, Object> map : productList) {
			System.out.println(map);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	
}
