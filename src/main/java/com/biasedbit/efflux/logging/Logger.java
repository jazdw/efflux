/*
 * Copyright 2010 Bruno de Carvalho
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.biasedbit.efflux.logging;

import android.util.Log;

/**
 * Facade for SLF4J's logger.
 * <p/>
 * Offers java 5 syntax support (varargs) and performs some optimisations.
 *
 * @author <a href="http://bruno.biasedbit.com/">Bruno de Carvalho</a>
 */
public class Logger {

    // internal vars --------------------------------------------------------------------------------------------------
    String name = null;
	
    //private org.slf4j.Logger logger;

    // constructors ---------------------------------------------------------------------------------------------------

    /**
     * Default constructor
     *
     * @param clazz Name of the class to log.
     */
    @SuppressWarnings("rawtypes")
    public Logger(Class clazz) {
        this.name = clazz.getName();
    }

    public Logger(String name) {
        this.name = name;
    }

    // static methods -------------------------------------------------------------------------------------------------

    /**
     * Return a logger for a given class.
     *
     * @param clazz Class.
     *
     * @return Logger for a class.
     */
    @SuppressWarnings("rawtypes")
	public static Logger getLogger(Class clazz) {
        return new Logger(clazz);
    }

    public static Logger getLogger(String name) {
        return new Logger(name);
    }

    // public methods -------------------------------------------------------------------------------------------------

    /**
     * Return the name of the logger.
     *
     * @return Name of the logger.
     */
    public String getName() {
        return name;
    }

    /**
     * Test whether trace logging level is enabled.
     *
     * @return <code>true</code> if trace logging level is enabled, <code>false</code> otherwise.
     */
    public boolean isTraceEnabled() {
    	return true;
    }

    /**
     * Print a trace message to the logs.
     *
     * @param message Message to be logged.
     */
    public void trace(String message) {
    	Log.d(name, message);
    }

    /**
     * Print a formatted trace message to the logs, with one parameter.
     *
     * @param message   Message to be logged.
     * @param parameter Message parameter.
     */
    public void trace(String message, Object parameter) {
    	Log.d(name, message.replaceFirst("\\{\\}", parameter.toString()));
    }

    /**
     * Print a formatted trace message to the logs, with an arbitrary number of parameters.
     *
     * @param message    Message to be logged.
     * @param parameters Array of parameters.
     */
    public void trace(String message, Object... parameters) {
    	for (Object obj : parameters) {
    		message = message.replaceFirst("\\{\\}", obj.toString());
    	}
    	Log.d(name, message);
    }

    /**
     * Print a trace message with an exception.
     *
     * @param message   Message to be logged.
     * @param throwable Throwable to be logged.
     */
    public void trace(String message, Throwable throwable) {
    	Log.d(name, message + "\r\n" + throwable.toString());
    }

    /**
     * Print a formatted trace message to the logs, with an arbitrary number of parameters and an exception.
     * <p/>
     * In order for this to work a workaround has to be done, because SLF4J's api only allows for exceptions to be
     * logged alongside with a String, thus disabling formatted message AND exception logging.<br/> Resorting to SLF4J's
     * MessageFormater to format the message with the parameters into a string and then passing that string as the
     * message, it is possible to have the best of both worlds.<br/> In order to avoid incurring the overhead of this
     * process, before doing it, a test to check if the log level is enabled is done.
     * <p/>
     * NOTE: The order of the throwable and object array is switched due to Java language limitations, but the exception
     * always shows up AFTER the formatted message.
     *
     * @param message    Message to be formatted.
     * @param throwable  Throwable to be logged.
     * @param parameters Parameters to format the message.
     */
    public void trace(String message, Throwable throwable, Object... parameters) {
    	for (Object obj : parameters) {
    		message = message.replaceFirst("\\{\\}", obj.toString());
    	}
    	Log.d(name, message + "\r\n" + throwable.toString());
    }

    /**
     * Test whether debug logging level is enabled.
     *
     * @return <code>true</code> if debug logging level is enabled, <code>false</code> otherwise.
     */
    public boolean isDebugEnabled() {
    	return false;
    }

    /**
     * Print a debug message to the logs.
     *
     * @param message Message to be logged.
     */
    public void debug(String message) {
    	Log.d(name, message);
    }

    /**
     * Print a formatted debug message to the logs, with one parameter.
     *
     * @param message   Message to be logged.
     * @param parameter Message parameter.
     */
    public void debug(String message, Object parameter) {
    	Log.d(name, message.replaceFirst("\\{\\}", parameter.toString()));
    }


