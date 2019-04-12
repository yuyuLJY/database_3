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
		//TODO �������ݿ�
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ����MYSQL JDBC��������
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
			// ����URLΪ jdbc:mysql//��������ַ/���ݿ��� �������2�������ֱ��ǵ�½�û���������	
			System.out.println("Success connect Mysql server!");
			Statement stmt = connect.createStatement();
			String flag = "1";
			while(!flag.equals("0")) {
				System.out.println("-----------------�˵�-------------------------------------");
				System.out.println("1 �鿴���ݿ�");
				System.out.println("2 ע�������Ϣ");
				System.out.println("3 �޸�ע�������Ϣ");
				System.out.println("4 ¼��/�޸�/ɾ����������");
				System.out.println("5 ¼��/�޸�/ɾ����������");
				System.out.println("6 ¼��/�޸�/ɾ����־");
				System.out.println("7 ¼��/ɾ����־����");//��д��
				System.out.println("8 ¼��/ɾ����־����");//��д��
				System.out.println("9 �鿴ĳ���û��ĺ���");//������Ϣ
				System.out.println("10 �鿴��ͼ");//�鿴ĳ����ͼ
				System.out.println("11 �鿴ĳ���߼�����");//�鿴ĳ���߼����Ӳ�ѯ
				System.out.println("------------------���������ѡ��---------------------------");
				String MenuChoice = in.nextLine();
				if(MenuChoice.equals("1")) {
					System.out.println("Q������Ҫ�鿴�����ݿ�����(e.g:PersonInfo)");
					String R1DtataBaseName = in.nextLine();
					tt.showDatabase(R1DtataBaseName,stmt);
				}else if(MenuChoice.equals("2")){
					System.out.println("Q�����롾�������Ա𡢳������ڡ��������䡢ͨѶ��ַ���û����롿");
					String R2 = in.nextLine();
					tt.insertInfo(R2,stmt,"PersonInfo");
				}else if(MenuChoice.equals("3")) {
					System.out.println("Q�������롾�û� ���� ������ֵ��");
					String R3 = in.nextLine();
					tt.modifyInfo(R3,stmt,"PersonInfo");
				}else if(MenuChoice.equals("4")) {
					System.out.println("Q�������������1¼�� 2�޸� 3ɾ����");
					String R4_action = in.nextLine();
					if(R4_action.equals("1")){
						System.out.println("Q�������롾�������䡢����������ֹ���¡�ѧУ���ơ�ѧλ��");
						String R4_info = in.nextLine();
						tt.insertInfo(R4_info,stmt,"EducationExperience");
					}
					if(R4_action.equals("2")){
						System.out.println("Q���������޸ġ��û� ���� ������ֵ��");
						String R4_info = in.nextLine();
						tt.modifyInfo(R4_info,stmt,"EducationExperience");
					}
					if(R4_action.equals("3")){
						System.out.println("Q��������ɾ�����ݡ��û���");
						String R4_info = in.nextLine();
						tt.deleteInfo(R4_info,stmt,"EducationExperience");
					}
				}else if(MenuChoice.equals("5")) {
					System.out.println("Q�������������1¼�� 2�޸� 3ɾ����");
					String R5_action = in.nextLine();
					if(R5_action.equals("1")){
						System.out.println("Q�������롾�������䡢����������ֹ���¡�ѧУ���ơ�ѧλ��");
						String R5_info = in.nextLine();
						tt.insertInfo(R5_info,stmt,"WorkExperience");
					}
					if(R5_action.equals("2")){
						System.out.println("Q���������޸ġ��û� ���� ������ֵ��");
						String R5_info = in.nextLine();
						tt.modifyInfo(R5_info,stmt,"WorkExperience");
					}
					if(R5_action.equals("3")){
						System.out.println("Q��������ɾ�����ݡ����� ����ֵ��");
						String R5_info = in.nextLine();
						tt.deleteInfo(R5_info,stmt,"WorkExperience");
					}
				}else if(MenuChoice.equals("6")) {//¼��/�޸�/ɾ����־
					System.out.println("Q�������������1¼�� 2�޸� 3ɾ����");
					String R6_action = in.nextLine();
					if(R6_action.equals("1")){
						System.out.println("Q�������롾��־ID����������,��־���ƣ���־ʱ�䡿");
						String R6_info = in.nextLine();
						tt.insertInfo(R6_info,stmt,"Journal");
					}
					if(R6_action.equals("2")){
						System.out.println("Q���������޸ġ��û� ���� ������ֵ��");
						String R6_info = in.nextLine();
						tt.modifyInfo(R6_info,stmt,"Journal");
					}
					if(R6_action.equals("3")){
						System.out.println("Q��������ɾ����־���ݡ����� ����ֵ��");
						String R6_info = in.nextLine();
						//��ɾ������������ֵ����ȥjournalȥɾ��
						tt.deleteInfo(R6_info,stmt,"ReplyJournal");
						tt.deleteInfo(R6_info,stmt,"ShareJournal");
						tt.deleteInfo(R6_info,stmt,"Journal");
					}
				}
				System.out.println("��Ҫ�˳���0�˳�/1���˳�");
				String QuitChoice = in.nextLine();
				if(QuitChoice.equals("0")) {
					flag = "0";
				}
			}
			if (connect != null) {
				connect.close();//���������ˣ�Ҫ�ر�
			}
		} catch (Exception e) {
			System.out.print("get data error!\n");
			e.printStackTrace();
		}
		
	}
	
	//չʾ���ݿ����Ϣ
	public void showDatabase(String name,Statement stmt) {
		try {
			ResultSet rs = stmt.executeQuery("select * from "+name);//��ѯ���
			//��ӡ����
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount(); //����
			for(int i = 1;i<=columnCount;i++) {
				System.out.printf("%-20s",rsmd.getColumnName(i));
			}
			System.out.print("\n");
			
			//��ӡ����
			while (rs.next()) {//����
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
	
	//���ע����Ϣ
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
			//SQL ���������update,insert�ĸ�����䣬Ӧ����statement��execute()������
			//����õ���statement��executeQuery()�ͻ�������⣺Can not issue data manipulation statements with executeQuery()
			//ResultSet rs = stmt.executeQuery(sql);//��ѯ���
			boolean rs = stmt.execute(sql);
		}catch (Exception e){
			e.printStackTrace();	
		}
	}
	
	//�޸�ע��Ļ�����Ϣ
	public void modifyInfo(String R3,Statement stmt,String tableName) throws SQLException {
		//Lily@163.com	sex	man
		//Lily@163.com	educationyear	2018
		String a[] = R3.split("\t");
		//System.out.println(Arrays.toString(a));
		//update PersonInfo set sex = 'man' where email = 'Lily@163.com';
		String sql = "update "+tableName+" set "+a[1]+"='"+a[2]+"' where email = '"+a[0]+"';";
		try {
			//ʹ��execute
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
			//SQL ���������update,insert�ĸ�����䣬Ӧ����statement��execute()������
			//����õ���statement��executeQuery()�ͻ�������⣺Can not issue data manipulation statements with executeQuery()
			//ResultSet rs = stmt.executeQuery(sql);//��ѯ���
			boolean rs = stmt.execute(sql);
		}catch (Exception e){
			e.printStackTrace();	
		}
	}
	
	
}
