package com.myclass.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.ShipDto;
import com.myclass.service.ShipService;


@Controller
@RequestMapping("ship")
public class ShipController {

	@Autowired
	private ShipService shipService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,RedirectAttributes redirect ) {
		request.getSession().setAttribute("shipList", null);
		if(model.asMap().get("success") != null)
			redirect.addFlashAttribute("success",model.asMap().get("success").toString());
		return "redirect:/ship/page/1";
	}
	
	@RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
	public String showShipPage(HttpServletRequest request, 
			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession()
				.getAttribute("shipList");
		int pagesize = 9;
		List<ShipDto> list =(List<ShipDto>) shipService.getAll();
		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("shipList", pages);
		int current = pages.getPage() + 1;		
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 9, pages.getPageCount());	
		int totalPageCount = pages.getPageCount();
		if ( current>=end) {
			current = pages.getPage() + 1;
			begin = Math.max(current - end+1, current - list.size());
			end = Math.min(current, pages.getPageCount());
			//totalPageCount = pages.getPageCount();
		}
		String baseUrl = "/ship/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("ships", pages);

		return "ship/index";
	} 
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		ShipDto Ship = new ShipDto();
		model.addAttribute("ship", Ship);
		return "ship/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("ship") ShipDto Ship, Model model) {
		ServiceInfo info = shipService.add(Ship);
		if(info.isStatus()==false) {
			model.addAttribute("message", info.getMessage());
			return "ship/add";
		}
		return "redirect:/ship";
	}
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(ModelMap model, @RequestParam("id") String id) {
		ShipDto ship = shipService.getById(id);
		model.addAttribute("ship",ship);
		return "ship/edit";
	}
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("ship") ShipDto shipDto ) {
		shipService.edit(shipDto.getShipId(), shipDto);
		return "redirect:/ship";
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(ModelMap model, @RequestParam("id") String shipId) {
		ShipDto Ship = shipService.getById(shipId);
		shipService.delete(Ship.getShipId());
		return "redirect:/ship";
	}
	
}
