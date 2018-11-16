package com.jaymansmann.gameserver.gameserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaymansmann.gameserver.gameserver.model.BasicConversation;
import com.jaymansmann.gameserver.gameserver.model.BasicMessage;
import com.jaymansmann.gameserver.gameserver.model.SystemMessage;
import com.jaymansmann.gameserver.gameserver.utility.JCache;
import com.jaymansmann.gameserver.gameserver.utility.JCacheable;
import com.jaymansmann.gameserver.gameserver.utility.UtilityClass;

@Service
public class BasicConversationService {
	@Autowired
	private JCache<String, BasicConversation> conversations;
	
	@Autowired
	private SystemMessage lastTimeDown;
	
	public BasicMessage create(BasicMessage message) {
		BasicConversation basicConversation = new BasicConversation();
		conversations.put(basicConversation.getId(), basicConversation);
		return update(basicConversation.getId(), message);
	}
	
	public List<BasicMessage> readMessagesFrom(String id, int startingIndex) {
		List<BasicMessage> list = getMessages(id);
		return list.subList(startingIndex, list.size());
	}
	
	public BasicMessage update(String id, BasicMessage message) {
		message.setId(UtilityClass.getRandomCodestring());
		getMessages(id).add(message);
		message.setConversationId(id);
		return message;
	}
	
	public void destroy(String id) {
		conversations.remove(id);
	}
	
	/**
	 * This method returns some message from the system.
	 * This app currently loses all data on shutdown,
	 * so this method provides a convenient way of informing clients
	 * that their data may be gone if they last sent a message before
	 * the system message's timestamp.
	 * 
	 * @return
	 */
	@JCacheable(time = 3_600_000)
	public SystemMessage getSystemMessage() {
		return this.lastTimeDown;
	}
	
	private List<BasicMessage> getMessages(String id) {
		return Optional.ofNullable(this.getConversation(id).getMessages())
				.orElseGet(ArrayList::new);
	}
	
	private BasicConversation getConversation(String id) {
		return conversations.get(id).orElseThrow(NoSuchElementException::new);
	}
}
