package git.com.postgraduate.bookstore.dao;

import java.util.List;

import git.com.postgraduate.bookstore.entity.Paper;

public interface PaperDao {

	/*create and update*/
	public void savePaper(Paper paper);
	
	/*read*/
	public List<Paper> listPaper();
	public Paper getPaper(Long id);
	public Paper getPaper(String province, String university, String school, String major);
	
	/*delete*/
	public void deletePaper(Long id);
	
	
}