    /**
     * Print a formatted debug message to the logs, with an arbitrary number of parameters.
     *
     * @param message    Message to be logged.
     * @param parameters Array of parameters.
     */
    public void debug(String message, Object... parameters) {
    	for (Object obj : parameters) {
    		message = message.replaceFirst("\\{\\}", obj.toString());
    	}
    	Log.d(name, message);
    }

    /**
     * Print a debug message with an exception.
     *
     * @param message   Message to be logged.
     * @param throwable Throwable to be logged.
     */
    public void debug(String message, Throwable throwable) {
    	Log.d(name, message + "\r\n" + throwable.toString());
    }

    /**
     * Print a formatted debug message to the logs, with an arbitrary number of parameters and an exception.
     * <p/>
     * In order for this to work a workaround has to be done, because SLF4J's api only allows for exceptions to be
     * logged alongside with a String, thus disabling formatted message AND exception logging.<br/> Resorting to SLF4J's
     * MessageFormater to format the message with the parameters into a string and then passing that string as the
     * message, it is possible to have the best of both worlds.<br/> In order to avoid incurring the overhead of this
     * process, before doing it, a test to check if the log level is enabled is done.
     * <p/>
     * NOTE: The order of the throwable and object array is switched due to Java language limitations, but the exception
     * always shows up AFTER the formatted message.
     *
     * @param message    Message to be formatted.
     * @param throwable  Throwable to be logged.
     * @param parameters Parameters to format the message.
     */
    public void debug(String message, Throwable throwable, Object... parameters) {
    	for (Object obj : parameters) {
    		message = message.replaceFirst("\\{\\}", obj.toString());
    	}
    	Log.d(name, message + "\r\n" + throwable.toString());
    }

    /**
     * Test whether info logging level is enabled.
     *
     * @return <code>true</code> if info logging level is enabled, <code>false</code> otherwise.
     */
    public boolean isInfoEnabled() {
    	return true;
    }

    /**
     * Print a info message to the logs.
     *
     * @param message Message to be logged.
     */
    public void info(String message) {
    	Log.i(name, message);
    }

    /**
     * Print a formatted info message to the logs, with one parameter.
     *
     * @param message   Message to be logged.
     * @param parameter Message parameter.
     */
    public void info(String message, Object parameter) {
    	Log.i(name, message.replaceFirst("\\{\\}", parameter.toString()));
    }

    /**
     * Print a formatted info message to the logs, with an arbitrary number of parameters.
     *
     * @param message    Message to be logged.
     * @param parameters Array of parameters.
     */
    public void info(String message, Object... parameters) {
    	for (Object obj : parameters) {
    		message = message.replaceFirst("\\{\\}", obj.toString());
    	}
    	Log.i(name, message);
    }

    /**
     * Print an info message with an exception.
     *
     * @param message   Message to be logged.
     * @param throwable Throwable to be logged.
     */
    public void info(String message, Throwable throwable) {
    	Log.i(name, message + "\r\n" + throwable.toString());
    }

    /**
     * Print a formatted info message to the logs, with an arbitrary number of parameters and an exception.
     * <p/>
     * In order for this to work a workaround has to be done, because SLF4J's api only allows for exceptions to be
     * logged alongside with a String, thus disabling formatted message AND exception logging.<br/> Resorting to SLF4J's
     * MessageFormater to format the message with the parameters into a string and then passing that string as the
     * message, it is possible to have the best of both worlds.<br/> In order to avoid incurring the overhead of this
     * process, before doing it, a test to check if the log level is enabled is done.
     * <p/>
     * NOTE: The order of the throwable and object array is switched due to Java language limitations, but the exception
     * always shows up AFTER the formatted message.
     *
     * @param message    Message to be formatted.
     * @param throwable  Throwable to be logged.
     * @param parameters Parameters to format the message.
     */
    public void info(String message, Throwable throwable, Object... parameters) {
    	for (Object obj : parameters) {
    		message = message.replaceFirst("\\{\\}", obj.toString());
    	}
    	Log.i(name, message + "\r\n" + throwable.toString());
    }

    /**
     * Test whether warn logging level is enabled.
     *
     * @return <code>true</code> if warn logging level is enabled, <code>false</code> otherwise.
     */
    public boolean isWarnEnabled() {
    	return true;
    }

