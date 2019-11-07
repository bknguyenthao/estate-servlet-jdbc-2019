package com.laptrinhjavaweb.paging;

public class PageRequest implements Pageable {

	private Integer page;
	private Integer size;
	private Sorter sorter;

	public PageRequest(Integer page, Integer size, Sorter sorter) {
		this.page = page;
		this.size = size;
		this.sorter = sorter;
	}

	@Override
	public Integer getPage() {
		return page;
	}

	@Override
	public Integer getOffset() {
		if (page != null && size != null) {
			return (page - 1) * size;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		return size;
	}

	@Override
	public Sorter getSorter() {
		return sorter;
	}
}
