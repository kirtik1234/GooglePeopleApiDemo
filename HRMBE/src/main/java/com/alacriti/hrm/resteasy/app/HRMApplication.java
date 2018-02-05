package com.alacriti.hrm.resteasy.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.alacriti.hrm.resteasy.resource.SampleResource;
import com.alacriti.hrm.resteasy.resource.UserResource;

public class HRMApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();

	public HRMApplication() {
		singletons.add(new SampleResource());
		singletons.add(new UserResource());
		
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
