package com.groyyo2.user.consumer;

import lombok.Data;



@Data
public class MsgUserResp{

	private String firstName;

	private String lastName;

	private String middleName;

	private String uuid;

	private boolean status;

}