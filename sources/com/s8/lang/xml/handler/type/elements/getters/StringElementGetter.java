package com.s8.lang.xml.handler.type.elements.getters;

import java.lang.reflect.Method;

import com.s8.lang.xml.composer.ObjectComposableScope;
import com.s8.lang.xml.composer.PrimitiveComposableElement.StringComposableElement;
import com.s8.lang.xml.handler.XML_ContextBuilder;
import com.s8.lang.xml.handler.type.TypeBuilder;
import com.s8.lang.xml.handler.type.XML_TypeCompilationException;


public class StringElementGetter extends PrimitiveElementGetter {

	public final static Prototype PROTOTYPE = new Prototype() {
		
		@Override
		public boolean matches(Method method) {
			Class<?> type = method.getReturnType();
			if(type==String.class && method.getParameterCount()==0){
				return true;
			}
			else {
				return false;
			}
		}
		
		@Override
		public ElementGetter.Builder create(Method method) {
			return new Builder(method);
		}
	};
	
	
	public static class Builder extends PrimitiveElementGetter.Builder {

		public Builder(Method method) {
			super(method);
		}

		@Override
		public boolean build1(XML_ContextBuilder contextBuilder, TypeBuilder typeBuilder) throws XML_TypeCompilationException {
			typeBuilder.putElementGetter(new StringElementGetter(fieldTag, method));
			return false;
		}

	}
	
	public StringElementGetter(String tag, Method method) {
		super(tag, method);
	}

	@Override
	public void createComposableElement(ObjectComposableScope scope) throws Exception {
		String value = (String) method.invoke(scope.getObject(), new Object[]{});
		if(value!=null) {
			scope.append(new StringComposableElement(fieldTag, value));	
		}
	}
}