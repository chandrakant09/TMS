package com.lei.utility.serverstart;

import java.io.PrintStream;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@Slf4j
public class SYSOController {
	public static void tieSystemOutAndErrToLog() {
		SYSOController controller = new SYSOController();
        System.setOut(controller.getInformationStream(System.out));
        System.setErr(controller.getErrorStream(System.err));
    }
    private PrintStream getInformationStream(final PrintStream realPrintStream) {
        return new PrintStream(realPrintStream) {
            public void print(final String string) {
                log.info(string);
            }
        };
    }
    
    private PrintStream getErrorStream(final PrintStream realPrintStream) {
        return new PrintStream(realPrintStream) {
            public void print(final String string) {
                log.error(string);
            }
        };
    }
}
