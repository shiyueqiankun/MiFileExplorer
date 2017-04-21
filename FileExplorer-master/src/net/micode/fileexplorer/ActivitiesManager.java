/*
 * Copyright (c) 2010-2011, The MiCode Open Source Community (www.micode.net)
 *
 * This file is part of FileExplorer.
 *
 * FileExplorer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FileExplorer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SwiFTP.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.micode.fileexplorer;

import java.util.HashMap;

import android.app.Activity;

/** ��ʵ��������� */
public class ActivitiesManager {
	public static final String ACTIVITY_FILE_VIEW = "FileView";
	// @Deprecatedû�б��κ�������ʹ��
	public static final String ACTIVITY_FILE_CATEGORY = "FileCategory";
	// @Deprecatedû�б��κ�������ʹ��
	public static final String ACTIVITY_TAB = "FileExplorerTab";

	private static ActivitiesManager instance;

	private HashMap<String, Activity> activities = new HashMap<String, Activity>();

	private ActivitiesManager() {
	}

	// return true indicates successful, false indicates the name exists
	// ����û�з���ֵ���ѵ�˵����W��Դ�桱�Ǳ��˸��ô��
	// ��FileViewActivityʹ���ˣ�����ǲ鿴�ļ���ʱ�򣬾ͻ��½���һ���
	public void registerActivity(String name, Activity a) {
		activities.put(name, a);
	}
	//@Deprecatedû�б��κ�������ʹ��
	public Activity getActivity(String name) {
		return activities.get(name);
	}

	public static ActivitiesManager getInstance() {
		if (instance == null)
			instance = new ActivitiesManager();
		return instance;
	}
}
