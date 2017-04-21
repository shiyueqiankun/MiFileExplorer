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

/**
 * ������һ���ļ����������Ϣ �����Լ��ķ�����FileInfo�Ǵ浽���ݿ��ĳ���ļ��FavoriteItem���ղؼе��
 * ��ȷ����path��FileInfo��filePath�Ƿ���ͬ��
 * */
public class FileInfo {
	// �ļ���
	public String fileName;
	// �ļ�·��
	public String filePath;
	// �ļ���С����λ��ɶ�أ���
	public long fileSize;
	// �Ƿ�ΪĿ¼
	public boolean IsDir;
	// ����
	public int Count;
	// �ϴ��޸�����
	public long ModifiedDate;
	// �Ƿ�ѡ��
	public boolean Selected;
	// �Ƿ�ɶ�
	public boolean canRead;
	// �Ƿ��д
	public boolean canWrite;
	// �Ƿ�����
	public boolean isHidden;
	// ��������ݿ��������������ݿ��е�id
	public long dbId; // id in the database, if is from database

}
