package com.lei.service.dashoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lei.dto.common.ResponseMessageDTO;
import com.lei.dto.master.DistrictDTO;
import com.lei.dto.user.UserDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.exception.user.LoginIDFormatException;
import com.lei.exception.user.SessionDoesNotExistException;
import com.lei.maintenance.master.IMasterMaintenance;
import com.lei.maintenance.user.IUserMaintenance;
import com.lei.utility.CommonUtils;
import com.lei.utility.constants.HttpStatusCodes;

@RestController
@RequestMapping("/master")
public class MasterService {
	
	@Autowired
	IMasterMaintenance masterMaintenance;
	
	@Autowired
	IUserMaintenance userMaintenance;
	
	@RequestMapping(value = "/getDistt",
			method = RequestMethod.GET,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getDistt() throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		List<DistrictDTO> dist=masterMaintenance.getDistricList(userDTO.getDistt());
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SUCCESS.getCode(), "Process completed successfully",dist);
	}

}
