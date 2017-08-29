package com.huipu.test.testthreadlocal;

import junit.framework.TestCase;

public class TestApp extends TestCase {

	public void testThreadLocal() {
		ThreadLocalTest t = new ThreadLocalTest();
		Thread t1 = new Thread(t, "Thread A");
		Thread t2 = new Thread(t, "Thread B");
		t1.start();
		t2.start();
	}
}
