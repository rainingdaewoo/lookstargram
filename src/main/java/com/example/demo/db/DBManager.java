package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.DMVO;
import com.example.demo.vo.InsertLookbookCommandVO;
import com.example.demo.vo.LookInfoVO;
import com.example.demo.vo.LookbookVO;
import com.example.demo.vo.Lookbook_styleVO;
import com.example.demo.vo.UsersVO;

public class DBManager {
	private static SqlSessionFactory factory;
	static {
		System.out.println("static block 동작함.");
		try {
			Reader reader = 
			Resources.getResourceAsReader("com/example/demo/db/dbConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			System.out.println("mybatis 설정파일 생성함: " + factory);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static int insertUsers(UsersVO u) {
		System.out.println("insertUsers 세션 작동함:" + u);
		SqlSession session = factory.openSession(true);
		int re = session.insert("users.insert", u);
		System.out.println("re:"+re);
		session.close();
		
		return re;
	}
	public static int updateInfo(UsersVO u) {
		SqlSession session = factory.openSession(true);
		int re = session.update("look.updateMyInfo",u);
		session.close();
		return re;
	}
	
	public static List<BoardVO> listMyBoard(){
		SqlSession session = factory.openSession();
		List<BoardVO> list = session.selectList("board.listMyBoard");
		session.close();
		return list;
	}
	
	
	
	public static int deleteUser(int users_no, String users_pw) {
		SqlSession session = factory.openSession(true);
		HashMap map = new HashMap();
		map.put("users_no", users_no);
		map.put("users_pw", users_pw);
		int re = session.delete("look.deleteUser",map);
		session.close();
		return re;
	}
	public static UsersVO getUsers(String username) {
		SqlSession session = factory.openSession();
		UsersVO u = session.selectOne("users.getUsers",username);
		session.close();
		return u;
	}
	public static UsersVO getUsers(int users_no) {
		SqlSession session = factory.openSession();
		UsersVO u = session.selectOne("look.getUser",users_no);
		session.close();
		return u;
	}

	
	//가연
	public static UsersVO getUsersByNickname(String users_nickname) {
		SqlSession session = factory.openSession();
		UsersVO u = session.selectOne("users.getUsersByNickname",users_nickname);
		session.close();
		return u;
	}
	
	public static List<LookbookVO> listLookbook(String sortField) {
		SqlSession session = factory.openSession();
		List<com.example.demo.vo.LookbookVO> list = session.selectList("lookbook.findAll",sortField);
		session.close();
		return list;
	}
	
	public static int insertLookbook(InsertLookbookCommandVO insertlook) {
		int re = 0;
		SqlSession session = factory.openSession(true);
		int r = session.selectOne("lookinfo.getNext_lookbook_no");
		System.out.println("--------------------------");
		System.out.println("새로운 lookbook_no:" + r);
		LookbookVO lookbook =  insertlook.getLookbook();
		lookbook.setLookbook_no(r);
		System.out.println("loobook객체: "+ lookbook);
		int re1 = session.insert("lookbook.insert",lookbook); // 1 
		
		List<LookInfoVO> list = insertlook.getList_info();
		int re2 = 0;
		for(LookInfoVO l : list) {
			l.setLookbook_no(r);	
			System.out.println("lookinfo 객체:"+ l);
			re2 += session.insert("lookinfo.insert", l);
		}	// list 사이즈
		
		
		List<Integer> list2 =  insertlook.getStyle_no();
		int re3 = 0;
		for(int i : list2) {
			Lookbook_styleVO style = new Lookbook_styleVO(r, i);
			System.out.println("lookbook_style 객체:"+ style);
			re3 += session.insert("lookbook_style.insert", style);
		}	// list2 사이즈
		
		//int re2 = session.insert("lookinfo.insert",insertlook.getList_info());
		//int re1 = session.insert("lookbook_style.insert",insertlook.getStyle_no());

		if(re1== 1&& re2 == list.size() && re3 == list2.size()) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		System.out.println("--------------------------");
		return re;
	}
	
	
	public static int insertDM(DMVO d) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("dm.insertDM",d);
		session.close();
		return re;
	}
	
	public static List<DMVO> listPeople(String users_nickname){
		SqlSession session = factory.openSession();
		List<DMVO> list = session.selectList("dm.findAll",users_nickname);
		session.close();
		return list;
		
	}
	
	public static List<DMVO> listDM(String users_nickname){
		SqlSession session = factory.openSession();
		List<DMVO> dmList = session.selectList("dm.findAll2",users_nickname);
		session.close();
		return dmList;
		
	}
	
	public static DMVO getDM(int dm_no) {
		SqlSession session = factory.openSession();
		DMVO d = session.selectOne("dm.getDM",dm_no);
		session.close();
		return d;
	}
	//board 관련 DBManager
	
		public static List<BoardVO> listBoard(HashMap map){
			SqlSession session = factory.openSession();
			List<BoardVO> list = session.selectList("board.findAll", map);
			session.close();
			return list;
		}
		
		public static int getNextNo() {
			SqlSession session = factory.openSession();
			int no = session.selectOne("board.getNextNo");
			session.close();
			return no;
		}
		
		public static int insertBoard(BoardVO b) {
			SqlSession session = factory.openSession(true);
			int re = session.insert("board.insertBoard", b);
			session.close();
			return re;
		}
		
		public static BoardVO getBoard(int no) {
			SqlSession session = factory.openSession();
			BoardVO b = session.selectOne("board.getBoard", no);
			session.close();
			return b;
		}
		
		public static void updateViews(int no) {
			SqlSession session = factory.openSession(true);
			session.update("board.updateViews", no);
			session.close();
		}

		public static void updateStep(int b_ref, int b_step) {
			// TODO Auto-generated method stub
			SqlSession session = factory.openSession();
			HashMap map = new HashMap();
			map.put("b_ref", b_ref);
			map.put("b_step", b_step);
			
			session.update("board.updateStep", map);
			session.commit();
			session.close();
		}
		
		public static int updateBoard(BoardVO b) {
			SqlSession session = factory.openSession(true);
			int re = session.update("board.updateBoard", b);
			session.close();
			return re;
		}
		
		public static int deleteBoard(int board_no) {
			SqlSession session = factory.openSession(true);
			/*
			 * HashMap map = new HashMap(); map.put("no", board_no);
			 * System.out.println("map:"+map);
			 */
			int re = session.delete("board.deleteBoard", board_no);
			
			session.commit();
			session.close();
			return re;
		}
		
		public static int getTotalRecord() {
			SqlSession session = factory.openSession();
			int n = session.selectOne("board.totalRecord");
			session.close();
			return n;
		}
		
		
}