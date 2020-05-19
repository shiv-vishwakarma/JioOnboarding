package telcoHandsOn;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jio.telco.framework.pool.PoolingManager;
import com.jio.telco.framework.resource.ResourceBuilder;

public class JettyServer {
	
	public static void main(String[] args) throws Exception {

		ResourceBuilder.jetty().setIP("127.0.0.1").setPort(8080).setMinThreadsForPool(2).setMaxThreadsForPool(16)
				.addHandler("/hello", JettyServletHandler.class).start();
		
		
		
		ResourceBuilder.objectPool().setMaxActive(100000).setMinIdle(50).setMaxIdle(1000).setWhenExhaustAction(false)
		.setTestOnBorrow(false).setTestOnReturn(false).setTestWhileIdle(false).setTimeBetweenEvictionRunsMillis(300000)
		.setMinEvictableIdleTimeMillis(60000).createObjectPool( new MyObjectPoolFactory<Object>(), Players.class);
		
		System.out.println("Before Borrowing: " +PoolingManager.getPoolingManager().getPoolStatistics());
		
		Players p  = (Players) PoolingManager.getPoolingManager().borrowObject(Players.class);
				
		System.out.println("After Borrowing: " +PoolingManager.getPoolingManager().getPoolStatistics());
		
		PoolingManager.getPoolingManager().returnObject(p);
		
		System.out.println("After Returning: " +PoolingManager.getPoolingManager().getPoolStatistics());
		

	}	

}
