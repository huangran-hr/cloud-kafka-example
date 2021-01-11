/**
 * 
 */
package com.sjlh.cloud.server.message;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.integration.channel.AbstractSubscribableChannel;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author Administrator
 *
 */
class SubscribeChannelTest {
	private String GREETING_STRING = "content........";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Tests creating a subscribable message channel and sending a message
	 * to it without any subscribers being subscribed.
	 * Expected result:
	 * An exception should be thrown indicating that the message could
	 * not be delivered.
	 */
	//@Test
	public void noSubscribersTest() {
	    final SubscribableChannel theSubscribableChannel;
	    final Message<String> theInputMessage;

	    theInputMessage = MessageBuilder
	        .withPayload(GREETING_STRING)
	        .build();

	    theSubscribableChannel = new DirectChannel();
	    /*
	     * Give the message channel a name so that it will
	     * appear in any related log messages.
	     */
	    ((AbstractSubscribableChannel) theSubscribableChannel)
	        .setBeanName("MessageChannelWithNoSubscribers");

	    Assertions.assertThrows(MessageDeliveryException.class, () ->
	        theSubscribableChannel.send(theInputMessage));
	}

	/**
	 * Tests creating a subscribable message channel and subscribing one
	 * subscriber to the channel. A message is then sent to the channel.
	 * Expected result:
	 * The single subscriber should receive the message sent to the
	 * subscribable message channel.
	 */
	@Test
	public void singleSubscriberTest() {
	    final SubscribableChannel theSubscribableChannel;
	    final Message<String> theInputMessage;
	    final List<Message> theSubscriberReceivedMessages =
	        new CopyOnWriteArrayList<>();

	    theInputMessage = MessageBuilder
	        .withPayload(GREETING_STRING)
	        .build();

	    theSubscribableChannel = new DirectChannel();
	    /*
	     * Give the message channel a name so that it will
	     * appear in any related log messages.
	     */
	    ((AbstractSubscribableChannel) theSubscribableChannel)
	        .setBeanName("MessageChannelWithSingleSubscriber");

	    /*
	     * Create a subscriber (message handler) that adds each received
	     * message to a list.
	     */
	    final MessageHandler theSubscriber = theSubscriberReceivedMessages::add;

	    /* Register the subscriber with the subscribable message channel. */
	    final boolean theSubscribedFlag = theSubscribableChannel.subscribe(theSubscriber);

	    Assertions.assertTrue(theSubscribedFlag);

	    theSubscribableChannel.send(theInputMessage);

	    /* Wait until the subscriber has received the message. */
//	    await()
//	        .atMost(2, TimeUnit.SECONDS)
//	        .until(() ->
//	            !theSubscriberReceivedMessages.isEmpty());

	    /*
	     * The subscriber that subscribed to the subscribable message
	     * channel prior to the message was sent to the message channel
	     * should receive the message.
	     */
	    System.out.println("Subscriber received message: "
	        + theSubscriberReceivedMessages.get(0));
	    Assertions.assertEquals(
	        1,
	        theSubscriberReceivedMessages.size(),
	        "A single message should have been received by the subscriber");
	}
}
