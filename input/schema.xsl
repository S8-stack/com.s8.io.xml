<?xml version="1.0" encoding="UTF-8"?>
<xs:schema
 xmlns:xs="http://www.w3.org/2001/XMLSchema"
 targetNamespace="http://s8.com"
 xmlns:tns="http://s8.com"
 elementFormDefault="qualified">
	<xs:element name="TestWrapperStructure" type="tns:com.s8.io.xml.demos.repo01.Wrapper"/>

	<xs:complexType name="com.s8.io.xml.demos.repo01.TestClass4">
		<xs:sequence>
		<xs:choice maxOccurs="unbounded" minOccurs="1">
			<xs:element name="user_user" type="tns:com.s8.io.xml.demos.repo01.User" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="user" type="tns:com.s8.io.xml.demos.repo01.User" minOccurs="0" maxOccurs="unbounded" />
		</xs:choice>
		</xs:sequence>
		<xs:attribute name="toto" type="xs:string"/>
		<xs:attribute name="b" type="xs:string"/>
	</xs:complexType>

	<xs:complexType name="com.s8.io.xml.demos.repo01.Wrapper">
		<xs:sequence>
		<xs:choice maxOccurs="unbounded" minOccurs="1">
			<xs:element name="EeClass3" type="tns:com.s8.io.xml.demos.repo01.TestClass3" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="field_Module" type="tns:com.s8.io.xml.demos.repo01.Module" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="field" type="tns:com.s8.io.xml.demos.repo01.Module" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="item_EeClass4" type="tns:com.s8.io.xml.demos.repo01.TestClass4" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="EeClass4" type="tns:com.s8.io.xml.demos.repo01.TestClass4" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="item_EeClass3" type="tns:com.s8.io.xml.demos.repo01.TestClass3" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="Module" type="tns:com.s8.io.xml.demos.repo01.Module" minOccurs="0" maxOccurs="unbounded" />
		</xs:choice>
		</xs:sequence>
		<xs:attribute name="factor" type="xs:string"/>
	</xs:complexType>

	<xs:complexType name="com.s8.io.xml.demos.repo01.Module">
		<xs:attribute name="b2" type="xs:string"/>
	</xs:complexType>

	<xs:complexType name="com.s8.io.xml.demos.repo01.User">
		<xs:sequence>
		<xs:choice maxOccurs="unbounded" minOccurs="1">
			<xs:element name="note" type="xs:string" minOccurs="0" maxOccurs="unbounded" />
		</xs:choice>
		</xs:sequence>
		<xs:attribute name="toto" type="xs:string"/>
		<xs:attribute name="password" type="xs:string"/>
		<xs:attribute name="name" type="xs:string"/>
	</xs:complexType>

	<xs:complexType name="com.s8.io.xml.demos.repo01.TestClass3">
		<xs:attribute name="toto" type="xs:string"/>
	</xs:complexType>

	<xs:complexType name="com.s8.io.xml.demos.repo01.TestClass2">
		<xs:sequence>
		<xs:choice maxOccurs="unbounded" minOccurs="1">
			<xs:element name="EeClass3" type="tns:com.s8.io.xml.demos.repo01.TestClass3" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="field_Module" type="tns:com.s8.io.xml.demos.repo01.Module" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="field" type="tns:com.s8.io.xml.demos.repo01.Module" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="item_EeClass4" type="tns:com.s8.io.xml.demos.repo01.TestClass4" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="EeClass4" type="tns:com.s8.io.xml.demos.repo01.TestClass4" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="item_EeClass3" type="tns:com.s8.io.xml.demos.repo01.TestClass3" minOccurs="0" maxOccurs="unbounded" />
			<xs:element name="Module" type="tns:com.s8.io.xml.demos.repo01.Module" minOccurs="0" maxOccurs="unbounded" />
		</xs:choice>
		</xs:sequence>
		<xs:attribute name="b2" type="xs:string"/>
		<xs:attribute name="factor" type="xs:string"/>
	</xs:complexType>
</xs:schema>