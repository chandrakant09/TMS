package com.lei.utility;

import com.lei.dto.wallet.EdisttDTO;

public interface IEDistricEncryptionControl {

	String encrypt(EdisttDTO edisttDTO);

	String Validate(String cyperText);

	EdisttDTO decrypt(String cyperText);

}
