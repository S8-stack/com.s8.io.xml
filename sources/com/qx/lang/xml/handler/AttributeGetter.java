package com.qx.lang.xml.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AttributeGetter {
	
	/**
	 * 
	 * @param method
	 * @return
	 */
	public static AttributeGetter create(Method method){
		Class<?>[] parameters = method.getParameterTypes();
		if(parameters.length!=0){
			throw new RuntimeException("Illegal number of parameters for a setter");
		}
		Class<?> type = method.getReturnType();
		
		if(type==boolean.class){
			return new BooleanFieldGetter(method);
		}
		else if(type==short.class){
			return new ShortFieldGetter(method);
		}
		else if(type==int.class){
			return new IntegerFieldGetter(method);
		}
		else if(type==long.class){
			return new LongFieldGetter(method);
		}
		else if(type==float.class){
			return new FloatFieldGetter(method);
		}
		else if(type==double.class){
			return new DoubleFieldGetter(method);
		}
		else if(type==String.class){
			return new StringFieldGetter(method);
		}
		else{
			throw new RuntimeException("parameters type is not supported: "+type.getName());
		}
	}

	public Method method;
	
	public AttributeGetter(Method method) {
		super();
		this.method = method;
	}
	
	public abstract String get(Object object)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	private static class BooleanFieldGetter extends AttributeGetter {

		public BooleanFieldGetter(Method method) {
			super(method);
		}

		@Override
		public String get(Object object)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			return Boolean.toString((boolean) method.invoke(object));
		}
		
	}
	
	private static class ShortFieldGetter extends AttributeGetter {

		public ShortFieldGetter(Method method) {
			super(method);
		}

		@Override
		public String get(Object object)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			return Short.toString((short) method.invoke(object));
		}
		
	}
	

	
	private static class IntegerFieldGetter extends AttributeGetter {

		public IntegerFieldGetter(Method method) {
			super(method);
		}

		@Override
		public String get(Object object)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			return Integer.toString((short) method.invoke(object));
		}
		
	}
	

	private static class LongFieldGetter extends AttributeGetter {

		public LongFieldGetter(Method method) {
			super(method);
		}

		@Override
		public String get(Object object)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			return Long.toString((short) method.invoke(object));
		}
		
	}
	


	private static class FloatFieldGetter extends AttributeGetter {

		public FloatFieldGetter(Method method) {
			super(method);
		}

		@Override
		public String get(Object object)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			return Float.toString((short) method.invoke(object));
		}
		
	}
	

	private static class DoubleFieldGetter extends AttributeGetter {

		public DoubleFieldGetter(Method method) {
			super(method);
		}

		@Override
		public String get(Object object)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			return Double.toString((short) method.invoke(object));
		}
		
	}
	

	private static class StringFieldGetter extends AttributeGetter {

		public StringFieldGetter(Method method) {
			super(method);
		}

		@Override
		public String get(Object object)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			return (String) method.invoke(object);
		}
		
	}
	
}