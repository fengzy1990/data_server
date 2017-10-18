package com.cn.dao;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.cn.entity.DataBean;

//定义关于DATA的数据操作方法借口
public interface DataDao {

	public List<DataBean> selectData();

	public boolean listData(HttpServletRequest request, String strPageSize, String strPageNo);
	
	public boolean listDataQuery(HttpServletRequest request, String strPageSize, String strPageNo,String...args);

	public DataBean getDataBean(int dataid);

	public boolean updateData(DataBean mb);

	public boolean updateDatabyDataid(DataBean mb, int dataid);

	public boolean insertData(DataBean mb);

	public boolean deleteData(int dataid);

	public boolean addData(DataBean dataBean);

	public boolean queryData(String...args);

	public Map<String, String> getDatasByTypeid(int typeid);
}
