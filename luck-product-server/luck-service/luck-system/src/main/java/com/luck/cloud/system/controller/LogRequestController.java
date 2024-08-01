package com.luck.cloud.system.controller;

import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.core.entity.LogRequest;
import com.luck.cloud.core.vo.LogRequestVO;
import com.luck.cloud.base.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.luck.cloud.base.controller.BaseController;
import com.luck.cloud.core.service.ILogRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;



/**
 * 系统请求日志
 *
 * @author luck
 * @since 2024-08-01
 */
@RestController("coreLogRequestController")
@RequestMapping("/core/logRequest")
public class LogRequestController extends BaseController {

    @Autowired
    private ILogRequestService logRequestService;

    @ApiOperation("分页获取数据")
    @PostMapping("page")
    public PageVO<LogRequestVO> pageLogRequest(@RequestBody PageVO<LogRequest> PageVO) {
        return ConvertUtils.convertPage(logRequestService.queryPage(PageVO), LogRequestVO.class);
    }

    @ApiOperation("根据id获取唯一数据")
    @GetMapping("/get/{id}")
    public ResultVO<LogRequestVO> get(@PathVariable("id") String id) {
        LogRequestVO logRequestVO = ConvertUtils.convert(logRequestService.queryOneById(id), LogRequestVO.class);
        return ResultVO.success(logRequestVO);
    }

    @ApiOperation("添加数据")
    @PostMapping("save")
    public ResultVO save(@RequestBody LogRequestVO logRequestVO) {
        LogRequest logRequest = ConvertUtils.convert(logRequestVO, LogRequest.class);
        logRequestService.saveOrUpdate(logRequest);
        return ResultVO.success(logRequest.getId());
    }

    @ApiOperation("更改数据")
    @PostMapping("update")
    public ResultVO update(@RequestBody LogRequestVO logRequestVO) {
        LogRequest logRequest = ConvertUtils.convert(logRequestVO, LogRequest.class);
        logRequestService.saveOrUpdate(logRequest);
        return ResultVO.success();
    }

    @ApiOperation("删除数据")
    @PostMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        logRequestService.deleteById(id);
        return ResultVO.success(id);
    }

    @ApiOperation("批量删除数据")
    @PostMapping("/delete/batch")
    public ResultVO deleteBatch(@RequestBody String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        boolean deleteCount = logRequestService.deleteByIds(idList);
        return ResultVO.success(deleteCount);
    }

}
