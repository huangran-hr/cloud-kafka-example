/**
 * 
 */
package com.sjlh.cloud.server.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Administrator
 *
 */
public interface SenderSource {
	@Input("input1")
	SubscribableChannel subscribe();
	
	@Output("output1")
	MessageChannel channel();
}