/**
 * 
 */
package com.sjlh.cloud.server.message;

import javax.annotation.Resource;

import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import com.sjlh.cloud.server.dto.PersonDto;

/**
 * @author Administrator
 *
 */
@Component
public class MessageSender{
	@Resource
	private StreamBridge streamBridge;
	//@Resource
	private PollableMessageSource source;
	
	public void sendTitle(PersonDto person) {
		streamBridge.send("toPerson", person);
	}
	
	public void cosume() {
		source.poll(m->{
			PersonDto payload = (PersonDto)m.getPayload();
			System.out.println(payload.getId() + ": " + payload.getName());
			throw new RuntimeException();
		}, new ParameterizedTypeReference<PersonDto>() {
		});
	}
}