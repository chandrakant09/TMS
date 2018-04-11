/*package com.lei.service.userform;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lei.dto.common.ResponseMessageDTO;
import com.lei.dto.userform.UserFormDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.maintenance.userinfo.IUserFormmaintenance;
import com.lei.utility.constants.HttpStatusCodes;

@Slf4j
@RestController
@RequestMapping("/uerformmaintenanceservice")
public class UserFormMaintenanceService {
	@Autowired
	IUserFormmaintenance userformMaintenance;
	@RequestMapping(value = "/save",
			method = RequestMethod.GET,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO save(@RequestBody UserFormDTO userformDto) throws ObjectNotSupportedException  {
					
				log.info("saveform");
				
				ResponseMessageDTO response = new ResponseMessageDTO();
					response.setResponseCode(HttpStatusCodes.SUCCESS.getCode());
					userformMaintenance.save(userformDto);
					
					return response;
			}
	
}
*/