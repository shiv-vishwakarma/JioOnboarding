package telcoHandsOn;

import com.jio.telco.framework.pool.PooledObject;
import com.jio.telco.framework.pool.PooledObjectFactory;
import com.jio.telco.framework.pool.impl.DefaultPooledObject;

public class MyObjectPoolFactory<T> implements PooledObjectFactory<T>{

	@SuppressWarnings("unchecked")
	@Override
	public PooledObject makeObject() throws Exception {
		// TODO Auto-generated method stub
		return new DefaultPooledObject( new Players());
	}

	@Override
	public void destroyObject(PooledObject<T> p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateObject(PooledObject<T> p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activateObject(PooledObject<T> p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passivateObject(PooledObject<T> p) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
