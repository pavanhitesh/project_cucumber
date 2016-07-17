package com.pavan.cvsreader;

/**
 * Class to peek into the runtime stack to figure out the callers package.
 * 
 * @author Gayathri
 * 
 */
public class CallerContext extends SecurityManager {

	/**
	 * Returns the run time execution stack.
	 * 
	 * @return Class[]
	 */
	public final Class[] getClassExecutionStack() {
		return getClassContext();
	}

}
