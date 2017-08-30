package com.oocl.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oocl.constant.MerchantStatus;
import com.oocl.pojo.CartItem;
import com.oocl.pojo.Dish;
import com.oocl.pojo.DishType;
import com.oocl.pojo.Merchant;
import com.oocl.service.CommentManager;
import com.oocl.service.DishManager;
import com.oocl.service.DishTypeManager;
import com.oocl.service.MerchantManager;
import com.oocl.util.HttpConnectionUtil;
import com.oocl.util.IPPropertiesUtil;
import com.oocl.util.StringUtil;
import com.oocl.vo.CommentVO;

@Controller
public class RestaurantController {
	
	private final String GET_MERCHANTS_URL = String.format(
			IPPropertiesUtil.getProperty("admin.getmerchants.url"),
			IPPropertiesUtil.getProperty("admin.ipaddr"),
			IPPropertiesUtil.getProperty("admin.port"));
	
	@Resource(name="dishTypeManagerImpl")
	private DishTypeManager dishTypeManager;
	
	@Resource(name="dishManagerImpl")
	private DishManager dishManager;
	
	@Resource(name="merchantManagerImpl")
	private MerchantManager merchantManager;
	
	@Resource(name="commentManagerImpl")
	private CommentManager commentManager;

	@RequestMapping(value="/restaurant/{mid}", method=RequestMethod.GET)
	public String showRestaurantPage(@PathVariable String mid, Model model, RedirectAttributes redirectAttributes, HttpSession sess) {
		
		String merchantsJson = HttpConnectionUtil.sendGet(GET_MERCHANTS_URL);
		
		if (StringUtil.isNullOrEmpty(merchantsJson)) {
			redirectAttributes.addFlashAttribute("errorMsg", "Error retrieving restaurant list!");
			return "redirect:/error";
		}
		
		Set<String> listObj = HttpConnectionUtil.getASideResponse(merchantsJson).getSet();
		List<String> allAllowedMids = merchantManager.MerchantsIdFilterByStatus(listObj);

		Merchant m = merchantManager.findOneMerchantById(mid);
		if (m == null || !m.getStatus().equals(MerchantStatus.APPLY_PASSED) || !allAllowedMids.contains(m.getId())) {
			redirectAttributes.addFlashAttribute("errorMsg", "The restaurant is not exist/not passed/on blacklist!");
			return "redirect:/error";
		}
		
		List<DishType> dishTypes = dishTypeManager.getAllDishTypes();
		List<Dish> dishes = dishManager.findDishes(0, null, mid);
		
		// get comments
		List<CommentVO> comments = commentManager.findCommentByMId(mid);
		
		model.addAttribute("dishTypes", dishTypes);
		model.addAttribute("merchant", m);
		model.addAttribute("dishes", dishes);
		model.addAttribute("merchantStar", merchantManager.getMerchantStar(mid));
		model.addAttribute("comments", comments);
		
		// Store merchant id into session
		String currentMerchantId = (String) sess.getAttribute("currentMerchantId");
		if (currentMerchantId != null && !currentMerchantId.equals(mid)) {
			@SuppressWarnings("unchecked")
			Map<String, CartItem> cartItems = (Map<String, CartItem>) sess.getAttribute("cart");
			if (cartItems != null) {
				cartItems.clear();
			}
		}
		
		sess.setAttribute("currentMerchantId", mid);
		
		return "restaurant";
	}
}
