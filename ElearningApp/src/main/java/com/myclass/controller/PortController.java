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
import com.myclass.dto.UserDto;
import com.myclass.service.CountryService;
import com.myclass.service.PortService;


@Controller
@RequestMapping("port")
public class PortController {
	@Autowired
	private PortService portService;
	
	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,RedirectAttributes redirect ) {
		request.getSession().setAttribute("portlist", null);
		if(model.asMap().get("success") != null)
			redirect.addFlashAttribute("success",model.asMap().get("success").toString());
		return "redirect:/port/page/1";
	}
	@RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
	public String showPortPage(HttpServletRequest request, 

			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession()
				.getAttribute("portlist");
		int pagesize = 10;
		List<PortDto> list = portService.getAll();
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
		request.getSession().setAttribute("portlist", pages);
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
		String baseUrl = "/port/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("ports", pages);
		return "port/index";
	} 
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		List<CountryDto> list = countryService.getAll();
		PortDto port = new PortDto();
		model.addAttribute("port", port);
		model.addAttribute("countrys", list);
		return "port/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("port") PortDto port, Model model) {
		ServiceInfo info = portService.add(port);
		if(info.isStatus()==false) {
			model.addAttribute("message", info.getMessage());
			return "port/add";
		}
		return "redirect:/port";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") String id, Model model) {
		PortDto port = portService.getById(id);
		List<CountryDto> list = countryService.getAll();
		model.addAttribute("port", port);
		model.addAttribute("countrys", list);
		return "port/edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("port") PortDto port) {
		portService.edit(port.getPortId(), port);
		return "redirect:/port";
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(ModelMap model, @RequestParam("id") String id) {
		PortDto port = portService.getById(id);
		try {
			portService.delete(port.getPortId());
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Người dùng không thể xóa");
		return "redirect:/port";
	}
}
