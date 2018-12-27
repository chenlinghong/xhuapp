package com.app.controller;
/**
 * 动态相关Controller
 */

import com.app.dao.IInterestTimesDao;
import com.app.entity.Dynamic;
import com.app.entity.Interest;
import com.app.service.IDynamicService;
import com.app.service.IInterestService;
import com.app.util.ApiFormatUtil;
import com.app.util.ChargePicUtil;
import com.app.util.ReturnTypeOfInterest;
import com.app.util.UploadUtil;
import com.app.vo.DynamicApiVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "/Dynamic")
public class DynamicController {

    @Resource
    private IDynamicService dynamicService;

    @Resource
    private IInterestService interestService;

    @Resource
    private IInterestTimesDao interestTimes;

    private String interestMsg = "";

    public DynamicController() {
    }

    @RequestMapping(value = "/findDynamicById", method = {RequestMethod.GET, RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findDynamicById(int dynamic_id) {
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        if (dynamic_id <= 0) {
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("数据不合法");
            dynamicApiVo.setDynamic(null);
        } else {
            int type = 0;
            int interest_id_f = dynamicService.findInterest_idById(dynamic_id);
            if (interest_id_f != 0) {
                Interest interest = interestService.findInterestById(interest_id_f);
                if (interest != null) {
                    type = ReturnTypeOfInterest.getType(interest);
                }
            }
            dynamicApiVo = dynamicService.getDynamicById(dynamic_id);
            if (type == 1) {
                interestTimes.updataCountryside_view_times(dynamicApiVo.getDynamic().getUser_id_f());
            } else if (type == 2) {
                interestTimes.updatAagriculture_view_times(dynamicApiVo.getDynamic().getUser_id_f());
            } else if (type == 3) {
                interestTimes.updatAgritainment_view_times(dynamicApiVo.getDynamic().getUser_id_f());
            } else if (type == 4) {
                interestTimes.updatOther_view_times(dynamicApiVo.getDynamic().getUser_id_f());
            } else if (type == 5) {
                interestTimes.updatAgriculture_products_times(dynamicApiVo.getDynamic().getUser_id_f());
            } else if (type == 6) {
                interestTimes.updatAgriculture_bp_times(dynamicApiVo.getDynamic().getUser_id_f());
            } else if (type == 7) {
                interestTimes.updatCreate_delifood_times(dynamicApiVo.getDynamic().getUser_id_f());
            } else if (type == 8) {
                interestTimes.updatDelifood_commont_times(dynamicApiVo.getDynamic().getUser_id_f());
            } else if (type == 9) {
                interestTimes.updatDelifood_entertainment_times(dynamicApiVo.getDynamic().getUser_id_f());
            } else if (type == 10) {
                interestTimes.updatFarm_introduce_times(dynamicApiVo.getDynamic().getUser_id_f());
            }
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(), dynamicApiVo.getMessage(), dynamicApiVo.getDynamic());
    }

    @RequestMapping(value = "/modifyDynamicById", method = {RequestMethod.GET, RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String modifyDynamicById(int dynamic_id, String title, String introduce,
                                    String address, int prize, int look_persons, String little_introduce) {
        Dynamic result = new Dynamic();
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        if (dynamic_id <= 0) {
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("数据不合法");
            dynamicApiVo.setDynamic(null);
        } else {
            result.setDynamic_id(dynamic_id);
            result.setIntroduce(introduce);
            result.setTitle(title);
            result.setAddress(address);
            result.setLook_persons(look_persons);
            result.setPrize(prize);
            result.setLittle_introduce(little_introduce);
            dynamicApiVo = dynamicService.modifyDynamicById(result);
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(), dynamicApiVo.getMessage(), dynamicApiVo.getDynamic());
    }

    @RequestMapping(value = "/insertOneDynamic", method = {RequestMethod.GET, RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String insertOneDynamic(String title, String introduce,
                                   @RequestParam("picfile") CommonsMultipartFile[] picfiles,
                                   int user_id_f, boolean dynamic_type, String little_introduce,
                                   @RequestParam(defaultValue = "成都") String address,
                                   @RequestParam(defaultValue = "0") int prize,
                                   @RequestParam(defaultValue = "0") int look_persons,
                                   Interest interest, HttpServletRequest request) throws SQLException {
        Dynamic newdynamic = new Dynamic();
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        String picfilepaths = "";
        boolean flag = true;
        if (user_id_f <= 0) {
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("不存在这个用户");
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
        } else {
            for (CommonsMultipartFile picfile : picfiles) {
                if (picfile.getOriginalFilename().equals("")) {
                    continue;
                }
                if (!ChargePicUtil.ispic(picfile.getOriginalFilename())) {
                    dynamicApiVo.setCode(0);
                    dynamicApiVo.setMessage("图片格式错误");
                    dynamicApiVo.setDynamic(null);
                    dynamicApiVo.setDynamicList(null);
                    flag = false;
                    break;
                } else {
                    //提高健壮性
                    String picturepath = UploadUtil.upload(picfile, request, 1);
                    picfilepaths = picfilepaths + picturepath + ";";
                }
            }
            if (flag) {
                interestMsg = interestService.insertOneInterest(interest);
                newdynamic.setPicture(picfilepaths);
                newdynamic.setUser_id_f(user_id_f);
                newdynamic.setTitle(title);
                newdynamic.setIntroduce(introduce);
                newdynamic.setDynamic_type(dynamic_type);
                newdynamic.setPrize(prize);
                newdynamic.setLook_persons(look_persons);
                newdynamic.setAddress(address);
                newdynamic.setLittle_introduce(little_introduce);
                newdynamic.setInterest_id_f(interest.getInterest_id());
                //关联小类型
                dynamicApiVo = dynamicService.insertOneDynamic(newdynamic);
            }
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(), dynamicApiVo.getMessage(), dynamicApiVo.getDynamic());
    }

    @RequestMapping(value = "/modifyPicById", method = {RequestMethod.GET, RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String modifyPicById(int dynamic_id, String prepicfilepath, @RequestParam("picfile")
            CommonsMultipartFile nowpicfile, HttpServletRequest request) {
        Dynamic newdynamic = new Dynamic();
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        String picturepath = "";
        boolean flag = false;
        if (nowpicfile.getOriginalFilename().equals("")) {
            flag = false;
        } else {
            flag = !ChargePicUtil.ispic(nowpicfile.getOriginalFilename());
        }

        if (dynamic_id <= 0) {
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("不存在这个动态");
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setDynamic(null);
        } else if (flag) {
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("图片格式错误");
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamicList(null);
        } else {
            try {
                new File(prepicfilepath).delete();
            } catch (Exception e) {
                dynamicApiVo.setCode(0);
                dynamicApiVo.setMessage("修改失败");
                dynamicApiVo.setDynamicList(null);
                dynamicApiVo.setDynamic(null);
            }
            picturepath = UploadUtil.upload(nowpicfile, request, 1);
            newdynamic.setPicture(picturepath);
            newdynamic.setDynamic_id(dynamic_id);
            dynamicApiVo = dynamicService.deletePicById(newdynamic, prepicfilepath);
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(), dynamicApiVo.getMessage(), dynamicApiVo.getDynamic());
    }

    @RequestMapping(value = "/findAllDynamicByUserId", method = {RequestMethod.GET, RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findAllDynamicByUserId(int user_id_f) {
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        if (user_id_f <= 0) {
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setCode(0);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("不存在这个用户");
        } else {
            dynamicApiVo = dynamicService.findAllDynamicByUser_id(user_id_f);
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(), dynamicApiVo.getMessage(), dynamicApiVo.getDynamicList());
    }

    @RequestMapping(value = "/findDynamicByUserIdAndType", method = {RequestMethod.GET, RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findDynamicByUserIdAndType(Dynamic dynamic) {
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        if (dynamic.getUser_id_f() <= 0) {
            dynamicApiVo.setCode(0);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("不存在这个用户");
        } else {
            dynamicApiVo = dynamicService.findDynamicByUserIdAndType(dynamic);
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(), dynamicApiVo.getMessage(), dynamicApiVo.getDynamicList());
    }

    @RequestMapping(value = "/deleteOneDynamicById", method = {RequestMethod.GET, RequestMethod.POST}
            , produces = "text/json;charset=utf-8")
    @ResponseBody
    public String deleteOneDynamicById(int dynamic_id) {
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        if (dynamic_id <= 0) {
            dynamicApiVo.setCode(0);
            dynamicApiVo.setDynamicList(null);
            dynamicApiVo.setMessage("动态的id格式错误");
            dynamicApiVo.setDynamic(null);
        } else {
            dynamicApiVo = dynamicService.deleteOneDynamicById(dynamic_id);
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(), dynamicApiVo.getMessage(), dynamicApiVo.getDynamic());
    }

    @RequestMapping(value = "/findDynamicByType", method = {RequestMethod.GET, RequestMethod.POST},
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findDynamicByType(boolean dynamic_type) {
        DynamicApiVo dynamicApiVo = new DynamicApiVo();
        if (dynamic_type == true || dynamic_type == false) {
            //找需求
            dynamicApiVo = dynamicService.findDynamicByType(dynamic_type);
        } else {
            dynamicApiVo.setCode(0);
            dynamicApiVo.setMessage("只能输入0或1");
            dynamicApiVo.setDynamic(null);
            dynamicApiVo.setDynamic(null);
        }
        return ApiFormatUtil.apiFormat(dynamicApiVo.getCode(), dynamicApiVo.getMessage(), dynamicApiVo.getDynamicList());
    }

}
