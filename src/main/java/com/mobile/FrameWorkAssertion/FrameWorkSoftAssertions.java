package com.mobile.FrameWorkAssertion;

import com.mobile.enums.LogType;
import com.mobile.reports.FrameWorkLogger;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.util.Map;

public class FrameWorkSoftAssertions extends Assertion {

    private final Map<AssertionError, IAssert<?>> m_Error = Maps.newLinkedHashMap();

    public void assertTrue(boolean b, String passMessage, String failMessage) {
        if (b) {
            FrameWorkLogger.log(LogType.PASS, passMessage);
        } else {
            FrameWorkLogger.log(LogType.FAIL, failMessage);
            Assert.fail(failMessage);
        }

    }

    public void assertEquals(final Integer actual, final Integer expected, final String passMessage, final String failMessage) {
        if (actual.equals(expected)) {
            FrameWorkLogger.log(LogType.PASS, passMessage);
        } else {
            FrameWorkLogger.log(LogType.FAIL, failMessage);
            Assert.fail(failMessage);
        }

    }

    public void assertEqual(String actual, String expected, String passMessage, String failMessage) {
        if (actual.equalsIgnoreCase(expected)) {
            FrameWorkLogger.log(LogType.PASS, passMessage);
        } else {
            FrameWorkLogger.log(LogType.FAIL, failMessage);
            Assert.fail(failMessage);
        }

    }

    public void assertNotNull(final String message, final String passMessage, final String failMessage) {
        if (message.isEmpty()) {
            FrameWorkLogger.log(LogType.FAIL, failMessage);
            Assert.fail(failMessage);
        } else {
            FrameWorkLogger.log(LogType.PASS, passMessage);
        }
    }

    public void assertNotNull(final int count, final String passMessage, final String failMessage) {
        if (count == 0) {
            FrameWorkLogger.log(LogType.FAIL, failMessage);
            Assert.fail(failMessage);
        } else {
            FrameWorkLogger.log(LogType.PASS, passMessage);
        }
    }


    protected void doAssert(IAssert<?> assertCommand) {
        onBeforeAssert(assertCommand);
        try {
            executeAssert(assertCommand);
            onAssertSuccess(assertCommand);
        } catch (AssertionError ex) {
            onAssertFailure(assertCommand, ex);
            throw ex;
        } finally {
            onAfterAssert(assertCommand);
        }
    }

    public void assertAll() {
        if (!m_Error.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("The following assert failed:");
            boolean first = true;
            for (Map.Entry<AssertionError, IAssert<?>> me : m_Error.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\n\t");
                stringBuilder.append(me.getKey().getMessage());
            }
            throw new AssertionError(stringBuilder.toString());
        }
    }


}
