package gameSystem.gameObjectSystem.gameObjectInfo;

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
	
	public ObjectInfoReader() {
		XmlPullParserFactory pullParserFactory;
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();
			String file = "res/xml/unitinfo.xml";
			InputStream in_s = this.getClass().getClassLoader().getResourceAsStream(file);
	
		    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	        parser.setInput(in_s, null);
	        
	        Log.d("objReader", "constructor before parseXML" + in_s.available());
	        parseXML(parser);

		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
	{
        int eventType = parser.getEventType();
        ObjectInfo currentObjectInfo = null;

        Log.d("parseXML", "before while" + (eventType == XmlPullParser.END_DOCUMENT));
        
        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	
                    /****************debug massege********************/
                    Log.d("xmlParser", "" + name);
                    /*************************************************/
                    
                	SoldierInfo = new ArrayList<ObjectInfo>();
                	TowerInfo = new ArrayList<ObjectInfo>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();//get the name of tag
                    
                    /****************debug massege********************/
                    Log.d("xmlParser", name);
                    /*************************************************/
                    
                    if(name == "soldier") {//start of <soldier>
                    	currentObjectInfo = new ObjectInfo(TYPE.Soldier);
                    }else if(name == "tower") {//start of <tower>
                    	currentObjectInfo = new ObjectInfo(TYPE.Tower);
                    }else if(currentObjectInfo != null) {//maybe soldier or tower
                    	if(currentObjectInfo.getType() == TYPE.Soldier) {
                    		if(name == "soldierName") {
                    			currentObjectInfo.setName(parser.nextText());
                    		}else if(name == "soldierHealth") {
                    			currentObjectInfo.setHp(Integer.parseInt(parser.nextText()));
                    		}else if(name == "soldierAttack") {
                    			currentObjectInfo.setAtk(Integer.parseInt(parser.nextText()));
                    		}else if(name == "soldierAtkRange") {
                    			currentObjectInfo.setRange(Float.parseFloat(parser.nextText()));
                    		}else if(name == "soldierSpeed") {
                    			currentObjectInfo.setSpeed(Integer.parseInt(parser.nextText()));
                    		}else if(name == "modlePath") {
                    			currentObjectInfo.setPath(parser.nextText());
                    		}                                
                    	}
                    	else if(currentObjectInfo.getType() == TYPE.Tower){
                    		if(name == "towerName") {
                    			currentObjectInfo.setName(parser.nextText());
                    		}else if(name == "towerHealth") {
                    			currentObjectInfo.setHp(Integer.parseInt(parser.nextText()));
                    		}else if(name == "modlePath") {
                    			currentObjectInfo.setPath(parser.nextText());
                    		}
                    	}
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    
                    /****************debug massege********************/
                    Log.d("xmlParser", name);
                    /*************************************************/
                    
                    if (name.equalsIgnoreCase("soldier") && currentObjectInfo != null) {
                    	SoldierInfo.add(currentObjectInfo);
                    }else if(name.equalsIgnoreCase("tower") && currentObjectInfo != null) {
                    	TowerInfo.add(currentObjectInfo);
                    }
                    //currentObjectInfo = null;
                    break;
                    
            }
            Log.d("parseXML", "1" + (eventType == XmlPullParser.END_DOCUMENT));
            eventType = parser.next();
            Log.d("parseXML", "2" + (eventType == XmlPullParser.END_DOCUMENT));
        }
	}
	
	public ObjectInfo getSoldierInfoByName(String name) {
		
		Log.d("objInfo", "in get Info" + SoldierInfo.size());
		
		for(ObjectInfo matchObjectInfo : SoldierInfo) {
			if(matchObjectInfo.getName().equals(name)) {
				return matchObjectInfo;
			}
			Log.d("objInfo", matchObjectInfo.getName() + " " +  matchObjectInfo.getAtk() + " " + matchObjectInfo.getHp() + " " + matchObjectInfo.getPath());
		}
		Log.e("inf", "info not found");
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
}