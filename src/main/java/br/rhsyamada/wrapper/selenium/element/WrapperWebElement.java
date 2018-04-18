package br.rhsyamada.wrapper.selenium.element;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.FindsByClassName;
import org.openqa.selenium.internal.FindsByCssSelector;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.internal.FindsByTagName;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.internal.HasIdentity;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.rhsyamada.wrapper.selenium.command.WrapperCommand;

@SuppressWarnings("deprecation")
public class WrapperWebElement extends RemoteWebElement
		implements WebElement, FindsByLinkText, FindsById, FindsByName, FindsByTagName, FindsByClassName,
		FindsByCssSelector, FindsByXPath, WrapsDriver, Locatable, HasIdentity, TakesScreenshot {
	private RemoteWebElement element;
	private WrapperCommand command;

	public WrapperWebElement(WebElement element, WrapperCommand command) {
		this.element = (RemoteWebElement) element;
		this.command = command;
		this.command.waitProcessPage();
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return element.getScreenshotAs(target);
	}

	@Override
	public void click() {
		command.scrollToElement(element);
		command.until(ExpectedConditions.visibilityOf(element));
		try {
			element.click();
		} catch (Exception e) {
			if (e.getMessage().contains("is not clickable"))
				command.clickJavaScript(element);
			else
				throw new WebDriverException(e);
		}

		command.waitProcessPage();
	}

	@Override
	public void submit() {
		command.scrollToElement(element);
		command.until(ExpectedConditions.visibilityOf(element));
		element.submit();
		command.waitProcessPage();
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		command.scrollToElement(element);
		command.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(keysToSend);
		command.waitProcessPage();
	}

	@Override
	public void clear() {
		command.scrollToElement(element);
		command.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		command.waitProcessPage();
	}

	@Override
	public String getTagName() {
		return element.getTagName();
	}

	@Override
	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	@Override
	public boolean isSelected() {
		return element.isSelected();
	}

	@Override
	public boolean isEnabled() {
		return element.isEnabled();
	}

	@Override
	public String getText() {
		return element.getText();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return element.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		return element.findElement(by);
	}

	@Override
	public boolean isDisplayed() {
		return element.isDisplayed();
	}

	@Override
	public Point getLocation() {
		return element.getLocation();
	}

	@Override
	public Dimension getSize() {
		return element.getSize();
	}

	@Override
	public Rectangle getRect() {
		return element.getRect();
	}

	@Override
	public String getCssValue(String propertyName) {
		return element.getCssValue(propertyName);
	}

	@Override
	public String getId() {
		return element.getId();
	}

	@Override
	public Coordinates getCoordinates() {
		return element.getCoordinates();
	}

	@Override
	public WebDriver getWrappedDriver() {
		return element.getWrappedDriver();
	}

	@Override
	public WebElement findElementByXPath(String using) {
		return element.findElementByXPath(using);
	}

	@Override
	public List<WebElement> findElementsByXPath(String using) {
		return element.findElementsByXPath(using);
	}

	@Override
	public WebElement findElementByCssSelector(String using) {
		return element.findElementByCssSelector(using);
	}

	@Override
	public List<WebElement> findElementsByCssSelector(String using) {
		return element.findElementsByCssSelector(using);
	}

	@Override
	public WebElement findElementByClassName(String using) {
		return element.findElementByClassName(using);
	}

	@Override
	public List<WebElement> findElementsByClassName(String using) {
		return element.findElementsByClassName(using);
	}

	@Override
	public WebElement findElementByTagName(String using) {
		return element.findElementByTagName(using);
	}

	@Override
	public List<WebElement> findElementsByTagName(String using) {
		return element.findElementsByTagName(using);
	}

	@Override
	public WebElement findElementByName(String using) {
		return element.findElementByName(using);
	}

	@Override
	public List<WebElement> findElementsByName(String using) {
		return element.findElementsByName(using);
	}

	@Override
	public WebElement findElementById(String using) {
		return element.findElementById(using);
	}

	@Override
	public List<WebElement> findElementsById(String using) {
		return element.findElementsById(using);
	}

	@Override
	public WebElement findElementByLinkText(String using) {
		return element.findElementByLinkText(using);
	}

	@Override
	public List<WebElement> findElementsByLinkText(String using) {
		return element.findElementsByLinkText(using);
	}

	@Override
	public WebElement findElementByPartialLinkText(String using) {
		return element.findElementByPartialLinkText(using);
	}

	@Override
	public List<WebElement> findElementsByPartialLinkText(String using) {
		return element.findElementsByPartialLinkText(using);
	}
}
