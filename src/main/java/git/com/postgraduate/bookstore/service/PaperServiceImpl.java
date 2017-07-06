package git.com.postgraduate.bookstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import git.com.postgraduate.bookstore.dao.PaperDao;
import git.com.postgraduate.bookstore.entity.Paper;

@Service
public class PaperServiceImpl implements PaperService {

	@Autowired
	PaperDao paperDao;
	
	@Transactional
	public void savePaper(Paper paper) {
		
		paperDao.savePaper(paper);
	}

	@Transactional(readOnly = true)
	public List<Paper> listPapers() {
		return paperDao.listPaper();
	}

	@Transactional(readOnly = true)
	public Paper getPaper(Long id) {
		
		return paperDao.getPaper(id);
	}
	
	@Transactional(readOnly = true)
	public Paper getPaper(String province, String university, String school, String major) {
		return paperDao.getPaper(province, university, school, major);
	}

	@Transactional
	public void deletePaper(Long id) {
		
		paperDao.deletePaper(id);
		
	}
	
}
