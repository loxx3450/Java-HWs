package com.loxx3450.hw_07_02_25;

import com.loxx3450.hw_07_02_25.annotations.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;

public class Validator {
	public static Map<String, List<String>> validateObject(Object obj, ExecutorService executorService) {
		if (obj == null)
			throw new NullPointerException("Object is null");

		Map<String, List<String>> errors = new ConcurrentHashMap<>();

		Class<?> clazz = obj.getClass();

		List<CompletableFuture<Void>> futures = new ArrayList<>();

		for (Field field : clazz.getDeclaredFields()) {
			futures.add(CompletableFuture.runAsync(() -> {
				try {
					checkFieldOnErrors(field, obj, errors);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}, executorService));
		}

		CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
		allOf.join();

		return errors;
	}

	private static void checkFieldOnErrors(Field field, Object obj, Map<String, List<String>> errors) throws IllegalAccessException {
		field.setAccessible(true);

		Object value = field.get(obj);

		if (field.isAnnotationPresent(NotNull.class)) {
			checkNotNull(value, field, errors);
		}
		if (field.isAnnotationPresent(NotEmpty.class)) {
			checkNotEmpty(value, field, errors);
		}

		// All further checks should avoid this case
		if (value == null)
			return;

		if (field.isAnnotationPresent(Size.class)) {
			checkSize(value, field, errors);
		}
		if (field.isAnnotationPresent(Min.class)) {
			checkMin(value, field, errors);
		}
		if (field.isAnnotationPresent(Max.class)) {
			checkMax(value, field, errors);
		}
		if (field.isAnnotationPresent(Pattern.class)) {
			checkPattern(value, field, errors);
		}
		if (field.isAnnotationPresent(Email.class)) {
			checkEmail(value, field, errors);
		}
		if (field.isAnnotationPresent(Future.class)) {
			checkFuture(value, field, errors);
		}
	}

	private static void checkNotNull(Object value, Field field, Map<String, List<String>> errors) {
		NotNull annotation = field.getAnnotation(NotNull.class);

		if (value == null) {
			addError(field, annotation.message(), errors);
		}
	}

	private static void checkNotEmpty(Object value, Field field, Map<String, List<String>> errors) {
		NotEmpty annotation = field.getAnnotation(NotEmpty.class);

		boolean isEmpty = value == null ||
			(value instanceof String && ((String) value).isEmpty()) ||
			(value instanceof List && ((List<?>) value).isEmpty()) ||
			(value instanceof Map && ((Map<?, ?>) value).isEmpty()) ||
			(value instanceof Collection && ((Collection<?>) value).isEmpty()) ||
			(value.getClass().isArray() && Array.getLength(value) == 0);

		if (isEmpty) {
			addError(field, annotation.message(), errors);
		}
	}

	private static void checkSize(Object value, Field field, Map<String, List<String>> errors) {
		Size annotation = field.getAnnotation(Size.class);

		int length = -1;

		if (value instanceof String)
			length = ((String) value).length();
		else if (value instanceof Collection)
			length = ((Collection<?>) value).size();
		else if (value instanceof Map)
			length = ((Map<?, ?>) value).size();
		else if (value.getClass().isArray())
			length = Array.getLength(value);

		if (length != -1 && (length < annotation.min() || length > annotation.max())) {
			String errorMessage = annotation.message()
				.replace("{min}", String.valueOf(annotation.min()))
				.replace("{max}", String.valueOf(annotation.max()));

			addError(field, errorMessage, errors);
		}
	}

	private static void checkMin(Object value, Field field, Map<String, List<String>> errors) {
		Min annotation = field.getAnnotation(Min.class);

		long minValue = annotation.value();

		if (value instanceof Number) {
			long numberValue = ((Number)value).longValue();
			if (numberValue < minValue) {
				String errorMessage = annotation.message()
					.replace("{value}", String.valueOf(minValue));

				addError(field, errorMessage, errors);
			}
		}
	}

	private static void checkMax(Object value, Field field, Map<String, List<String>> errors) {
		Max annotation = field.getAnnotation(Max.class);

		long maxValue = annotation.value();

		if (value instanceof Number) {
			long numberValue = ((Number)value).longValue();
			if (numberValue > maxValue) {
				String errorMessage = annotation.message()
					.replace("{value}", String.valueOf(maxValue));

				addError(field, errorMessage, errors);
			}
		}
	}

	private static void checkPattern(Object value, Field field, Map<String, List<String>> errors) {
		Pattern annotation = field.getAnnotation(Pattern.class);

		if (! valueMatchesRegex(value, annotation.regex())) {
			addError(field, annotation.message(), errors);
		}
	}

	private static void checkEmail(Object value, Field field, Map<String, List<String>> errors) {
		Email annotation = field.getAnnotation(Email.class);

		if (! valueMatchesRegex(value, annotation.regex())) {
			addError(field, annotation.message(), errors);
		}
	}

	private static boolean valueMatchesRegex(Object value, String regex) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);

		Matcher matcher = pattern.matcher(String.valueOf(value));

		return matcher.matches();
	}

	private static void checkFuture(Object value, Field field, Map<String, List<String>> errors) {
		Future annotation = field.getAnnotation(Future.class);

		boolean isValid = isInFuture(value);

		if (! isValid) {
			addError(field, annotation.message(), errors);
		}
	}

	private static boolean isInFuture(Object value) {
		if (value instanceof LocalDate localDate) {
			return localDate.isAfter(LocalDate.now());
		} else if (value instanceof LocalDateTime localDateTime) {
			return localDateTime.isAfter(LocalDateTime.now());
		} else if (value instanceof ZonedDateTime zonedDateTime) {
			return zonedDateTime.isAfter(ZonedDateTime.now());
		} else if (value instanceof Date date) {
			return date.after(new Date());
		}
		return false;
	}

	private static void addError(Field field, String errorMessage, Map<String, List<String>> errors) {
		errors.computeIfAbsent(field.getName(), _ -> new ArrayList<>())
			.add(errorMessage);
	}
}
