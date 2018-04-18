package br.rhsyamada.wrapper.selenium.elementlocator;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class WrapperElementLocatorFactory implements ElementLocatorFactory {
	private final SearchContext searchContext;
	private final Object instance;

	public WrapperElementLocatorFactory(final SearchContext searchContext, final Object instance) {
		this.searchContext = searchContext;
		this.instance = instance;
	}

	@Override
	public ElementLocator createLocator(Field field) {
		return new WrapperElementLocator(searchContext, field, instance);
	}
}
