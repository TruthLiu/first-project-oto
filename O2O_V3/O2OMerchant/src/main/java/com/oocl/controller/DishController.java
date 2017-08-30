package com.oocl.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.oocl.pojo.Dish;
import com.oocl.pojo.DishType;
import com.oocl.pojo.JSONResponse;
import com.oocl.pojo.Merchant;
import com.oocl.service.DishService;
import com.oocl.service.DishTypeService;
import com.oocl.util.FileUploadUtil;
import com.oocl.util.StatusCode;

import org.apache.log4j.Logger;
@Controller
@RequestMapping("/dish")
public class DishController {

	@Resource(name="dishTypeServiceImpl")
	private DishTypeService dishTypeService;
	
	@Resource(name="dishServiceImpl")
	private DishService dishService;
	private Logger logger = Logger.getLogger(DishController.class);
	
	
	@RequestMapping("/sec/dishManage")
	public String jump(Model model)
	{	
	
		List<DishType> dishTypes = dishTypeService.getAllDishTypes();
		model.addAttribute("dishTypes", dishTypes);
		return "dishManage";
	}
	//获取所有菜品类型
	@ResponseBody
	@RequestMapping("sec/getAllDishTypes")
	public List<DishType> getAllDishTypes()
	{
		logger.info("getAllDishTypes");
		return dishTypeService.getAllDishTypes();
	}
	@ResponseBody
	@RequestMapping(value="sec/addDish",method=RequestMethod.POST)
	public JSONResponse<Dish> addDish(MultipartHttpServletRequest multiRequest)
	{	
		String dName = multiRequest.getParameter("dName");
		String id = multiRequest.getParameter("id");
		String dType = multiRequest.getParameter("dType");
		String dPrice = multiRequest.getParameter("dPrice");
		logger.info("addDish");
		
		JSONResponse<Dish> response = new JSONResponse<Dish>();
		Map<String, MultipartFile> uploadFiles = new HashMap<>(); 
		MultipartFile mfile = multiRequest.getFile("headImg");
		if(mfile.isEmpty()){
			response.setCode(StatusCode.FAIL);
			response.setMsg("you have to upload headImage");
			return response;
		}
		if (mfile.getSize()>1048576) {
        	response.setCode(StatusCode.FAIL);
			response.setMsg("picture is not bigger than 1MB");
			return response;
		}
		String dishID = UUID.randomUUID().toString();
		String imgHead = "images/dish/"+dishID+".jpg";
		uploadFiles.put(imgHead, mfile);
		String result = FileUploadUtil.fileUpload(uploadFiles);
        if (result.equals(StatusCode.FILE_SERVER_FAIL)) {
        	response.setCode(StatusCode.FAIL);
			response.setMsg("file server fail");
			return response;
		}
		
		Dish d = new Dish(dishID, dName, Double.parseDouble(dPrice), imgHead, Integer.parseInt(dType), id);
		dishService.addDish(d);
		response.setCode(StatusCode.SUCCESS);
	    response.setResult(d);
		return response;
	}
	@ResponseBody
	@RequestMapping(value="sec/updateDish",method=RequestMethod.POST)
	public JSONResponse<Dish> updateDish(MultipartHttpServletRequest multiRequest)
	{
		String dName = multiRequest.getParameter("dName");
		String mid = multiRequest.getParameter("mid");
		String did = multiRequest.getParameter("id");
		String dType = multiRequest.getParameter("dType");
		String dPrice = multiRequest.getParameter("dPrice");
		String dImage = multiRequest.getParameter("dImage");
		logger.info("updateDish");
		
		JSONResponse<Dish> response = new JSONResponse<Dish>();
		Map<String, MultipartFile> uploadFiles = new HashMap<>(); 
		MultipartFile mfile = multiRequest.getFile("headImg");
		Dish d=null;
		
		if(mfile.isEmpty()){
			d = new Dish(did, dName, Double.parseDouble(dPrice), dImage, Integer.parseInt(dType), mid);
			response.setCode(StatusCode.SUCCESS);
			response.setMsg("Update success");
			Dish dish = dishService.updateDish(d);
			response.setResult(dish);
			return response;
		}
		if (mfile.getSize()>1048576) {
        	response.setCode(StatusCode.FAIL);
			response.setMsg("picture is not bigger than 1MB");
			return response;
		}
//		String dishID = UUID.randomUUID().toString();
		String imgHead = "images/dish/"+did+".jpg";
		uploadFiles.put(imgHead, mfile);
		String result = FileUploadUtil.fileUpload(uploadFiles);
        if (result.equals(StatusCode.FILE_SERVER_FAIL)) {
        	response.setCode(StatusCode.FAIL);
			response.setMsg("file server fail");
			return response;
		}
		
		d = new Dish(did, dName, Double.parseDouble(dPrice), imgHead, Integer.parseInt(dType), mid);
		Dish dish = dishService.updateDish(d);
		response.setCode(StatusCode.SUCCESS);
	    response.setResult(dish);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="sec/deleteDish",method=RequestMethod.DELETE)
	public JSONResponse<Dish> deleteDish(String id)
	{
		JSONResponse<Dish> response = new JSONResponse<Dish>();
		logger.info("deleteDish");
		Dish dish = dishService.deleteDish(id);
		if(null==dish){
			response.setCode(StatusCode.FAIL);
			response.setMsg("delete fail");
		}else {
			response.setCode(StatusCode.SUCCESS);
			response.setResult(dish);
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping("sec/findAllDishes")
	public List<Dish> findAllDishes(String mId)
	{
		logger.info("findAllDishes");
		return dishService.findAllDishes(mId);
	}
	@ResponseBody
	@RequestMapping(value="sec/findDishesByType")
	public List<Dish> findDishesByType(Integer dType,String mId){
		logger.info("according dType and mId to find dishes");
		if(dType==0){
			List<Dish> dishes = dishService.findAllDishes(mId);
			for (Dish dish : dishes) {
				System.out.println(dish);
			}
			return dishes;
		}
		return dishService.findDishesByType(dType, mId);
	}

	@ResponseBody
	@RequestMapping("sec/findDishesByBoth")
	public List<Dish> findDishesByBoth(Integer dType,String dName,String mId){
		logger.info("according dType,dName and mId to find dishes");
		if(dType==0){
			List<Dish> dishes = dishService.findDishesByName(dName, mId);
			return dishes;
		}
		List<Dish> dishes = dishService.findDishesByBoth(dType, dName, mId);
		for (Dish dish : dishes) {
			System.out.println("000"+dish);
		}
		return dishes;
	}
	@ResponseBody
	@RequestMapping("sec/findDishById")
	public Dish findDishById(String dId){
		logger.info("according dId to find dishes");
		return dishService.findDishById(dId);
	}
}
