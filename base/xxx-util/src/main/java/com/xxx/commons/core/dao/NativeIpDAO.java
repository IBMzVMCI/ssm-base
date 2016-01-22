package com.xxx.commons.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NativeIpDAO {

	private static NativeIpDAO instance = new NativeIpDAO();

	private NativeIpDAO() {}

	public static NativeIpDAO getInstance() {
		return instance;
	}

	private static final String TABLE_NAME = "native_ip";
	private static final String FIELDS = "ip";
	
	private static final String SELECT_SQL = "select " + FIELDS + " from "
			+ TABLE_NAME + " limit 1000";

	@SuppressWarnings("unchecked")
	public List<String> getIps() throws SQLException {
//		OpList op = new OpList(SELECT_SQL, DataAccessMgr.BIZ_COMMON) {
//			@Override
//			public Object parse(ResultSet rs) throws SQLException {
//				return rs.getString("ip");
//			}
//		};
//		return (List) DataAccessMgr.getInstance().queryList(op);
		return null;
	}

}
