package top.ybq87.service.impl;

import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ybq87.dao.UmsRoleDao;
import top.ybq87.dao.UmsRolePermissionRelationDao;
import top.ybq87.dto.UmsRolePermissionRelationParam;
import top.ybq87.mbg.mapper.UmsRoleMapper;
import top.ybq87.mbg.mapper.UmsRolePermissionRelationMapper;
import top.ybq87.mbg.model.UmsPermission;
import top.ybq87.mbg.model.UmsRole;
import top.ybq87.mbg.model.UmsRoleExample;
import top.ybq87.mbg.model.UmsRolePermissionRelation;
import top.ybq87.mbg.model.UmsRolePermissionRelationExample;
import top.ybq87.service.UmsRoleService;

/**
 * 角色功能实现类
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/5 15:42
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    
    @Autowired
    private UmsRoleMapper roleMapper;
    
    @Autowired
    private UmsRoleDao roleDao;
    
    @Autowired
    private UmsRolePermissionRelationDao rolePermissionRelationDao;
    
    @Autowired
    private UmsRolePermissionRelationMapper rolePermissionRelationMapper;
    
    @Override
    public List<UmsRole> list(String keyword, Integer status, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return roleDao.getList(keyword, status);
    }
    
    @Override
    public int create(UmsRole role) {
        role.setCreateTime(new Date());
        role.setStatus(1);
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }
    
    @Override
    public int update(Long id, UmsRole role) {
        role.setId(id);
        // 不为空的字段就更新, 如果需要将字段设置为空, 需要传递字段.
        return roleMapper.updateByPrimaryKeySelective(role);
    }
    
    @Override
    public int delete(List<Long> ids) {
        UmsRoleExample example = new UmsRoleExample();
        example.createCriteria().andIdIn(ids);
        return roleMapper.deleteByExample(example);
    }
    
    @Override
    public List<UmsPermission> getPermissionList(Long roleId) {
        return rolePermissionRelationDao.getPermissionList(roleId);
    }
    
    @Override
    public int updatePermission(UmsRolePermissionRelationParam param) {
        // 先删除已有的关系
        UmsRolePermissionRelationExample example = new UmsRolePermissionRelationExample();
        example.createCriteria().andRoleIdEqualTo(param.getRoleId());
        rolePermissionRelationMapper.deleteByExample(example);
        List<UmsRolePermissionRelation> relations = new ArrayList<>();
        // 插入新的关系
        for (Long permissionId : param.getPermissionIds()) {
            UmsRolePermissionRelation rolePermissionRelation = new UmsRolePermissionRelation();
            rolePermissionRelation.setPermissionId(permissionId);
            rolePermissionRelation.setRoleId(param.getRoleId());
            relations.add(rolePermissionRelation);
        }
        return rolePermissionRelationDao.insertList(relations);
    }
    
    
}
