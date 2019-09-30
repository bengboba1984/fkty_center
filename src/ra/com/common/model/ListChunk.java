package ra.com.common.model;

import java.util.Collection;
import java.util.List;

public class ListChunk implements java.io.Serializable {
	private int			pageSize = -1;
	private int			pageNo = -1;
	private int			totalPages = -1;
    private int         totalElements;
    private List     	elementsInThisList;
    private int         firstElementOfThisList;
    private int         countOfElementsInthisList;
    
    public ListChunk(int count, List list, int first, int curCount) {
        this.totalElements = count;
        this.elementsInThisList = list;
        this.firstElementOfThisList = first;
        this.countOfElementsInthisList = curCount;
    }

    public ListChunk(int pageNo, int pageSize, int count, List list, int first, int curCount) {
    	this.pageNo = pageNo;
    	this.pageSize = pageSize;
        this.totalElements = count;
        this.elementsInThisList = list;
        this.firstElementOfThisList = first;
        this.countOfElementsInthisList = curCount;
        this.totalPages = (int)((totalElements - 1) / pageSize) + 1;
    }

    public ListChunk() {}
    
    public int getPageNo() { return pageNo; }
    public void setPageNo(int pageNo) { this.pageNo = pageNo; }
    
    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }
    
    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
    
    public int getTotalCount() { return totalElements;}
    public void setTotalCount(int totalCount) { this.totalElements = totalElements; }
    
    //public void setCollection(Collection elementsInThisList) { this.elementsInThisList = elementsInThisList;}
    public Collection getCollection() {return (Collection)elementsInThisList; }
    public List getList() {return elementsInThisList; }
    public void setList(List elementsInThisList) { this.elementsInThisList = elementsInThisList;}
    
    public int getCurrentCount() { return countOfElementsInthisList; }
    public void setCurrentCount(int currentCount) { this.countOfElementsInthisList = countOfElementsInthisList; }
    
    public int getFirstElementIndex() { return firstElementOfThisList; }
    public void setFirstElementIndex(int firstElementOfThisList) { this.firstElementOfThisList = firstElementOfThisList;}
}