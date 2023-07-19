package org.sp.app0719.admin.controller;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.sp.app0719.model.Member2;
import org.sp.app0719.model.Member2DAO;

//j테이블에게 정보를 전달해주는 테이블 모델 객체
//이 테이블 모델 객체가 존재함으로써 뷰인 디자인 영역과 모델인 데이터 및 로직 영역이 분리되어질 수 있음

public class Member2TableModel extends AbstractTableModel {
	Member2DAO member2DAO;
	List<Member2> list;
	String[] column= {"member2_IDX", "ID", "PASS", "NAME", "EMAIL"};

	public Member2TableModel() {
		member2DAO = new Member2DAO();
		list = member2DAO.selectAll();

	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}
	@Override
	public String getColumnName(int col) {
	
		return column[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Member2 member2=list.get(row); //리스트의 층수에 접근
		
		String value=null;
		
		switch(col) {
		case 0:value=Integer.toString(member2.getMember2_idx());break;
		case 1:value=member2.getId();break;
		case 2:value=member2.getPass();break;
		case 3:value=member2.getName();break;
		case 4:value=member2.getEmail();break;
		
		}
		
		return value;
	}

}
