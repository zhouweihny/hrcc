package com.commons.shirosession;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

public class MySessionDao extends AbstractSessionDAO {
	private Map<Serializable, Session> map = new HashMap<Serializable, Session>();

	public void delete(Session session) {
		// TODO Auto-generated method stubsession
//		System.out.println("now delete session");
		map.remove(session.getId());
	}

	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub

		return map.values();
	}

	public void update(Session session) throws UnknownSessionException {
		// TODO Auto-generated method stub
//		System.out.println("now update session");
		map.put(session.getId(), session);
	}

	@Override
	protected Serializable doCreate(Session session) {
		// TODO Auto-generated method stub
		
//		System.out.println("now doCreate session");
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		map.put(sessionId, session);
//		System.out.println(session.toString());
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// TODO Auto-generated method stub
//		System.out.println("now doReadSession session");
		return map.get(sessionId);
	}

}
