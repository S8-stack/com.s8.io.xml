package com.qx.lang.xml.parser;

import java.util.Stack;

import com.qx.lang.xml.context.XML_Context;

public class XML_Parser {

	private XML_StreamReader reader;
	
	protected State state;

	private Stack<ParsedElement> stack;

	private RootParsedElement rootBuilder;

	public XML_Parser(XML_Context context, XML_StreamReader reader) {
		super();
		this.reader = reader;
		rootBuilder = new RootParsedElement(context);
		stack = new Stack<>();
		state = readContent;
		
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object parse() throws Exception{
		while(state!=null){
			state.parse();
		}
		return rootBuilder.getObject();
	}
	
	
	private void push(String tag) throws Exception{
		if(stack.isEmpty()){
			stack.push(rootBuilder.createField(tag));
		}
		else{
			stack.push(stack.peek().createField(tag));	
		}
	}


	private void pop(String tag) throws Exception{
		if(!tag.equals(stack.peek().getTag())){
			throw new Exception("Tag is not matching");
		}
		pop();
	}

	private void pop() throws Exception{
		stack.peek().close();
		stack.pop();
		if(stack.isEmpty()){
			state = null;
		}
		else{
			state = readContent;	
		}
	}

	/**
	 * 
	 * @author pc
	 *
	 */
	private abstract class State {

		public abstract void parse() throws Exception;

	}


	private State readContent = new State() {

		@Override
		public void parse() throws Exception {

			String value = reader.until(
					/* stop at */ new char[]{'<'},
					/* ignore */ null,
					/* forbid */ null,
					/* include current? */ false);	

			// set value if any
			if(!isBlank(value)){
				stack.peek().setValue(value);
			}
			state = readTag;
		}
	};


	private State readTag = new State() {

		@Override
		public void parse() throws Exception {
			reader.check('<');
			reader.next();
			// closing tag
			if(reader.isCurrent('/')){
				state = readClosingTag;
			}
			else if(reader.isCurrent('-')){
				state = readComment;
			}
			else if(reader.isCurrent('?')){
				state = readHeader;
			}
			else{
				state = readOpeningTag;
			}
		}
	};


	private State readOpeningTag = new State() {

		@Override
		public void parse() throws Exception {
			String tag = reader.until(
					/* stop at */ new char[]{'>', ' ', '/'},
					/* ignore */ null,
					/* forbid */ new char[]{',', '=', '"'},
					/* include current? */ true);

			// prefix is detected -> field name
			if(reader.isCurrent('>')) {
				push(tag);
				state = readContent;
			}
			else if(reader.isCurrent(' ')){
				push(tag);
				reader.skipWhiteSpace();
				state = readAttribute;
			}
			else if(reader.isCurrent('/')){
				reader.next();
				reader.check('>');
				pop(tag);
			}
		}
	};


	private State readClosingTag = new State() {

		@Override
		public void parse() throws Exception {
			String tag = reader.until(
					/* stop at */ new char[]{'>'},
					/* ignore */ new char[]{' '},
					/* forbid */ new char[]{',', '=', '"', '/'},
					/* include current? */ false);
			pop(tag);
		}

	};

	private State readComment = new State() {

		@Override
		public void parse() throws Exception {
			String comment = reader.until(
					/* stop at */ new char[]{'>'},
					/* ignore */ null,
					/* forbid */ null,
					/* include current? */ false);
			System.out.println("XML COmment: "+comment);
			state = readContent;
		}

	};
	
	private State readHeader = new State() {

		@Override
		public void parse() throws Exception {
			String header = reader.until(
					/* stop at */ new char[]{'>'},
					/* ignore */ null,
					/* forbid */ null,
					/* include current? */ false);
			System.out.println("XML Header: "+header);
			state = readContent;
		}

	};



	private State readAttribute = new State() {

		@Override
		public void parse() throws Exception {
			
			String name = reader.until(
					/* stop at */ new char[]{'='},
					/* ignore */ new char[]{' '},
					/* forbid */ new char[]{',', '<', '>', '"'},
					/* include current? */ true);

			reader.skipWhiteSpace();
			reader.check('"');
			String value = reader.until(
					/* stop at */ new char[]{'"'},
					/* ignore */ new char[]{' '},
					/* forbid */ new char[]{',', '<', '>', '='},
					/* include current? */ false);
			stack.peek().setAttribute(name, value);
			reader.skipWhiteSpace();
			if(reader.isCurrent('>')){
				state = readContent;
			}
			else if(reader.isCurrent('/')){
				reader.next();
				reader.check('>');
				pop();
			}
			// else: state = readAttribute
		}
	};


	private static boolean isBlank(String str){
		int n = str.length();
		if(n>0){
			for(int i=0; i<n; i++){
				if(str.charAt(i)!=' '){
					return false;
				}
			}
			return true;	
		}
		else{
			return true;
		}
		
	}

}
