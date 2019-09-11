package com.kori_47.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ObjectUtilsTest {
	/*-------------------------------------------------------------------------------------
	 * 
	 * NUMBERS
	 *
	 * ------------------------------------------------------------------------------------
	 */
	@Test
	public void testIsNegative() {
		// Test returns true when a number is negative
		assertTrue(ObjectUtils.isNegative(-5));
		assertTrue(ObjectUtils.isNegative(-9l));
		assertTrue(ObjectUtils.isNegative(-78.0f));
		assertTrue(ObjectUtils.isNegative(-123.676673893277843d));
		assertTrue(ObjectUtils.isNegative(new BigDecimal("-94.45")));
		
		// Test returns false when a number is positive
		assertFalse(ObjectUtils.isNegative(56));
		assertFalse(ObjectUtils.isNegative(1l));
		assertFalse(ObjectUtils.isNegative(85.56f));
		assertFalse(ObjectUtils.isNegative(213.6539643849432438547d));
		assertFalse(ObjectUtils.isNegative(new BigDecimal("344.78")));
	}

	@Test
	public void testNonNegativeReturnValues() {
		assertEquals(36, ObjectUtils.requireNonNegative(36));
		assertEquals(64l, ObjectUtils.requireNonNegative(64l));
		assertEquals(56f, ObjectUtils.requireNonNegative(56f));
		assertEquals(98.09d, ObjectUtils.requireNonNegative(98.09d));
		assertEquals(new BigDecimal("872.23"), ObjectUtils.requireNonNegative(new BigDecimal("872.23")));
		
		assertEquals(574, ObjectUtils.requireNonNegative(574, null));
		assertEquals(934l, ObjectUtils.requireNonNegative(934l, null));
		assertEquals(2.56f, ObjectUtils.requireNonNegative(2.56f, null));
		assertEquals(490.50d, ObjectUtils.requireNonNegative(490.50d, null));
		assertEquals(new BigDecimal("0.50"), ObjectUtils.requireNonNegative(new BigDecimal("0.50"), null));
	}

	@Test
	public void testNonNegativeExceptions() {
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-36));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-64l));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-56f));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-98.09d));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(new BigDecimal("-872.23")));
		assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonNegative(null));
		
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-36, null));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-64l, null));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-56f, null));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-98.09d, null));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(new BigDecimal("-872.23"), null));
		assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonNegative(null, null));
	}

	@Test
	public void testNonNegativeExceptionMessages() {
		IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-36, "given integer must not be negative."));
		assertEquals("given integer must not be negative.", ex1.getMessage());
		IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-64l, "given long must not be negative."));
		assertEquals("given long must not be negative.", ex2.getMessage());
		IllegalArgumentException ex3 = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-56f, "given float must not be negative."));
		assertEquals("given float must not be negative.", ex3.getMessage());
		IllegalArgumentException ex4 = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(-98.09d, "given decimal must not be negative."));
		assertEquals("given decimal must not be negative.", ex4.getMessage());
		IllegalArgumentException ex5 = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonNegative(new BigDecimal("-872.23"), "given BigDecimal must not be negative."));
		assertEquals("given BigDecimal must not be negative.", ex5.getMessage());
	}

	/*-------------------------------------------------------------------------------------
	 * 
	 * SERIALIZABLES
	 *
	 * ------------------------------------------------------------------------------------
	 */
	@Test
	public void testIsSerializable() {
		assertFalse(ObjectUtils.isSerializable(new NonSerializableClass()));
		assertTrue(ObjectUtils.isSerializable(new SerializableClass()));
	}

	@Test
	public void testRequireSerializableReturnValues() {
		// Test the methods returns the expected value
		SerializableClass instance = new SerializableClass();
		assertEquals(instance, ObjectUtils.requireSerializable(instance));
		assertEquals(instance, ObjectUtils.requireSerializable(instance, null));
	}

	@Test
	public void testRequireSerializableExceptions() {
		NonSerializableClass instance = new NonSerializableClass();
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireSerializable(instance));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireSerializable(instance, null));
	}

	@Test
	public void testRequireSerializableExceptionMessages() {
		NonSerializableClass instance = new NonSerializableClass();
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireSerializable(instance, "instance must be serializable."));
		assertEquals("instance must be serializable.", ex.getMessage());
	}

	/*-------------------------------------------------------------------------------------
	 * 
	 * STRINGS
	 *
	 * ------------------------------------------------------------------------------------
	 */
	@Test
	public void testStringRequireNonNullReturnValues() {
		// Test the methods returns the expected value
		assertEquals("name", ObjectUtils.requireNonEmptyString("name"));
		assertEquals("name", ObjectUtils.requireNonEmptyString("name", null));
		assertEquals("name", ObjectUtils.requireNonEmptyString("name", null, null));
	}

	@Test
	public void testStringRequireNonNullExceptions() {
		// Test the methods throw a NullPointerException when a null string is passed
		assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonEmptyString(null));
		assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonEmptyString(null, null));
		assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonEmptyString(null, null, null));
		
		// Test the methods throw an IllegalArgumentException when an empty string is passed
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmptyString(""));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmptyString("", null));
		assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmptyString("", null, null));
	}
	
	@Test
	public void testStringRequireNonNullExceptionMessages() {
		// Test the methods throw a NullPointerException with the right message.
		NullPointerException ex1 = assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonEmptyString(null, null));
		assertEquals("value cannot be null.", ex1.getMessage());
		NullPointerException ex2 = assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonEmptyString(null, "firstName"));
		assertEquals("firstName cannot be null.", ex2.getMessage());
		
		NullPointerException ex3 = assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonEmptyString(null, null, null));
		assertEquals("value cannot be null.", ex3.getMessage());
		NullPointerException ex4 = assertThrows(NullPointerException.class, () -> ObjectUtils.requireNonEmptyString(null, "firstName cannot be null.", null));
		assertEquals("firstName cannot be null.", ex4.getMessage());
		
		// Test the methods throw an IllegalArgumentException with the right message.
		IllegalArgumentException ex5 = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmptyString("", null));
		assertEquals("value cannot be empty.", ex5.getMessage());
		IllegalArgumentException ex6 = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmptyString("", "firstName"));
		assertEquals("firstName cannot be empty.", ex6.getMessage());
		
		IllegalArgumentException ex7 = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmptyString("", null, null));
		assertEquals("value cannot be empty.", ex7.getMessage());
		IllegalArgumentException ex8 = assertThrows(IllegalArgumentException.class, () -> ObjectUtils.requireNonEmptyString("", null, "firstName cannot be empty."));
		assertEquals("firstName cannot be empty.", ex8.getMessage());
	}
	
	static class NonSerializableClass {}
	
	static class SerializableClass implements Serializable {

		/**
		 * SUID
		 */
		private static final long serialVersionUID = -8077617538474793989L;
		
	}
}
