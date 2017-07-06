package git.com.postgraduate.bookstore.utils;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodCacheInterceptor implements MethodInterceptor {
	
	private List<String> targetNameList; //不加入缓存的service名称
	private List<String> methodNameList; //不加入缓存的方法名称
	private Long defaultCacheExpireTime; //缓存默认的过期时间
	private Long xxxRecordManagerTime;
	private Long xxxSetRecordManagerTime;

	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
	

}
