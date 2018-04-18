package br.rhsyamada.wrapper.selenium.command;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WrapperCommand {
	public Boolean existsElement(By by, long timeWait);

	public void moveToElement(WebElement element);

	public void scrollToElement(WebElement element);

	public <V> V until(Function<? super WebDriver, V> isTrue);

	public Boolean waitProcessPage();

	public void clickJavaScript(WebElement element);

	public int getFullHeight();

	public int getFullWidth();

	public int getWindowHeight();

	public int getWindowWidth();

	public int getCurrentScrollY();

	public int getCurrentScrollX();

	public void scrollToWithJavaScript(int scrollX, int scrollY);

	public <T> T executeJs(String script, Object... args);

	public WebElement castElement(WebElement element);

	public List<WebElement> castElement(List<WebElement> element);
}
