package com.oocl.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oocl.dto.RecommendDto;
import com.oocl.pojo.ASideResponse;
import com.oocl.pojo.Dish;
import com.oocl.pojo.DishType;
import com.oocl.pojo.Merchant;
import com.oocl.service.DishManager;
import com.oocl.service.DishTypeManager;
import com.oocl.service.MerchantManager;
import com.oocl.util.HttpConnectionUtil;
import com.oocl.util.IPPropertiesUtil;
import com.oocl.util.StringUtil;

@Controller
public class HomeController {
	
	private final String GET_MERCHANTS_URL = String.format(
			IPPropertiesUtil.getProperty("admin.getmerchants.url"),
			IPPropertiesUtil.getProperty("admin.ipaddr"),
			IPPropertiesUtil.getProperty("admin.port"));
	
	private final String GET_RECOMMENDS_URL = String.format(
			IPPropertiesUtil.getProperty("admin.getadvertisements.url"),
			IPPropertiesUtil.getProperty("admin.ipaddr"),
			IPPropertiesUtil.getProperty("admin.port"));
	
	@Resource(name="dishTypeManagerImpl")
	private DishTypeManager dishTypeManager;
	
	@Resource(name="dishManagerImpl")
	private DishManager dishManager;
	
	@Resource(name="merchantManagerImpl")
	private MerchantManager merchantManager;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showHomePage(Model model, RedirectAttributes redirectAttributes) {
		
		String merchantsJson = HttpConnectionUtil.sendGet(GET_MERCHANTS_URL);
		String recommendJson = HttpConnectionUtil.sendGet(GET_RECOMMENDS_URL);
		
		if (StringUtil.isNullOrEmpty(merchantsJson)) {
			redirectAttributes.addFlashAttribute("errorMsg", "Error retrieving restaurant list!");
			return "redirect:/error";
		}
		
		if (StringUtil.isNullOrEmpty(recommendJson)) {
			redirectAttributes.addFlashAttribute("errorMsg", "Error retrieving recommend list!");
			return "redirect:/error";
		}
		
		Set<String> set = HttpConnectionUtil.getASideResponse(merchantsJson).getSet();
		List<String> allAllowedMids = merchantManager.MerchantsIdFilterByStatus(set);
		
		ASideResponse<RecommendDto> asr = HttpConnectionUtil.getASideRecommendResponse(recommendJson);
		List<RecommendDto> recommends = null;
		if (asr != null) {
			recommends = HttpConnectionUtil.getASideRecommendResponse(recommendJson).getList();
			for (RecommendDto rec : recommends) {
				Dish d = dishManager.findDishById(rec.getDid());
				if (d == null) {
					rec.setdName("No such dish exist");
				} else {
					rec.setdName(d.getdName());
				}
			}
		}
		
		List<DishType> dishTypes = dishTypeManager.getAllDishTypes();
		List<Merchant> merchants = merchantManager.findMerchantsByMIdList(null, 0, allAllowedMids);
		
		model.addAttribute("dishTypes", dishTypes);
		model.addAttribute("merchants", merchants);
		model.addAttribute("recommends", recommends);
		return "home";
	}
}
