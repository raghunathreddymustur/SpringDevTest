package com.raghu.spring.test;

import com.googlecode.junittoolbox.WildcardPatternSuite;
import org.junit.runner.RunWith;

import static org.junit.runners.Suite.SuiteClasses;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses({UnitTestsSuite.class, IntegrationTestsSuite.class})
public class AllTestsSuite {
}