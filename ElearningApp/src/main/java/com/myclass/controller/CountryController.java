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
import com.myclass.entity.Country;
import com.myclass.service.CountryService;

@Controller
@RequestMapping("country")
public class CountryController {
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,RedirectAttributes redirect ) {
		request.getSession().setAttribute("countrylist", null);
		if(model.asMap().get("success") != null)
			redirect.addFlashAttribute("success",model.asMap().get("success").toString());
		return "redirect:/country/page/1";
	}
	
	@RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
	public String showCountryPage(HttpServletRequest request, 
			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession()
				.getAttribute("countrylist");
		int pagesize = 9;
		List<CountryDto> list = countryService.getAll();
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
		request.getSession().setAttribute("countrylist", pages);
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
		String baseUrl = "/country/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("countrys", pages);

		return "country/index";
	} 
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		CountryDto country = new CountryDto();
		model.addAttribute("country", country);
		return "country/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("country") CountryDto country, Model model) {
		ServiceInfo info = countryService.add(country);
		if(info.isStatus()==false) {
			model.addAttribute("message", info.getMessage());
			return "country/add";
		}
		return "redirect:/country";
	}
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(ModelMap model, @RequestParam("id") String id) {
		CountryDto country = countryService.getById(id);
		model.addAttribute("country",country);
		return "country/edit";
	}
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("country") CountryDto country ) {
		countryService.edit(country.getId(), country);
		return "redirect:/country";
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(ModelMap model, @RequestParam("id") String id) {
		CountryDto country = countryService.getById(id);
		try {
			countryService.delete(country.getId());
			
		}catch (Exception e) {
			// TODO: handle exception
		}		
		return "redirect:/country";
	}
}
