package br.com.plugins.copydbplugin;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint("SdCardPath")
public class CopyDataBasePlugin extends CordovaPlugin{
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext){
		Log.d("CopyDataBasePlugin", "Entrou no execute");  
		if(action.equalsIgnoreCase("begin")){
			Log.d("CopyDataBasePlugin","INICIOU");

			try {
				
				String databasename = args.getString(0);
				String packagename = args.getString(1);
		        try{
		        	Log.d("CopyDataBasePlugin","package = "+args.getString(1)+"\nDatabasename="+databasename);
		        	this.copy(databasename,"/data/data/"+packagename+"/databases/");
		        	callbackContext.success(databasename);
		        	return true;
		        }
		        catch (IOException e){
		        	callbackContext.error("Erro ao tentar copiar o banco de dados");
		        	Log.d("CopyDataBasePlugin",e.getMessage());
		        	return false;
		        }			

			} catch (JSONException e) {
				callbackContext.error("Não foi infomado o banco de dados: "+e.getMessage());
				return false;
			}

			
				
		}
		else if(action.equalsIgnoreCase("delete")){
			Log.d("CopyDataBasePlugin","apagando arquivo");

			try {
				
				String databasename = args.getString(0);
				String packagename = args.getString(1);
		        try{
		        	Log.d("CopyDataBasePlugin","package = "+args.getString(1)+"\nDatabasename="+databasename);
		        	this.delete(databasename,"/data/data/"+packagename+"/databases/");
		        	callbackContext.success(databasename);
		        	return true;
		        }
		        catch (IOException e){
		        	callbackContext.error("Erro ao tentar copiar o banco de dados");
		        	Log.d("CopyDataBasePlugin",e.getMessage());
		        	return false;
		        }			

			} catch (JSONException e) {
				callbackContext.error("Não foi infomado o banco de dados: "+e.getMessage());
				return false;
			}			
		}
		else{
			callbackContext.error(action+" Não existe");
			return false;
		}

	}

    //Copy Paste this function in the class where you used above part
	void copy(String file, String folder) throws IOException{
	
		File CheckDirectory;
		CheckDirectory = new File(folder);
		if (!CheckDirectory.exists()){ 
			CheckDirectory.mkdir();
		}
		
		
	    File arquivo =  new File(folder+file);  
	    if(!arquivo.exists()) {  
	    	 
			 InputStream in = this.cordova.getActivity().getApplicationContext().getAssets().open("www/database/"+file);

			 OutputStream out = new FileOutputStream(folder+file);	
			 // Transfer bytes from in to out
			 byte[] buf = new byte[1024];
			 int len; while ((len = in.read(buf)) > 0) out.write(buf, 0, len);
			 in.close(); out.close();
	    }
		
	}
	
	void delete(String file, String folder) throws IOException{		
		File CheckDirectory;
		CheckDirectory = new File(folder);
		if (!CheckDirectory.exists()){ 
			CheckDirectory.mkdir();
		}
		
		
	    File arquivo =  new File(folder+file);  
	    if(arquivo.exists()) {  
	      arquivo.delete();
	    }
		
	}	
}
