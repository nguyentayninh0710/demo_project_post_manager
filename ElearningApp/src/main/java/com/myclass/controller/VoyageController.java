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
import com.myclass.dto.PortDto;
import com.myclass.dto.RouteDto;
import com.myclass.dto.ShipDto;
import com.myclass.dto.VoyageDto;
import com.myclass.entity.Route;
import com.myclass.service.RouteService;
import com.myclass.service.ShipService;
import com.myclass.service.VoyageService;

@Controller
@RequestMapping("voyage")
public class VoyageController {

	@Autowired
	private VoyageService voyageService;
	
	@Autowired
	private ShipService shipService;
	
	@Autowired
	private RouteService routeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("voyageList", null);
		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());
		return "redirect:/voyage/page/1";
	}

	@RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
	public String showVoyagePage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("voyageList");
		int pagesize = 9;
		List<VoyageDto> list = (List<VoyageDto>) voyageService.getAll();
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
		request.getSession().setAttribute("voyageList", pages);
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
		String baseUrl = "/voyage/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("voyages", pages);

		return "voyage/index";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		List<RouteDto> routes = routeService.getAll();
		model.addAttribute("routes", routes);
		List<ShipDto> ships = shipService.getAll();
		model.addAttribute("routes", ships);
		VoyageDto voyage = new VoyageDto();
		model.addAttribute("voyage", voyage);
		return "voyage/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("voyage") VoyageDto voyage, Model model) {
		ServiceInfo info = voyageService.add(voyage);
		if (info.isStatus() == false) {
			model.addAttribute("message", info.getMessage());
			return "voyage/add";
		}
		return "redirect:/voyage";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(ModelMap model, @RequestParam("id") String voyageId) {
		VoyageDto voyage = voyageService.getByVoyageId(voyageId);
		model.addAttribute("voyage", voyage);
		List<RouteDto> routes = routeService.getAll();
		model.addAttribute("routes", routes);
		List<ShipDto> ships = shipService.getAll();
		model.addAttribute("routes", ships);
		return "voyage/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("voyage") VoyageDto voyage) {
		voyageService.edit(voyage.getVoyageId(), voyage);
		return "redirect:/voyage";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(ModelMap model, @RequestParam("voyageId") String voyageId) {
		VoyageDto voyage = voyageService.getByVoyageId(voyageId);
		voyageService.delete(voyage.getVoyageId());
		return "redirect:/country";
	}

}
