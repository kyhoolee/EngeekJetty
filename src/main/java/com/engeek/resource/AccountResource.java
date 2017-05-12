package com.engeek.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.engeek.logic.AccountLogic;
import com.engeek.util.LoggerUtil;


public class AccountResource {
	public static Logger logger = LoggerUtil.getDailyLogger("NewsResource_log");
	
	
	public AccountResource() {
		
		
	}
	
	
	@Path("/register")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response register(String info, @Context HttpServletRequest req) {
		String userName = null;
		String passWord = null;
		try {
			JSONObject jsonObject = new JSONObject(info);
			JSONObject data = jsonObject.getJSONObject("data");
			JSONObject log = jsonObject.getJSONObject("log");


			userName = data.getString("userName");
			passWord = data.getString("passWord");
			
			logger.info("register: " + log);
		} catch (Exception e) { 
			logger.error(e);
		}
		
		
		JSONObject res = AccountLogic.register(userName, passWord);
		res.put("log", userName + " -- " + passWord);
		
		return Response.ok(res.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}
	

	@Path("/login")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response login(String info, @Context HttpServletRequest req) {
		String userName = null;
		String passWord = null;
		try {
			JSONObject jsonObject = new JSONObject(info);
			JSONObject data = jsonObject.getJSONObject("data");
			JSONObject log = jsonObject.getJSONObject("log");


			userName = data.getString("userName");
			passWord = data.getString("passWord");
			logger.info("login: " + log);
		} catch (Exception e) { 
			logger.error(e);
		}
		
		JSONObject res = AccountLogic.login(userName, passWord);
		return Response.ok(res.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}
	
	
	@Path("/profile")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getProfile(String info, @Context HttpServletRequest req) {
		String userName = null;
		String sessionKey = req.getHeader("sessionKey");
		try {
			JSONObject jsonObject = new JSONObject(info);
			JSONObject data = jsonObject.getJSONObject("data");
			JSONObject log = jsonObject.getJSONObject("log");


			userName = data.getString("userName");
			logger.info("login: " + log);
		} catch (Exception e) { 
			logger.error(e);
		}
		
		
		boolean authen = AccountLogic.checkUserSession(userName, sessionKey);
		JSONObject res;
		if(authen) {
			res = AccountLogic.getProfile(userName);
			res.put("log", sessionKey);
		} else {
			res = AccountLogic.genErrorSession();
		}
		return Response.ok(res.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}
	

	@Path("/shop/update_profile")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response updateShopProfile(String info, @Context HttpServletRequest req) {
		String userName = "";
		String name = "";
		String gender = "";
		String facebook = "";
		String google = "";
		String avatarUrl = "";
		int type = -1;
		int hint = -1;
		int invitedFriends = -1;
		// name, gender, google, facebook, avatarUrl, type, hint, invitedFriends
		
		String sessionKey = req.getHeader("sessionKey");
		try {
			JSONObject jsonObject = new JSONObject(info);
			JSONObject data = jsonObject.getJSONObject("data");
			JSONObject log = jsonObject.getJSONObject("log");


			userName = data.getString("userName");
			
			name = data.getString("name");
			gender = data.getString("gender");
			facebook = data.getString("facebook");
			google = data.getString("google");
			avatarUrl = data.getString("avatarUrl");
			
			type = data.getInt("type");
			hint = data.getInt("hint");
			invitedFriends = data.getInt("invitedFriends");
			
			
			logger.info("shop_update_profile: " + log);
		} catch (Exception e) { 
			logger.error(e);
		}
		
		boolean authen = AccountLogic.checkUserSession(userName,  sessionKey);
		JSONObject res;
		if(authen) {
			res = AccountLogic.updateUserProfile(userName, name, gender, google, facebook, avatarUrl, type, hint, invitedFriends);
		} else {
			res = AccountLogic.genErrorSession();
		}
		return Response.ok(res.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}
}
