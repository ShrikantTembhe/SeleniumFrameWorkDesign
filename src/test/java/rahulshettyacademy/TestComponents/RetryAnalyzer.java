package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	// Define the variable to retry the test with specific frequency.
	int count = 0;
	int maxTry = 1;

	// Write an logic so that the test will be retried only once.
	// if all test passed then this class will never execute.
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (count < maxTry) {
			count++;
			return true;
		}

		return false;
	}

}
