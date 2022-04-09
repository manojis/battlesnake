/**
 * 
 */
package com.tesla.battlesnake.rest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Manoj
 *
 */
@RestController
@EnableAutoConfiguration
public class BattleSnakeRestController {
	
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private static final Logger LOG = LoggerFactory.getLogger(BattleSnakeRestController.class);
	
	/**
     * For the start/end request
     */
    private static final Map<String, String> EMPTY = new HashMap<>();
    
    @RequestMapping(value = "/", method = RequestMethod.GET, 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> index() {
    	
    	 Map<String, String> response = new HashMap<>();
         response.put("apiversion", "1");
         response.put("author", ""); // TODO: Your Battlesnake Username
         response.put("color", "#888888"); // TODO: Personalize
         response.put("head", "default"); // TODO: Personalize
         response.put("tail", "default"); // TODO: Personalize
         
         return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK); 
    }
    
    @Scope("request")
    @RequestMapping(value = "start", method = RequestMethod.GET, 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> start(JsonNode startRequest){
    	
    	LOG.info("START");    	
    	return new ResponseEntity<Map<String, String>>(EMPTY, HttpStatus.OK); 
    }

}
