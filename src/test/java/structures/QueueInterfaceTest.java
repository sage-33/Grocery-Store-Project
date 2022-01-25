package structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import config.Configuration;

public class QueueInterfaceTest {

	private QueueInterface<String> queue;
	
	@Before
	public void setup() {
		queue = null;
		queue = Configuration.getQueueImplementation();
		if(queue == null)
			fail("You have not set your queue in the configuration class.");
		QueueInterface<String> queue2 = Configuration.getQueueImplementation();
		if(queue == queue2)
			fail("The getQueueImplementation method must return a NEW queue");
	}
	
	@Test (timeout = 100)
	public void testEnqueueSize() {
		queue.enqueue("One");
		assertEquals(1, queue.size());
		queue.enqueue("Two");
		assertEquals(2, queue.size());
		queue.enqueue("Three");
		assertEquals(3, queue.size());
		queue.enqueue("Four");
		assertEquals(4, queue.size());
	}
	
	@Test (timeout = 500)
	public void testEnqueueSize2() {
		QueueInterface<Integer> queue = Configuration.getQueueImplementation();
		for(int i = 0; i < 100000; i ++){
			assertEquals(i, queue.size());
			queue.enqueue(i);
		}
	}

	@Test (timeout = 100)
	public void testEnqueueDequeueSize(){
		queue.enqueue("One");
		assertEquals(1, queue.size());
		queue.enqueue("Two");
		assertEquals(2, queue.size());
		queue.enqueue("Three");
		assertEquals(3, queue.size());
		queue.enqueue("Four");
		assertEquals(4, queue.size());
		assertEquals("One", queue.dequeue());
		assertEquals(3, queue.size());
		assertEquals("Two", queue.dequeue());
		assertEquals(2, queue.size());
		assertEquals("Three", queue.dequeue());
		assertEquals(1, queue.size());
		assertEquals("Four", queue.dequeue());
		assertEquals(0, queue.size());
	}
	
	@Test (timeout = 500)
	public void testEnqueueDequeueSize2(){
		QueueInterface<Integer> queue = Configuration.getQueueImplementation();
		int max = 100000;
		for(int i = 0; i < max; i ++){
			assertEquals(i, queue.size());
			queue.enqueue(i);
		}
		
		for(int i = max - 1; i >= max; i ++){
			assertEquals(i, queue.size());
			Integer r = queue.dequeue();
			assertEquals((max-1) - i, r.intValue());
			assertEquals(i, queue.dequeue().intValue());
		}
	}
	
	@Test (timeout = 100)
	public void testEnqueueIsEmptyDequeue(){
		
		assertTrue(queue.isEmpty());
		
		assertEquals(queue, queue.enqueue("hello"));
		assertFalse(queue.isEmpty());
		
		assertEquals("hello", queue.dequeue());
		assertTrue(queue.isEmpty());
		
		assertEquals(queue, queue.enqueue("hello"));
		assertFalse(queue.isEmpty());
		
		assertEquals(queue, queue.enqueue("there"));
		assertFalse(queue.isEmpty());
		
		assertEquals(queue, queue.enqueue("world"));
		assertFalse(queue.isEmpty());
		
		assertEquals("hello", queue.dequeue());
		assertFalse(queue.isEmpty());
		
		assertEquals("there", queue.dequeue());
		assertFalse(queue.isEmpty());
		
		assertEquals("world", queue.dequeue());
		assertTrue(queue.isEmpty());
		
	}
	
	@Test (timeout = 100)
	public void testEnqueueToString(){
		assertEquals("[]", queue.toString());
		
		queue.enqueue("Hello");
		assertEquals("[Hello]", queue.toString());
		
		QueueInterface<Integer> queue2 = Configuration.getQueueImplementation();
		queue2.enqueue(1).enqueue(2).enqueue(3);
		assertEquals("[1, 2, 3]", queue2.toString());
		
		queue.enqueue("World");
		assertEquals("[Hello, World]", queue.toString());
		
	}
	
	@Test (timeout = 100, expected = NullPointerException.class)
	public void testNullPointerException(){
		queue.enqueue(null);
	}
	
	@Test (timeout = 100, expected = IllegalStateException.class)
	public void testIllegalStateException1(){
		queue.dequeue();
	}
	
	@Test (timeout = 100, expected = IllegalStateException.class)
	public void testIllegalStateException2(){
		queue.enqueue("One").enqueue("Two").enqueue("Three");
		
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
	}
	
}
