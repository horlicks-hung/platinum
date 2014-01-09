package com.platinumwill.test.data.service;


public interface DbCopyService {
	
	public <T> void deleteAllRecords(Class<T> clazz);
	public <T> void copyTable(Class<T> clazz);
}
