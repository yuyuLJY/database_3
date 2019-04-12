package database_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class social {
	public static void main(String[] args) {
		//TODO 加载数据库
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			// Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}
		Scanner in = new Scanner(System.in);
		social tt = new social(); 
		try {
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_experiment3?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false", "root", "198876");
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码	
			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			String flag = "1";
			while(!flag.equals("0")) {
				System.out.println("-----------------菜单-------------------------------------");
				System.out.println("1 查看数据库");
				System.out.println("2 注册基本信息");
				System.out.println("3 修改注册基本信息");
				System.out.println("4 录入/修改/删除教育经历");
				System.out.println("5 录入/修改/删除工作经历");
				System.out.println("6 录入/修改/删除日志");
				System.out.println("7 录入/删除日志评论");//不写了
				System.out.println("8 录入/删除日志分享");//不写了
				System.out.println("9 查看某个用户的好友");//分组信息
				System.out.println("10 查看视图");//查看某个视图
				System.out.println("11 查看某个高级连接");//查看某个高级连接查询
				System.out.println("------------------请输入你的选择---------------------------");
				String MenuChoice = in.nextLine();
				if(MenuChoice.equals("1")) {
					System.out.println("Q：输入要查看的数据库名称(e.g:PersonInfo)");
					String R1DtataBaseName = in.nextLine();
					tt.showDatabase(R1DtataBaseName,stmt);
				}else if(MenuChoice.equals("2")){
					System.out.println("Q：输入【姓名、性别、出生日期、电子邮箱、通讯地址、用户密码】");
					String R2 = in.nextLine();
					tt.insertInfo(R2,stmt,"PersonInfo");
				}else if(MenuChoice.equals("3")) {
					System.out.println("Q：请输入【用户 属性 新属新值】");
					String R3 = in.nextLine();
					tt.modifyInfo(R3,stmt,"PersonInfo");
				}else if(MenuChoice.equals("4")) {
					System.out.println("Q：请输入操作【1录入 2修改 3删除】");
					String R4_action = in.nextLine();
					if(R4_action.equals("1")){
						System.out.println("Q：请输入【电子邮箱、教育级别、起止年月、学校名称、学位】");
						String R4_info = in.nextLine();
						tt.insertInfo(R4_info,stmt,"EducationExperience");
					}
					if(R4_action.equals("2")){
						System.out.println("Q：请输入修改【用户 属性 新属新值】");
						String R4_info = in.nextLine();
						tt.modifyInfo(R4_info,stmt,"EducationExperience");
					}
					if(R4_action.equals("3")){
						System.out.println("Q：请输入删除内容【用户】");
						String R4_info = in.nextLine();
						tt.deleteInfo(R4_info,stmt,"EducationExperience");
					}
				}else if(MenuChoice.equals("5")) {
					System.out.println("Q：请输入操作【1录入 2修改 3删除】");
					String R5_action = in.nextLine();
					if(R5_action.equals("1")){
						System.out.println("Q：请输入【电子邮箱、教育级别、起止年月、学校名称、学位】");
						String R5_info = in.nextLine();
						tt.insertInfo(R5_info,stmt,"WorkExperience");
					}
					if(R5_action.equals("2")){
						System.out.println("Q：请输入修改【用户 属性 新属新值】");
						String R5_info = in.nextLine();
						tt.modifyInfo(R5_info,stmt,"WorkExperience");
					}
					if(R5_action.equals("3")){
						System.out.println("Q：请输入删除内容【属性 属性值】");
						String R5_info = in.nextLine();
						tt.deleteInfo(R5_info,stmt,"WorkExperience");
					}
				}else if(MenuChoice.equals("6")) {//录入/修改/删除日志
					System.out.println("Q：请输入操作【1录入 2修改 3删除】");
					String R6_action = in.nextLine();
					if(R6_action.equals("1")){
						System.out.println("Q：请输入【日志ID，电子邮箱,日志名称，日志时间】");
						String R6_info = in.nextLine();
						tt.insertInfo(R6_info,stmt,"Journal");
					}
					if(R6_action.equals("2")){
						System.out.println("Q：请输入修改【用户 属性 新属新值】");
						String R6_info = in.nextLine();
						tt.modifyInfo(R6_info,stmt,"Journal");
					}
					if(R6_action.equals("3")){
						System.out.println("Q：请输入删除日志内容【属性 属性值】");
						String R6_info = in.nextLine();
						//先删除关联副键的值，再去journal去删除
						tt.deleteInfo(R6_info,stmt,"ReplyJournal");
						tt.deleteInfo(R6_info,stmt,"ShareJournal");
						tt.deleteInfo(R6_info,stmt,"Journal");
					}
				}
				System.out.println("需要退出吗？0退出/1不退出");
				String QuitChoice = in.nextLine();
				if(QuitChoice.equals("0")) {
					flag = "0";
				}
			}
			if (connect != null) {
				connect.close();//结束流程了，要关闭
			}
		} catch (Exception e) {
			System.out.print("get data error!\n");
			e.printStackTrace();
		}
		
	}
	
	//展示数据库的信息
	public void showDatabase(String name,Statement stmt) {
		try {
			ResultSet rs = stmt.executeQuery("select * from "+name);//查询语句
			//打印列名
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount(); //列数
			for(int i = 1;i<=columnCount;i++) {
				System.out.printf("%-20s",rsmd.getColumnName(i));
			}
			System.out.print("\n");
			
			//打印数据
			while (rs.next()) {//按行
				for(int i=1;i<=columnCount;i++){
					System.out.printf("%-20s",rs.getString(i));					
				}				
				System.out.println();
			}
		}catch (Exception e) {
			System.out.print("get data error!\n");
			e.printStackTrace();
		}
	}
	
	//添加注册信息
	public void insertInfo(String R2,Statement stmt,String tableName) throws SQLException {
		//Lili	woman	20090419	Lily@163.com	Heilongjia	123456
		String a[] = R2.split("\t");
		String value ="";
		for(String s : a) {
			value = value+"'"+s+"'"+',';
		}
		value = value.substring(0,value.length()-1);
		if(tableName.equals("Journal") || tableName.equals("ReplyJournal")) {
			String time = "";
			value = ","+value+"'"+time+"'";
		}
		String sql = "insert into "+tableName+" VALUES"+"("+value+");";
		//System.out.println(sql);
		try {
			//SQL 语句是诸如update,insert的更新语句，应该用statement的execute()方法，
			//如果用的是statement的executeQuery()就会出现问题：Can not issue data manipulation statements with executeQuery()
			//ResultSet rs = stmt.executeQuery(sql);//查询语句
			boolean rs = stmt.execute(sql);
		}catch (Exception e){
			e.printStackTrace();	
		}
	}
	
	//修改注册的基本信息
	public void modifyInfo(String R3,Statement stmt,String tableName) throws SQLException {
		//Lily@163.com	sex	man
		//Lily@163.com	educationyear	2018
		String a[] = R3.split("\t");
		//System.out.println(Arrays.toString(a));
		//update PersonInfo set sex = 'man' where email = 'Lily@163.com';
		String sql = "update "+tableName+" set "+a[1]+"='"+a[2]+"' where email = '"+a[0]+"';";
		try {
			//使用execute
			boolean rs = stmt.execute(sql);
		}catch (Exception e){
			e.printStackTrace();	
		}
	}
	
	public void deleteInfo(String R2,Statement stmt,String tableName) throws SQLException {
		//Lili	woman	20090419	Lily@163.com	Heilongjia	123456
		//email Lily@163.com 
		//journalname	cooking journal
		//delete from EducationExperience where email ='';
		String a[] = R2.split("\t");
		String sql = "delete from "+tableName+" where "+a[0]+" ='"+a[1]+"';";
		//System.out.println(sql);
		try {
			//SQL 语句是诸如update,insert的更新语句，应该用statement的execute()方法，
			//如果用的是statement的executeQuery()就会出现问题：Can not issue data manipulation statements with executeQuery()
			//ResultSet rs = stmt.executeQuery(sql);//查询语句
			boolean rs = stmt.execute(sql);
		}catch (Exception e){
			e.printStackTrace();	
		}
	}
	
	
}
