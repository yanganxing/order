package com.alix.order.utils;

import com.alix.order.vo.ResultVO;

/**
 * @author Alix
 * @date 2019-03-14 13:23
 */
public class ResultVOUtil {

    public static ResultVO scuess(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