    /**
     * Print a warning message to the logs.
     *
     * @param message Message to be logged.
     */
    public void warn(String message) {
    	Log.w(name, message);
    }

    /**
     * Print a formatted warning message to the logs, with one parameter.
     *
     * @param message   Message to be logged.
     * @param parameter Message parameter.
     */
    public void warn(String message, Object parameter) {
    	Log.w(name, message.replaceFirst("\\{\\}", parameter.toString()));
    }

    /**
     * Print a formatted warning message to the logs, with an arbitrary number of parameters.
     *
     * @param message    Message to be logged.
     * @param parameters Array of parameters.
     */
    public void warn(String message, Object... parameters) {
    	for (Object obj : parameters) {
    		message = message.replaceFirst("\\{\\}", obj.toString());
    	}
    	Log.w(name, message);
    }

    /**
     * Print a warning message with an exception.
     *
     * @param message   Message to be logged.
     * @param throwable Throwable to be logged.
     */
    public void warn(String message, Throwable throwable) {
    	Log.w(name, message + "\r\n" + throwable.toString());
    }

    /**
     * Print a formatted warn message to the logs, with an arbitrary number of parameters and an exception.
     * <p/>
     * In order for this to work a workaround has to be done, because SLF4J's api only allows for exceptions to be
     * logged alongside with a String, thus disabling formatted message AND exception logging.<br/> Resorting to SLF4J's
     * MessageFormater to format the message with the parameters into a string and then passing that string as the
     * message, it is possible to have the best of both worlds.<br/> In order to avoid incurring the overhead of this
     * process, before doing it, a test to check if the log level is enabled is done.
     * <p/>
     * NOTE: The order of the throwable and object array is switched due to Java language limitations, but the exception
     * always shows up AFTER the formatted message.
     *
     * @param message    Message to be formatted.
     * @param throwable  Throwable to be logged.
     * @param parameters Parameters to format the message.
     */
    public void warn(String message, Throwable throwable, Object... parameters) {
    	for (Object obj : parameters) {
    		message = message.replaceFirst("\\{\\}", obj.toString());
    	}
    	Log.w(name, message + "\r\n" + throwable.toString());
    }

    /**
     * Test whether error logging level is enabled.
     *
     * @return <code>true</code> if error logging level is enabled, <code>false</code> otherwise.
     */
    public boolean isErrorEnabled() {
    	return true;
    }

    /**
     * Print an error message to the logs.
     *
     * @param message Message to be logged.
     */
    public void error(String message) {
    	Log.e(name, message);
    }

    /**
     * Print a formatted error message to the logs, with one parameter.
     *
     * @param message   Message to be logged.
     * @param parameter Message parameter.
     */
    public void error(String message, Object parameter) {
    	Log.e(name, message.replaceFirst("\\{\\}", parameter.toString()));
    }

    /**
     * Print a formatted error message to the logs, with an arbitrary number of parameters.
     *
     * @param message    Message to be logged.
     * @param parameters Array of parameters.
     */
    public void error(String message, Object... parameters) {
    	for (Object obj : parameters) {
    		message = message.replaceFirst("\\{\\}", obj.toString());
    	}
    	Log.e(name, message);
    }

    /**
     * Print an error message with an exception.
     *
     * @param message   Message to be logged.
     * @param throwable Throwable to be logged.
     */
    public void error(String message, Throwable throwable) {
    	Log.e(name, message + "\r\n" + throwable.toString());
    }

    /**
     * Print a formatted debug message to the logs, with an arbitrary number of parameters and an exception.
     * <p/>
     * In order for this to work a workaround has to be done, because SLF4J's api only allows for exceptions to be
     * logged alongside with a String, thus disabling formatted message AND exception logging.<br/> Resorting to SLF4J's
     * MessageFormater to format the message with the parameters into a string and then passing that string as the
     * message, it is possible to have the best of both worlds.<br/> In order to avoid incurring the overhead of this
     * process, before doing it, a test to check if the log level is enabled is done.
     * <p/>
     * NOTE: The order of the throwable and object array is switched due to Java language limitations, but the exception
     * always shows up AFTER the formatted message.
     *
     * @param message    Message to be formatted.
     * @param throwable  Throwable to be logged.
     * @param parameters Parameters to format the message.
     */
    public void error(String message, Throwable throwable, Object... parameters) {
    	for (Object obj : parameters) {
    		message = message.replaceFirst("\\{\\}", obj.toString());
    	}
    	Log.e(name, message + "\r\n" + throwable.toString());
    }
}
