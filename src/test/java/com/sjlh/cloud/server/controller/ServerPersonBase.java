/**
 * 
 */
package com.sjlh.cloud.server.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

/**
 * @author Administrator
 *
 */
public abstract class ServerPersonBase {
	MainController mainController = new MainController();
	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(mainController);
	}
}