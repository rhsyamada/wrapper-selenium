package br.rhsyamada.wrapper.selenium.driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.internal.FindsByClassName;
import org.openqa.selenium.internal.FindsByCssSelector;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.internal.FindsByTagName;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.remote.ErrorHandler;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.rhsyamada.wrapper.selenium.command.WrapperCommand;
import br.rhsyamada.wrapper.selenium.element.WrapperWebElement;

public class WrapperWebDriver extends RemoteWebDriver implements WebDriver, JavascriptExecutor, FindsById,
		FindsByClassName, FindsByLinkText, FindsByName, FindsByCssSelector, FindsByTagName, FindsByXPath,
		HasInputDevices, HasCapabilities, Interactive, TakesScreenshot, WrapperCommand {

	private RemoteWebDriver driver;
	private int timeout;

	public WrapperWebDriver(RemoteWebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
	}
	
	public WrapperWebDriver(RemoteWebDriver driver) {
		this.driver = driver;
		this.timeout = 30;
	}

	private RemoteWebDriver getWebDriverWait() {
		waitProcessPage();
		return driver;
	}

	// private WebElementRede castElement(WebElement element) {
	// return new WebElementRede(driver, element, timeout);
	// }

	// private List<WebElement> castElement(List<WebElement> elements) {
	// List<WebElement> ret = new ArrayList<>();
	// elements.forEach(p -> ret.add(castElement(p)));
	// return ret;
	// }

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return driver.getScreenshotAs(target);
	}

	@Override
	public void perform(Collection<Sequence> actions) {
		driver.perform(actions);
	}

	@Override
	public void resetInputState() {
		driver.resetInputState();
	}

	@Override
	public Capabilities getCapabilities() {
		return driver.getCapabilities();
	}

	@Override
	public Keyboard getKeyboard() {
		return driver.getKeyboard();
	}

	@Override
	public Mouse getMouse() {
		return driver.getMouse();
	}

	@Override
	public WebElement findElementByXPath(String using) {
		return castElement(getWebDriverWait().findElementByXPath(using));
	}

	@Override
	public List<WebElement> findElementsByXPath(String using) {
		return castElement(getWebDriverWait().findElementsByXPath(using));
	}

	@Override
	public WebElement findElementByTagName(String using) {
		return castElement(getWebDriverWait().findElementByTagName(using));
	}

	@Override
	public List<WebElement> findElementsByTagName(String using) {
		return castElement(getWebDriverWait().findElementsByTagName(using));
	}

	@Override
	public WebElement findElementByCssSelector(String using) {
		return castElement(getWebDriverWait().findElementByCssSelector(using));
	}

	@Override
	public List<WebElement> findElementsByCssSelector(String using) {
		return castElement(getWebDriverWait().findElementsByCssSelector(using));
	}

	@Override
	public WebElement findElementByName(String using) {
		return castElement(getWebDriverWait().findElementByName(using));
	}

	@Override
	public List<WebElement> findElementsByName(String using) {
		return castElement(getWebDriverWait().findElementsByName(using));
	}

	@Override
	public WebElement findElementByLinkText(String using) {
		return castElement(getWebDriverWait().findElementByLinkText(using));
	}

	@Override
	public List<WebElement> findElementsByLinkText(String using) {
		return castElement(getWebDriverWait().findElementsByLinkText(using));
	}

	@Override
	public WebElement findElementByPartialLinkText(String using) {
		return castElement(getWebDriverWait().findElementByPartialLinkText(using));
	}

	@Override
	public List<WebElement> findElementsByPartialLinkText(String using) {
		return castElement(getWebDriverWait().findElementsByPartialLinkText(using));
	}

	@Override
	public WebElement findElementByClassName(String using) {
		return castElement(getWebDriverWait().findElementByClassName(using));
	}

	@Override
	public List<WebElement> findElementsByClassName(String using) {
		return castElement(getWebDriverWait().findElementsByClassName(using));
	}

	@Override
	public WebElement findElementById(String using) {
		return castElement(getWebDriverWait().findElementById(using));
	}

	@Override
	public List<WebElement> findElementsById(String using) {
		return castElement(getWebDriverWait().findElementsById(using));
	}

	@Override
	public Object executeScript(String script, Object... args) {
		return driver.executeScript(script, args);
	}

	@Override
	public Object executeAsyncScript(String script, Object... args) {
		return driver.executeAsyncScript(script, args);
	}

	@Override
	public void get(String url) {
		driver.get(url);
		waitProcessPage();
	}

	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return castElement(getWebDriverWait().findElements(by));
	}

	@Override
	public WebElement findElement(By by) {
		return castElement(getWebDriverWait().findElement(by));
	}

	@Override
	public String getPageSource() {
		return driver.getPageSource();
	}

	@Override
	public void close() {
		driver.close();
	}

	@Override
	public void quit() {
		driver.quit();
	}

	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override
	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	@Override
	public Navigation navigate() {
		return driver.navigate();
	}

	@Override
	public Options manage() {
		return driver.manage();
	}

	public void setFileDetector(FileDetector detector) {
		driver.setFileDetector(detector);
	}

	public SessionId getSessionId() {
		return driver.getSessionId();
	}

	public ErrorHandler getErrorHandler() {
		return driver.getErrorHandler();
	}

	public void setErrorHandler(ErrorHandler handler) {
		driver.setErrorHandler(handler);
	}

	public void setLogLevel(Level level) {
		driver.setLogLevel(level);
	}

	public enum When {
		BEFORE, AFTER, EXCEPTION
	}

	@Override
	public String toString() {
		return driver.toString();
	}

	@Override
	public Boolean existsElement(By by, long timeWait) {
		try {
			ExpectedCondition<Boolean> waitStar = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						driver.findElement(by);
						return true;
					} catch (Exception e) {
						return false;
					}
				}
			};
			WebDriverWait tempWebDriverWait = new WebDriverWait(driver, timeWait);
			return tempWebDriverWait.until(waitStar);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void moveToElement(WebElement element) {
		new Actions(driver).moveToElement(element).build().perform();
	}

	@Override
	public void scrollToElement(WebElement element) {
		int iniY = getCurrentScrollY();
		int finY = getWindowHeight();
		int positionY = element.getLocation().getY();

		if (!(positionY >= iniY && positionY <= finY)) {
			scrollToWithJavaScript(element.getLocation().getX(), positionY);
		}
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	@Override
	public <V> V until(Function<? super WebDriver, V> isTrue) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
		return webDriverWait.until(isTrue);
	}

	@Override
	public Boolean waitProcessPage() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
		return webDriverWait.until(waitProcessAngularJs()) && webDriverWait.until(waitProcessJQuery())
				&& webDriverWait.until(waitProcessJavaScript());
	}

	@Override
	public void clickJavaScript(WebElement element) {
		scrollToElement(element);
		executeJs("arguments[0].click();", element);
		waitProcessPage();
	}

	@Override
	public int getFullHeight() {
		String pageHeightJs = "return Math.max(document.body.scrollHeight, document.body.offsetHeight, "
				+ "document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight);";
		return ((Number) executeJs(pageHeightJs)).intValue();
	}

	@Override
	public int getFullWidth() {
		String pageHeightJs = "return Math.max(document.body.scrollHeight, document.body.offsetHeight, "
				+ "document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight);";
		return ((Number) executeJs(pageHeightJs)).intValue();
	}

	@Override
	public int getWindowHeight() {
		String viewReportHeightJs = "return window.innerHeight || document.documentElement.clientHeight || document.getElementsByTagName('body')[0].clientHeight;";
		return ((Number) executeJs(viewReportHeightJs)).intValue();
	}

	@Override
	public int getWindowWidth() {
		String viewReportHeightJs = "return window.innerWidth || document.documentElement.clientWidth || document.getElementsByTagName('body')[0].clientWidth;";
		return ((Number) executeJs(viewReportHeightJs)).intValue();
	}

	@Override
	public int getCurrentScrollY() {
		return ((Number) executeJs("var scrY = window.scrollY; if(scrY){return scrY;} else {return 0;}"))
				.intValue();
	}

	@Override
	public int getCurrentScrollX() {
		return ((Number) executeJs("var scrX = window.scrollX; if(scrX){return scrX;} else {return 0;}"))
				.intValue();
	}

	@Override
	public void scrollToWithJavaScript(int scrollX, int scrollY) {
		executeJs("scrollTo(arguments[0], arguments[1]); return [];", scrollX, scrollY);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T executeJs(String script, Object... args) {
		try {
			return (T) ((JavascriptExecutor) driver).executeScript(script, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public WebElement castElement(WebElement element) {
		return new WrapperWebElement(element, this);
	}

	@Override
	public List<WebElement> castElement(List<WebElement> element) {
		List<WebElement> ret = new ArrayList<>();
		element.forEach(p -> ret.add(new WrapperWebElement(p, this)));
		return ret;
	}

	private ExpectedCondition<Boolean> waitProcessJQuery() {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return Long.parseLong(
							((JavascriptExecutor) driver).executeScript("return jQuery.active").toString()) == 0;
				} catch (Exception e) {
					return true;
				}
			}
		};
		return jQueryLoad;
	}

	private ExpectedCondition<Boolean> waitProcessJavaScript() {
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return jsLoad;
	}

	private ExpectedCondition<Boolean> waitProcessAngularJs() {
		ExpectedCondition<Boolean> AngularJsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					String js = "if (typeof angular !== 'undefined') {\r\n"
							+ "	return angular.element(document.body).injector().get('$http').pendingRequests.length;\r\n"
							+ "} else {return 0;}";
					return ((JavascriptExecutor) driver).executeScript(js).toString().equals("0");
				} catch (Exception e) {
					return true;
				}
			}
		};
		return AngularJsLoad;
	}

}
