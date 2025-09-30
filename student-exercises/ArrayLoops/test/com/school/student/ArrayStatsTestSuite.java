package com.school.student;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ ArrayStatsTest.class, ArrayStatsEdgeCasesTest.class })
@IncludeClassNamePatterns(".*Test")
public class ArrayStatsTestSuite {
    // No implementation needed. The annotations identify the tests to run.
}
