package com.luck.cloud.system.controller;

import com.luck.cloud.base.controller.BaseController;
import com.luck.cloud.base.utils.ConvertUtils;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.core.entity.Role;
import com.luck.cloud.core.service.IRoleService;
import com.luck.cloud.core.vo.RoleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;



/**
 * 系统角色
 *
 * @author luck
 * @since 2024-07-30
 */
@RestController("coreRoleController")
@RequestMapping("/core/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation("分页获取数据")
    @PostMapping("page")
    public PageVO<RoleVO> pageRole(@RequestBody PageVO<Role> PageVO) {
        return ConvertUtils.convertPage(roleService.queryPage(PageVO), RoleVO.class);
    }

    @ApiOperation("根据id获取唯一数据")
    @GetMapping("/get/{id}")
    public ResultVO<RoleVO> get(@PathVariable("id") String id) {
        RoleVO roleVO = ConvertUtils.convert(roleService.queryOneById(id), RoleVO.class);
        return ResultVO.success(roleVO);
    }

    @ApiOperation("添加数据")
    @PostMapping("save")
    public ResultVO save(@RequestBody RoleVO roleVO) {
        Role role = ConvertUtils.convert(roleVO, Role.class);
        roleService.saveOrUpdate(role);
        return ResultVO.success(role.getId());
    }

    @ApiOperation("更改数据")
    @PostMapping("update")
    public ResultVO update(@RequestBody RoleVO roleVO) {
        Role role = ConvertUtils.convert(roleVO, Role.class);
        roleService.saveOrUpdate(role);
        return ResultVO.success();
    }

    @ApiOperation("删除数据")
    @PostMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        roleService.deleteById(id);
        return ResultVO.success(id);
    }

    @ApiOperation("批量删除数据")
    @PostMapping("/delete/batch")
    public ResultVO deleteBatch(@RequestBody String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        boolean deleteCount = roleService.deleteByIds(idList);
        return ResultVO.success(deleteCount);
    }

}
