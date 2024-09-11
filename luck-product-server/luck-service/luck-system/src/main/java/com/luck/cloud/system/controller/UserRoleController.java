package com.luck.cloud.system.controller;

import com.luck.cloud.base.controller.BaseController;
import com.luck.cloud.base.utils.ConvertUtils;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.core.entity.UserRole;
import com.luck.cloud.core.service.IUserRoleService;
import com.luck.cloud.core.vo.UserRoleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 系统用户角色
 *
 * @author luck
 * @since 2024-08-01
 */
@RestController("sysUserRoleController")
@RequestMapping("/userRole")
public class UserRoleController extends BaseController {

    @Autowired
    private IUserRoleService userRoleService;

    @ApiOperation("分页获取数据")
    @PostMapping("page")
    public PageVO<UserRoleVO> pageUserRole(@RequestBody PageVO<UserRole> PageVO) {
        return ConvertUtils.convertPage(userRoleService.queryPage(PageVO), UserRoleVO.class);
    }

    @ApiOperation("根据id获取唯一数据")
    @GetMapping("/get/{id}")
    public ResultVO<UserRoleVO> get(@PathVariable("id") String id) {
        UserRoleVO userRoleVO = ConvertUtils.convert(userRoleService.queryOneById(id), UserRoleVO.class);
        return ResultVO.success(userRoleVO);
    }

    @ApiOperation("添加数据")
    @PostMapping("save")
    public ResultVO save(@RequestBody UserRoleVO userRoleVO) {
        UserRole userRole = ConvertUtils.convert(userRoleVO, UserRole.class);
        userRoleService.saveOrUpdate(userRole);
        return ResultVO.success(userRole.getId());
    }

    @ApiOperation("更改数据")
    @PostMapping("update")
    public ResultVO update(@RequestBody UserRoleVO userRoleVO) {
        UserRole userRole = ConvertUtils.convert(userRoleVO, UserRole.class);
        userRoleService.saveOrUpdate(userRole);
        return ResultVO.success();
    }

    @ApiOperation("删除数据")
    @PostMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        userRoleService.deleteById(id);
        return ResultVO.success(id);
    }

    @ApiOperation("批量删除数据")
    @PostMapping("/delete/batch")
    public ResultVO deleteBatch(@RequestBody String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        boolean deleteCount = userRoleService.deleteByIds(idList);
        return ResultVO.success(deleteCount);
    }

}
