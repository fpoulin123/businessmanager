package ca.xpertproject.apps.businessmanager.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

public class PageUtils {


	/**
	 * Use this function to page items
	 * @param <T>
	 * @param rawList : the list of items to be paged
	 * @param model
	 * @param page : the page number
	 * @param pagedListName : the name of the list returned in the model
	 * @return a paged list of items
	 */
	public static <T> List<T> getPagedItems(List<T> rawList, Model model, String page, String pagedListName ){

		List<T> pagedItems = new ArrayList<T>();

		if(page!=null) {

			int numByPage = 10;
			int pageInt = Integer.parseInt(page);
			int numItems = rawList.size();

			int numPage = (int) numItems / numByPage;

			Double numItemsDbl = Double.valueOf(numItems);
			Double numByPageDbl = Double.valueOf(numByPage);

			Double rest = numItemsDbl % numByPageDbl;
			if(rest>0.0)numPage++;

			int idxStart = (pageInt * numByPage) - numByPage;
			int idxEnd = idxStart + numByPage;

			for(int idx = idxStart;idx<idxEnd;idx++) {
				if(idx>=numItems)break;
				pagedItems.add(rawList.get(idx));
			}

			model.addAttribute("currentPage", pageInt);
			model.addAttribute("pages", numPage);

			if(pageInt>1) {
				model.addAttribute("prevPage", pageInt -1);
			}

			if(pageInt<numPage) {
				model.addAttribute("nextPage", pageInt + 1);
			}

			model.addAttribute(pagedListName, pagedItems);
		}else {
			model.addAttribute(pagedListName, rawList);
		}

		return rawList;

	}
}
