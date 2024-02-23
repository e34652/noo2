package dto;

import java.util.List;

public class ArticlePage {

	private int total;
	private int currentPage;
	private List<Board> contentB;
	private List<Member> contentM;
	private int totalPages;
	private int startPage;
	private int endPage;

	//size: 한 페이지당 보여줄 게시글의 개수
	public ArticlePage(int total, int currentPage, int size, List<Member> contentM) {
	    this(total, currentPage, size, null, contentM);
	}

	public ArticlePage(int total, int currentPage, int size, List<Board> contentB, List<Member> contentM) {
		this.total = total;
		this.currentPage = currentPage;
		this.contentM = contentM;
	    this.contentB = contentB;
	    
		if (total == 0) { 
			this.totalPages = 0;
			this.startPage = 0;
			this.endPage = 0;
		} else {
			this.totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			
			//다른 페이지를 눌렀을 때  
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1; // currentPage가 0인 경우를 대비한 수식 
			if (modVal == 0)
				startPage -= 5;
			// startPage, endPage: 화면 하단에 보여줄 페이지 이동 링크의 시작 번호와 끝 번호
			endPage = startPage + 4; 
			if (endPage > totalPages)
				endPage = totalPages;
		}
	}
	public boolean hasNoArticles() {
		return total == 0;
	}
	
	public boolean hasArticles() {
		return total > 0;
	}
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	
	public List<Board> getContentB() {
		return contentB;
	}
	
	public List<Member> getContentM() {
		return contentM;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	

	
	
}
