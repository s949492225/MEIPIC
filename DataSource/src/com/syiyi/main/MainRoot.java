package com.syiyi.main;

import java.util.List;

import com.syiyi.bean.PageItem;
import com.syiyi.httpUtil.DownPageListUtil;
import com.syiyi.inf.DownloadHandler;

public class MainRoot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DownPageListUtil.getDirItmList("http://pic.yesky.com/c/6_19591.shtml", new DownloadHandler<List<PageItem>>() {
			@Override
			public void onSuccess(List<PageItem> mDatas) {

			}

			@Override
			public void onFail(Exception e) {

			}
		});


	}



}

