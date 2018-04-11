package com.lei.utility.serverstart;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.extern.slf4j.Slf4j;

import com.lei.exception.common.ContextStartupFailedException;
import com.lei.utility.PropertyUtility;
import com.lei.utility.constants.ApplicationConstants;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@Slf4j
public class ServerStartupContextListener implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("---------- Context Destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0){
		log.info("ContextInitialized Called");
		log.info("Binding SYSO and Error to Logger");
		SYSOController.tieSystemOutAndErrToLog();
		try{
			PropertyUtility.loadPropertyFile(ApplicationConstants.EXCEPTIONBUNDLE.getValue(),ApplicationConstants.EMAILBUNDLE.getValue(),ApplicationConstants.CONFIGURATIONSBUNDLE.getValue());
			PropertyUtility.loadPropertyFile(ApplicationConstants.CAPTCHAVALIDATIONBUNDLE.getValue(),ApplicationConstants.SEARCHPARAMETERBUNDLE.getValue(),ApplicationConstants.WORKFLOWBUNDLE.getValue());
			PropertyUtility.loadPropertyFile(ApplicationConstants.DAOABSTRACTIONBUNDLE.getValue());
			
			/*IFileMaintenance fileMaintenance = new FileMaintenanceImpl();
			fileMaintenance.init();*/
		}catch(Exception e){
			throw new ContextStartupFailedException("------ Exception Generated. Unable to start server. Details :"+e+" -- "+e.getMessage());
		}
	}
}
