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
import com.myclass.dto.CountryDto;
import com.myclass.dto.PortDto;
import com.myclass.dto.RouteDto;
import com.myclass.service.PortService;
import com.myclass.service.RouteService;


@Controller
@RequestMapping("route")
public class RouteController {

	@Autowired
	private RouteService routeService;
	
	@Autowired
	private PortService portService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,RedirectAttributes redirect ) {
		request.getSession().setAttribute("routeList", null);
		if(model.asMap().get("success") != null)
			redirect.addFlashAttribute("success",model.asMap().get("success").toString());
		return "redirect:/route/page/1";
	}
	
	@RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
	public String showroutePage(HttpServletRequest request, 
			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession()
				.getAttribute("routeList");
		int pagesize = 9;
		List<RouteDto> list =(List<RouteDto>) routeService.getAll();
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
		request.getSession().setAttribute("routeList", pages);
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
		String baseUrl = "/route/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("routes", pages);

		return "route/index";
	} 
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		List<PortDto> list = portService.getAll();
		model.addAttribute("ports", list);
		RouteDto route = new RouteDto();
		model.addAttribute("route", route);
		return "route/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("route") RouteDto route, Model model) {
		ServiceInfo info = routeService.add(route);
		if(info.isStatus()==false) {
			model.addAttribute("message", info.getMessage());
			return "route/add";
		}
		return "redirect:/route";
	}
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(ModelMap model, @RequestParam("id") String id) {
		RouteDto route = routeService.getById(id);
		model.addAttribute("route",route);
		List<PortDto> list = portService.getAll();
		model.addAttribute("ports", list);
		return "route/edit";
	}
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("route") RouteDto route ) {
		routeService.edit(route.getRouteId(), route);
		return "redirect:/route";
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(ModelMap model, @RequestParam("id") String id) {
		RouteDto route = routeService.getById(id);
		routeService.delete(route.getRouteId());
		return "redirect:/route";
	}
	
}
