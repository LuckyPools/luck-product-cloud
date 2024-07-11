package com.luck.cloud.system.controller;

import com.luck.cloud.base.controller.BaseController;
import com.luck.cloud.base.utils.ConvertUtils;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.system.entity.User;
import com.luck.cloud.system.service.IUserService;
import com.luck.cloud.system.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 用户信息Controller
 *
 * @author luck
 * @create 2024-4-10
 **/
@RestController("sysUserController")
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 分页模板
     *
     * @return
     */
    @PostMapping("/page")
    public PageVO<UserVO> page(PageVO<User> pageVO) {
        userService.queryPage(pageVO);
        return ConvertUtils.convertPage(pageVO, UserVO.class);
    }


    /**
     * 根据ID查询相关记录
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResultVO<UserVO> get(@PathVariable("id") String id) {
        User user = userService.queryOneById(id);
        UserVO userVO  = ConvertUtils.convert(user, UserVO.class);
        return ResultVO.success(userVO);
    }

    /**
     * 插入
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/save")
    public ResultVO save(@RequestBody UserVO userVO) {
        User user = ConvertUtils.convert(userVO,User.class);
        userService.insert(user);
        return ResultVO.success(user.getId());
    }


    /**
     * 根据ID修改对饮记录
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public ResultVO update(@Validated @RequestBody UserVO userVO) {
        User user = ConvertUtils.convert(userVO,User.class);
        userService.updateById(user);
        return ResultVO.success(user.getId());
    }


    /**
     * 根据ID删除指定记录,这里被删除的记录会进入删除记录表
     *
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        userService.deleteById(id);
        return ResultVO.success(id);
    }

    /**
     * 删除批量ID对应的记录
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete/batch")
    public ResultVO deleteBatch(@RequestBody String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        boolean deleteCount = userService.deleteByIds(idList);
        return ResultVO.success(deleteCount);
    }
}
