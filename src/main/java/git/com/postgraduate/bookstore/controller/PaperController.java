package git.com.postgraduate.bookstore.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import git.com.postgraduate.bookstore.entity.Paper;
import git.com.postgraduate.bookstore.service.PaperService;

@Controller
@RequestMapping("/paper")
public class PaperController {

	@Autowired
	PaperService paperService;
	
	@RequestMapping(value = {"/", "/listPapers"})
	public String listPaper(Map<String, Object> map) {
		
		map.put("paper", new Paper());
		
		map.put("paperList", paperService.listPapers());
		
		return "/listPapers";	
	}
	
	@RequestMapping("/get/{paperId}")
	public String getPaper(@PathVariable Long paperId, Map<String, Object> map) {
		
		Paper paper = paperService.getPaper(paperId);
		map.put("paper", paper);
		
		return "/paper/paperForm";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePaper(Paper paper) {
		paperService.savePaper(paper);
		return "redirect:listPapers";
	}
	
	@RequestMapping("/delete/{paperId}")
	public String deletePaper(@PathVariable("paperId") Long id) {
		paperService.deletePaper(id);
		
		return "redirect:/paper/listPapers";
	}
	
	
}
