/**
 * 
 */
package com.sjlh.cloud.server.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjlh.cloud.server.dto.PersonDto;
import com.sjlh.cloud.server.message.MessageSender;
import com.sjlh.springmvc.log.annotation.LogEnableEnum;
import com.sjlh.springmvc.log.annotation.LogRequest;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/qunar")
//@LogRequest(enable = LogEnableEnum.Enable, headerLoged = LogEnableEnum.Enable, payloadLoged = LogEnableEnum.Enable)
public class MainController {
	@Resource
	private MessageSender messageSender;
	@Value("${apollo.key.name.value}")
	private String key;
	
	@PostMapping(value="/hello", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	//@LogRequest(enable = LogEnableEnum.Enable, headerLoged = LogEnableEnum.Enable)
	public Object test(@RequestBody PersonDto person) {
		person.setId(2);
		person.setName("Hawking");
//		messageSender.sendTitle(person);
		if(messageSender!=null)messageSender.cosume();
		return person;
	}
	
	@RequestMapping(value="/xml/hello", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	//@LogRequest(enable = LogEnableEnum.Enable, headerLoged = LogEnableEnum.Enable)
	public Object testXml(@RequestBody PersonDto person) {
		person.setId(2);
		person.setName("Hawking");
		return person;
	}
}