package com.hhcpro.tools.keepalive;

public interface kAlive {
	
	public void init(String CompGroup, String GroupName, String ServiceName, String SubSerice);
	
	public boolean ImAlive();
	
	public boolean ImIAlive();

}
