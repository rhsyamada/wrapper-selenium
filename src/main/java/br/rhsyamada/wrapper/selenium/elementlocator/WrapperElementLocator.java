package br.rhsyamada.wrapper.selenium.elementlocator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class WrapperElementLocator implements ElementLocator {
	private SearchContext searchContext;
	private final boolean shouldCache;
	private final By by;
	private WebElement cachedElement;
	private List<WebElement> cachedElementList;
	private final Object instance;
	private final Field field;

	public WrapperElementLocator(SearchContext searchContext, Field field, Object instance) {
		this(searchContext, new Annotations(field), field, instance);
	}

	public WrapperElementLocator(SearchContext searchContext, AbstractAnnotations annotations, Field field,
			Object instance) {
		this.searchContext = searchContext;
		this.shouldCache = annotations.isLookupCached();
		this.by = annotations.buildBy();
		this.instance = instance;
		this.field = field;
	}

	@Override
	public WebElement findElement() {
		if (cachedElement != null && shouldCache) {
			return cachedElement;
		}

		WebElement element = searchContext.findElement(castByWithDynamicElement(by));
		if (shouldCache) {
			cachedElement = element;
		}

		return element;
	}

	@Override
	public List<WebElement> findElements() {
		if (cachedElementList != null && shouldCache) {
			return cachedElementList;
		}

		List<WebElement> elements = searchContext.findElements(castByWithDynamicElement(by));
		if (shouldCache) {
			cachedElementList = elements;
		}

		return elements;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " '" + by + "'";
	}

	@SuppressWarnings("unchecked")
	private By castByWithDynamicElement(By by) {
		Object[] val = ((Map<String, Object[]>) getValueField("dynamicElementKeys", instance)).get(field.getName());
		if (by.getClass().equals(By.ById.class)) {
			String value = String.format((String) getValueField("id", by), val);
			return new By.ById(value);
		} else if (by.getClass().equals(By.ByLinkText.class)) {
			String value = String.format((String) getValueField("linkText", by), val);
			return new By.ByLinkText(value);
		} else if (by.getClass().equals(By.ByPartialLinkText.class)) {
			String value = String.format((String) getValueField("linkText", by), val);
			return new By.ByPartialLinkText(value);
		} else if (by.getClass().equals(By.ByName.class)) {
			String value = String.format((String) getValueField("name", by), val);
			return new By.ByName(value);
		} else if (by.getClass().equals(By.ByTagName.class)) {
			String value = String.format((String) getValueField("name", by), val);
			return new By.ByTagName(value);
		} else if (by.getClass().equals(By.ByXPath.class)) {
			String value = String.format((String) getValueField("xpathExpression", by), val);
			return new By.ByXPath(value);
		} else if (by.getClass().equals(By.ByClassName.class)) {
			String value = String.format((String) getValueField("className", by), val);
			return new By.ByClassName(value);
		} else if (by.getClass().equals(By.ByCssSelector.class)) {
			String value = String.format((String) getValueField("selector", by), val);
			return new By.ByCssSelector(value);
		} else {
			return by;
		}
	}

	private Object getValue(String key, Object object, Class<?> klass) {
		Field field = null;
		try {
			field = klass.getDeclaredField(key);
		} catch (Exception e) {
		}

		if (field != null) {
			try {
				field.setAccessible(true);
				return field.get(object);
			} catch (Exception e) {
				throw new RuntimeException("Erro ao encontrar o valor do field [" + key + "].", e);
			}

		} else if (klass.getSuperclass() != null)
			return getValue(key, object, klass.getSuperclass());
		else
			throw new RuntimeException("Erro ao encontrar o valor do field [" + key + "].");
	}

	private Object getValueField(String key, Object object) {
		return getValue(key, object, object.getClass());
	}
}
