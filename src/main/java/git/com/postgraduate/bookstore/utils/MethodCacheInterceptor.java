package git.com.postgraduate.bookstore.utils;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodCacheInterceptor implements MethodInterceptor {
	
	private List<String> targetNameList; //�����뻺���service����
	private List<String> methodNameList; //�����뻺��ķ�������
	private Long defaultCacheExpireTime; //����Ĭ�ϵĹ���ʱ��
	private Long xxxRecordManagerTime;
	private Long xxxSetRecordManagerTime;

	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
	

}
