package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.ChatVO;
import com.example.demo.vo.LookbookVO;
import com.example.demo.vo.UpdateUsersCommandVO;
import com.example.demo.vo.UsersVO;
import com.example.demo.vo.Users_like_styleVO;
import com.example.demo.vo.Users_outVO;
import com.example.demo.vo.FollowVO;

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
	
	public static List<BoardVO> listMyBoard(){
		SqlSession session = factory.openSession();
		List<BoardVO> list = session.selectList("board.listMyBoard");
		session.close();
		return list;
	}
	
	// 김보민
	
	public static int updateUsersWithStyle(UpdateUsersCommandVO updateUsers) {
		int re = 0;
		SqlSession session = factory.openSession(true);
		
		
		//유저라이크스타일넘버 
		int u = session.selectOne("Users_like_Style.getNext_users_like_no");
		System.out.println("-----------------");

		UsersVO users = updateUsers.getUsers();
		System.out.println("users의 객체: "+ users);
		int re1 = session.update("users.updateMyInfo",users);
		
		int users_no = users.getUsers_no();
		System.out.println("users_no:"+users_no);
		// users_no=1 인 users_like_style 삭제 
		int re3 = session.delete("Users_like_Style.deleteStyle",users_no);
		
		
		List<Integer> list = updateUsers.getStyle_no();
		int re2 = 0;
		for(int i: list) {
			Users_like_styleVO users_style = new Users_like_styleVO(u,users_no,i);
			re2+=session.insert("Users_like_Style.insert", users_style);
		}
		
		if(re1==1 && re2 ==list.size()) {
			session.commit();
			re = 1;
		}else {
			session.rollback();
		}
		session.close();
		return re;
		
		
		
	}
	
	public static int updateInfo(UsersVO u) {
		SqlSession session = factory.openSession(true);
		int re = session.update("users.updateMyInfo",u);
		session.close();
		return re;
	}
	
	public static int updateStyle(int users_no) {
		System.out.println("dbmanager updatestyle 동작함");
		SqlSession session = factory.openSession(true);
		int re = session.update("Users_like_Style.updateUsers_like_Style",users_no);
		session.close();
		return re;
	}
	
	//회원탈퇴시 users테이블 속성 업데이트
	public static int updateUsersDel(int users_no) {
		SqlSession session = factory.openSession(true);
		int re = session.update("users.updateUsersDel",users_no);
		session.close();
		return re;
	}
	
	//회원탈퇴시 탈퇴테이블에 insert
	
	
	public static int insertUsers_out(Users_outVO uo) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("users.insertUsers_out",uo);
		session.close();
		return re;
	}
	
	
	public static UsersVO getUsers(String username) {
		SqlSession session = factory.openSession();
		UsersVO u = session.selectOne("users.getUsers",username);
		session.close();
		return u;
	}
	public static UsersVO getUser(int users_no) {
		SqlSession session = factory.openSession();
		UsersVO u = session.selectOne("users.getUsers",users_no);
		session.close();
		return u;
	}

	public static List<LookbookVO> listLookbook(String sortField) {
		SqlSession session = factory.openSession();
		List<com.example.demo.vo.LookbookVO> list = session.selectList("lookbook.findAll",sortField);
		session.close();
		return list;
	}

	public static int insert(LookbookVO l) {
		SqlSession session = factory.openSession();
		int re = session.insert("lookbook.insert",l);
		session.commit();
		session.close();
		return re;
	}
	
	public static int insertDM(ChatVO c) {
		SqlSession session = factory.openSession(true);
		int re = session.insert("chat.insertDM",c);
		session.close();
		return re;
	}
	
	public static List<ChatVO> listDM(String from_id) {
		SqlSession session = factory.openSession();
		List<ChatVO> c = session.selectList("chat.listDM",from_id);
		session.close();
		return c;
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
		
		public static int updateProfile(UsersVO u) {
			SqlSession session = factory.openSession(true);
			int re = session.update("users.updateProfile",u);
			session.close();
			return re;
		}
		
		public static int updatePwd(UsersVO u) {
			SqlSession session = factory.openSession(true);
			int re = session.update("users.updatePwd",u);
			session.close();
			return re;
		}
		
		public static List<UsersVO> listFollw(String users_id) {
			System.out.println("매니저에서의 users_id:"+users_id);
			SqlSession session = factory.openSession();
			List<UsersVO> flist = session.selectList("users.listFollow",users_id);
			session.close();
			return flist;
		}
		
		
}