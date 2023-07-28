package com.raghu.spring.test;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;
import org.junit.runner.RunWith;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses({"**/*Test.class", "!**/*IntegrationTest.class"})
public class UnitTestsSuite {
}