package com.platinumwill.dao.impl.hibernate;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDaoImpl {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final Class<?> COLLECTION_INTERFACE = Collection.class;
	
	@SuppressWarnings("unchecked")
	protected void copyPropertiesForHibernate(Object source, Object target, String[] ignoreProperties, boolean ignoreCollection) {
		BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
		BeanWrapper targetWrapper = new BeanWrapperImpl(target);
		List<String> originalIgnorePropertyList = (ignoreProperties != null ) ? Arrays.asList(ignoreProperties): new ArrayList<String>();
		List<String> ignorePropertyList = new ArrayList<String>();
		ignorePropertyList.addAll(originalIgnorePropertyList);
		
		List<String> copyableCollectionPropertyNameList = new ArrayList<String>();
		PropertyDescriptor[] sourceProperties = sourceWrapper.getPropertyDescriptors();
		for (PropertyDescriptor sourceProperty: sourceProperties) {
			String propertyName = sourceProperty.getName();
			if (originalIgnorePropertyList.contains(propertyName)) {
				continue;
			}
			Class<?> propertyClass = sourceProperty.getPropertyType();
			boolean isCollection = this.isCollection(propertyClass);
			if (isCollection) {
				if (!ignorePropertyList.contains(propertyName)) {
					ignorePropertyList.add(propertyName);
				}
				if (ignoreCollection) {
					continue;
				}
				if (hasTrasientAnnotation(sourceProperty)) {
					continue;
				}
				copyableCollectionPropertyNameList.add(propertyName);
				Object targetPropertyValue = targetWrapper.getPropertyValue(propertyName);
				if (targetPropertyValue != null) {
					Collection<?> targetCollectionPropertyValue = (Collection<?>) targetPropertyValue;
					targetCollectionPropertyValue.clear();
				}
			}
		}
		
		this.getSessionFactory().getCurrentSession().flush();
		
		for (String copyableCollectionPropertyName: copyableCollectionPropertyNameList) {
			Object sourcePropertyValue = sourceWrapper.getPropertyValue(copyableCollectionPropertyName);
			if (sourcePropertyValue != null) {
				Object targetPropertyValue = targetWrapper.getPropertyValue(copyableCollectionPropertyName);
				@SuppressWarnings("rawtypes")
				Collection targetCollectionPropertyValue = (Collection) targetPropertyValue;
				@SuppressWarnings("rawtypes")
				Collection sourceCollectionPropertyValue = (Collection) sourcePropertyValue;
				targetCollectionPropertyValue.addAll(sourceCollectionPropertyValue);
			}
		}
		
		ignoreProperties = ignorePropertyList.toArray(new String[]{});
		BeanUtils.copyProperties(source, target, ignoreProperties);
	}
	
	private boolean hasTrasientAnnotation(PropertyDescriptor sourceProperty) {
		for (Annotation annotation: sourceProperty.getReadMethod().getAnnotations()) {
			if (annotation instanceof Transient) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isCollection(Class<?> clazz) {
		if (clazz.equals(COLLECTION_INTERFACE)) {
			return true;
		}
		Class<?>[] interfaces = clazz.getInterfaces();
		List<Class<?>> propertyInterfaceList = Arrays.asList(interfaces);
		if (propertyInterfaceList.contains(COLLECTION_INTERFACE)) {
			return true;
		}
		return false;
	}
	
	protected Session getSession() {
		Session result = this.getSessionFactory().getCurrentSession();
		return result;
	}

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
