package com.modules.service;

import com.modules.pojo.SysRoleMenu;
import com.commons.base.BaseService;

/**
 * 
 * @author Du.Jun
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu> {

	public void bindmenus(String roleid, String menuids) throws Exception;

}
