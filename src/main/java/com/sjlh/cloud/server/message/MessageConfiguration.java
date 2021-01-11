/**
 * 
 */
package com.sjlh.cloud.server.message;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.cloud.function.context.PollableBean;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sjlh.cloud.server.dto.PersonDto;

/**
 * @author Administrator
 *
 */
@Configuration
//@EnableBinding(MessageConfiguration.PollableMessageSourceConsumer.class)
public class MessageConfiguration {
	private Integer index = 0;
	
	//@Bean
	public Supplier<PersonDto> sendPerson(){
		return ()->{
			PersonDto p = new PersonDto();
			index++;
			p.setId(index);
			p.setName("name" + index);
			return p;
		};
	}
	
	public static interface PollableMessageSourceConsumer {
		@Input("log-in-0")
		public PollableMessageSource log();
	}
	
	//@Bean
	//@PollableBean
	//@Input
	public Consumer<PersonDto> log(){
		return person->{
			System.out.println("pppppppp====" + person.getId() + ","+person.getName());
			throw new RuntimeException();
//			try {
//				Thread.sleep(60000L);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		};
	}
	
	//@Bean
	public Function<PersonDto, String> func(){
		return p->{
			System.out.println("func: " + p.getId() + ","+p.getName());
			return "name: "+p.getName();
		};
	}
	
	//@Bean
	public Consumer<PersonDto> log1(){
		return p->{
			System.out.println("log1 " + p.getId() + ","+p.getName());
		};
	}
}