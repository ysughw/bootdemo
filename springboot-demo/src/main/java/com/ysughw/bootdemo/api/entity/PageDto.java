package com.ysughw.bootdemo.api.entity;


/**
 * 分页DTO  方便接收分页入参
 * @author david.guo
 * @version 2017年6月19日 09:11:22
 */
public class PageDto<T> {

	private static final long serialVersionUID = 822153650456525281L;
	
	private int _APP_P_NO = 1; // 当前页码
	private int _APP_P_ROW_SIZE = 10; // 页面大小
	
	/**
	 * 查询参数对象
	 */
	protected T param;
	
	public int get_APP_P_NO() {
		return _APP_P_NO;
	}
	public void set_APP_P_NO(int _APP_P_NO) {
		this._APP_P_NO = _APP_P_NO;
	}
	public int get_APP_P_ROW_SIZE() {
		return _APP_P_ROW_SIZE;
	}
	public void set_APP_P_ROW_SIZE(int _APP_P_ROW_SIZE) {
		this._APP_P_ROW_SIZE = _APP_P_ROW_SIZE;
	}
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	
}
