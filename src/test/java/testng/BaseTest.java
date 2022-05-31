package testng;

import org.testng.annotations.Listeners;
import ta.core.testng.listeners.TestListener;

@Listeners(TestListener.class)
public abstract class BaseTest {
}
