/**
 * 
 */
package com.tesla.battlesnake.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Manoj
 *
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class BattleSnakeRestController {
	
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private static final Logger LOG = LoggerFactory.getLogger(BattleSnakeRestController.class);
	
	/**
     * For the start/end request
     */
    private static final Map<String, String> EMPTY = new HashMap<>();
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> index() {
    	
    	 Map<String, String> response = new HashMap<>();
         response.put("apiversion", "1");
         response.put("author", "battlesnake-manoj");
         response.put("color", "#736CCB");
         response.put("head", "beluga");
         response.put("tail", "curled");
         
         return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK); 
    }
    
    @Scope("request")
    @RequestMapping(value = "/start", method = RequestMethod.GET, 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> start(@RequestBody JsonNode startRequest){
    	  	
    	LOG.info("START: "+startRequest.toPrettyString());
    	return new ResponseEntity<Map<String, String>>(EMPTY, HttpStatus.OK); 
    }
    
    @Scope("request")
    @RequestMapping(value = "/move", method = RequestMethod.GET, 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> move(@RequestBody JsonNode moveRequest){
    	  	
    	try {
            LOG.info("Data: {}", JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(moveRequest));
        } catch (JsonProcessingException e) {
            LOG.error("Error parsing payload", e);
        }

        /*
         * Example how to retrieve data from the request payload:
         * 
         * String gameId = moveRequest.get("game").get("id").asText();
         * 
         * int height = moveRequest.get("board").get("height").asInt();
         * 
         */

        JsonNode head = moveRequest.get("you").get("head");
        JsonNode body = moveRequest.get("you").get("body");

        ArrayList<String> possibleMoves = new ArrayList<>(Arrays.asList("up", "down", "left", "right"));

        // Don't allow your Battlesnake to move back in on it's own neck
        avoidMyNeck(head, body, possibleMoves);

        // TODO: Using information from 'moveRequest', find the edges of the board and
        // don't
        // let your Battlesnake move beyond them board_height = ? board_width = ?

        // TODO Using information from 'moveRequest', don't let your Battlesnake pick a
        // move
        // that would hit its own body

        // TODO: Using information from 'moveRequest', don't let your Battlesnake pick a
        // move
        // that would collide with another Battlesnake

        // TODO: Using information from 'moveRequest', make your Battlesnake move
        // towards a
        // piece of food on the board

        // Choose a random direction to move in
        final int choice = new Random().nextInt(possibleMoves.size());
        final String move = possibleMoves.get(choice);

        LOG.info("MOVE {}", move);

        Map<String, String> response = new HashMap<>();
        response.put("move", move);
        return new ResponseEntity<Map<String, String>>(EMPTY, HttpStatus.OK); 
    }
    
    /**
     * Remove the 'neck' direction from the list of possible moves
     * 
     * @param head          JsonNode of the head position e.g. {"x": 0, "y": 0}
     * @param body          JsonNode of x/y coordinates for every segment of a
     *                      Battlesnake. e.g. [ {"x": 0, "y": 0}, {"x": 1, "y": 0},
     *                      {"x": 2, "y": 0} ]
     * @param possibleMoves ArrayList of String. Moves to pick from.
     */
    public void avoidMyNeck(JsonNode head, JsonNode body, ArrayList<String> possibleMoves) {
        JsonNode neck = body.get(1);

        if (neck.get("x").asInt() < head.get("x").asInt()) {
            possibleMoves.remove("left");
        } else if (neck.get("x").asInt() > head.get("x").asInt()) {
            possibleMoves.remove("right");
        } else if (neck.get("y").asInt() < head.get("y").asInt()) {
            possibleMoves.remove("down");
        } else if (neck.get("y").asInt() > head.get("y").asInt()) {
            possibleMoves.remove("up");
        }
    }
    
    /**
     * This method is called when a game your Battlesnake was in ends.
     * 
     * It is purely for informational purposes, you don't have to make any decisions
     * here.
     *
     * @param endRequest a map containing the JSON sent to this snake. Use this data
     *                   to know which game has ended
     * @return responses back to the engine are ignored.
     */
    @Scope("request")
    @RequestMapping(value = "/end", method = RequestMethod.GET, 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> end(@RequestBody JsonNode endRequest){
    	  	
    	 try {
			LOG.info("END Data: {}", JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(endRequest));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new ResponseEntity<Map<String, String>>(EMPTY, HttpStatus.OK); 
    }

}
