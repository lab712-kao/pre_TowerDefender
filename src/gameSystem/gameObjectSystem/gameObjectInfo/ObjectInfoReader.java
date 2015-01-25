package gameSystem.gameObjectSystem.gameObjectInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfo.TYPE;

public class ObjectInfoReader {
	
	ArrayList<ObjectInfo> SoldierInfo = null;
	ArrayList<ObjectInfo> TowerInfo = null;
	private InputStream in_s = null;
	
	public ObjectInfoReader(InputStream ins) {
		XmlPullParserFactory pullParserFactory;
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();
			String file = "res/xml/unitinfo.xml";
//			InputStream in_s = this.getClass().getClassLoader().getResourceAsStream(file);
			in_s = ins;

		    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	        parser.setInput(in_s, null);
	        parseXML(parser);

		} catch (XmlPullParserException e) {
        	//Log.d("moveStart",e+ "<<<<<<<exception++++++++++++++++++++++++++++");			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//Log.d("moveStart",e+ "<<<<<<<exception++++++++++++++++++++++++++++");			
			e.printStackTrace();
		}
	}
	
	private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
	{
        int eventType = parser.getEventType();
        ObjectInfo currentObjectInfo = null;
        while (eventType != XmlPullParser.END_DOCUMENT){
        	 
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	//Log.d("moveStart",  eventType +"<<<<<<<eventType++++++++++++++++++++++++++++");
                	//Log.d("moveStart",  XmlPullParser.START_DOCUMENT+"<<START_DOCUMENT++++++++++++++++++++++++++++");
                	SoldierInfo = new ArrayList<ObjectInfo>();
                	//Log.d("moveStart",  "<<end SoldierInfo++++++++++++++++++++++++++++");
                	TowerInfo = new ArrayList<ObjectInfo>();
                	//Log.d("moveStart",  "<<end TowerInfo++++++++++++++++++++++++++++");
                	//Log.d("moveStart",  "<<end START_DOCUMENT++++++++++++++++++++++++++++");
                	
                    break;
                case XmlPullParser.START_TAG:
                	//Log.d("moveStart",  eventType +"<<<<<<<eventType++++++++++++++++++++++++++++");
                	//Log.d("moveStart",  XmlPullParser.START_TAG+"<<START_TAG++++++++++++++++++++++++++++");
                    name = parser.getName();//get the name of tag
                    
                    //Log.d("moveStart", name+"<<<name++++++++++++++++++++++++++++");
                    if(name.equals("soldier")) {//start of <soldier>
                    	currentObjectInfo = new ObjectInfo(TYPE.Soldier);
                    }else if(name.equals("tower") ) {//start of <tower>
                    	currentObjectInfo = new ObjectInfo(TYPE.Tower);
                    }else if(currentObjectInfo != null) {//maybe soldier or tower
                    	if(currentObjectInfo.getType() == TYPE.Soldier) {
                    		if(name.equals("soldierName")) {
                    			currentObjectInfo.setName(parser.nextText());
                    		}else if(name.equals("soldierHealth")) {
                    			currentObjectInfo.setHp(Integer.parseInt(parser.nextText()));
                    		}else if(name.equals("soldierAttack")) {
                    			currentObjectInfo.setAtk(Integer.parseInt(parser.nextText()));
                    			Log.d("moveStart", currentObjectInfo.getAtk()+"<<atk++++++++++++++++++++++++++++");
                    		}else if(name.equals("soldierAtkRange")) {
                    			currentObjectInfo.setRange(Float.parseFloat(parser.nextText()));
                    		}else if(name.equals("soldierSpeed")) {
                    			currentObjectInfo.setSpeed(Integer.parseInt(parser.nextText()));
                    		}else if(name.equals("modlePath")) {
                    			currentObjectInfo.setPath(parser.nextText());
                    		}                                
                    	}
                    	else if(currentObjectInfo.getType() == TYPE.Tower){
                    		if(name.equals("towerName")) {
                    			currentObjectInfo.setName(parser.nextText());
                    		}else if(name.equals("towerHealth")) {
                    			currentObjectInfo.setHp(Integer.parseInt(parser.nextText()));
                    		}else if(name.equals("modlePath")) {
                    			currentObjectInfo.setPath(parser.nextText());
                    		}
                    	}
                    }
                    break;
                case XmlPullParser.END_TAG:
//                	Log.d("moveStart",  eventType +"<<<<<<<eventType++++++++++++++++++++++++++++");
//                	Log.d("moveStart",  XmlPullParser.END_TAG+"<<END_TAG++++++++++++++++++++++++++++");
                    name = parser.getName();
                    
                    /****************debug massege********************/
                    //Log.d("xmlParser", name);
                    /*************************************************/
                    
                    if (name.equalsIgnoreCase("soldier") && currentObjectInfo != null) {
                    	SoldierInfo.add(currentObjectInfo);
                    }else if(name.equalsIgnoreCase("tower") && currentObjectInfo != null) {
                    	TowerInfo.add(currentObjectInfo);
                    }
                    //currentObjectInfo = null;
                    break;
                    
            }
            //Log.d("parseXML", "1" + (eventType == XmlPullParser.END_DOCUMENT));
            eventType = parser.next();
            //Log.d("parseXML", "2" + (eventType == XmlPullParser.END_DOCUMENT));
        }
	}
	
	public ObjectInfo getSoldierInfoByName(String name) {
		
		Log.d("objInfo", "in get Info" + SoldierInfo.size());
		
		for(ObjectInfo matchObjectInfo : SoldierInfo) {
			if(matchObjectInfo.getName().equals(name)) {
				return matchObjectInfo;
			}
			//Log.d("objInfo", matchObjectInfo.getName() + " " +  matchObjectInfo.getAtk() + " " + matchObjectInfo.getHp() + " " + matchObjectInfo.getPath());
		}
		//Log.e("inf", "info not found");
		return null;
	}
	
	public ObjectInfo getTowerInfoByName(String name) {
		
		for(ObjectInfo matchObjectInfo : SoldierInfo) {
			if(matchObjectInfo.getName().equals(name)) {
				return matchObjectInfo;
			}
		}
		return null;
	}
	
	public void setInputStream(InputStream ins){
		in_s = ins;
		
	}
}
