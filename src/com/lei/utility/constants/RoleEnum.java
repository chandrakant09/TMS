package com.lei.utility.constants;

public enum RoleEnum {
	

		Admin(10001,"Admin"),
		User(10002,"User"),
		SuperAdmin(10004,"Super Admin");
		
		private Integer id;
		private String status;
		private RoleEnum(Integer statusId,String status) {
			this.status=status;
			this.id = statusId;
		}
		public String getStatus() {
			return status;
		}
		public Integer getID(){
			return id;
		}

	}

