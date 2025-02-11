package com.buni.user.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.user.entity.SysRole;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.role.*;

/**
 * @author Administrator
 * @description 针对表【role(角色
 * )】的数据库操作Service
 * @createDate 2023-09-25 13:45:26
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 新增角色
	 *
	 * @param addVO 角色信息
	 * @return true/false
	 */
	boolean save(AddVO addVO);

	/**
	 * 根据ID修改权限
	 *
	 * @param updateVO
	 * @return true/false
	 */
	boolean update(UpdateVO updateVO);

	/**
	 * 根据ID删除权限
	 *
	 * @param id id
	 * @return true/false
	 */
	boolean delete(Long id);


	/**
	 * 根据ID集合批量删除角色
	 *
	 * @param idVOs id集合
	 * @return true/false
	 */
	boolean batchDelete(IdVOs idVOs);

	/**
	 * 根据ID查询角色
	 *
	 * @param id 角色id
	 * @return {@link RoleInfoVO}
	 */
	RoleInfoVO findById(Long id);

	/**
	 * 分页查询角色信心
	 *
	 * @param pageVO 分页条件
	 * @return IPage<UserInfoVO>
	 */
	IPage<RoleGetVO> findPage(PageVO pageVO);


}
