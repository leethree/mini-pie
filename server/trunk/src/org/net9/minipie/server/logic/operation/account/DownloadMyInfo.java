/**
 * DownloadMyInfo.java
 *     in package: * org.net9.minipie.server.logic.operation.account
 * by Mini-Pie Project
 */
package org.net9.minipie.server.logic.operation.account;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.net9.minipie.server.data.entity.AddressData;
import org.net9.minipie.server.data.entity.EmailData;
import org.net9.minipie.server.data.entity.IMData;
import org.net9.minipie.server.data.entity.PhoneNoData;
import org.net9.minipie.server.data.entity.URLData;
import org.net9.minipie.server.data.entity.UserEntity;
import org.net9.minipie.server.exception.DataFormatException;
import org.net9.minipie.server.exception.ServerErrorException;
import org.net9.minipie.server.logic.operation.Command;
import org.net9.minipie.server.logic.storage.UserStorage;

/**
 * @author Seastar
 *
 */
public class DownloadMyInfo extends Command<String>{
	private long userId;
	private String filePath;
	//private String urlPath;
	private String fileName="";
	
	public DownloadMyInfo(long userId,String filePath){
		this.userId=userId;
		this.filePath=filePath;
		generateFileName();
	}
	private void generateFileName(){
		UUID id=UUID.randomUUID();
        long n=id.getLeastSignificantBits();
        long m=id.getMostSignificantBits();
        if(userId%3==0){
        	fileName=String.valueOf((long)(userId*Math.random()))+"__"+String.valueOf(Math.abs(m))+".txt";
        }else if(userId%3==1){
        	fileName=String.valueOf((long)((userId+7)*Math.random()))+"__"+String.valueOf(Math.abs(n))+".txt";
        }else if(userId%6==2){
        	fileName="__"+String.valueOf(Math.abs(n+77))+String.valueOf((long)((userId+777)*Math.random()))+".txt";
        }else{
        	fileName="__"+String.valueOf(Math.abs(m+7))+String.valueOf((long)((userId+7777)*Math.random()))+".txt";
        }
	}
	/* (non-Javadoc)
	 * @see org.net9.minipie.server.logic.operation.Command#execute()
	 */
	@Override
	public String execute() {
		UserStorage executor = getStorageFactory().getUserStorage();	
		UserEntity entity=executor.selectBasicInfo(userId).getEntity();
		entity.setAddrs(executor.selectAddr(userId));
		entity.setEmails(executor.selectEmail(userId));
		entity.setIms(executor.selectIM(userId));
		entity.setTels(executor.selectTel(userId));
		entity.setUrls(executor.selectURL(userId));
		try {
			FileWriter os=new FileWriter(new File(filePath,fileName));
			os.write("Basic User Info\r\n");
			os.write("Name,RegisteredEmail,NickName,DisplayName,Notes,Birthday,BirthdayPermission" +
					",BirthyearPermission,Gender,GenderPermission,AddAsContactPermission\r\n");
			os.write(entity.getName()+","+entity.getRegisteredEmail()+","+entity.getNickName()+","
					+entity.getDisplayname()+","+entity.getNotes()+",");
			if(entity.getBirthday()!=null)
			os.write(entity.getBirthday().toString()
					+","+entity.getBirthdatePermission().toString()+","+entity.getBirthyearPermission()
					+","+entity.getAddAsContactPermission().toString()+"\r\n\r\n");
			else os.write("null"
					+","+entity.getBirthdatePermission().toString()+","+entity.getBirthyearPermission()
					+","+entity.getAddAsContactPermission().toString()+"\r\n\r\n");
			os.write("Address Info\r\n");
			for(AddressData temp:entity.getAddrs()){
				os.write("value,type,primary,permission,zipcode\r\n");
				os.write(temp.getValue()+","+temp.getType()+","+String.valueOf(temp.isPrimary())+","
						+temp.getPermission().toString()+","+temp.getZipcode()+"\r\n");
				os.write("\r\n");
			}
			
			os.write("Email Info\r\n");
			for(EmailData temp:entity.getEmails()){
				os.write("value,type,primary,permission\r\n");
				os.write(temp.getValue()+","+temp.getType()+","+String.valueOf(temp.isPrimary())+","
						+temp.getPermission().toString()+"\r\n");
				os.write("\r\n");
			}
			
			os.write("IM Info\r\n");
			for(IMData temp:entity.getIms()){
				os.write("value,type,primary,permission\r\n");
				os.write(temp.getValue()+","+temp.getType()+","+String.valueOf(temp.isPrimary())+","
						+temp.getPermission().toString()+"\r\n");
				os.write("\r\n");
			}
			
			os.write("Telephone Info\r\n");
			for(PhoneNoData temp:entity.getTels()){
				os.write("value,type,primary,permission\r\n");
				os.write(temp.getValue()+","+temp.getType()+","+String.valueOf(temp.isPrimary())+","
						+temp.getPermission().toString()+"\r\n");
				os.write("\r\n");
			}
			
			os.write("URL Info\r\n");
			for(URLData temp:entity.getUrls()){
				os.write("value,type,primary,permission\r\n");
				os.write(temp.getValue().toString()+","+temp.getType()+","+String.valueOf(temp.isPrimary())+","
						+temp.getPermission().toString()+"\r\n");
				os.write("\r\n");
			}
			
			os.close();			
		} catch (IOException e) {
			throw new ServerErrorException("can't write the file");
		} catch (DataFormatException e) {
			throw new ServerErrorException("data format error");
		}
		return fileName;
	}

}
